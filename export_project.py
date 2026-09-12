import os

# Tên file xuất ra
OUTPUT_FILE = "project_context.txt"

# Các thư mục cần bỏ qua
EXCLUDED_DIRS = {
    ".git", ".idea", ".vscode", "target", "build",
    ".gradle", "bin", "out", "node_modules"
}

# Các đuôi file cần quét (có thể thêm bớt tùy nhu cầu)
ALLOWED_EXTENSIONS = {
    ".java", ".xml", ".properties", ".yml", ".yaml",
    ".sql", ".gradle", ".md", ".json"
}

# Các file cụ thể cần bỏ qua (ví dụ file output, file wrapper binary)
EXCLUDED_FILES = {
    OUTPUT_FILE, "mvnw", "mvnw.cmd", "gradlew", "gradlew.bat"
}

def is_text_file(filename):
    return any(filename.endswith(ext) for ext in ALLOWED_EXTENSIONS)

def main():
    root_dir = os.path.dirname(os.path.abspath(__file__))

    with open(OUTPUT_FILE, "w", encoding="utf-8") as outfile:
        for root, dirs, files in os.walk(root_dir):
            # Lọc bỏ thư mục không cần thiết tại chỗ để không quét sâu vào trong
            dirs[:] = [d for d in dirs if d not in EXCLUDED_DIRS]

            for file in sorted(files):
                if file in EXCLUDED_FILES or not is_text_file(file):
                    continue

                file_path = os.path.join(root, file)
                rel_path = os.path.relpath(file_path, root_dir)

                # Ghi tiêu đề phân cách rõ ràng từng file
                outfile.write("=" * 80 + "\n")
                outfile.write(f"FILE: {rel_path}\n")
                outfile.write("=" * 80 + "\n\n")

                try:
                    with open(file_path, "r", encoding="utf-8", errors="replace") as infile:
                        outfile.write(infile.read())
                    outfile.write("\n\n")
                except Exception as e:
                    outfile.write(f"[Lỗi đọc file: {e}]\n\n")

    print(f"Đã xuất toàn bộ mã nguồn vào: {OUTPUT_FILE}")

if __name__ == "__main__":
    main()