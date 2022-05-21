package br.com.rest_apis.services.impl;

import br.com.rest_apis.domain.Usuario;
import br.com.rest_apis.repositories.UserRepository;
import br.com.rest_apis.services.UserService;
import br.com.rest_apis.services.excepitions.ObjectNotFoundExcepition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Usuario findById(Integer id) {
        Optional<Usuario> opt = userRepository.findById(id);

        return opt.orElseThrow(() -> new ObjectNotFoundExcepition("Objeto não encontrado"));
    }
}
