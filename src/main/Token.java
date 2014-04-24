package main;

import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.Random;

public class Token {
	/**
	 * Generates or retreives tokens for preventing CSRF attacks
	 *
	 * @param session HttpSession object, to be retreived from request.getSession()
	 * @return 22 character long String
	 */
	public static String getToken(HttpSession session) {
		String token = (String) session.getAttribute("token");

		if (token == null) {
			byte[] randomBytes = new byte[16];
			new Random().nextBytes(randomBytes);
			token = Base64.getEncoder().encodeToString(randomBytes).substring(0, 19);
			session.setAttribute("token", token);
		}

		return token;
	}
}
