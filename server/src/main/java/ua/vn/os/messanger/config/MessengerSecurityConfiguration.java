package ua.vn.os.messanger.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.WebSessionSecurityContextRepository;

@EnableWebFluxSecurity
public class MessengerSecurityConfiguration {

    @Bean
    public SecurityWebFilterChain messengerSecurityWebFilterChain(ApplicationContext context, ReactiveAuthenticationManager manager) {
        HttpSecurity http = context.getBean(HttpSecurity.class);
        http.securityContextRepository(new WebSessionSecurityContextRepository());
        http.authenticationManager(manager);
        http.httpBasic();

        HttpSecurity.AuthorizeExchangeBuilder authorize = http.authorizeExchange();
        authorize.pathMatchers("/*.css", "/*.js").permitAll();
        authorize.pathMatchers("/").permitAll();
        authorize.pathMatchers("/users/**").permitAll();
        authorize.anyExchange().authenticated().and().formLogin().disable();
        return http.build();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager(UserDetailsRepository userDetailsRepository) {
        return new UserDetailsRepositoryAuthenticationManager(userDetailsRepository);
    }
}
