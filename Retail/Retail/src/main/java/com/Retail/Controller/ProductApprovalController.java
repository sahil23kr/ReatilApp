package com.Retail.Controller;

import com.Retail.Model.Product;
import com.Retail.Model.ProductApproval;
import com.Retail.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/products")
@CrossOrigin("*")
@RestController
public class ProductApprovalController {

    @Autowired
    ProductService productService;
    @PostMapping("/approval-queue")
    public ResponseEntity<List<Product>> productApprovalQueue(@RequestBody Product p){

            List<Product> productList=productService.productApprovalQueue(p);
            return new ResponseEntity<>(productList,HttpStatus.OK);
    }

    @PutMapping("/approval-queue{productId}")
    public ResponseEntity<Object> productApproval( ){
        productService.productApproval();
        return new ResponseEntity("", HttpStatus.OK);
    }
}
