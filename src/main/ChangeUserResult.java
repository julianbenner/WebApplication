package main;

/**
 * Created by Julian on 24/02/14.
 */
public class ChangeUserResult {
	private boolean oldPasswordWrong;
	private boolean newPasswordsDontMatch;

	public ChangeUserResult(boolean oldPasswordWrong, boolean newPasswordsDontMatch) {
		this.oldPasswordWrong = oldPasswordWrong;
		this.newPasswordsDontMatch = newPasswordsDontMatch;
	}

	public boolean isOldPasswordWrong() {
		return oldPasswordWrong;
	}

	public boolean isNewPasswordsDontMatch() {
		return newPasswordsDontMatch;
	}
}
