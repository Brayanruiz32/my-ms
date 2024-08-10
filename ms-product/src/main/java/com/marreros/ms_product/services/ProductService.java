package com.marreros.ms_product.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marreros.ms_product.entities.Product;
import com.marreros.ms_product.repositories.ProductRepository;

@Service
public class ProductService implements IServices<Product> {

    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Product create(Product nuevoRegistro) {
        return productRepository.save(nuevoRegistro);
    }

    @Override
    public Product update(Long id, Product nuevoRegistro) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setNombre(nuevoRegistro.getNombre());
        product.setDescripcion(nuevoRegistro.getDescripcion());
        return productRepository.save(product);
    }
    
    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }
}
