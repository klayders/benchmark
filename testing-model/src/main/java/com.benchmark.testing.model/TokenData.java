package com.benchmark.testing.model;

import lombok.Data;

import java.util.Date;

@Data
public class TokenData {
    private final String token;
    private final Date expiresAt;
}
