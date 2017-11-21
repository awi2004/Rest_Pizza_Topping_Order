package de.tub.ise.anwsys.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderItem implements Serializable {
    public OrderItem(PizzaOrder order, Pizza pizza, long quantity) {
        super();
        this.pizzaOrder  = order;
        this.pizza = pizza;
        this.quantity = quantity;
    }

    public OrderItem(long order_id, long pizza_id, long quantity) {
        super();
        this.pizzaOrder  = new PizzaOrder(order_id);
        this.pizza = new Pizza(pizza_id);
        this.quantity = quantity;
    }

    public OrderItem() {
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pizza_id")
    @Id
    private Pizza pizza;


    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pizza_order_id")
    private PizzaOrder pizzaOrder ;
    private long quantity;

    @JsonIgnore
    public PizzaOrder getPizzaOrder() {
        return pizzaOrder ;
    }

    public void setPizzaOrder(PizzaOrder order) {
        this.pizzaOrder  = order;
    }

    public long getPizzaOrderId() {
        if (this.pizzaOrder != null)
            return pizzaOrder.getId();
        return 0;
    }

    public void setPizzaOrderId(long order_id) {
        this.pizzaOrder  = new PizzaOrder(order_id);
    }

    public long getPizzaId() {
        if (this.pizza != null)
            return this.pizza.getId();
        return 0;
    }

    public void setPizzaId(long pizza_Id) {
        this.pizza = new Pizza(pizza_Id);
    }

    @JsonIgnore
    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }


}