package com.benchmark.testing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "menu")
public class SnapshotCounter {
    private Counter counter;
}