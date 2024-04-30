package com.Retail.Service;

import com.Retail.Model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Object createProduct(Product product) throws UnexpectedException;

    void productApproval();

    List<Product> productApprovalQueue(Product p);

    String uploadImages(String path, MultipartFile file) throws IOException;

    Optional<List<Product>> getAllActiveproduct();

    Object updateProduct(String productId, Product product);
}
