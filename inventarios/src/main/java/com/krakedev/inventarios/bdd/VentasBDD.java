package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.CabeceraPedido;
import com.krakedev.inventarios.entidades.CabeceraVentas;
import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.DetalleVentas;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class VentasBDD {
	public void insertar(CabeceraVentas cabeceraVentas) throws KrakeDevException {

		Connection con = null;
		ResultSet rsClave = null;
		int codigoCabecera = 0;
		PreparedStatement psDet = null;
		PreparedStatement psDetAct = null;
		PreparedStatement psHistorial = null;

		try {
			con = ConexionBDD.obtenerConexion();

			// Inserción de la cabecera de ventas
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO public.cabecera_ventas(cabv_fecha, cabv_totalsiniva, cabv_iva, cabv_total) VALUES (?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			ps.setBigDecimal(2, BigDecimal.valueOf(0.0)); // Inicialmente 0.0
			ps.setBigDecimal(3, BigDecimal.valueOf(0.0)); // Inicialmente 0.0
			ps.setBigDecimal(4, BigDecimal.valueOf(0.0)); // Inicialmente 0.0
			ps.executeUpdate();

			// Obtener el ID generado para la cabecera
			rsClave = ps.getGeneratedKeys();
			if (rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}

			// Variables para los totales
			BigDecimal totalSinIva = BigDecimal.valueOf(0.0);
			BigDecimal totalIva = BigDecimal.valueOf(0.0);
			BigDecimal totalConIva = BigDecimal.valueOf(0.0);

			// Procesar los detalles de la venta
			ArrayList<DetalleVentas> detallesVentas = cabeceraVentas.getDetalles();
			DetalleVentas det;
			for (int i = 0; i < detallesVentas.size(); i++) {
				det = detallesVentas.get(i);
				psDet = con.prepareStatement("INSERT INTO public.detalle_ventas( "
						+ "	cabv_id, prod_id, det_cantidad, det_precioventa, det_subtotal, det_subtotalconiva) "
						+ "	VALUES (?, ?, ?, ?, ?, ?)");

				psDet.setInt(1, codigoCabecera);
				psDet.setInt(2, det.getProdId().getProId());
				psDet.setInt(3, det.getDetCantidad());
				psDet.setBigDecimal(4, det.getDetPrecioVenta());

				// Subtotal sin IVA
				BigDecimal precioVenta = det.getDetPrecioVenta();
				BigDecimal cantidad = new BigDecimal(det.getDetCantidad());
				BigDecimal subtotal = precioVenta.multiply(cantidad);
				psDet.setBigDecimal(5, subtotal);

				// Subtotal con IVA
				BigDecimal subtotalConIva;
				if (det.getProdId().isProdTieneIva()) {
					subtotalConIva = subtotal.multiply(BigDecimal.valueOf(1.12));
					totalIva = totalIva.add(subtotalConIva.subtract(subtotal)); // Acumulando el IVA
				} else {
					subtotalConIva = subtotal; // Si no tiene IVA
				}
				psDet.setBigDecimal(6, subtotalConIva);
				psDet.executeUpdate();
				
				ResultSet rsDet = psDet.getGeneratedKeys();
				if (rsDet.next()) {
				    det.setDetId(rsDet.getInt(1));  // Asignar el ID generado al detalle
				}
				System.out.println(rsDet);
				// Acumulando los totales
				totalSinIva = totalSinIva.add(subtotal);
				totalConIva = totalConIva.add(subtotalConIva);
				
				Date fechaActual = new Date();
				java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());

				psHistorial = con.prepareStatement("INSERT INTO historial_stock( "
						+ " his_fecha, his_referencia, prod_id, his_cantidad) " + "	VALUES (?, ?, ?, ?);");
				psHistorial.setDate(1, new java.sql.Date(System.currentTimeMillis()));
				psHistorial.setString(2, "VENTA "+ codigoCabecera); // Aquí debes definir la referencia que necesitas
				psHistorial.setInt(3, det.getProdId().getProId()); // ID del producto
				psHistorial.setInt(4, det.getDetCantidad()); // Cantidad recibida
			    psHistorial.executeUpdate();
			}

			// El IVA es la diferencia entre el total con IVA y el total sin IVA
			totalIva = totalConIva.subtract(totalSinIva);

			// Actualizar la cabecera con los valores calculados
			psDetAct = con.prepareStatement(
					"UPDATE cabecera_ventas SET cabv_totalsiniva = ?, cabv_iva = ?, cabv_total = ? WHERE cabv_id = ?");
			psDetAct.setBigDecimal(1, totalSinIva); // Total sin IVA
			psDetAct.setBigDecimal(2, totalIva); // IVA
			psDetAct.setBigDecimal(3, totalConIva); // Total con IVA
			psDetAct.setInt(4, codigoCabecera); // ID de la cabecera
			psDetAct.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar CabeceraVentas. Detalle: " + e.getMessage());
		} catch (KrakeDevException e) {
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	} 

}
