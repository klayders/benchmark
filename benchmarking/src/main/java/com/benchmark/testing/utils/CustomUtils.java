package com.benchmark.testing.utils;

import com.benchmark.testing.modell.FirstImpl;
import com.benchmark.testing.modell.Inter;
import com.benchmark.testing.modell.SecondImpl;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CustomUtils {

  public static final int MAX_STEP = 5;


  public static void main(String[] args) throws InterruptedException {

    List.of(1, 2,3, 4, 5, 6, 7, 8)
        .stream()
        .limit(6)
        .forEach(integer -> System.out.println(integer));

    Inter first = new FirstImpl();
    Inter second = new SecondImpl();
    System.out.println(first.getClass().getSimpleName());
    System.out.println(second.getClass().getName());
  }
}
