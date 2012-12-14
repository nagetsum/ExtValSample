package controller;

import javax.enterprise.inject.Model;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.apache.myfaces.extensions.validator.crossval.annotation.Equals;

import token.CheckToken;
import token.SaveToken;

@Model
public class UserManageController {
	@NotNull
	private String currentPassword;
	
	@NotNull
	@Equals(value="newPasswordRetype")
	private String newPassword;
	
	@NotNull
	private String newPasswordRetype;
	
	@Inject private Flash flash;
	
	@CheckToken(errorPage="error.xhtml")
	public String changePassword() throws InterruptedException {
		// @CheckTokenを使わずに判定する場合は以下のコードを使う。
		// トークンエラー時にページ遷移以外のロギング等の処理を行う場合は、
		// Tokenを@Injectで取得して、自分でisTokenValid()を実行。
//		if (!token.isTokenValid()) {
//			// 現在のページをもう一度表示
//			System.out.println("トークンチェック失敗");
//			return null;
//		}
		
		// TODO パスワード変更処理
		Thread.sleep(3000);
		System.out.println("処理が完了しました " + currentPassword);
		
		// リクエストスコープの場合は、リダイレクトによって消去されるので、
		// 次ページで必要な値はフラッシュスコープに格納する
		flash.put("currentPassword", currentPassword);
		
		// リダイレクトで遷移させる
		// 遷移後にF5を押されても、再ポストを防げる。
		return "result.xhtml?faces-redirect=true";
	}
	
	@SaveToken
	public String forward() {
		return "login.xhtml";
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
