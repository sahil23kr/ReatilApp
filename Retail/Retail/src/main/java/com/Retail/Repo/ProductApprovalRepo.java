package com.Retail.Repo;

import com.Retail.Model.ProductApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductApprovalRepo extends JpaRepository<ProductApproval,Integer> {
}
