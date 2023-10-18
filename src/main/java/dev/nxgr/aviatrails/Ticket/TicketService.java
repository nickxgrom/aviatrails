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
import java.util.Objects;

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

    public void refund(Long userId, Long ticketId, int refundAmount) {
//        TODO: block refund from day before flightStartUtc
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user-not-found"));
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ticket-not-found"));

        if (!Objects.equals(ticket.getUser().getId(), user.getId())) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ticket-not-found");
        }

        if (refundAmount > ticket.getAmount()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "refund-bigger-than-amount");
        }

        Route route = ticket.getRoute();

        route.setAmount(route.getAmount() + refundAmount);
        user.setBalance(user.getBalance() + route.getCoast() * refundAmount);

        if (ticket.getAmount() - refundAmount == 0) {
            ticketRepository.deleteById(ticketId);
        } else {
            ticket.setAmount(ticket.getAmount() - refundAmount);
            ticketRepository.save(ticket);
        }

        routeRepository.save(route);
        userRepository.save(user);
    }
}
