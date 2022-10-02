package com.cb.klimakord.klimakord.controllers;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.cb.klimakord.klimakord.apiExGetData.Request;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de la api rest springboot
 */
@RestController
@RequestMapping("/api/")
public class StartController {
	
	/*
	 * Pre:---
	 * Post: creación y devolución de keys
	 */
	public static boolean keyValid(String key) {
		ArrayList<String> array = new ArrayList<String>();
		array.add("cb-default-key");
		boolean very = false;
		for(int i = 0; i < array.size();i++) {
			if(array.get(i).equalsIgnoreCase(key)) {
				very = true;
			}
		}
		System.out.println("Peticion con key: " + key);
		return very;
	}
	
	/*
	 * Pre:---
	 * Post: Programa java el cual devuelve la altura del
	 * 		 mar dependiendo de diferentes variables
	 */
	@CrossOrigin
	@GetMapping("time/ano=/{ano}/vehiculos=/{vehi}/industrias=/{indus}/agicolas=/{agric}/viviendas=/{viv}/otros=/{otr}/key=/{key}")
	public String inicio(@PathVariable("ano") String ano
			,@PathVariable("vehi") String vehi
			,@PathVariable("indus") String indus
			,@PathVariable("agric") String agric
			,@PathVariable("viv") String viv
			,@PathVariable("otr") String otr
			,@PathVariable("key") String key) {
		String data = "0";
		if(keyValid(key)) {
			try {
				final double bVehi = 0.13;
				final double bIndus = 0.17;
				final double bAgric = 0.05;
				final double bViv = 0.56;
				final double bOtr = 0.09;
				long dAno = Integer.parseInt(ano);
				long dVehi = Integer.parseInt(vehi);
				long dIndus = Integer.parseInt(indus);
				long dAgric = Integer.parseInt(agric);
				long dViv = Integer.parseInt(viv);
				long dOtr = Integer.parseInt(otr);
				final double total = (dVehi/100*bVehi)+(dIndus/100*bIndus)+
									 (dAgric/100*bAgric)+(dViv/100*bViv)+(dOtr/100*bOtr);
				LocalDate current_date = LocalDate.now();
				long current_Year = current_date.getYear();
	            long real_years = dAno - current_Year;       
	            double msubidaY = 0.0039*total;
	            double metros = msubidaY * real_years;
	            if(metros < 0) {metros = 0;}
	            data = "{\"altmeters\": "+metros + "}";
			} catch(Exception e) {
				data ="err";
				System.out.println(e);
			}
		}
		return data; // envio de la array
	}
	
	/*
	 * Pre:---
	 * Post: Controller el cual devuelve una temperatura
	 */
	@CrossOrigin
	@GetMapping("weather/{lat}/{lon}/key=/{key}")
	public String tempo(@PathVariable("lat") String lat
					   ,@PathVariable("lon") String lon
					   ,@PathVariable("key") String key) {
		String vuelta = "-1";
		if(keyValid(key)) {
			Request r = new Request();
			vuelta = r.getDataWeather(lat,lon);
		}else {
			vuelta ="{ERROR - NO KEY}";
		}
		return vuelta;
	}
}
