package com.Retail.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="productapproval")
public class ProductApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int approvalId;
    private String status;
    private String productId;

    @JsonFormat
    private LocalDateTime date;

    public int getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(int approvalId) {
        this.approvalId = approvalId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ProductApproval() {
    }

    @Override
    public String toString() {
        return "ProductApproval{" +
                "approvalId=" + approvalId +
                ", status='" + status + '\'' +
                ", productId=" + productId +
                ", date=" + date +
                '}';
    }
}
