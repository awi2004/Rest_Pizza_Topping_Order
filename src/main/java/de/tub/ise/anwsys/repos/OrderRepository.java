package de.tub.ise.anwsys.repos;

import de.tub.ise.anwsys.models.Pizza;
import de.tub.ise.anwsys.models.PizzaOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository <PizzaOrder, Long>{

    @Query(value = "Select id from order_table_id")
    public List<PizzaOrder>findID();

    List<PizzaOrder> findById( Collection<Long> id);
    //List<PizzaOrder> findByPizzaId(Long pizzaId);
}


