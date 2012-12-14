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
		// ���݂̃R���e�L�X�g�̃Z�b�V�������擾
		HttpSession session = getCurrentSession();
		if (session == null) {
			return false;
		}
		
		// isTokenValid()����s�Ŏ��s�����ꍇ�A�{���͂P�̃g�[�N����
		// 1��̔���ɂ̂ݗL�������A�X���b�h�R���e�L�X�g�ɂ���ẮA
		// �����̃��N�G�X�g��������Ă��܂��\��������B
		// ���̂��߁A�Z�b�V���������b�N�I�u�W�F�N�g�Ƃ��A����Z�b�V�����ɂ��
		// �������s���N�G�X�g�𒲒₷��B
		synchronized (session) {
			// �Z�b�V�����ɕۑ����ꂽ�g�[�N�����擾
			String saved = (String) session.getAttribute(TokenInput.TOKEN_NAME);
		
			if (saved == null) {
				return false;
			}
		
			// �`�F�b�N�����̗L���Ɋւ�炸�A�g�[�N�������Z�b�g
			session.removeAttribute(TokenInput.TOKEN_NAME);
		
			// ���N�G�X�g�X�R�[�v���瑗�M���ꂽ�g�[�N�����擾
			String token = getRequestScopeToken();	
		
			if (token == null) {
				return false;
			}
		
			return saved.equals(token);
		}
	}
	
	private String generateToken() {
		// TODO �����������@�͂��������l����K�v����
		// �ȉ��̃R�[�h��http://www.javapractices.com/topic/TopicAction.do?Id=56�̈��p
		// CSRF�΍��JSF��VIEWSTATE�ɔC���A���̃g�[�N���͂����܂�2�x�����p�Ȃ̂ŁA
		// System.nanoTime()�Ȃǂ̌��ݎ��������̂܂܃g�[�N���ɂ��Ă����������B
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
