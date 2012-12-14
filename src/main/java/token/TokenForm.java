package token;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@FacesComponent("sample.TokenForm")
public class TokenForm extends HtmlForm {
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		
		// トークンが未設定であった場合は、hidden要素は追加しない
		if (session != null) {
			String token = (String) session.getAttribute(TokenInput.TOKEN_NAME);
			if (token != null) {
				TokenInput tokenInput =  new TokenInput();
				tokenInput.setId(getClientId() + "_Token");
			
				// Formの子要素として、トークンを持つhidden要素を追加
				getChildren().add(tokenInput);
			}
		}	
		
		super.encodeBegin(context);
	}

}
