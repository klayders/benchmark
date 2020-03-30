package org.orglot.gosloto.app.model.ticket.snapshot.transfer;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "ofStatuses")
public class TicketsRedisBatchInfo {

	private List<TicketTransferStatus> ticketsStatuses;
	private boolean done;


	public void updateDoneStatus() {
		if (CollectionUtils.isEmpty(ticketsStatuses)) {
			this.done = true;
		} else {
			done = ticketsStatuses.stream()
				.filter(ticketTransferStatus -> TransferStatus.pending.equals(ticketTransferStatus.getStatus()))
				.findFirst()
				.isEmpty();
		}
	}

	public void changeTicketStatusToError(long ticketId, int errorMessage) {
		ticketsStatuses.stream()
			.filter(ticketTransferStatus -> ticketId == ticketTransferStatus.getTicketId())
			.findFirst()
			.ifPresent(ticketTransferStatus -> ticketTransferStatus.updateToErrorStatus(errorMessage));
		updateDoneStatus();
	}

	public void changeTicketStatusToOk(long ticketId) {
		ticketsStatuses.stream()
			.filter(ticketTransferStatus -> ticketId == ticketTransferStatus.getTicketId())
			.findFirst()
			.ifPresent(TicketTransferStatus::updateToOkStatus);

		updateDoneStatus();

	}
}
