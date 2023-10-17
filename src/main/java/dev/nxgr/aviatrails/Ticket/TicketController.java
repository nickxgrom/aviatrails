package dev.nxgr.aviatrails.Ticket;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    public final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public Ticket add(@RequestBody TicketDto ticket) {
        return ticketService.add(ticket);
    }

    @GetMapping("{userId}")
    public List<Ticket> getTicketList(
        @PathVariable Long userId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int pageSize
    ) {
        return ticketService.getTicketList(page, pageSize, userId);
    }
}
