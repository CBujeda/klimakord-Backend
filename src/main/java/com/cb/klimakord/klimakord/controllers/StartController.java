package com.cb.klimakord.klimakord.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de la api rest springboot
 */
@RestController
@RequestMapping("/api/rest/")
public class StartController {
	
	@GetMapping("subida/{key}")
	public List inicio(@PathVariable("key") String key) {
		System.out.println("Peticion con key: " + key);
		ArrayList<String> array = new ArrayList<String>();
		if(key.equalsIgnoreCase("capasblancasKey")) {
			
			array.add("Hola");
			array.add("Que ");
			array.add("tal?");
			
		}else {
			array.add("Error No key");
		}
		List data = array; 
		return data; // envio de la array
	}
}
