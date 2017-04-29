package com.huaxia.framework.common.spring;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import com.google.common.base.Preconditions;

/**
 * 
 * Title: framework.common SpringBeanUtils.java <br>
 * Description: spring工具类，提供获取bean和配置文件值的方法，注意改类要在spring上下文加载配置文件后立刻执行初始化，否则会出现错误。
 * 最好将@Component注解去除，在spring配置文件里context:property-placeholder的配置之后立刻使用
 * <bean class="com.joinandjoin.framework.common.spring.SpringBeanUtils"/>进行配置；关于spring注解扫描的配置
 * 应放在此配置之后
 * <br>
 * Date: 2016年9月12日 <br>
 * Copyright (c) 2016 Joinandjoin <br>
 * 
 * @author shilei
 */
public class SpringBeanUtils extends ApplicationObjectSupport implements EnvironmentAware {
	
    private static Environment environment;
    
    private static SpringBeanUtils that = null;

    public void init() throws Exception {
    	that = this;
        this.assertContextInjected();
    }

    @Override
    protected void initApplicationContext() throws BeansException {
    	// TODO Auto-generated method stub
    	try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
	public void setEnvironment(Environment environment) {
    	SpringBeanUtils.environment = environment;
	}
    
	public static String getProperty(String name){
		return environment.getProperty(name);
	}
	
	public static String getRequiredProperty(String name){
		return environment.getRequiredProperty(name);
	}
	
	public static String getProperty(String name, String defalutValue){
		return environment.getProperty(name,defalutValue);
	}

	public static <T> T getProperty(String name,Class<T> requiredType){
		return environment.getProperty(name,requiredType);
	}
	
	public static <T> T getProperty(String name,Class<T> requiredType, T defaultValue){
		return environment.getProperty(name,requiredType,defaultValue);
	}
	
	public static <T> T getRequiredProperty(String name,Class<T> requiredType){
		return environment.getRequiredProperty(name,requiredType);
	}

    /**
     * 检查ApplicationContext
     */
    private void assertContextInjected() {
        Preconditions.checkNotNull(getApplicationContext(),
                "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
    }

    public static ConfigurableApplicationContext getCurrentBeanFactory() {
        return (ConfigurableApplicationContext) that.getApplicationContext();
    }

    public static BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return (BeanDefinitionRegistry) getCurrentBeanFactory().getBeanFactory();
    }

    public static void registeFromCfgLocalString(String configLocationString) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(
                getBeanDefinitionRegistry());
        beanDefinitionReader.setResourceLoader(that.getApplicationContext());
        beanDefinitionReader
                .setEntityResolver(new ResourceEntityResolver(that.getApplicationContext()));
        try {
            String[] configLocations = configLocationString.split(";");
            for (int i = 0; i < configLocations.length; i++) {
                beanDefinitionReader.loadBeanDefinitions(
                        that.getApplicationContext().getResource(configLocations[i]));
            }
        } catch (BeansException e) {

        }
    }

    public static void registerBean(String beanName, BeanDefinition beanDefinition) {
        getBeanDefinitionRegistry().registerBeanDefinition(beanName, beanDefinition);
    }

    /**
     * 通过名称取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        that.assertContextInjected();
        Assert.hasText(name, "要获取的bean的名称不能为空！！");
        return (T) that.getApplicationContext().getBean(name);
    }

    /**
     * 通过类型取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        that.assertContextInjected();
        Assert.notNull(requiredType, "要获取的bean的类型不能为空！！");
        return that.getApplicationContext().getBean(requiredType);
    }

    /**
     * 获取spring上下文中配置的所有bean的名称
     * 
     * @return String[] spring上下文中配置的所有bean的名称
     */
    public static String[] getBeanDefNames() {
        return that.getApplicationContext().getBeanDefinitionNames();
    }

    /**
     * 通过消息key和默认消息值从资源文件中获取消息内容
     * 
     * @param code
     *            String 消息key
     * @param defaultMessage
     *            String 默认消息
     * @return String 消息内容，如果没有找到指定key的消息，则返回默认消息
     * @author shilei
     */
    public static String getMessage(String code, String defaultMessage) {
        return that.getMessageSourceAccessor().getMessage(code, defaultMessage);
    }

