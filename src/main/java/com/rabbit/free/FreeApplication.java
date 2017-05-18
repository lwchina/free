package com.rabbit.free;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Properties;

@SpringBootApplication
public class FreeApplication {

	public static void main(String[] args) throws IOException {
		String basicPath = System.getProperty("user.dir");
		Properties properties = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(basicPath + "/config/application.properties"));
		properties.load(in);
		SpringApplication springApplication = new SpringApplication(FreeApplication.class);
		//设置默认读取配置文件位置  读取jar包外的配置文件
		springApplication.setDefaultProperties(properties);
		springApplication.run(args);
		//读取jar包外的配置文件
		PropertyConfigurator.configure(basicPath + "/config/log4j.properties");
	}
}
