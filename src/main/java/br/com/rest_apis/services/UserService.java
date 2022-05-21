package br.com.rest_apis.services;

import br.com.rest_apis.domain.Usuario;


public interface UserService {

    Usuario findById(Integer id);

}
