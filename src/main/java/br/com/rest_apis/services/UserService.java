package br.com.rest_apis.services;

import br.com.rest_apis.domain.User;

public interface UserService {

    User findById(Integer id);

}
