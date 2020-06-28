package com.project.custom;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义 Annotation ：@interface
 */
@Retention(RetentionPolicy.RUNTIME) //运行时生效
public @interface NetAnnotation {
    String title() default "NetAnnotation";
    String content() default "CustomAnnotation";
    boolean isNeed() default false;
    Class<?> clazz();
}
