package view;

import javax.enterprise.inject.Model;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Model
public class ChangePasswordFormStyle {
	
	public ChangePasswordFormStyle() {
		this.currentPasswordStyle = BootStrapFormStyle.DEFAULT.getStyleClass();
		this.newPasswordStyle = BootStrapFormStyle.DEFAULT.getStyleClass();
		this.newPasswordRetypeStyle = BootStrapFormStyle.DEFAULT.getStyleClass();
	}
	
	private String currentPasswordStyle;
	private String newPasswordStyle;
	private String newPasswordRetypeStyle;
	
	@Inject
	private FacesContext facesContext;
	
	public boolean isErrorMsgRendered (String componentId) {
		UIInput input = (UIInput)facesContext.getViewRoot().findComponent(componentId);
		return !input.isValid();
	}
	
	public String selectStyle(String componentId) {
		if (isErrorMsgRendered(componentId)) {
			return BootStrapFormStyle.ERROR.getStyleClass();
		}
		return BootStrapFormStyle.DEFAULT.getStyleClass();
	}

	public String getCurrentPasswordStyle() {
		return currentPasswordStyle;
	}

	public void setCurrentPasswordStyle(String currentPasswordStyle) {
		this.currentPasswordStyle = currentPasswordStyle;
	}

	public String getNewPasswordStyle() {
		return newPasswordStyle;
	}

	public void setNewPasswordStyle(String newPasswordStyle) {
		this.newPasswordStyle = newPasswordStyle;
	}

	public String getNewPasswordRetypeStyle() {
		return newPasswordRetypeStyle;
	}

	public void setNewPasswordRetypeStyle(String newPasswordRetypeStyle) {
		this.newPasswordRetypeStyle = newPasswordRetypeStyle;
	}
}
