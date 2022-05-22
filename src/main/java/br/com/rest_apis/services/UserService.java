package br.com.rest_apis.services;

import java.util.List;

import br.com.rest_apis.domain.Usuario;


public interface UserService {

    Usuario findById(Integer id);
    List<Usuario> findAll();

}
