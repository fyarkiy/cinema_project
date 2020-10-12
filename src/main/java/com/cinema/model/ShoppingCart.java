package com.cinema.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
    @OneToMany
    @Column(name = "ticket_id")
    private List<Ticket> tickets;

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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingCart)) {
            return false;
        }
        ShoppingCart that = (ShoppingCart) o;
        return getId().equals(that.getId())
                && getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser());
    }

    @Override
    public String toString() {
        return "ShoppingCart{ id=" + id
                + ", user=" + user
                + ", tickets=" + tickets + " '}";
    }
}
