package com.salesianostriana.dam.rest.ticket;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.rest.ticket.model.Ticket;

import java.time.LocalDateTime;

public record GetTicketDto(
        Long id,
        String title,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime deadline
) {

        public GetTicketDto of(Ticket t){
                return new GetTicketDto(
                        t.getId(),
                        t.getTitle(),
                        t.getDeadline()
                );

        }

}
