package br.com.rest_apis.resources;


import br.com.rest_apis.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserResources {

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> fyndById(@PathVariable Integer id){

        return ResponseEntity.ok().body(new User(1,"Josiel","morais@teste.com","123"));

    }

}
