package de.tub.ise.anwsys.repos;
import de.tub.ise.anwsys.models.Topping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
@Repository
public interface ToppingRepository extends CrudRepository<Topping,Long> {

    //@Query(value = "Select t.id from topping_table where t.id= ")
    //public List<Topping>findID();
    //List<Topping> findById( Collection<Long> id);
    List<Topping> findById(Collection<Long> topping_id);
    Topping findOneByPizzaIdAndId(Long id,Long topping_id);
    List<Topping> findByPizzaId(Long pizzaId);
}
