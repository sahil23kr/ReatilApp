package com.Retail.Controller;

import com.Retail.DTO.FileResponse;
import com.Retail.Model.Product;
import com.Retail.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class ProductController {

    @Value("${project.image}")
    private String path;
    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) throws UnexpectedException {
        Object product1=productService.createProduct(product);
        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }

    @GetMapping("/getAllActiveproduct")
    public ResponseEntity<Optional<List<Product>>> getAllActiveproduct(){
        Optional<List<Product>> product1=productService.getAllActiveproduct();
        return new ResponseEntity<>(product1,HttpStatus.OK);
    }

    @PutMapping("updateProduct/{pId}")
    public ResponseEntity<Object> updateProduct(@PathVariable("pId") String productId,@RequestBody Product product){
        Object product1=productService.updateProduct(productId,product);
        return new ResponseEntity<>(product1,HttpStatus.OK);
    }
    @PostMapping("/uploadImages")
    public ResponseEntity<FileResponse> uploadImages(@RequestParam("image")MultipartFile file) throws IOException {
        //String f= file.getOriginalFilename();
        String filename=productService.uploadImages(path,file);
        FileResponse fileResponse=new FileResponse();
        fileResponse.setFileName(filename);
        fileResponse.setMessage("Successfully uploaded");

        return new ResponseEntity<>(fileResponse,HttpStatus.OK);
    }



}
