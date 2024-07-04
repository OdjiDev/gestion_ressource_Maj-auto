//package com.odji.spring_back_end.security;
//
//
//import com.nimbusds.jose.jwk.source.ImmutableSecret;
//import com.odji.spring_back_end.services.UserService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.authentication.PasswordEncoderParser;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import javax.crypto.spec.SecretKeySpec;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//    @Value("{$jwt.secret}")
//    private String secretKey;
//
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        PasswordEncoder passwordEncoder = passwordEncoder();
//        return new InMemoryUserDetailsManager(
//                User.withUsername("user1").password(passwordEncoder.encode("12345")).authorities("USER").build(),
//                User.withUsername("admin").password(passwordEncoder.encode("12345")).authorities("USER", "ADMIN").build()
//        );
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .csrf(csrf -> csrf.disable())
//                .cors(Customizer.withDefaults())
//                .authorizeHttpRequests(ar->ar.requestMatchers("/auth/login/**").permitAll())
//                .authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
//                // .httpBasic(Customizer.withDefaults())
//                .oauth2ResourceServer(oa -> oa.jwt(Customizer.withDefaults()))
//                .build();
//    }
//
//
//    @Bean
//    JwtEncoder jwtEncoder() {
//        //String secretKey ="A1b2C3d4E5f6G7h8I9j0K1l2M3n4O5p6Q7r8S9t0U1v2W3x4Y5z6A7b8C9d0E1";
//        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder() {
//        //   String secretKey ="A1b2C3d4E5f6G7h8I9j0K1l2M3n4O5p6Q7r8S9t0U1v2W3x4Y5z6A7b8C9d0E1";
//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "RSA");
//        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS256).build();
//    }
//@Bean
//    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//
//        return new ProviderManager(daoAuthenticationProvider);
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource(){
//            CorsConfiguration config = new CorsConfiguration();
//            config.addAllowedOrigin("*"); // Replace with allowed origins
//            config.addAllowedMethod("*");
//            config.addAllowedHeader("*");
//            //config.setExposedHeaders(List.of("x-auth-token"));
//        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**",config);
//          //  config.setAllowCredentials(true); // Set to true if credentials (cookies, etc.) are allowed
//
////            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////            source.registerCorsConfiguration("/**", config); // Map the config to all paths
//            return source;
//        }
//    }
//
//
