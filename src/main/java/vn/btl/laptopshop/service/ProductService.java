package vn.btl.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.btl.laptopshop.domain.Product;
import vn.btl.laptopshop.repository.ProductRepository;
import vn.btl.laptopshop.repository.RoleRepository;
import vn.btl.laptopshop.repository.UserRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, RoleRepository roleRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product pr) {
        Product eric = this.productRepository.save(pr);
        return eric;
    }

    public List<Product> fetchProducts() {
        return this.productRepository.findAll();
    }

    public Product fetchProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public List<Product> searchProductsByName(String name) {
        return this.productRepository.findByNameContainingIgnoreCase(name);
    }
}
