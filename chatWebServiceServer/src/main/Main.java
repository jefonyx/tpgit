package main;

import com.google.gson.Gson;

import dao.MessageBean;
import dao.UserBean;

public class Main {
	public static void main(String[] args) {
		try {
			UserBean user = new UserBean("Vikoh", true);
			MessageBean message = new MessageBean("tachatte", 20180404, user);
			Gson gson = new Gson();

			System.out.println(gson.toJson(message));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
