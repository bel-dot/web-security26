package chnu.edu.websecurity26.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( auth ->
                        auth.requestMatchers("/index.html").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/games/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/games/", "/api/v1/games").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/games/", "/api/v1/games").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/games/", "/api/v1/games").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/api/v1/games/hello").permitAll()
                                .requestMatchers("/api/v1/games/hello/admin").hasRole("ADMIN")
                                .requestMatchers("/api/v1/games/hello/user").hasRole("USER")
                                .requestMatchers("/api/v1/games/hello/human").hasAnyRole("ADMIN", "USER")
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}Co0l@dmin1337")
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password("{noop}user")
                .roles("USER")
                .build();

        UserDetails superadmin = User.builder()
                .username("superadmin")
                .password("{noop}superadmin")
                .roles("SUPERADMIN", "ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user, superadmin);
    }
}
