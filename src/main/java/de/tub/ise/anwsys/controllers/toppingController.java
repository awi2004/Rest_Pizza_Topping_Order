package de.tub.ise.anwsys.controllers;
import de.tub.ise.anwsys.models.Pizza;
import de.tub.ise.anwsys.models.Topping;
import de.tub.ise.anwsys.repos.PizzaRepository;
import de.tub.ise.anwsys.repos.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class toppingController {

    //@Qualifier("ToppingRepository")
    @Autowired
    public ToppingRepository topp_repo;

    @Autowired
    public PizzaRepository pizzarepo;



    @RequestMapping(value = "/v1/pizza/{id}/topping", method = RequestMethod.GET)
    public ResponseEntity<List<Topping>> getAllTopping( @PathVariable Long id ) {
        Pizza pizza = pizzarepo.findOne(id);
        System.out.println("id is" + id);
        List<Topping> all_toppings = new ArrayList<>();
        if (pizza != null) {

            topp_repo.findByPizzaId(id).forEach(all_toppings::add);
            //all_toppings=topp_repo.findID();
            //List<pizza> red=test1.getall();
            if (all_toppings.isEmpty()) {

                //return ResponseEntity.status(HttpStatus.NO_CONTENT).body(handleBadRequests(HttpStatus.NO_CONTENT));
                throw new exception_defined("Topping list is empty");
            } else {
                return new ResponseEntity<List<Topping>>(all_toppings, HttpStatus.OK);

            }

        }
        throw new exception_inavlidInput("Invalid Pizza input for topping");
    }


    @RequestMapping(value = "/v1/pizza/{id}/topping",method = RequestMethod.POST)
    public ResponseEntity<String> createTopping( @RequestBody Topping top, @PathVariable Long id, HttpServletRequest request) {
        Pizza pizza = pizzarepo.findOne(id);
        String test=request.getRequestURI();
        //URI test = ServletUriComponentsBuilder
             //   .fromCurrentRequest().path("/{toppingId}")
               // .buildAndExpand(top.getId()).toUri();

        if (pizza != null) {
            top.setPizza_Id(pizza.getId());
            top=topp_repo.save(top);
            //Pizza.getTopping_list().add(t_top.getId().intValue());
            //pizzarepo.save(Pizza);
            return ResponseEntity.status(HttpStatus.CREATED).header("location",test).body("create new topping");
        }
        throw new exception_inavlidInput("Pizza id is not defined");
    }

    @RequestMapping(value = "/v1/pizza/{id}/topping/{topping_id}", method = RequestMethod.GET)
    public Topping getToppingByID(@PathVariable Long id , @PathVariable Long topping_id ) {

            //Pizza pizza = pizzarepo.findOne(id);
            //Topping top_id = topp_repo.findOne(topping_id);
            Topping top_id_new=topp_repo.findOneByPizzaIdAndId(id,topping_id);
            if (top_id_new!=null){

                return top_id_new;
            }
            throw new exception_defined("Pizza not found for topping");

    }
    @RequestMapping(value = "/v1/pizza/{id}/topping/{topping_id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteToppingId( @PathVariable Long id ,@ PathVariable Long topping_id ) {
        Topping top_id = topp_repo.findOne(topping_id);
        Topping top_id_new=topp_repo.findOneByPizzaIdAndId(id,topping_id);
        if (top_id_new!=null){

            topp_repo.delete(topping_id);
            return new ResponseEntity(HttpStatus.OK);
        }

            throw new exception_inavlidInput("Topping id not present to delete");

    }
}
