package br.com.rest_apis.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
public class UsuarioDTO {
	
	 private Integer id;
	 private String name;
	 private String email;
	
	 // Nessa anotação: quando for leitura ignorar, quando for escrita liberar
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 private String password;

}
