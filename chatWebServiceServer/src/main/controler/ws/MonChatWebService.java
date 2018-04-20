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

	// Methode test pour verifier que ça fonctionne
	// http://localhost:8080/chatWebServiceServer/rest/MonChatWebService/helloWorld
	@Path("/helloWorld")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public static String hello() {
		System.out.println("HellowWorld!!");
		return "HelloWorld";
	}

	// Reçoit un Json et demande à DbUtils d'inscrire le message en BDD
	// http://localhost:8080/chatWebServiceServer/rest/MonChatWebService/sendMessage
	// {"content": "Hello WOrld", "createdAt": 2018/04/20, "user":
	// {"name":"Vikoh","isConnected":false}}
	@Path("/sendMessage")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public static Response sendMessage(String messageBeanJson) throws Exception {

		// On parse en Java le message reçu(Json)
		MessageBean messageToSend = GSON.fromJson(messageBeanJson, MessageBean.class);
		// On demande au modèle d’ajouter le message en BD
		DbUtils.addMessage(messageToSend);
		return Response.status(200).build();
	}

	// Appelle la méthode getMessages de DbUtils et retourne la réponse sous forme
	// de Json
	// http://localhost:8080/chatWebServiceServer/rest/MonChatWebService/getMessages
	@Path("/getMessages")
	@GET
	public static Response getMessages() throws Exception {

		// Demander au modèle l’ArrayList des messages

		ArrayList<MessageBean> allMessages = DbUtils.getMessages();
		// On parse en Json
		// On retourne la réponse contenant l’ArrayList en Json(statut 200)
		return Response.status(200).entity(GSON.toJson(allMessages)).build();
	}

	// Reçoit un Json et demande à DbUtils d'inscrire le user en BDD
	// http://localhost:8080/chatWebServiceServer/rest/MonChatWebService/addUser
	// {"name": "Vikoh"}
	@Path("/addUser")
	@POST
	public static Response addUser(String userBeanJson) throws Exception {

		// On parse en Java l’user reçu(Json)
		UserBean userToAdd = GSON.fromJson(userBeanJson, UserBean.class);
		// On demande au modèle d’ajouter l’user en BD
		DbUtils.addUser(userToAdd);
		// On retourne la réponse en statut 200
		return Response.status(200).build();

	}

	// Appelle la méthode getUsers de DbUtils et retourne la réponse sous forme
	// de Json
	// http://localhost:8080/chatWebServiceServer/rest/MonChatWebService/getUsers
	@Path("/getUsers")
	@GET
	public static Response getUsers() throws Exception {

		// Demander au modèle l’ArrayList des users
		ArrayList<UserBean> allUsers = DbUtils.getUsers();
		// On parse en Json

		// On retourne la réponse contenant l’ArrayList en Json(statut 200)
		return Response.status(200).entity(GSON.toJson(allUsers)).build();
	}
}
