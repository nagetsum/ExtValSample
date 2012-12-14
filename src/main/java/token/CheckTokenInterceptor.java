package token;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@CheckToken
@Interceptor
public class CheckTokenInterceptor {

	private static final String DEFAULT_ERROR_PAGE = "";
	
	/** トークンを管理するシングルトン */
	@Inject Token token;

	@AroundInvoke
	public Object checkToken(InvocationContext ic) throws Exception {
		if (token.isTokenValid()) {
			// トークンが正しい場合は、アクションを実行
			return ic.proceed();
		}
		
		CheckToken checkToken = ic.getMethod().getAnnotation(CheckToken.class);
		String errorPage = checkToken.errorPage();
		
		// @CheckTokenのerrorPage要素がデフォルトの場合は、現在の画面を再表示
		if (DEFAULT_ERROR_PAGE.equals(errorPage)) {
			return null;
		}
		
		// デフォルトではない場合は、指定されたページに遷移
		return errorPage;
	}

}
