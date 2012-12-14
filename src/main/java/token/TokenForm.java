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
		
		// �g�[�N�������ݒ�ł������ꍇ�́Ahidden�v�f�͒ǉ����Ȃ�
		if (session != null) {
			String token = (String) session.getAttribute(TokenInput.TOKEN_NAME);
			if (token != null) {
				TokenInput tokenInput =  new TokenInput();
				tokenInput.setId(getClientId() + "_Token");
			
				// Form�̎q�v�f�Ƃ��āA�g�[�N��������hidden�v�f��ǉ�
				getChildren().add(tokenInput);
			}
		}	
		
		super.encodeBegin(context);
	}

}
