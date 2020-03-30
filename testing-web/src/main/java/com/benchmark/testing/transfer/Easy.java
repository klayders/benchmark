package com.benchmark.testing.transfer;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
public class Easy implements Serializable {

  private long id;
  private boolean maybe;
}
