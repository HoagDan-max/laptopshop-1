package vn.btl.laptopshop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.btl.laptopshop.domain.Product;
import vn.btl.laptopshop.service.ProductService;

@Controller
public class HomePageController {
    private final ProductService productService;

    public HomePageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = this.productService.fetchProducts();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }

    @GetMapping("/products")
    public String getProductsPage(Model model, @RequestParam(value = "search", required = false) String search) {
        List<Product> products;

        // Nếu người dùng có nhập từ khóa tìm kiếm
        if (search != null && !search.trim().isEmpty()) {
            products = this.productService.searchProductsByName(search);
            model.addAttribute("searchKeyword", search); // Gửi lại từ khóa ra ngoài giao diện nếu cần hiển thị
        } else {
            // Nếu không tìm kiếm, hiển thị tất cả sản phẩm giống trang chủ hoặc trang shop
            products = this.productService.fetchProducts();
        }

        model.addAttribute("products", products);
        return "client/homepage/show"; // Bạn có thể đổi thành trang kết quả tìm kiếm riêng nếu muốn
    }

}
