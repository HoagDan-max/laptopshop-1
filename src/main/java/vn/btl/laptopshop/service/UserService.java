package vn.btl.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.btl.laptopshop.domain.Role;
import vn.btl.laptopshop.domain.User;
import vn.btl.laptopshop.repository.RoleRepository;
import vn.btl.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String handleHello() {
        return "Hello from Service";
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // public List<User> getAllUsersByEmail(String email) {
    // return this.userRepository.findByEmail(email);
    // }

    public User handleSaveUser(User user) {
        User eric = this.userRepository.save(user);
        return eric;
    }

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public void deleteAUser(long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    // Trong UserService.java
    public User checkLogin(String email, String password) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            // Lưu ý: Ở đây đang kiểm tra dạng text thuần (Plain text).
            // Nếu sau này bạn có mã hóa mật khẩu bằng BCrypt, hãy dùng
            // passwordEncoder.matches()
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Đăng nhập thất bại
    }

}