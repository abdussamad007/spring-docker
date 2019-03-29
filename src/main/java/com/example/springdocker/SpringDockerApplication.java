package com.example.springdocker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDockerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

@RestController
class RestApi {

	@Autowired
	private RestTemplate restTemplate;

	private File logFile =  new File("logs/health.txt");
	
	@GetMapping(value = "/service")
	public ResponseEntity<Object> writeToFile() throws JsonGenerationException, JsonMappingException, IOException {
		ResponseEntity<Object> result = restTemplate.exchange("http://localhost:8080/actuator/health", HttpMethod.GET,
				new HttpEntity(new HttpHeaders()), Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		FileWriter fileWriter = new FileWriter(logFile, true);;
		objectMapper.writeValue(fileWriter, result);
		return result;
	}
}