package wang.ronnie;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import wang.ronnie.config.PersistenceConfig;
import wang.ronnie.config.WebSocketConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.HttpSolrServerFactoryBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ronnie
 */
@SpringBootApplication
@Import(value = {PersistenceConfig.class, WebSocketConfig.class})
@EnableSolrRepositories("wang.ronnie.solr.repository")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MyLifeApplication extends SpringBootServletInitializer {

    @Autowired
    private Environment environment;

//    @Bean
//    public EmbeddedSolrServerFactoryBean solrServerFactoryBean() {
//        EmbeddedSolrServerFactoryBean factory = new EmbeddedSolrServerFactoryBean();
//
//        factory.setSolrHome(environment.getRequiredProperty("solr.solr.home"));
//
//        return factory;
//    }

    @Bean
    public HttpSolrServerFactoryBean solrServerFactoryBean() {

        HttpSolrServerFactoryBean factory = new HttpSolrServerFactoryBean();

        factory.setUrl(environment.getRequiredProperty("solr.server.url"));

        return factory;
    }

    @Bean
    public SolrTemplate solrTemplate() throws Exception {

        return new SolrTemplate(solrServerFactoryBean().getObject());
    }

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

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(MyLifeApplication.class);
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8081);
        connector.setSecure(false);
        connector.setRedirectPort(8443);

        return connector;
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

    public static void main(String[] args) throws Exception {

//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//
//        simpleMailMessage.setFrom("ronniewang1993@outlook.com");
//        simpleMailMessage.setTo("wangshengyu@caiex.com");
//        simpleMailMessage.setSubject("learn");
//        simpleMailMessage.setText("learn");
//        javaMailSender().send(simpleMailMessage);

        SpringApplication.run(MyLifeApplication.class, args);
    }
}
