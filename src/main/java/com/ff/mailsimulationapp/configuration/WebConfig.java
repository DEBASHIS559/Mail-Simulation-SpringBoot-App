package com.ff.mailsimulationapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ff.mailsimulationapp.util.SessionInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new SessionInterceptor())
				.addPathPatterns("/app/**")
				.addPathPatterns("/user/logout")
				.excludePathPatterns("/user/login")
				.excludePathPatterns("/password/**");
	}

}
