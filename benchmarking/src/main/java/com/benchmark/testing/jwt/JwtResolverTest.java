package com.benchmark.testing.jwt;

import com.benchmark.testing.model.User;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;


public class JwtResolverTest {

  private static final JwtService jwtService = new JwtService();

  public static void main(String[] args) throws IOException {
    for (int i = 0; i <10; i++) {
      User user = jwtService.resolveTokenToUser(jwtService.getTestToken());
    }
//    org.openjdk.jmh.Main.main(args);
  }

//  @Benchmark
//  @Threads(4)
//  @Fork(value = 1)
//  @Warmup(iterations = 10, time = 10, timeUnit = TimeUnit.SECONDS)
//  @OutputTimeUnit(TimeUnit.MILLISECONDS)
//  public void testJavaSecurityProvider(Blackhole blackhole) {
//    User user = jwtService.resolveTokenToUser(jwtService.getTestToken());
//    blackhole.consume(user);
//  }
}
