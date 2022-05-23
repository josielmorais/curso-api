package br.com.rest_apis.services;

import java.util.List;

import br.com.rest_apis.domain.Usuario;
import br.com.rest_apis.domain.dto.UsuarioDTO;


public interface UserService {

    Usuario findById(Integer id);
    List<Usuario> findAll();
    Usuario create(UsuarioDTO obj);
    Usuario update(UsuarioDTO obj);

}
