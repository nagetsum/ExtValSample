package controller;

import javax.enterprise.inject.Model;
import javax.validation.constraints.NotNull;

import org.apache.myfaces.extensions.validator.crossval.annotation.Equals;

@Model
public class UserManageController {
	
	@NotNull
	private String currentPassword;
	
	@NotNull
	@Equals(value="newPasswordRetype")
	private String newPassword;
	
	@NotNull
	private String newPasswordRetype;
	
	public String changePassword() {
		// TODO パスワード変更処理
		return null;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordRetype() {
		return newPasswordRetype;
	}

	public void setNewPasswordRetype(String newPasswordRetype) {
		this.newPasswordRetype = newPasswordRetype;
	}
}
