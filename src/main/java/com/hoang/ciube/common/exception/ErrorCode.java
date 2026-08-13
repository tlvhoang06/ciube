package com.hoang.ciube.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum ErrorCode {
    INVALID_CREDENTIALS(1001, "Invalid username or password", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND(1002, "User not found", HttpStatus.NOT_FOUND),
    PHONE_NUMBER_EXISTED(1003, "Phone number existed", HttpStatus.BAD_REQUEST),
    // JWT Exception
    INVALID_TOKEN(1101, "Invalid Token", HttpStatus.BAD_REQUEST),
    EXPIRED_TOKEN(1102, "Expired Token", HttpStatus.BAD_REQUEST);


    int code;
    String message;
    HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
