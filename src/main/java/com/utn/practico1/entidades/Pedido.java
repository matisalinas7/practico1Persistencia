package com.utn.practico1.entidades;

import com.utn.practico1.enums.EstadoPedido;
import com.utn.practico1.enums.TipoEnvioPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido extends BaseEntidad {

    @Temporal(TemporalType.DATE)
    private Date fecha;
    private double total;
    private TipoEnvioPedido tipoEnvio;
    private EstadoPedido estado;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER) //Es necesario agregar el parametro de "FetchType.EAGER"
    @JoinColumn(name = "Pedido_id")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    public void agregarDetallePedido(DetallePedido detallePedi){
        detallePedidos.add(detallePedi);
    }
}
