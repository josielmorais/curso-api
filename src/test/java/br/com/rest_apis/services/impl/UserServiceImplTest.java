package br.com.rest_apis.services.impl;

import br.com.rest_apis.domain.Usuario;
import br.com.rest_apis.domain.dto.UsuarioDTO;
import br.com.rest_apis.repositories.UserRepository;
import br.com.rest_apis.services.excepitions.DataIntegratyViolationExcepition;
import br.com.rest_apis.services.excepitions.ObjectNotFoundExcepition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;


import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Valdir";
    public static final String EMAIL = "valdir@email.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
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
            Assertions.assertEquals("Objeto não encontrado",ex.getMessage());
        }

    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
       Mockito.when(repository.findAll()).thenReturn(List.of(user));

       List<Usuario> response = service.findAll();

       Assertions.assertNotNull(response);
       Assertions.assertEquals(1,response.size());
       Assertions.assertEquals(1,response.get(0).getId());

    }

    @Test
    void whenCreateThenReturnSucess() {
       Mockito.when(repository.save(Mockito.any())).thenReturn(user);

       Usuario response = service.create(userDTO);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Usuario.class,response.getClass());
        Assertions.assertEquals(ID,response.getId());
        Assertions.assertEquals(NAME,response.getName());
        Assertions.assertEquals(EMAIL,response.getEmail());
        Assertions.assertEquals(PASSWORD,response.getPassword());
    }
    @Test
    void whenCreateThenReturnDataIntegrityViolationsException() {
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.create(userDTO);
        } catch (Exception ex){
            Assertions.assertEquals(DataIntegratyViolationExcepition.class,ex.getMessage());
            Assertions.assertEquals("E-mail já cadastrado no sistema",ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSucess() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        Usuario response = service.update(userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Usuario.class,response.getClass());
        Assertions.assertEquals(ID,response.getId());
        Assertions.assertEquals(NAME,response.getName());
        Assertions.assertEquals(EMAIL,response.getEmail());
        Assertions.assertEquals(PASSWORD,response.getPassword());
    }

    @Test
    void whenUpdateThenReturnDataIntegrityViolationsException() {
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.create(userDTO);
        } catch (Exception ex){
            Assertions.assertEquals(DataIntegratyViolationExcepition.class,ex.getMessage());
            Assertions.assertEquals("E-mail já cadastrado no sistema",ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
        Mockito.doNothing().when(repository).deleteById(Mockito.anyInt());
        service.delete(ID);
        Mockito.verify(repository,Mockito.times(1)).deleteById(Mockito.anyInt());

    }

    @Test
    void deleteWithObjectNotFoundException(){
        Mockito.when(repository.findById(Mockito.anyInt())).
                thenThrow(new ObjectNotFoundExcepition("Objeto não encontrado"));

        try{
            service.delete(ID);
        } catch (Exception ex){
            Assertions.assertEquals(ObjectNotFoundExcepition.class,ex.getClass());
            Assertions.assertEquals(OBJETO_NAO_ENCONTRADO,ex.getMessage());
        }


    }

    private void startUser(){
        user = new Usuario(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UsuarioDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new Usuario(ID, NAME, EMAIL, PASSWORD));

    }
}