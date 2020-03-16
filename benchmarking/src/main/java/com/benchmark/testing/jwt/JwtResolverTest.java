package com.benchmark.testing.jwt;

import com.benchmark.testing.model.SnapshotCounter;
import com.benchmark.testing.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class JwtResolverTest {

    private static final JwtService jwtService = new JwtService();
    private static final String tokenData = jwtService.getTestToken();

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @Fork(value = 1, warmups = 2)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void readValueAsJson() {
        User user = jwtService.resolveTokenToUser(tokenData);
    }


}
