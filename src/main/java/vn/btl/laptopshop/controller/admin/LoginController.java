package vn.btl.laptopshop.controller.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.btl.laptopshop.domain.User;
import vn.btl.laptopshop.service.UserService;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // 1. Hiển thị trang đăng nhập
    @GetMapping("/admin/login")
    public String getLoginPage() {
        return "admin/auth/login"; // Đường dẫn tới file giao diện login của bạn (HTML/JSP)
    }

    // 2. Xử lý khi nhấn nút Đăng nhập
    @PostMapping("/admin/login")
    public String handleLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        User user = this.userService.checkLogin(email, password);

        if (user == null) {
            // Đăng nhập thất bại, gửi thông báo lỗi ra giao diện
            model.addAttribute("error", "Email hoặc mật khẩu không chính xác!");
            return "admin/auth/login";
        }

        // Đăng nhập thành công -> Lưu thông tin user vào Session để kiểm tra bảo mật
        session.setAttribute("adminUser", user);

        // Chuyển hướng thẳng vào trang Dashboard admin
        return "redirect:/admin";
    }

    // 3. Xử lý Đăng xuất (Logout)
    @GetMapping("/admin/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate(); // Xóa toàn bộ session
        return "redirect:/admin/login";
    }
}