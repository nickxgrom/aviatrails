package dev.nxgr.aviatrails.Ticket;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("select t from Ticket t where t.user.id = :userId")
    List<Ticket> getTickets(Pageable pageable, @Param("userId") Long userId);
}
