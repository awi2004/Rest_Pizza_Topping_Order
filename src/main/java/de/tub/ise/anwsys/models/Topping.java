package de.tub.ise.anwsys.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name="topping_table")
public class Topping implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pizza_id",updatable =false,insertable = false)
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    private Pizza pizza;

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza( Pizza pizzaa ) {
        this.pizza = pizzaa;
    }

    @Column(name="pizza_id")
    private long pizza_id;

    public long getPizza_Id() {

       return pizza_id;
    }

    public void setPizza_Id( long pizza_id ) {

        this.pizza_id = pizza_id;
    }

    public Topping() {
    }

    public Topping( long id, String name, double price, long pizzaId ) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.pizza = new Pizza(pizzaId, "", "", 0.0);
        this.pizza_id = pizzaId;
    }


    public Long getId() {
        return id;
    }

    public void setId( Long topping_id ) {
        this.id = topping_id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice( double price ) {
        this.price = price;
    }
    //@ManyToMany(fetch=FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    //private List<pizza> Pizza;
}
