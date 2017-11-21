/*package de.tub.ise.anwsys.controllers;

import de.tub.ise.anwsys.models.User;
import de.tub.ise.anwsys.repos.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class HelloController {

    private UserRepository userRepository;

    @Autowired
    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //@ApiOperation(value ="hi",response = Iterable.class)
    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public ResponseEntity<?> answerAndRegister(@RequestParam(value = "user", defaultValue = "AnwSys Student") String username) {
        Optional<User> foundUser = userRepository.findByName(username);

        if (foundUser.isPresent()) {
            //ode=201;
           //reurn ResponseEntity.ok(String.format("Welcome back, %s. How are your studies going?", foundUser.get().getName()));
            return new ResponseEntity(HttpStatus.CREATED);
        }
        else {
            User u = new User(username);
            userRepository.save(u);
            return ResponseEntity.ok(String.format("Welcome new User %s!", u.getName()));
        }
    }
}

*/


