package com.utn.practico1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.query.JSqlParserUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rubro extends BaseEntidad {

    private String denominacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto prod) {
        productos.add(prod);
    }

    public void mostrarProductos() {
        System.out.println("- Productos del rubro: " + denominacion);
        for (Producto producto : productos) {
            System.out.println("- Denominacion: " + producto.getDenominacion() +
                    ", Tipo: " + producto.getTipo() + ", Unidad de medida: " + producto.getUnidadMedida() +
                    ", Receta: " + producto.getReceta() +
                    ", Tiempo estimado cocina: " + producto.getTiempoEstimadoCocina() +
                    ", Precio compra: " + producto.getPrecioCompra() + ", Precio venta: " + producto.getPrecioVenta() +
                    ", Stock minimo: " + producto.getStockMinimo() + ", Stock actual: " + producto.getStockActual());
        }
    }
}
