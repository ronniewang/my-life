package com.test;

import com.test.config.PersistenceConfig;
import com.test.config.RestConfiguration;
import com.test.config.WebSocketConfig;
import com.test.filter.PassportFilter;
import com.test.service.PassportService;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.servlet.Filter;
import java.util.Properties;

/**
 * @author ronnie
 */
@SpringBootApplication
@Import(value = {PersistenceConfig.class, WebSocketConfig.class, RestConfiguration.class/*, LogAspect.class*/})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application extends SpringBootServletInitializer {

    @Autowired
    private Environment environment;

    @Autowired
    private PassportService passportService;

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Bean
    public static JavaMailSender javaMailSender() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp-mail.outlook.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("ronniewang1993@outlook.com");
        javaMailSender.setPassword("1b3456789!");
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }

    public static void main(String[] args) throws Exception {

//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//
//        simpleMailMessage.setFrom("ronniewang1993@outlook.com");
//        simpleMailMessage.setTo("wangshengyu@caiex.com");
//        simpleMailMessage.setSubject("learn");
//        simpleMailMessage.setText("learn");
//        javaMailSender().send(simpleMailMessage);

        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean passportFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter passportFilter = new PassportFilter(passportService);
        beanFactory.autowireBean(passportFilter);
        registration.setFilter(passportFilter);
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(Application.class);
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {

        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {

                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/jfiekskadlfc");
//                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

//        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

//    @Configuration
//    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//
//            http.authorizeRequests().anyRequest().permitAll();
//            http.authorizeRequests().antMatchers("/css/**", "/js/**", "/image/**").permitAll().anyRequest()
//                    .fullyAuthenticated().and().formLogin().loginPage("/login")
//                    .failureUrl("/login?error=1").permitAll().and().logout().permitAll();
//        }
//
//        @Override
//        public void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//            auth.inMemoryAuthentication().withUser("caiex").password("caiex")
//                    .roles("ADMIN", "USER").and().withUser("user").password("user")
//                    .roles("USER");
//        }
//
//    }

    private Connector initiateHttpConnector() {

        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
//        connector.setPort(8082);
//        connector.setSecure(false);
//        connector.setRedirectPort(8443);

        return connector;
    }
}
