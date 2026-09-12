package vn.btl.laptopshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
   @Bean
   public ViewResolver viewResolver() {
      final InternalResourceViewResolver bean = new InternalResourceViewResolver();
      bean.setViewClass(JstlView.class);
      bean.setPrefix("/WEB-INF/view/");
      bean.setSuffix(".jsp");
      return bean;
   }

   @Override
   public void configureViewResolvers(ViewResolverRegistry registry) {
      registry.viewResolver(viewResolver());
   }

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
      registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
      registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
      registry.addResourceHandler("/client/**").addResourceLocations("/resources/client/");

   }

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      // Tạo một Interceptor kiểm tra đăng nhập ngay tại chỗ
      HandlerInterceptor adminInterceptor = new HandlerInterceptor() {
         @Override
         public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
               throws Exception {
            HttpSession session = request.getSession();
            // Nếu chưa đăng nhập (adminUser bằng null)
            if (session.getAttribute("adminUser") == null) {
               // Chuyển hướng người dùng về trang login
               response.sendRedirect("/admin/login");
               return false; // Ngăn không cho chạy tiếp vào Controller
            }
            return true; // Cho phép đi tiếp vào trang admin nếu đã đăng nhập
         }
      };

      // Áp dụng bộ lọc này cho tất cả các URL bắt đầu bằng /admin/...
      // Nhưng PHẢI loại trừ (exclude) chính trang login để tránh bị lặp vô tận
      registry.addInterceptor(adminInterceptor)
            .addPathPatterns("/admin/**")
            // CHỈ CẦN loại trừ trang login và toàn bộ tài nguyên tĩnh bắt đầu bằng
            // /resources/**
            .excludePathPatterns("/admin/login", "/resources/**");

   }
}