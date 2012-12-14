package token;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.http.HttpSession;
import javax.faces.context.FacesContext;
import javax.inject.Singleton;

@Singleton
public class Token {
	
	public void saveToken() {
		HttpSession session  = getCurrentSession();
		session.setAttribute(TokenInput.TOKEN_NAME, generateToken());
	}
	
	public boolean isTokenValid() {
		// 現在のコンテキストのセッションを取得
		HttpSession session = getCurrentSession();
		if (session == null) {
			return false;
		}
		
		// isTokenValid()を並行で実行した場合、本来は１つのトークンは
		// 1回の判定にのみ有効だが、スレッドコンテキストによっては、
		// 複数のリクエストが許可されてしまう可能性がある。
		// そのため、セッションをロックオブジェクトとし、同一セッションによる
		// 複数並行リクエストを調停する。
		synchronized (session) {
			// セッションに保存されたトークンを取得
			String saved = (String) session.getAttribute(TokenInput.TOKEN_NAME);
		
			if (saved == null) {
				return false;
			}
		
			// チェック成功の有無に関わらず、トークンをリセット
			session.removeAttribute(TokenInput.TOKEN_NAME);
		
			// リクエストスコープから送信されたトークンを取得
			String token = getRequestScopeToken();	
		
			if (token == null) {
				return false;
			}
		
			return saved.equals(token);
		}
	}
	
	private String generateToken() {
		// TODO 乱数生成方法はもう少し考える必要あり
		// 以下のコードはhttp://www.javapractices.com/topic/TopicAction.do?Id=56の引用
		// CSRF対策はJSFのVIEWSTATEに任せ、このトークンはあくまで2度押し用なので、
		// System.nanoTime()などの現在時刻をそのままトークンにしてもいいかも。
		try {
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

		    //generate a random number
		    String randomNum = new Integer( prng.nextInt() ).toString();

		    //get its digest
		    MessageDigest sha = MessageDigest.getInstance("SHA-1");
		    byte[] result =  sha.digest(randomNum.getBytes());
		    
		    return toHex(result);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
		
    private String toHex(byte[] buffer) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);

        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
        }

        return sb.toString();
    }
	
	private HttpSession getCurrentSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
	
	private String getRequestScopeToken() {		
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(TokenInput.TOKEN_NAME);
	}
	

}
