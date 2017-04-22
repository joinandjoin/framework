package org.spring.boot.hxframework.autoconfigure.beanutils;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huaixa.framework.common.spring.SpringBeanUtils;

@Configuration
@ConditionalOnClass(ApplicationContext.class)
public class SpringBeanUtilsAutoConfig {
	@Bean
	@ConditionalOnMissingBean
	public SpringBeanUtils setSpringBeanUtils(ApplicationContext context){
		SpringBeanUtils beanUtils = new SpringBeanUtils();
		beanUtils.setApplicationContext(context);
		return beanUtils;
	}
}