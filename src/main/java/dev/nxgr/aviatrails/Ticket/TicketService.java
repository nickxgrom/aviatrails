package dev.nxgr.aviatrails.Ticket;

import dev.nxgr.aviatrails.Route.Route;
import dev.nxgr.aviatrails.Route.RouteRepository;
import dev.nxgr.aviatrails.User.User;
import dev.nxgr.aviatrails.User.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, RouteRepository routeRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.routeRepository = routeRepository;
    }

    public void add(TicketDto ticketDto) {
        User user = userRepository.findById(ticketDto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user-not-found"));
        Route route = routeRepository.findById(ticketDto.getRouteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "route-not-found"));
        ticketRepository.save(new Ticket(user, route, ticketDto.getAmount()));
    }
}
