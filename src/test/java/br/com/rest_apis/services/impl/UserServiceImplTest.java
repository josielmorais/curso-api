package br.com.rest_apis.services.impl;

import br.com.rest_apis.domain.Usuario;
import br.com.rest_apis.domain.dto.UsuarioDTO;
import br.com.rest_apis.repositories.UserRepository;
import br.com.rest_apis.services.excepitions.ObjectNotFoundExcepition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Valdir";
    public static final String EMAIL = "valdir@email.com";
    public static final String PASSWORD = "123";
    @InjectMocks // Criar uma instância real de uso de UserServiceImpl
    private UserServiceImpl service;
    @Mock // Criar uma instância de mentira
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;
    private Usuario user;
    private UsuarioDTO userDTO;
    private Optional<Usuario> optionalUser;

    @BeforeEach
    void setUp(){
        //Iniciar os mock dessa nossa classe
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnUserInstance() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        Usuario reponse = service.findById(ID);

        Assertions.assertEquals(Usuario.class,reponse.getClass());
    }
    @Test
    void whenFinByIdThenReturnAnObjectNotFoundException(){
        Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundExcepition("Objeto não encontrado"));

        try{

        }catch (Exception ex){
            Assertions.assertEquals(ObjectNotFoundExcepition.class,ex.getClass());
        }

    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new Usuario(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UsuarioDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new Usuario(ID, NAME, EMAIL, PASSWORD));

    }
}