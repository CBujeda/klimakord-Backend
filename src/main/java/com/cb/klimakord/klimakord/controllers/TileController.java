package com.cb.klimakord.klimakord.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.awt.image.BufferedImage;
import com.cb.klimakord.klimakord.apiExGetData.Request;

@Controller
public class TileController {
@RequestMapping(value = "tile/{x}/{y}/{z}/{a}/key=/{key}", method = RequestMethod.GET, produces = "image/jpg")
public @ResponseBody byte[] getFile(@PathVariable("x") String x
							,@PathVariable("y") String y
							,@PathVariable("z") String z
							,@PathVariable("a") String a
							,@PathVariable("key") String key)  {
	
	if(StartController.keyValid(key)) {
		 try {
		        BufferedImage img = Request.getTile(x,y,z,a);
		        // Create a byte array output stream.
		        ByteArrayOutputStream bao = new ByteArrayOutputStream();
		        // Write to output stream
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
