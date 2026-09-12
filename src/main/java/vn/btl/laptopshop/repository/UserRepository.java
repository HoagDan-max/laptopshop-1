package vn.btl.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.btl.laptopshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User eric);

    void deleteById(User eric);

    List<User> findAll();

    User findById(long id);

    // List<User> findByEmail(String email);
    User findByEmail(String email);
}
