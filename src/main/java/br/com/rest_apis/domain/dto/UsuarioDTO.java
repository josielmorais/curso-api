package br.com.rest_apis.domain.dto;

import javax.persistence.Column;

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
	 private String email;
	 private String name;
	 private String password;

}
