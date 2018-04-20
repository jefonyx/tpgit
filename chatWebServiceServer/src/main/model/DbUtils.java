package main.model;

import java.util.ArrayList;

import dao.MessageBean;
import dao.UserBean;

public class DbUtils {
	// -----------------------------------------------------------------------------------
	// Static variables
	// -----------------------------------------------------------------------------------
	private static ArrayList<MessageBean> messageList = new ArrayList<>();
	private static ArrayList<UserBean> userList = new ArrayList<>();

	public static void addMessage(MessageBean messageToAdd) throws Exception {
		messageList.add(messageToAdd);
	}

	public static ArrayList<MessageBean> getMessages() throws Exception {
		return messageList;
	}

	public static void addUser(UserBean userToAdd) throws Exception {
		userList.add(userToAdd);
	}

	public static ArrayList<UserBean> getUsers() throws Exception {
		return userList;
	}

}
