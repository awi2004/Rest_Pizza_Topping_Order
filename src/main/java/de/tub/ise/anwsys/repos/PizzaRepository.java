package de.tub.ise.anwsys.repos;

import de.tub.ise.anwsys.models.Pizza;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Long> {
    @Query(value = "Select id from pizza_table")
    public List<Pizza>findID();

    Pizza findByName(String name);
    //Optional<User> findByName (String desc);
    List<Pizza> findById(Collection<Long> id);
    List<Pizza> findByIdIn(Collection<Long> ids);

}