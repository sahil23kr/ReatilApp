package com.Retail.DTO;

import com.Retail.Model.Product;

public class ProductApprovalResponseDTO {

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductApprovalResponseDTO(Product product) {
        this.product = product;
    }

    public ProductApprovalResponseDTO() {
    }

    @Override
    public String toString() {
        return "ProductApprovalResponseDTO{" +
                "product=" + product +
                '}';
    }
}