    /**
     * 通过消息key和默认消息值从资源文件中获取指定地区的消息内容
     * 
     * @param code
     *            String 消息key
     * @param defaultMessage
     *            String 默认消息
     * @param locale
     *            Locale 地区
     * @return String 消息内容，如果没有找到指定key的消息，则返回默认消息
     * @author shilei
     */
    public static String getMessage(String code, String defaultMessage, Locale locale) {
        return that.getMessageSourceAccessor().getMessage(code, defaultMessage, locale);
    }

    /**
     * 通过消息key，消息参数，和默认消息值从资源文件中获取指定的消息内容
     * 
     * @param code
     *            String 消息key
     * @param args
     *            Object[] 消息参数
     * @param defaultMessage
     *            String 默认消息
     * @return String 消息内容，如果没有找到指定key的消息，则返回默认消息
     * @author shilei
     */
    public static String getMessage(String code, Object args[], String defaultMessage) {
        return that.getMessageSourceAccessor().getMessage(code, args, defaultMessage);
    }

    /**
     * 通过消息key，消息参数，和默认消息值从资源文件中获取指定地区的消息内容
     * 
     * @param code
     *            String 消息key
     * @param args
     *            Object[] 消息参数
     * @param defaultMessage
     *            String 默认消息
     * @param locale
     *            Locale 地区
     * @return String 消息内容，如果没有找到指定key的消息，则返回默认消息
     * @author shilei
     */
    public static String getMessage(String code, Object args[], String defaultMessage,
            Locale locale) {
        return that.getMessageSourceAccessor().getMessage(code, args, defaultMessage, locale);
    }

    /**
     * 通过消息key从资源文件中获取指定的消息内容
     * 
     * @param code
     *            String 消息key
     * @return String 消息内容，如果没有找到指定key的消息，则报出NoSuchMessageException异常
     * @throws NoSuchMessageException
     *             异常
     * @author shilei
     */
    public static String getMessage(String code) throws NoSuchMessageException {
        return that.getMessageSourceAccessor().getMessage(code);
    }

    /**
     * 通过消息key从资源文件中获取指定地区的消息内容
     * 
     * @param code
     *            String 消息key
     * @param locale
     *            Locale 地区
     * @return String 消息内容，如果没有找到指定key的消息，则报出NoSuchMessageException异常
     * @throws NoSuchMessageException
     *             异常
     * @author shilei
     */
    public static String getMessage(String code, Locale locale) throws NoSuchMessageException {
        return that.getMessageSourceAccessor().getMessage(code, locale);
    }

    /**
     * 通过消息key，消息参数，从资源文件中获取指定的消息内容
     * 
     * @param code
     *            String 消息key
     * @param args
     *            Object[] 消息参数
     * @return String 消息内容，如果没有找到指定key的消息，则报出NoSuchMessageException异常
     * @throws NoSuchMessageException
     *             异常
     * @author shilei
     */
    public static String getMessage(String code, Object args[]) throws NoSuchMessageException {
        return that.getMessageSourceAccessor().getMessage(code, args);
    }

    /**
     * 通过消息key，消息参数，从资源文件中获取指定地区的消息内容
     * 
     * @param code
     *            String 消息key
     * @param args
     *            Object[] 消息参数
     * @param locale
     *            Locale 地区
     * @return String 消息内容，如果没有找到指定key的消息，则报出NoSuchMessageException异常
     * @throws NoSuchMessageException
     *             异常
     * @author shilei
     */
    public static String getMessage(String code, Object args[], Locale locale)
            throws NoSuchMessageException {
        return that.getMessageSourceAccessor().getMessage(code, args, locale);
    }

    /**
     * 通过指定的消息解析器从资源文件中获取消息内容
     * 
     * @param resolvable
     *            MessageSourceResolvable 消息解析器
     * @return String 消息内容，如果没有找到指定的消息，则报出NoSuchMessageException异常
     * @throws NoSuchMessageException
     *             异常
     * @author shilei
     */
    public static String getMessage(MessageSourceResolvable resolvable)
            throws NoSuchMessageException {
        return that.getMessageSourceAccessor().getMessage(resolvable);
    }

    /**
     * 通过指定的消息解析器从资源文件中获取指定地区的消息内容
     * 
     * @param resolvable
     *            MessageSourceResolvable
     * @param locale
     *            Locale 地区
     * @return String 消息内容，如果没有找到指定的消息，则报出NoSuchMessageException异常
     * @throws NoSuchMessageException
     *             异常
     * @author shilei
     */
    public static String getMessage(MessageSourceResolvable resolvable, Locale locale)
            throws NoSuchMessageException {
        return that.getMessageSourceAccessor().getMessage(resolvable, locale);
    }
}
