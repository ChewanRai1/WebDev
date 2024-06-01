package com.system.Fashionhive.repo;

import com.system.Fashionhive.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
    @Query(value = "SELECT * FROM review where product_id=?1", nativeQuery = true)
    Optional<List<Review>> findAllByProduct(Integer productId);
}
