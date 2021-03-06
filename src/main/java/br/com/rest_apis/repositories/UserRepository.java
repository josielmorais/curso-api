package br.com.rest_apis.repositories;

import br.com.rest_apis.domain.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario,Integer> {

	Optional<Usuario> findByEmail(String email);

}
