package com.ituac.utils.spring;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * desp: Spring ApplicationContext tool
 * @author itauc
 */


@Component
public class SpringUtils implements ApplicationContextAware{

    private static ApplicationContext app;
	
	@Override
	public void setApplicationContext(ApplicationContext cxt) throws BeansException {
		this.app = cxt;
	}
	
	public static <T> T getBean(String beanName) {
        if(app.containsBean(beanName)){
            return (T) app.getBean(beanName);
        }else{
            return null;
        }
    }
	
	public static <T> Map<String, T> getBeansOfType(Class<T> baseType){
        return app.getBeansOfType(baseType);
    }

	
}
