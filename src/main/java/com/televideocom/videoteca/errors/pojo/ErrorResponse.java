package com.televideocom.videoteca.errors.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	private Date data = new Date();
	private HttpStatus status = HttpStatus.OK;
	private String message;
}

