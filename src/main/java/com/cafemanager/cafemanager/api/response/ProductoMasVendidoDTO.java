package com.cafemanager.cafemanager.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductoMasVendidoDTO {

    private String producto;

    private Long cantidad;

}
