package com.benchmark.testing.transfer;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class EasyUser implements Serializable {

  private String userId;
  private long ticketId;
  private UserStatus status;
}
