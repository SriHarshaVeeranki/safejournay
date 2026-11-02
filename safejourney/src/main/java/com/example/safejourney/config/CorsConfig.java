package com.example.safejourney.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        
        CorsConfiguration config = new CorsConfiguration();
        
        config.setAllowedOrigins(List.of(
            "http://localhost:5173",
            "http://localhost:3000",
            "https://safejourney-frontend.onrender.com",
            "https://safejourney.vercel.app",
            "https://safejourney.netlify.app"
        ));
        
        // If you want to allow ALL origins (for testing), use this line instead of above:
        // config.setAllowedOriginPatterns(List.of("*"));
        
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return source;
    }
}