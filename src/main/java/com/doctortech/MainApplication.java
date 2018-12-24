package com.doctortech;

import com.doctortech.framework.common.shiro.ShiroKit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.filter.HttpPutFormContentFilter;

import javax.servlet.Filter;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 运行
 * java -jar target/myproject-0.0.1-SNAPSHOT.jar --server.port=8081
 * 
 * or
 * 
 * mvn spring-boot:run
 * 
 * @author gong
 *	
 * 2017年7月21日
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class, org.activiti.spring.boot.SecurityAutoConfiguration.class//禁用 activi 登录功能
})
public class MainApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer{
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
}
	
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {

	}
	
	 	@Bean
	    public Filter initializeHttpPutHandler() {
	        return new HttpPutFormContentFilter();
	    }

	    @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(MainApplication.class);
	    }

	    @Bean
	    public Converter<String, Date> addNewConvert() {
	        return new Converter<String, Date>() {
	            @Override
	            public Date convert(String source) {
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                 Date date = null;
	            try {
	                date = sdf.parse((String) source);
	                } catch (Exception e) {
	                e.printStackTrace();
	                }
	             return date;
	            }
	        };
	    }

}
