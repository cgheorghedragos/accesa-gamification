package ciurezu.gheorghe.dragos.accesa.gamification.security.filter;

import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.security.jwt.JwtGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class GamificationAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtGenerator tokenGenerator;

    private final AuthenticationManager authenticationManager;

    public GamificationAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = new JwtGenerator();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String requestData, username ="", password ="";
        username = request.getParameter("username");
        password = request.getParameter("password");
        if(username == null && password == null){
            // retreive from JSON body
            try {
                requestData = request.getReader().lines().collect(Collectors.joining());
                ObjectMapper objectMapper = new ObjectMapper();
                GamificationUserDto userDTO = objectMapper.readValue(requestData,GamificationUserDto.class);
                username = userDTO.getUsername();
                password = userDTO.getPassword();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        log.info("Username is: {}", username);
        log.info("Password is: {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        String access_token = tokenGenerator
                .generateAccessToken(user.getUsername(),
                        request.getRequestURL().toString(),
                        user.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()));


        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType(APPLICATION_JSON_VALUE);

        Map<String, String> error = new HashMap<>();

        error.put("error", "User Not Found");
        response.setStatus(HttpStatus.NOT_FOUND.value());
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
}
