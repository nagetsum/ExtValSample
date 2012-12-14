package token;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

@Documented
@InterceptorBinding
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface CheckToken {
	/**
	 * Nonbindingを設定しないと、@CheckToken(errorPage="error.xhtml")の
	 * ようにパラメータを設定すると、インターセプトされなくなる。
	 * 要素の有無を含めて厳格にバインドされているため。
	 * 
	 * @return トークンチェックエラー時の遷移先ページ
	 */
	@Nonbinding String errorPage() default "";
}
