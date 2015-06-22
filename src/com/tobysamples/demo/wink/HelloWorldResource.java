package com.tobysamples.demo.wink;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import lotus.domino.NotesException;
import lotus.domino.Session;

import com.ibm.commons.util.io.json.JsonJavaObject;
import com.ibm.domino.osgi.core.context.ContextInfo;

@Path("/helloworld")
public class HelloWorldResource {
    @GET
    public Response getMessage() throws NotesException {
    	JsonJavaObject jjo = new JsonJavaObject();
    	Session session = ContextInfo.getUserSession();
    	String message = "Hello World";
    	if (session != null) {
    		message += " " + session.getCommonUserName();
    	}
    	jjo.put("message", message);
    	ResponseBuilder builder = Response.ok(jjo.toString(), MediaType.APPLICATION_JSON);
    	return builder.build();
    }
    
    @POST
    @Path("/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postMessage(@PathParam("param") String msg, String body) {
    	String output = "POST: " + msg;
    	JsonJavaObject jjo = new JsonJavaObject();
    	jjo.put("ParamMessage", output);
    	jjo.put("jsonObject", body);
        return Response.ok(jjo.toString(), MediaType.APPLICATION_JSON).build();
    }
    
}