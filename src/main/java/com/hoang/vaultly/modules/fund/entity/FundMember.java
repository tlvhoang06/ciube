package com.hoang.vaultly.modules.fund.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fund_members")
public class FundMember {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    UUID id;


}
