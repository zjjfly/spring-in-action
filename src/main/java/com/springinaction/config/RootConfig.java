package com.springinaction.config;

import com.springinaction.web.Repl;
import org.springframework.context.annotation.*;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.util.regex.Pattern;

/**
 * Created by zjjfly on 2016/12/27.
 */
@Configuration
//把带有EnableWebMvc注解的类过滤掉
@ComponentScan(basePackages = "com.springinaction",excludeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM,value = RootConfig.WebPackage.class)})
public class RootConfig {
    public static class WebPackage extends RegexPatternTypeFilter{
        public WebPackage() {
            super(Pattern.compile("com\\.springinaction\\.web"));
        }
    }

    @Bean
    @Profile("dev")
    public Repl repl(){
        return new Repl();
    }
}
