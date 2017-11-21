package de.tub.ise.anwsys.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity(name = "pizza_table")
public class Pizza implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String size;

    private enum pizza_size {
        Standard(1), Large(2);
        private int value;

        pizza_size( int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
        public static pizza_size parse(int value) {
            pizza_size size = null;
            for (pizza_size item : pizza_size.values()) {
                if (item.getValue() == value) {
                    size = item;
                    break;
                }
            }
            return size;
        }

        public static pizza_size parse(String sizeName) {
            pizza_size size = null;
            for (pizza_size item : pizza_size.values()) {
                if (item.name().toUpperCase().equals(sizeName.toUpperCase())) {
                    size = item;
                    break;
                }
            }
            return size;
        }



    }


    private double price;

    //@JsonIgnore
    //private List<Integer> topping_list = new ArrayList<Integer>();
    /*@OneToMany(mappedBy = "pizza")
    @JsonIgnore
    private Set<OrderItem> orderItems;*/


    @OneToMany(mappedBy = "pizza",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Topping> toppings;

    public Set<Topping> getToppings() {
        return toppings;
    }

    public void setToppings( Set<Topping> toppingss ) {
        this.toppings = toppingss;
    }

    @OneToMany(mappedBy = "pizza")
    @JsonIgnore
    private Set<OrderItem> order_Items;

    public Set<OrderItem> getOrder_Items() {
        return order_Items;
    }

    public void setOrder_Items( Set<OrderItem> order_Items ) {
        this.order_Items = order_Items;
    }

    public Pizza() {
    }
    public Pizza(long id) {
        this.id=id;
    }

    public Pizza( long id, String name, String size, double price ) {
        super();
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public  Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setSize( String size ) {
        this.size = size;
    }

    public void setPrice( double price ) {
        this.price = price;
    }

    @JsonIgnore
    public double getTotalPrice() {
        double totalPrice = this.price;
        if (this.toppings != null && this.toppings.size() > 0) {
            for (Topping topping : this.toppings) {
                totalPrice += topping.getPrice();
            }
        }
        return totalPrice;
    }

}
