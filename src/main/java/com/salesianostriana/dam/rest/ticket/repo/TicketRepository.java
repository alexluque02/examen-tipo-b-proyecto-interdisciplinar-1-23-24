package com.salesianostriana.dam.rest.ticket.repo;

import com.salesianostriana.dam.rest.ticket.GetTicketDto;
import com.salesianostriana.dam.rest.ticket.model.Ticket;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t")
    Page<Ticket> findAllPageable(Pageable pageable);


    @Query("""
            SELECT new com.salesianostriana.dam.rest.ticket.GetTicketDto(
            t.id, t.title, t.deadline
            ) FROM Ticket t
            """)
    Page<GetTicketDto> getTicketsDto(Pageable pageable);
}
