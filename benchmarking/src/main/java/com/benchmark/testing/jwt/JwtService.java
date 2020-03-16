package com.benchmark.testing.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.benchmark.testing.model.TokenData;
import com.benchmark.testing.model.User;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Slf4j
@NoArgsConstructor
public class JwtService {

    private static final Duration expires = Duration.ofDays(2);
    private static final String secretKeySite = "fFHIEW!#$%__@@#$";
    private static final Algorithm algorithmSite = Algorithm.HMAC256(secretKeySite);
    private static final JWTVerifier jwtVerifierSite = JWT.require(algorithmSite)
            .acceptExpiresAt(expires.toMillis())
            .build();


    private static final String TEST_USER_PHONE = "79265605575";
    public static final User TEST_USER = new User(TEST_USER_PHONE, 2620262L);


    public User resolveTokenToUser(String token) {
        DecodedJWT jwt = null;
        try {
            jwt = jwtVerifierSite.verify(token);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

        var userPhone = jwt.getClaim("m").asString();
        var regFlag = jwt.getClaim("r").isNull() ? Boolean.FALSE : jwt.getClaim("r").asBoolean();
        var regStatus = jwt.getClaim("st").asString();
        var walletId = jwt.getClaim("w").asLong();
        var isOfertaAccepted = jwt.getClaim("o").isNull() ? true : jwt.getClaim("o").asBoolean();
        var registered = regFlag.booleanValue() || "REGISTERED".equalsIgnoreCase(regStatus);
        if (!registered || !isOfertaAccepted) {
            return null;
        }
        return new User(userPhone, walletId == null ? 0 : walletId.longValue());
    }

    public  String getTestToken() {
        return issue(TEST_USER, true).getToken();
    }


    private static TokenData issue(User user, boolean registered) {
        Date expiresAt = Date.from(Instant.now().plusMillis(expires.toMillis()));
        return new TokenData(JWT.create()
                .withExpiresAt(expiresAt)
                .withClaim("m", user.getUserPhone())
                .withClaim("r", registered)
                .withClaim("w", user.getWalletId())
                .withClaim("o", registered)
                .sign(algorithmSite), expiresAt);
    }


}
