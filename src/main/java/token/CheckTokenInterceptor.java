package token;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@CheckToken
@Interceptor
public class CheckTokenInterceptor {

	private static final String DEFAULT_ERROR_PAGE = "";
	
	/** �g�[�N�����Ǘ�����V���O���g�� */
	@Inject Token token;

	@AroundInvoke
	public Object checkToken(InvocationContext ic) throws Exception {
		if (token.isTokenValid()) {
			// �g�[�N�����������ꍇ�́A�A�N�V���������s
			return ic.proceed();
		}
		
		CheckToken checkToken = ic.getMethod().getAnnotation(CheckToken.class);
		String errorPage = checkToken.errorPage();
		
		// @CheckToken��errorPage�v�f���f�t�H���g�̏ꍇ�́A���݂̉�ʂ��ĕ\��
		if (DEFAULT_ERROR_PAGE.equals(errorPage)) {
			return null;
		}
		
		// �f�t�H���g�ł͂Ȃ��ꍇ�́A�w�肳�ꂽ�y�[�W�ɑJ��
		return errorPage;
	}

}
