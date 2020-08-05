package br.com.eullen.ecommerce.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ConfiguracaoServidorRecursos extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "ecommerce-rest-api";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
            .resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
            .and()
                .authorizeRequests()
                    .anyRequest()
                        .authenticated();
    }
}