package org.onn.webportal.application.boot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class ApplicationEntry {
	
	protected ApplicationContext context;
	
	private static ApplicationEntry appEntry = null;
	
	private App  app;
	
	public static ApplicationEntry getInstance(){
		if(appEntry == null)
		{
			appEntry = new ApplicationEntry();
		}
		return appEntry;
	}
			
	private ApplicationEntry(){
		context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/spring-core-config.xml");
		app = (App) context.getBean(App.class);
	}
	
	public ApplicationContext getContext(){
		return context;
	}
	
	public App getApp(){
		return app;
	}
}
