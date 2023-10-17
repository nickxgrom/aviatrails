package dev.nxgr.aviatrails.Ticket;

import dev.nxgr.aviatrails.Route.Route;
import dev.nxgr.aviatrails.Route.RouteRepository;
import dev.nxgr.aviatrails.User.User;
import dev.nxgr.aviatrails.User.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public Ticket add(TicketDto ticketDto) {
        User user = userRepository.findById(ticketDto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user-not-found"));
        Route route = routeRepository.findById(ticketDto.getRouteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "route-not-found"));

        if (user.getBalance() < ticketDto.getAmount() * route.getCoast()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "insufficient-funds");
        }

        user.setBalance(user.getBalance() - ticketDto.getAmount() * route.getCoast());
        route.setAmount(route.getAmount() - ticketDto.getAmount());

        userRepository.save(user);
        routeRepository.save(route);

        return ticketRepository.save(new Ticket(user, route, ticketDto.getAmount()));
    }

    public List<Ticket> getTicketList(int page, int pageSize, Long userId) {
        Pageable paging = PageRequest.of(page, pageSize);
        return ticketRepository.getTickets(paging, userId);
    }
}
