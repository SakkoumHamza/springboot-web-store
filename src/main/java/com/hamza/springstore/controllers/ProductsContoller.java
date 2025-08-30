package com.hamza.springstore.controllers;


import com.hamza.springstore.dtos.ProductDto;
import com.hamza.springstore.mappers.ProductMapper;
import com.hamza.springstore.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@RestController
@AllArgsConstructor
public class ProductsContoller {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @GetMapping
    public Iterable<ProductDto> getProducts(@RequestParam(required = false, name="categoryId", defaultValue = "") Byte categoryId){
       if(categoryId == null){
            return productRepository.findAllWithCategory().stream().map(productMapper::toDto).toList();
       }
       return  productRepository.findByCategoryId(categoryId).stream().map(productMapper::toDto).toList();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        var product = productRepository.findById(id).orElseThrow();
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }


}
