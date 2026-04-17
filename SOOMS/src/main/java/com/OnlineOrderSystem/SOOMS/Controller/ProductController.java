package com.OnlineOrderSystem.SOOMS.Controller;

import com.OnlineOrderSystem.SOOMS.dto.product_request;
import com.OnlineOrderSystem.SOOMS.dto.product_response;
import com.OnlineOrderSystem.SOOMS.entity.Product;
import com.OnlineOrderSystem.SOOMS.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RequestMapping("/api/products")
@RestController
public class ProductController {
    @Autowired
    public productService productService;

    @PostMapping
    public ResponseEntity<product_response> AddProduct(@RequestBody product_request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.CreateProduct(request));

    }


    @GetMapping
    public ResponseEntity<ArrayList<Product>> GetAllProdcuts() {
        ArrayList<Product> getprod = (ArrayList<Product>) productService.GetProducts();
        return ResponseEntity.ok(getprod);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> GetProductUsingId(@PathVariable int id) {
        Product ProdById = productService.GetProductById(id);
        return ResponseEntity.ok(ProdById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<product_response> UpdateProdDetails(@PathVariable int id,@RequestBody product_request productRequest){
        return  ResponseEntity.ok(productService.updateProd(id, productRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteprod(@PathVariable int id) {
        boolean isdeleted = productService.DeleteTheProd(id);
        if (isdeleted == false) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } else return ResponseEntity.status(HttpStatus.OK).body("Product is Deleted SuccessFully!!");
    }

    }

