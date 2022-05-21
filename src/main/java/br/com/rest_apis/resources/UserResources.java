package br.com.rest_apis.resources;


import br.com.rest_apis.domain.Usuario;
import br.com.rest_apis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserResources {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> fyndById(@PathVariable Integer id){

        return ResponseEntity.ok().body(userService.findById(id));

    }

}
