package br.com.rest_apis.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	
	 private Integer id;
	 private String name;
	 private String email;
	
	 // Nessa anotação: quando for leitura ignora, quando for escrita libera
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 private String password;

}
