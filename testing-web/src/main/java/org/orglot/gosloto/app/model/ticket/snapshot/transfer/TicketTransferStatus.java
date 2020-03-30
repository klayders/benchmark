package org.orglot.gosloto.app.model.ticket.snapshot.transfer;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
//@JsonDeserialize(using = TicketTransferStatusSerializer.class)
public class TicketTransferStatus {

	private long ticketId;
	private TransferStatus status;
	private long prize;
	private int error;

	public static TicketTransferStatus pending(long ticketId, long prize){
		return TicketTransferStatus.builder()
			.ticketId(ticketId)
			.status(TransferStatus.pending)
			.prize(prize)
			.build();
	}

	public static TicketTransferStatus pending(Ticket ticket){
		return TicketTransferStatus.builder()
			.ticketId(ticket.getId())
			.status(TransferStatus.pending)
			.prize(ticket.getPrize())
			.build();
	}

	public static TicketTransferStatus error(Ticket ticket, int error){
		return TicketTransferStatus.builder()
			.ticketId(ticket.getId())
			.status(TransferStatus.error)
			.prize(ticket.getPrize())
			.error(error)
			.build();
	}

	public void updateToErrorStatus(int error){
		this.status = TransferStatus.error;
		this.error = error;
	}

	public void updateToOkStatus(){
		this.status = TransferStatus.ok;
	}


	public void setStatusOK(){
		this.status = TransferStatus.ok;
	}

}
