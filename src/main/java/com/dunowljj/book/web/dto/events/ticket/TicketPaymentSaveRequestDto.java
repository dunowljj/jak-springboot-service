package com.dunowljj.book.web.dto.events.ticket;

import com.dunowljj.book.domain.events.ticket.PayGroup;
import com.dunowljj.book.domain.events.ticket.PayStatus;
import com.dunowljj.book.domain.events.ticket.TicketPayment;
import com.dunowljj.book.domain.events.ticket.TicketReservation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class TicketPaymentSaveRequestDto {
    private Long reservationId;
    private Long total;
    private PayGroup payGroup;

    // todo : default를 서버단에서 제공하는게 맞는가?
//    private LocalDate payDate = LocalDate.now();
//    private PayStatus payStatus = PayStatus.COMP;

    public TicketPaymentSaveRequestDto(Long reservationId, Long total, PayGroup payGroup) {
        this.reservationId = reservationId;
        this.total = total;
        this.payGroup = payGroup;
    }

    //  todo : db 저장 시 id만 있으면 되는데, 값을 매번 엔티티를 조회해야하나?
    public TicketPayment toEntity(TicketReservation ticketReservation) {
        return TicketPayment.builder()
                .ticketReservation(ticketReservation)
                .total(total)
                .payGroup(payGroup)
                .payDate(LocalDate.now())
                .payStatus(PayStatus.COMP)
                .build();
    }
}
