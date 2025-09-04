package com.hamza.springstore.controllers;


import com.hamza.springstore.dtos.ProductDto;
import com.hamza.springstore.mappers.ProductMapper;
import com.hamza.springstore.repositories.CategoryRepository;
import com.hamza.springstore.repositories.ProductRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/products")
@RestController
@AllArgsConstructor
@Tag(name ="Products")
public class ProductsContoller {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;


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

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto productDto,
            UriComponentsBuilder uriBuilder
    ){
        var category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(null);
        if(category == null){
            return ResponseEntity.badRequest().build();
        }
        var product = productMapper.toEntity(productDto);
        product.setCategory(category);
        productRepository.save(product); // id gets generated here automatically
        productDto.setId(product.getId());

        var uri = uriBuilder.path("/{id}").buildAndExpand(productDto.getId()).toUri();
        return ResponseEntity.created(uri).body(productDto); // A URI is needed because it’s the universal way to uniquely identify and access resources in web systems and APIs.
    }

    @PutMapping("/{id}/update") // for update
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable(name = "id") Long id,
            @RequestBody ProductDto productDto
    ){
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productDto.setId(id);
        productMapper.update(productDto, product);
        productRepository.save(product);

        return ResponseEntity.ok(productDto);
    }



    @DeleteMapping("/{id}/delete")// for delete
    public ResponseEntity<Void> deleteProduct(
            @PathVariable(name = "id") Long id
    ){
        var product = productRepository.findById(id).orElseThrow(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }




}
