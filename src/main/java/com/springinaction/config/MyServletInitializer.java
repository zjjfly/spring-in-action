package com.springinaction.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by zjjfly on 2017/1/9.
 */
public class MyServletInitializer implements WebApplicationInitializer {
    //通过重载onStartup注册Servlet，Filter，Listener
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        FilterRegistration.Dynamic myFilter = servletContext.addFilter("myFilter", MyFilter.class);
//        myFilter.addMappingForUrlPatterns(null,false,"/custom/**");
    }
}
