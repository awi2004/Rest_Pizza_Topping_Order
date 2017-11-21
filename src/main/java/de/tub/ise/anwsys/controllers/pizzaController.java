package de.tub.ise.anwsys.controllers;
import de.tub.ise.anwsys.models.Pizza;
import de.tub.ise.anwsys.repos.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class pizzaController {

    @Autowired
    public PizzaRepository pizzarepo;

    @RequestMapping(value = "/v1/pizza", method = RequestMethod.GET)
    public ResponseEntity<List<Pizza>> getAll() {
        //String[] top=new String[10000];
        List<Pizza> topics = new ArrayList<>();
        //pizzarepo.findAll().forEach(topics::add);
        topics=pizzarepo.findID();
        //List<pizza> red=test1.getall();
        if (topics.isEmpty()) {

            //return ResponseEntity.status(HttpStatus.NO_CONTENT).body(handleBadRequests(HttpStatus.NO_CONTENT));
            throw new exception_defined("Pizza list is empty");
        }
        return new ResponseEntity<List<Pizza>>(topics,HttpStatus.OK);

    }

    @RequestMapping(value = "/v1/pizza/{id}", method = RequestMethod.GET)
    public Pizza getId( @PathVariable Long id ) throws exception_defined {
        Pizza pizza = pizzarepo.findOne(id);
        if (pizza==null){
            throw new exception_defined("Pizza not available" );
        }
        return pizza;
    }

    @RequestMapping(value = "/v1/pizza", method = RequestMethod.POST)
    public ResponseEntity<Pizza> addPizza( @RequestBody Pizza piiza ) {
        pizzarepo.save(piiza);
        /*URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{pizzaId}")
                .buildAndExpand(piiza.getId()).toUri()*/
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @RequestMapping(value = "/v1/pizza/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Pizza> updatePizza( @RequestBody Pizza piiza,@PathVariable Long id ) {
        if (piiza == null) {
            throw new exception_defined("Invalid input");
        }
        pizzarepo.save(piiza);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/v1/pizza/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteId( @PathVariable Long id ) {
        Pizza pizza = pizzarepo.findOne(id);
        if (pizza != null) {
            pizzarepo.delete(id);
            return new ResponseEntity(HttpStatus.OK);
            //return Pizza;
        }
        throw new exception_inavlidInput("Pizza id not found");
    }



}
