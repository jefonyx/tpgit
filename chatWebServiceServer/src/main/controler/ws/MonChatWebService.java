package main.controler.ws;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import dao.MessageBean;
import dao.UserBean;
import main.model.DbUtils;

@Path("/MonChatWebService")
public class MonChatWebService {
	final static Gson GSON = new Gson();

	// Methode test pour verifier que �a fonctionne
	// http://localhost:8080/chatWebServiceServer/rest/MonChatWebService/helloWorld
	@Path("/helloWorld")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public static String hello() {
		System.out.println("HellowWorld!!");
		return "HelloWorld";
	}

	// Re�oit un Json et demande � DbUtils d'inscrire le message en BDD
	// http://localhost:8080/chatWebServiceServer/rest/MonChatWebService/sendMessage
	// {"content": "Hello WOrld", "createdAt": 2018/04/20, "user":
	// {"name":"Vikoh","isConnected":false}}
	@Path("/sendMessage")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public static Response sendMessage(String messageBeanJson) throws Exception {

		// On parse en Java le message re�u(Json)
		MessageBean messageToSend = GSON.fromJson(messageBeanJson, MessageBean.class);
		// On demande au mod�le d�ajouter le message en BD
		DbUtils.addMessage(messageToSend);
		return Response.status(200).build();
	}

	// Appelle la m�thode getMessages de DbUtils et retourne la r�ponse sous forme
	// de Json
	// http://localhost:8080/chatWebServiceServer/rest/MonChatWebService/getMessages
	@Path("/getMessages")
	@GET
	public static Response getMessages() throws Exception {

		// Demander au mod�le l�ArrayList des messages

		ArrayList<MessageBean> allMessages = DbUtils.getMessages();
		// On parse en Json
		// On retourne la r�ponse contenant l�ArrayList en Json(statut 200)
		return Response.status(200).entity(GSON.toJson(allMessages)).build();
	}

	// Re�oit un Json et demande � DbUtils d'inscrire le user en BDD
	// http://localhost:8080/chatWebServiceServer/rest/MonChatWebService/addUser
	// {"name": "Vikoh"}
	@Path("/addUser")
	@POST
	public static Response addUser(String userBeanJson) throws Exception {

		// On parse en Java l�user re�u(Json)
		UserBean userToAdd = GSON.fromJson(userBeanJson, UserBean.class);
		// On demande au mod�le d�ajouter l�user en BD
		DbUtils.addUser(userToAdd);
		// On retourne la r�ponse en statut 200
		return Response.status(200).build();

	}

	// Appelle la m�thode getUsers de DbUtils et retourne la r�ponse sous forme
	// de Json
	// http://localhost:8080/chatWebServiceServer/rest/MonChatWebService/getUsers
	@Path("/getUsers")
	@GET
	public static Response getUsers() throws Exception {

		// Demander au mod�le l�ArrayList des users
		ArrayList<UserBean> allUsers = DbUtils.getUsers();
		// On parse en Json

		// On retourne la r�ponse contenant l�ArrayList en Json(statut 200)
		return Response.status(200).entity(GSON.toJson(allUsers)).build();
	}
}
