package com.utn.practico1.entidades;

import com.utn.practico1.enums.TipoProducto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto extends BaseEntidad {

    private int tiempoEstimadoCocina;
    private int stockActual;
    private int stockMinimo;
    private double precioVenta;
    private double precioCompra;
    private String denominacion;
    private String unidadMedida;
    private String receta;
    private TipoProducto tipo;

}
