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
	 * Nonbinding��ݒ肵�Ȃ��ƁA@CheckToken(errorPage="error.xhtml")��
	 * �悤�Ƀp�����[�^��ݒ肷��ƁA�C���^�[�Z�v�g����Ȃ��Ȃ�B
	 * �v�f�̗L�����܂߂Č��i�Ƀo�C���h����Ă��邽�߁B
	 * 
	 * @return �g�[�N���`�F�b�N�G���[���̑J�ڐ�y�[�W
	 */
	@Nonbinding String errorPage() default "";
}
