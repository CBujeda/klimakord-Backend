package com.cb.klimakord.klimakord.apiExGetData;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.apache.tomcat.util.json.JSONParser;

import com.cb.klimakord.klimakord.procesor.TransparentTransformation;

public class Request {

	/**
	 * Pre:
	 * Post: Api get wheather data
	 */
	public static String getDataWeather(String mlat,String mlon) {
		String apiKey = "fa73231ed639de04e5b94302afa495cd";
		String api = "https://api.openweathermap.org/data/2.5/weather?lat="+mlat+"&lon="+mlon+"&appid="+ apiKey + "";
		URL url = null;
		try {
			url = new URL(api);
		} catch (MalformedURLException e1) {e1.printStackTrace();}
		HttpURLConnection conn;
		int responsecode = -1;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			responsecode = conn.getResponseCode();
		} catch (ProtocolException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}
		String inline = "";
		if (responsecode != 200) {
		    throw new RuntimeException("HttpResponseCode: " + responsecode);
		} else {
		    
		    Scanner scanner;
			try {
				scanner = new Scanner(url.openStream());
			    while (scanner.hasNext()) {
			       inline += scanner.nextLine();
			    }
			    scanner.close();
			} catch (IOException e) {e.printStackTrace();} 
		}
		System.out.println(inline);
		return inline;
	}
	
	public static BufferedImage getTile(String x,String y, String z, String level) {

		
		String apiDefault = "https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/"+z+"/"+y+"/"+x;
		String apiOcean = "https://www.floodmap.net/getFMTile.ashx?x="+x+"&y="+y+"&z="+z+"&e="+level;
		return mapFusion(apiDefault,apiOcean);
		
		
	}
	
	public static BufferedImage mapFusion(String link,String link2) {
		
		BufferedImage combined = new BufferedImage (256,256,BufferedImage.TYPE_INT_ARGB);
		try {
		URL url = new URL(link);
		InputStream in1 = new BufferedInputStream(url.openStream());
		URL url2 = new URL(link2);
		InputStream in2 = new BufferedInputStream(url2.openStream());
		// base path of the images // load source images 
		BufferedImage image = ImageIO.read(in1); 
		BufferedImage overlay = ImageIO.read(in2); 
		//TransparentTransformation transparent = new TransparentTransformation(overlay);
		//overlay = transparent.makeColorTransparent(new Color(0, 0, 255));
		//ImageIO.write(image, "PNG", new File("./data/image.png"));
		//ImageIO.write(overlay, "PNG", new File("./data/overlay.png"));
		
		// create the new image, canvas size is the max. of both image sizes 
		int w = Math.max(image.getWidth(), overlay.getWidth()); 
		int h = Math.max(image.getHeight(), overlay.getHeight()); 
		combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB); 
		// paint both images, preserving the alpha channels 
		Graphics g = combined.getGraphics(); 
		g.drawImage(image, 0, 0, null);
		g.drawImage(overlay, 0, 0, null); 
		// Save as new image 
		
		//ImageIO.write(combined, "PNG", new File("./data/combined.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return combined;
	}
	
	
}
