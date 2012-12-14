package token;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpSession;

@FacesComponent("sample.CSRFTokenInput")
public class TokenInput extends UIComponentBase {
	
	public static final String TOKEN_NAME = "DoubleClickPreventToken";
	
	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
		
		String token = (String) session.getAttribute(TokenInput.TOKEN_NAME);
		
		ResponseWriter responseWriter = context.getResponseWriter();
		responseWriter.startElement("input", null);
		responseWriter.writeAttribute("type", "hidden", null);
		responseWriter.writeAttribute("name", TOKEN_NAME, "cliendId");
		responseWriter.writeAttribute("value", token, "CSRFTOKEN_NAME");
		responseWriter.endElement("input");
	}

	@Override
	public String getFamily() {
		return null;
	}
}
