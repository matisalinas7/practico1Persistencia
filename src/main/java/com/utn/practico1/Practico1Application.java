package com.utn.practico1;

import com.utn.practico1.entidades.*;
import com.utn.practico1.enums.EstadoPedido;
import com.utn.practico1.enums.FormaPagoFactura;
import com.utn.practico1.enums.TipoEnvioPedido;
import com.utn.practico1.enums.TipoProducto;
import com.utn.practico1.repositorios.ClienteRepository;
import com.utn.practico1.repositorios.DomicilioRepository;
import com.utn.practico1.repositorios.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Practico1Application {

	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(Practico1Application.class, args);
	}

	@Bean
	public CommandLineRunner init(ClienteRepository clienteRepository1, RubroRepository rubroRepository1) {
		return args -> {
			System.out.println("----- ESTOY FUNCIONANDO -----");

			// Crear instancia de rubro
			Rubro rubro1 = Rubro.builder()
					.denominacion("Hamburguesas")
					.build();

			// Crear instancias de productos
			Producto producto1 = Producto.builder()
					.tipo(TipoProducto.INSUMO)
					.tiempoEstimadoCocina(60)
					.denominacion("Cheeseburger")
					.precioCompra(800)
					.precioVenta(2000)
					.stockMinimo(5)
					.stockActual(40)
					.unidadMedida("unidad1")
					.receta("receta1")
					.build();
			Producto producto2 = Producto.builder()
					.tipo(TipoProducto.INSUMO)
					.tiempoEstimadoCocina(60)
					.denominacion("Bacon burger")
					.precioCompra(1000)
					.precioVenta(2500)
					.stockMinimo(8)
					.stockActual(30)
					.unidadMedida("unidad2")
					.receta("receta2")
					.build();

			// Agregar productos al rubro
			rubro1.agregarProducto(producto1);
			rubro1.agregarProducto(producto2);

			// Guardar rubro
			rubroRepository.save(rubro1);

			// Crear instancia Cliente
			Cliente cliente1 = Cliente.builder()
					.nombre("Juan")
					.apellido("Perez")
					.telefono("987654321")
					.email("juanperez@gmail.com")
					.build();

			// Crear instancia domicilio
			Domicilio domicilio1 = Domicilio.builder()
					.localidad("Lujan de Cuyo")
					.calle("Calle Falsa")
					.numero("123")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.localidad("Godoy Cruz")
					.calle("Calle Peru")
					.numero("456")
					.build();

			// Agregar domicilios a cliente
			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);

			// Crear instancia detalle pedido
			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(4000)
					.build();
			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(2500)
					.build();
			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(6000)
					.build();

			// Configurar fecha
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			String fechaString = "2023-01-01";

			// Parsear la cadena en un objeto Date
			Date fecha = formatoFecha.parse(fechaString);

			// Crear instancia Pedido
			Pedido pedido1 = Pedido.builder()
					.fecha(fecha)
					.total(6500)
					.estado(EstadoPedido.ENTREGADO)
					.tipoEnvio(TipoEnvioPedido.DELIVERY)
					.build();
			Pedido pedido2 = Pedido.builder()
					.fecha(fecha)
					.total(6000)
					.estado(EstadoPedido.PREPARACION)
					.tipoEnvio(TipoEnvioPedido.RETIRA)
					.build();

			// Crear instancia de factura
			Factura factura1 = Factura.builder()
					.fecha(fecha)
					.total(6500)
					.numero(1)
					.descuento(500)
					.formaPago(FormaPagoFactura.MERCADOPAGO)
					.build();
			Factura factura2 = Factura.builder()
					.fecha(fecha)
					.total(6000)
					.numero(2)
					.descuento(590)
					.formaPago(FormaPagoFactura.EFECTIVO)
					.build();

			// Agregar detalle al pedido
			pedido1.agregarDetallePedido(detallePedido1);
			pedido1.agregarDetallePedido(detallePedido2);
			pedido2.agregarDetallePedido(detallePedido3);

			// Agregar pedidos al cliente
			cliente1.agregarPedido(pedido1);
			cliente1.agregarPedido(pedido2);

			// Vincular el detalle pedido con el producto
			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto1);

			// Vincular factura con pedido
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);

			// Guardar el objeto cliente en la base de datos
			clienteRepository.save(cliente1);

			// Recuperar el objeto Rubro desde la base de datos
			Rubro rubroRecuperado = rubroRepository.findById(rubro1.getId()).orElse(null);
			if (rubroRecuperado != null) {
				System.out.println("- Denominacion: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProductos();
			}

			// Recuperar el objeto Cliente desde la base de datos
			Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);

			if (clienteRecuperado != null) {
				System.out.println("\n");
				System.out.println("- Nombre: " + clienteRecuperado.getNombre());
				System.out.println("- Apellido: " + clienteRecuperado.getApellido());
				System.out.println("- Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("- Email: " + clienteRecuperado.getEmail());
				System.out.println("\n");
				clienteRecuperado.mostrarDomicilios();
				System.out.println("\n");
				clienteRecuperado.mostrarPedidos();
			}
		};


	  }
}
