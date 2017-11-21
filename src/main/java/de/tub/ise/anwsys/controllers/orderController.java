package de.tub.ise.anwsys.controllers;

import de.tub.ise.anwsys.models.OrderItem;
import de.tub.ise.anwsys.models.Pizza;
import de.tub.ise.anwsys.models.PizzaOrder;
import de.tub.ise.anwsys.repos.OrderRepository;
import de.tub.ise.anwsys.repos.PizzaRepository;
import de.tub.ise.anwsys.repos.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class orderController {

    @Autowired
    public ToppingRepository topp_repo;

    @Autowired
    public PizzaRepository pizzarepo;

    @Autowired
    public OrderRepository order_repo;



    @RequestMapping(value = "/v1/order", method = RequestMethod.GET)
    public ResponseEntity<List<PizzaOrder>> getAllOrder() {
        List<PizzaOrder> topics = new ArrayList<>();
        //order_repo.findAll().forEach(topics::add);
        topics=order_repo.findID();
        if (topics.isEmpty()) {

            //return ResponseEntity.status(HttpStatus.NO_CONTENT).body(handleBadRequests(HttpStatus.NO_CONTENT));
            throw new exception_defined("Order list is empty");
        }

        return new ResponseEntity<List<PizzaOrder>>(topics,HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/order",method = RequestMethod.POST)
    public ResponseEntity<List<String>>createOrder( @RequestBody PizzaOrder order) {
       if (order==null) {

           throw new exception_defined("None of the Order item is present");
       }

           HashSet<Long> pizzaIds = new HashSet<>();
           for (OrderItem item : order.getOrderItems()) {
               pizzaIds.add(item.getPizzaId());
           }
           List<Pizza> pizzas = pizzarepo.findByIdIn(pizzaIds);
           double totlaPrice = 0.0;

           if (pizzas != null && pizzas.size() > 0) {
               HashMap<Long, Long> foundPizzas = new HashMap<Long, Long>();
               int size = order.getOrderItems().size();
               OrderItem[] items = new OrderItem[size];
               order.getOrderItems().toArray(items);
               for (int i = 0; i < size; i++) {
                   OrderItem item = items[i];
                   Optional<Pizza> pizza = pizzas.stream().filter(x -> x.getId() == item.getPizzaId()).findFirst();
                   if (pizza.isPresent()) {
                       totlaPrice += (pizza.get().getTotalPrice()) * (item.getQuantity());
                   } else {
                       throw new exception_inavlidInput("Invalid Pizza Id");
                   }
                   if (!foundPizzas.containsKey(item.getPizzaId())) {
                       foundPizzas.put(item.getPizzaId(), item.getQuantity());
                   } else {
                       foundPizzas.put(item.getPizzaId(), foundPizzas.get(item.getPizzaId()) + item.getQuantity());
                       order.deleteItem(item);
                   }
               }

               for (OrderItem item : order.getOrderItems()) {
                   item.setPizzaOrder(order);
                   item.setQuantity(foundPizzas.get(item.getPizzaId()));
               }
               order.setTotalPrice(totlaPrice);
               order_repo.save(order);
           }
           return new ResponseEntity(HttpStatus.CREATED);
       }
    @RequestMapping(value = "/v1/order/{id}", method = RequestMethod.GET)
    public ResponseEntity getOrderByID(@PathVariable Long id) {
        PizzaOrder topics=order_repo.findOne(id);
        if (topics==null) {

            //return ResponseEntity.status(HttpStatus.NO_CONTENT).body(handleBadRequests(HttpStatus.NO_CONTENT));
            throw new exception_defined("Order list is empty");
        }

        return new ResponseEntity(topics,HttpStatus.OK);
    }
    @RequestMapping(value = "/v1/order/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable Long id) {
        PizzaOrder topics=order_repo.findOne(id);
        if (topics==null) {

            //return ResponseEntity.status(HttpStatus.NO_CONTENT).body(handleBadRequests(HttpStatus.NO_CONTENT));
            throw new exception_defined("No order id to delete");
        }
        order_repo.delete(id);
        return new ResponseEntity(topics,HttpStatus.OK);
    }

}