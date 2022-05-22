package br.com.rest_apis.resources;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rest_apis.domain.dto.UsuarioDTO;
import br.com.rest_apis.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserResources {

	@Autowired
	private ModelMapper mapper;
	
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> fyndById(@PathVariable Integer id){

        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UsuarioDTO.class));

    }

}
