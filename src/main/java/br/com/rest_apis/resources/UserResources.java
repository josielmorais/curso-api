package br.com.rest_apis.resources;


import java.net.URI;
import java.security.Provider.Service;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rest_apis.domain.Usuario;
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
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id){

        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UsuarioDTO.class));

    }
    
    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> findAll(){
    	return ResponseEntity.ok().
    			body(userService.findAll().stream().map(x -> mapper.map(x, UsuarioDTO.class)).collect(Collectors.toList()));

    }
    
    @PostMapping
    public ResponseEntity<List<UsuarioDTO>> create(@RequestBody UsuarioDTO obj){
    	URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(userService.create(obj).getId()).toUri();
    	return ResponseEntity.created(uri).build();

    }
   
    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id,@RequestBody UsuarioDTO obj){
    	obj.setId(id);
    	return ResponseEntity.ok().body(mapper.map(userService.update(obj), UsuarioDTO.class));

    }

}
