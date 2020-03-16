package com.benchmark.testing.utils;

import com.benchmark.testing.model.Counter;
import com.benchmark.testing.model.SnapshotCounter;

import java.util.List;
import java.util.Random;

public class PojoRequestUtils {

    private static final long SPEED = 100000000;

    public static final SnapshotCounter HARD_CODE_COUNTER = SnapshotCounter.menu(
            Counter.of(
                    List.of(
                            new Random(SPEED).nextLong(),
                            new Random(SPEED).nextLong(),
                            new Random(SPEED).nextLong(),
                            new Random(SPEED).nextLong()
                    ),
                    List.of(
                            new Random(SPEED).nextLong(),
                            new Random(SPEED).nextLong(),
                            new Random(SPEED).nextLong(),
                            new Random(SPEED).nextLong()
                    ),
                    List.of(
                            new Random(SPEED).nextLong(),
                            new Random(SPEED).nextLong(),
                            new Random(SPEED).nextLong(),
                            new Random(SPEED).nextLong())
            )
    );


}
