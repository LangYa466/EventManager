package cn.langya;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LangYa
 * @since 2024/10/9 00:38
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface EventTarget {
    int priority() default 0;
}
