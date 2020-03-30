package com.benchmark.testing.json;

import com.benchmark.testing.model.Counter;
import com.benchmark.testing.model.SnapshotCounter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import finch.json.Json;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.OutputTimeUnit;

public class JsonSerializationTest {

  private static final ObjectMapper mapper = new ObjectMapper();
  private static final Gson gson = new GsonBuilder().create();

  private static final SnapshotCounter snapshotCounter = SnapshotCounter.menu(
      Counter.of(
          List.of(new Random().nextLong(), new Random().nextLong(), new Random().nextLong(), new Random().nextLong(), new Random().nextLong(), new Random().nextLong()),
          List.of(new Random().nextLong(), new Random().nextLong(), new Random().nextLong(), new Random().nextLong(), new Random().nextLong(), new Random().nextLong()),
          List.of(new Random().nextLong(), new Random().nextLong(), new Random().nextLong(), new Random().nextLong(), new Random().nextLong(), new Random().nextLong())
      )
  );

  public static void main(String[] args) throws IOException {
    org.openjdk.jmh.Main.main(args);
  }

  @Benchmark
  @Fork(value = 1, warmups = 2)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void writeValueAsJackson() {
    try {
      var menuData = mapper.writeValueAsString(snapshotCounter);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  @Benchmark
  @Fork(value = 1, warmups = 2)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void writeValueAsGoogleGson() {
      var menuData = gson.toJson(snapshotCounter);
  }

  @Benchmark
  @Fork(value = 1, warmups = 2)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public void writeValueAsFinchJson() {
    var menuData = Json.json(snapshotCounter).toString();
  }
}
