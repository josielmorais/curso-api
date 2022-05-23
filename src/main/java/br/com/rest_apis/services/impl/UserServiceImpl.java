package br.com.rest_apis.services.impl;

import br.com.rest_apis.domain.Usuario;
import br.com.rest_apis.domain.dto.UsuarioDTO;
import br.com.rest_apis.repositories.UserRepository;
import br.com.rest_apis.services.UserService;
import br.com.rest_apis.services.excepitions.DataIntegratyViolationExcepition;
import br.com.rest_apis.services.excepitions.ObjectNotFoundExcepition;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    
    @Autowired
    private ModelMapper mapper;
    
    

    @Override
    public Usuario findById(Integer id) {
        Optional<Usuario> opt = repository.findById(id);
        return opt.orElseThrow(() -> new ObjectNotFoundExcepition("Objeto não encontrado"));
    }
    
    public List<Usuario> findAll(){
    	return repository.findAll();
    }

	@Override
	public Usuario create(UsuarioDTO obj) {
		findByEmail(obj);
		// TODO Auto-generated method stub
		return repository.save(mapper.map(obj, Usuario.class));
	}
	
	
	private void findByEmail(UsuarioDTO obj) {
		Optional<Usuario> usuario = repository.findByEmail(obj.getEmail());
		if(usuario.isPresent() && !usuario.get().getId().equals(obj.getId())) {
			throw new DataIntegratyViolationExcepition("E-mail já cadastrado no sistema");
		}
	}

	@Override
	public Usuario update(UsuarioDTO obj) {
		findByEmail(obj);
		return repository.save(mapper.map(obj, Usuario.class));
	}

	@Override
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
		
	}
}
