package token;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * �g�[�N����ۑ�����C���^�[�Z�v�^�B
 * �C���^�[�Z�v�g�Ώۂ̃��\�b�h�����s��A
 * ��O�����̗L���Ɋւ�炸�g�[�N����ۑ�����B
 * @author norito agetsuma
 */
@SaveToken
@Interceptor
public class SaveTokenInterceptor {
	
	/** �g�[�N�����Ǘ�����V���O���g�� */
	@Inject Token token;
	
	@AroundInvoke
	public Object saveToken(InvocationContext ic) throws Exception {
		try {
			return ic.proceed();
		} finally {
			// �g�[�N����V���ɐ������A�Z�b�V�����ɕۑ�����B
			// ���\�b�h�����s������Ƀg�[�N����ݒ肵�Ă���̂́A
			// @SaveToken���t����ꂽ���\�b�h���A�������Ƃ��ɁA
			// isTokenValid()���g���ăA�N�V�����Ō��؂���O�ɁA
			// �g�[�N�����ύX�����̂�h�����߁B
			token.saveToken();
			System.out.println("ObjectId " + this.toString());
		}

	}
}
