package br.com.rest_apis.resouces.excepitions;

import br.com.rest_apis.resources.excepitions.ResourceExcepitionHandler;
import br.com.rest_apis.resources.excepitions.StandardError;
import br.com.rest_apis.services.excepitions.DataIntegratyViolationExcepition;
import br.com.rest_apis.services.excepitions.ObjectNotFoundExcepition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ResourceExcepitionHandlerTest {

    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final String E_MAIL_JA_CADASTRADO = "E-mail já cadastrado no sistema";
    @InjectMocks
    private ResourceExcepitionHandler excepitionHandler;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity(){
        ResponseEntity<StandardError> response = excepitionHandler.objectNotFound(
                new ObjectNotFoundExcepition(OBJETO_NAO_ENCONTRADO),
                new MockHttpServletRequest());

       assertNotNull(response);
       assertNotNull(response.getBody());
       assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
       assertEquals(ResponseEntity.class, response.getClass());
       assertEquals(StandardError.class, response.getBody().getClass());
       assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
       assertEquals(404, response.getBody().getStatus());
       assertNotEquals("/user/2",response.getBody().getPath());
       assertNotEquals(LocalDateTime.now(),response.getBody().getTimestamp());
    }

    @Test
    void dataIntegrityViolationException(){
        ResponseEntity<StandardError> response = excepitionHandler.objectNotFound(
                new DataIntegratyViolationExcepition(E_MAIL_JA_CADASTRADO),
                new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(E_MAIL_JA_CADASTRADO, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }

}
