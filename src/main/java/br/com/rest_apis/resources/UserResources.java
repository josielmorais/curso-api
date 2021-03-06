package br.com.rest_apis.resources;


import br.com.rest_apis.domain.dto.UsuarioDTO;
import br.com.rest_apis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserResources {

	private static final String ID = "/{id}";
	
	@Autowired
	private ModelMapper mapper;
	
    @Autowired
    private UserService userService;

    @GetMapping(value = ID)
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id){

        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UsuarioDTO.class));

    }
    
    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> findAll(){
    	return ResponseEntity.ok().
    			body(userService.findAll().stream().map(x -> mapper.map(x, UsuarioDTO.class)).collect(Collectors.toList()));

    }
    
    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO obj){
    	URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(userService.create(obj).getId()).toUri();
    	return ResponseEntity.created(uri).build();

    }
   
    @PutMapping(value = ID)
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id,@RequestBody UsuarioDTO obj){
    	obj.setId(id);
    	return ResponseEntity.ok().body(mapper.map(userService.update(obj), UsuarioDTO.class));

    }
    
    @DeleteMapping(value = ID)
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Integer id){
    	userService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
