package com.doctortech.fhq.utils.beanvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.apache.commons.lang3.StringUtils;

import com.doctortech.fhq.utils.DateUtil;

/**
 * 日期校验，不做required校验，需要的结合 {@link @NotBlank}}
 * @author jiaxm
 *
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DateField.DateValidator.class })
public @interface DateField {
	
	String message() default "日期格式不正确";

	/**
	 * 字符串日期需要转换的格式
	 * @author jiaxm
	 * @date 2018年6月22日
	 * @return
	 */
	String pattern() default "yyyy-MM-dd";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	public class DateValidator implements ConstraintValidator<DateField, String> {
		String pattern;
		@Override
		public void initialize(DateField constraintAnnotation) {
			pattern= constraintAnnotation.pattern();
		}

		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			if (StringUtils.isBlank(value)) {
				return true ;
			}
			Date date= DateUtil.getDate(value, new SimpleDateFormat(pattern));
			if (date!=null) {
				return true;
			} else {
				return false;
			}
		}

	}
}
