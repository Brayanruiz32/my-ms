package com.marreros.ms_product.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IServices<I> {

    I findById(@PathVariable Long id);

    List<I> findAll();

    I create(@RequestBody I nuevoRegistro);

    I update(@PathVariable Long id, @RequestBody I nuevoRegistro);

    void delete(@PathVariable Long id);

}
