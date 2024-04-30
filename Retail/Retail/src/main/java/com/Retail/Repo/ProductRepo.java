package com.Retail.Repo;

import com.Retail.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {

    @Query(value="select * from product where status = :status ", nativeQuery=true)
    List<Product> findByStatus(@Param("status") String status);
}
