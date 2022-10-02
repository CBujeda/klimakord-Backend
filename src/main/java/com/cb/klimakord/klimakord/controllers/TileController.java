package com.cb.klimakord.klimakord.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.awt.image.BufferedImage;
import com.cb.klimakord.klimakord.apiExGetData.Request;
import com.cb.klimakord.klimakord.services.CacheService;

@Controller
@RequestMapping("/api/")
public class TileController {

	private final CacheService cache;
		
	public TileController(CacheService cache) {
		this.cache = cache;
	}
	@CrossOrigin
	@RequestMapping(value = "tile/{x}/{y}/{z}/{a}/key=/{key}", method = RequestMethod.GET, produces = "image/jpg")
	public @ResponseBody byte[] getFile(@PathVariable("x") String x
							,@PathVariable("y") String y
							,@PathVariable("z") String z
							,@PathVariable("a") String a
							,@PathVariable("key") String key)  {
		int dz = 0;
		try {
			 dz = Integer.parseInt(z);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		if(dz > 15) {
			return new byte[1];
		}
		
		if(cache.existe(x, y, z, a, key)) { // buscar los en java
			return (byte[]) cache.getCahe(x, y, z, a, key);
		}else {
			byte[] rvalid = cargador(x,y,z,a,key);
			cache.saveCache(x, y, z, a, key, rvalid);
			return rvalid;
		}
		
		
	
	}
	
	
	private byte[] cargador(String x, String y, String z,String a, String key ) {
		if(StartController.keyValid(key)) {
			 try {
				 	Request r = new Request();
			        BufferedImage img = r.getTile(x,y,z,a);
			        ByteArrayOutputStream bao = new ByteArrayOutputStream();
			        ImageIO.write(img, "png", bao);
			        return bao.toByteArray();
			    } catch (IOException e) {
			        System.out.println(e);
			        return new byte[1];
			    }
		}else {
			 return new byte[1];
		}
	}
}
