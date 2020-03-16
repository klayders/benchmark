package com.benchmark.testing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Counter {

    private List<Long> first;

    private List<Long> second;

    private List<Long> third;
}