package com.marreros.ms_product_fallback;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Product DEFAULT_COMPANY = Product.builder()
        .descripcion("holii desde el fallback")
        .id(Long.parseLong("1"))
        .nombre("fernet con micheladas")
        .build();


    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
         return new ResponseEntity<>(DEFAULT_COMPANY, HttpStatus.OK);
    }
    


}
