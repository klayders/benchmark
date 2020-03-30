package org.orglot.gosloto.app.model.ticket.snapshot.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

  private long id;
  private long prize;
}
