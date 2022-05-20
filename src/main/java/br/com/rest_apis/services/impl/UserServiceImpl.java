package br.com.rest_apis.services.impl;

import br.com.rest_apis.domain.User;
import br.com.rest_apis.repositories.UserRepository;
import br.com.rest_apis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> opt = userRepository.findById(id);

        return opt.orElse(null);
    }
}
