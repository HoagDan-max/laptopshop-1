package vn.btl.laptopshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Cho phép tất cả mọi người truy cập vào bất kỳ đường dẫn nào (permitAll)
                        .anyRequest().permitAll())
                // Tạm thời tắt tính năng bảo vệ CSRF để lúc team làm chức năng submit Form
                // (Thêm phòng) không bị báo lỗi 403
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}