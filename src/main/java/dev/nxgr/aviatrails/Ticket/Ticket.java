package dev.nxgr.aviatrails.Ticket;

import dev.nxgr.aviatrails.Route.Route;
import dev.nxgr.aviatrails.User.User;
import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
//    @JoinColumn(referencedColumnName = "user_id")
    private User user;
    @OneToOne
    private Route route;
    private int amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Ticket(User user, Route route, int amount) {
        this.user = user;
        this.route = route;
        this.amount = amount;
    }

    public Ticket(Long id, User user, Route route, int amount) {
        this.id = id;
        this.user = user;
        this.route = route;
        this.amount = amount;
    }

    public Ticket() {
    }
}
