package com.benchmark.testing.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userPhone;

    private long walletId;

    public static User ofPhone(String userPhone) {
        return User.builder()
                .userPhone(userPhone).walletId(0)
                .build();
    }

}
