package main;

import javax.servlet.http.HttpSession;
import java.util.Random;

public class Token {
	/**
	 * Generates or retreives tokens for preventing CSRF attacks
	 *
	 * @param session HttpSession object, to be retreived from request.getSession()
	 * @return 64 character long String
	 */
	public static String getToken(HttpSession session) {
		String token = (String) session.getAttribute("token");

		if (token == null) {
			byte[] randomBytes = new byte[16];
			new Random().nextBytes(randomBytes);

			try {
				StringBuilder sb = new StringBuilder(2 * randomBytes.length);
				for (byte b : randomBytes) {
					sb.append(String.format("%02x", b & 0xff));
				}

				token = sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("token", token);
		}

		return token;
	}
}
