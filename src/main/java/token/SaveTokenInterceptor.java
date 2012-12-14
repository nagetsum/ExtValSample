package token;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * トークンを保存するインターセプタ。
 * インターセプト対象のメソッドを実行後、
 * 例外発生の有無に関わらずトークンを保存する。
 * @author norito agetsuma
 */
@SaveToken
@Interceptor
public class SaveTokenInterceptor {
	
	/** トークンを管理するシングルトン */
	@Inject Token token;
	
	@AroundInvoke
	public Object saveToken(InvocationContext ic) throws Exception {
		try {
			return ic.proceed();
		} finally {
			// トークンを新たに生成し、セッションに保存する。
			// メソッドを実行した後にトークンを設定しているのは、
			// @SaveTokenが付けられたメソッドが連続したときに、
			// isTokenValid()を使ってアクションで検証する前に、
			// トークンが変更されるのを防ぐため。
			token.saveToken();
			System.out.println("ObjectId " + this.toString());
		}

	}
}
