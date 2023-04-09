package ciurezu.gheorghe.dragos.accesa.gamification.security;

import ciurezu.gheorghe.dragos.accesa.gamification.data.StandardRoles;
import ciurezu.gheorghe.dragos.accesa.gamification.security.filter.GamificationAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static ciurezu.gheorghe.dragos.accesa.gamification.security.GamificationDSL.gamificationDSL;
import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests().requestMatchers("/**").permitAll();
                ///((req) ->
        //                req.requestMatchers("/api/login/**").permitAll()
        //                        .requestMatchers("/api/v1/**").hasAnyAuthority(StandardRoles.ROLE_USER)
        //                        ).httpBasic();

        http.apply(gamificationDSL());
        http.addFilterBefore( new GamificationAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

