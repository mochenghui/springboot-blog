package cn.mchpro.blog.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
/**
 * ConstraintViolationException 处理器
 * @author mch
 *
 */
@Validated
public class ConstraintViolationExceptionHandler {
	
	/**
	 * 获取批量异常信息
	 * @param e
	 * @return
	 */
	public static String getMessage(ConstraintViolationException e) {
		List<String> msgList = new ArrayList<>();
		for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
			msgList.add(constraintViolation.getMessage());
        }
		String messages = StringUtils.join(msgList.toArray(), ";");
		return messages;
	}
	
}
