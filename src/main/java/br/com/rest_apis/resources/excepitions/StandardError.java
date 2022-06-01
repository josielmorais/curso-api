package br.com.rest_apis.resources.excepitions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class StandardError {
	 
	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String path;
	

}
