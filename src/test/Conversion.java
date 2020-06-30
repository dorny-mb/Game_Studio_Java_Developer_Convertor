package test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@Path("") 
public class Conversion {
	
	
	@GET
	@Path("/ktoc/{kelvinValue}")
	@Produces(MediaType.TEXT_HTML)
	public String ktoc(@PathParam("kelvinValue") String kelvinValue)
	{
		float kelvin = Float.parseFloat(kelvinValue);
		float celsius = kelvin - 273.15F;
		return "The celcius value is  [" + celsius + "]";
		
		
	}
	
	@GET
	@Path("/ctok/{celsiusValue}")
	@Produces(MediaType.TEXT_HTML)
	public String ctok(@PathParam("celsiusValue") String celsiusValue)
	{
		float celsius = Float.parseFloat(celsiusValue);
		float kelvin = celsius + 273.15F;
		return "The celcius value is  [" + kelvin + "]";
	}
	
	@GET
	@Path("/mtok/{milesValue}")
	@Produces(MediaType.TEXT_HTML)
	public String mtok(@PathParam("milesValue") String milesValue)
	{
		double distanceInMiles = Double.parseDouble(milesValue);
		return "amount in kilometers is  [" + distanceInMiles / 1000 + "]";
	}
	
	@GET
	@Path("/ktom/{kmValue}")
	@Produces(MediaType.TEXT_HTML)
	public String ktom(@PathParam("kmValue") String kmValue)
	{
		double distanceInKm = Double.parseDouble(kmValue);
		return "amount in miles is  [" + distanceInKm * 1000 + "]";
	}
	
	
	
	
	public void doFilter(ServletRequest request, ServletResponse response,
	        FilterChain p_filterChain) throws IOException, ServletException {


	    long startTime = System.currentTimeMillis();
	    String url = "unknown"; 
	    if (request instanceof HttpServletRequest) {
	         url = ((HttpServletRequest)request).getRequestURL().toString();
	         String queryString = ((HttpServletRequest)request).getQueryString();
	         if(queryString != null)
	             url += "?" + queryString;
	    }


	    p_filterChain.doFilter(request, response);

	    long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;

	    
	    try {  
		      FileWriter myWriter = new FileWriter("log.txt");
		      myWriter.write(url + elapsedTime);
		      myWriter.close();
		} catch (IOException e) {
		      
		} 
	          
	}
}
