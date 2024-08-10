package com.marreros.ms_product_fallback;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {


    private Long id;
    private String nombre;
    private String descripcion;

}
