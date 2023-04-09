package ciurezu.gheorghe.dragos.accesa.gamification.security;

import ciurezu.gheorghe.dragos.accesa.gamification.security.filter.GamificationAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class GamificationDSL extends AbstractHttpConfigurer<GamificationDSL, HttpSecurity> {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        GamificationAuthenticationFilter authFilter = new GamificationAuthenticationFilter(authenticationManager);
        authFilter.setFilterProcessesUrl("/api/login");
        http.addFilter(authFilter);
    }

    public static GamificationDSL gamificationDSL() {
        return new GamificationDSL();
    }
}
