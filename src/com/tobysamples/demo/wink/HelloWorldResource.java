package com.tobysamples.demo.wink;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import lotus.domino.Document;

import lotus.domino.NotesException;
import lotus.domino.Session;
import lotus.domino.Database;

import com.ibm.commons.util.io.json.JsonJavaObject;
import com.ibm.domino.osgi.core.context.ContextInfo;

@Path("/helloworld")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    @GET
    public MyBean getMessage() throws NotesException {
    	Session s = SudoUtils.getSessionAs("CN=Mark Roden/O=PSCDM");
    	Database db = s.getDatabase("", "mydealiq\\dealsscm.nsf");
    	Document doc = db.createDocument();
    	doc.replaceItemValue("Test", "Toby");
    	doc.save();
    	MyBean mybean = new MyBean();
    	mybean.setBirthdate(new Date());
    	mybean.setEmail("test@test.com");
    	mybean.setFname(s.getEffectiveUserName());
    	mybean.setLname("User");
    	mybean.setPhone("123.456.7841");
    	return mybean;
    }
    
    /*public Response getMessage() throws NotesException {
    	JsonJavaObject jjo = new JsonJavaObject();
    	Session session = ContextInfo.getUserSession();
    	String message = "Hello World";
    	if (session != null) {
    		message += " " + session.getCommonUserName();
    	}
    	jjo.put("message", message);
    	ResponseBuilder builder = Response.ok(jjo.toString(), MediaType.APPLICATION_JSON);
    	return builder.build();
    }*/
    
    
    @Path("/toby")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyBean postMessage(MyBean mybean) {
    	mybean.setFname("Toby");
        return mybean;
    }
    
}