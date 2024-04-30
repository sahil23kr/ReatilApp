package com.Retail.ServiceImpl;

import com.Retail.DTO.ProductApprovalResponseDTO;
import com.Retail.GlobalExceptionHandling.*;
import com.Retail.Model.Product;
import com.Retail.Model.ProductApproval;
import com.Retail.Repo.ProductApprovalRepo;
import com.Retail.Repo.ProductRepo;
import com.Retail.Service.ProductService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.time.LocalDateTime;
import java.util.*;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    public static Logger logger= LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ProductApprovalRepo productApprovalRepo;
    @Override
    public Object createProduct(Product product) throws UnexpectedException {
        RestTemplate template=new RestTemplate();
        ProductApprovalResponseDTO responseDTO=null;
        ProductApproval productApproval=new ProductApproval();
        //try {
            if(product.getPrice() > 10000) {
                logger.info("<<<<<Price of product should be less than $10000>>>");
                throw new ProductException();
            }
                 if (product.getPrice() > 5000) {
                    /*HttpEntity<Product> request = new HttpEntity<>(product);
                    logger.info("<<<<<checking serviceImpl>>>>>");
                    //String url = "http://localhost:9091/getApproval";
                    String url="http://localhost:9091/approval-queue";
                    restTemplate.postForObject(url, request, Product.class);*/
                    //String s = restTemplate.getForObject(url, String.class);
                    logger.info("<<<<< Response from second Api >>>>>> " );
                    //System.out.println("Response from another API " );
                    //String status=responseDTO.getProduct().getStatus();
                     logger.info("<<<<<saving into product table>>>>>");
                     product.setStatus("not approved");
                     product.setProductId(UUID.randomUUID().toString());
                     productRepo.save(product);
                     logger.info("<<<<successfully saved into product table>>>>>");
                     logger.info("<<<<saving into approval table>>>>");
                     productApproval.setStatus("not approved");
                     productApproval.setDate(LocalDateTime.now());
                     productApproval.setProductId(product.getProductId());
                     productApprovalRepo.save(productApproval);
                     logger.info("<<<<successfully saved into product table>>>>");
                     return new ProductApprovalResponseDTO(product);
                }

            product.setProductId(UUID.randomUUID().toString());
            product.setStatus("approved");
            return productRepo.save(product);
        /*catch (Exception e){
            e.printStackTrace();
            throw new UnexpectedException("Unknown error");
        }*/
    }

    @Override
    public void productApproval() {
        String url="http://localhost:9090/api/products";
        List<Product> productList=productRepo.findByStatus("not approved");

    }

    @Override
    public List<Product> productApprovalQueue(Product p) {

        List<Product> productList=productRepo.findByStatus("not approved");
        return productList;
    }

    @Override
    public String uploadImages(String path, MultipartFile file) throws IOException {
        //file Name
        String name= file.getOriginalFilename();
        logger.info("<<<<checking filename>>>>>>"+name);
        //file fullpath
        String fullpath=path+ File.separator+name;
        logger.info("<<<<<<<fullfilepath>>>>>>>>"+fullpath);
        //
        File f=new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        try {
            Files.copy(file.getInputStream(), Paths.get(fullpath));
        }
        catch (Exception e){
            throw new FileuploadException();
        }


        return name;
    }

    @Override
    public Optional<List<Product>> getAllActiveproduct() {
        Optional<List<Product>> productList= Optional.of(productRepo.findByStatus("approved").stream().toList());
        if(productList.get().size()!=0)
        //if(!productList.isEmpty())
        return productList;
        else
          throw new ActiveProductException();
    }

    @Override
    public Object updateProduct(String productId, Product product) {
        ProductApproval productApproval=new ProductApproval();
        ProductApprovalResponseDTO productApprovalResponseDTO=null;
        Optional<Product> product1=productRepo.findById(productId);
        if (product1.isEmpty()){
           throw new ProductNotFoundException();
        }
        else {
            if (product.getPrice()>(50/100)*product1.get().getPrice()){
                logger.info("<<<<<saving into product table>>>>>");
                product.setProductId(productId);
                product.setName(product.getName());
                product.setStatus("not approved");
                product.setPrice(product.getPrice());
                productRepo.save(product);
                logger.info("<<<<saving into approval table>>>>");
                productApproval.setProductId(productId);
                productApproval.setStatus("not approved");
                productApproval.setDate(LocalDateTime.now());
                productApprovalRepo.save(productApproval);
                return new ProductApprovalResponseDTO(product);
            }
            product.setProductId(productId);
            product.setName(product.getName());
            product.setStatus("approved");
            productRepo.save(product);
            return product;
        }
    }
}
