package com.OnlineOrderSystem.SOOMS.service;

import com.OnlineOrderSystem.SOOMS.dto.product_request;
import com.OnlineOrderSystem.SOOMS.dto.product_response;
import com.OnlineOrderSystem.SOOMS.entity.Product;
import com.OnlineOrderSystem.SOOMS.repository.Productrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class productService {
    @Autowired
    public Productrepository productrepository;


    public product_response CreateProduct(product_request request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        Product savedProduct = productrepository.save(product);
        product_response response = new product_response();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setPrice(savedProduct.getPrice());
        response.setStock(savedProduct.getStock());
        return response;


    }


    public List<Product> GetProducts() {
//        return new ArrayList<>(ProdDb.values());
        List<Product> products = productrepository.findAll();
        return  products;
    }

    public  Product GetProductById(int id) {
        return productrepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product Does not found with "+id));
    }

    public product_response updateProd(int id,product_request productRequest) {
        Product product =productrepository.findById(id).orElseThrow(() -> new RuntimeException("Product Does not exist with :"+id));

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        Product updatedproduct = productrepository.save(product);

        product_response productResponse = new product_response();
        productResponse.setId(updatedproduct.getId());
        productResponse.setName(updatedproduct.getName());
        productResponse.setPrice(updatedproduct.getPrice());
        productResponse.setStock(updatedproduct.getStock());

        return  productResponse;


    }

//
    public  boolean DeleteTheProd(int id) {
        if(!productrepository.existsById(id)){
            return false;
        }
        productrepository.deleteById(id);
        return  true;
    }
}
