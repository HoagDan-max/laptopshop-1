package vn.btl.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.btl.laptopshop.domain.Product;
import vn.btl.laptopshop.domain.Role;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);

    List<Product> findByNameContainingIgnoreCase(String name);
}