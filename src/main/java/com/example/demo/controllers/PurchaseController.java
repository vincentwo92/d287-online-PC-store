package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PurchaseController {

    @Autowired
    private final ProductRepository productRepository;

    public PurchaseController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/purchase")
    public String buyProduct(@RequestParam Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product returnedProduct = productOptional.get();
            if (returnedProduct.getInv() >= 1) {
                returnedProduct.setInv(returnedProduct.getInv() - 1);
                productRepository.save(returnedProduct);

                return "purchaseSuccess";
            }
        }

        return "purchaseUnsuccessful";
    }

}
