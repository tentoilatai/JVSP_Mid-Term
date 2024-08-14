package vn.kaiz.midterm.services;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.kaiz.midterm.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
}
