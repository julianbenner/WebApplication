package main;

public class ChangeUserResult {
	private final boolean oldPasswordWrong;
	private final boolean newPasswordsDontMatch;

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
