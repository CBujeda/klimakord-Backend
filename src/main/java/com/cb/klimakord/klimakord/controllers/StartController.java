package com.cb.klimakord.klimakord.controllers;

import java.util.ArrayList;
import java.util.List;
import com.cb.klimakord.klimakord.apiExGetData.Request;

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
	
	
	public boolean keyValid(String key) {
		ArrayList<String> array = new ArrayList<String>();
		array.add("cb-clara-key");
		array.add("cb-blas-key");
		array.add("cb-elias-key");
		array.add("cb-raul-key");
		array.add("cb-almendivar-key");
		array.add("cb-silva-key");
		boolean very = false;
		for(int i = 0; i < array.size();i++) {
			if(array.get(i).equalsIgnoreCase(key)) {
				very = true;
			}
		}
		return very;
	}
	
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
	
	@GetMapping("weather/{lat}/{lon}/key=/{key}")
	public String tempo(@PathVariable("lat") String lat
					   ,@PathVariable("lon") String lon
					   ,@PathVariable("key") String key) {
		String vuelta = "-1";
		if(keyValid(key)) {
			vuelta = Request.getDataWeather(lat,lon);
		}else {
			vuelta ="{ERROR - NO KEY}";
		}
		
		return vuelta;
		
	}
	//@RequestMapping(value = "/image", method = RequestMethod.GET, produces = "image/jpg")
	@GetMapping("tileOcean/{lat}/{lon}/key=/{key}")
	public String tileOcean(@PathVariable("lat") String lat
					   ,@PathVariable("lon") String lon
					   ,@PathVariable("key") String key) {
		String vuelta = "-1";
		
		return vuelta;
		
	}
}
