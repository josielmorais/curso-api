package br.com.rest_apis.resouces;


import br.com.rest_apis.domain.Usuario;
import br.com.rest_apis.domain.dto.UsuarioDTO;
import br.com.rest_apis.resources.UserResources;
import br.com.rest_apis.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserResourcesTest {

    public static final Integer ID = 1;
    public static final String NAME = "Valdir";
    public static final String EMAIL = "valdir@email.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final List<Usuario> USUARIO_LIST = new ArrayList<>();
    public static final int INDEX = 0;
    private Usuario user;
    private UsuarioDTO userDTO;


    @InjectMocks
    private UserResources resource;
    @Mock
    private UserServiceImpl service;
    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSuccess(){
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(),any())).thenReturn(userDTO);

        ResponseEntity<UsuarioDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(UsuarioDTO.class,response.getBody().getClass());

        assertEquals(ID,response.getBody().getId());
        assertEquals(NAME,response.getBody().getName());
        assertEquals(EMAIL,response.getBody().getEmail());
        assertEquals(PASSWORD,response.getBody().getPassword());

    }

    @Test
    void whenFindAllThenReturnListOfUserDTO(){
        when(service.findAll()).thenReturn(USUARIO_LIST);
        when(mapper.map(any(),any())).thenReturn(userDTO);

        ResponseEntity<List<UsuarioDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(ArrayList.class,response.getBody().getClass());
        assertEquals(UsuarioDTO.class,response.getBody().get(0).getClass());

        assertEquals(ID,response.getBody().get(INDEX).getId());
        assertEquals(NAME,response.getBody().get(INDEX).getName());
        assertEquals(EMAIL,response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD,response.getBody().get(INDEX).getPassword());
    }
    @Test
    void whenCreateTheReturnCreated(){
        when(service.create(any())).thenReturn(user);

        ResponseEntity<UsuarioDTO> response = resource.create(userDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    private void startUser(){

        user = new Usuario(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UsuarioDTO(ID, NAME, EMAIL, PASSWORD);
        USUARIO_LIST.add(user);

    }
}
