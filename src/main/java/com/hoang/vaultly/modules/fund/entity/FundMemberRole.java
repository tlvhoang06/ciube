package com.hoang.vaultly.modules.fund.entity;

import com.hoang.vaultly.modules.fund.enums.FundRole;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FundMemberRole {

    @ManyToMany
    FundMember member;

    FundRole role;
}
