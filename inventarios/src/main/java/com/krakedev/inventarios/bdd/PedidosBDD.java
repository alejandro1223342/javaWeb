package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.CabeceraPedido;
import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class PedidosBDD {
	public void insertar(CabeceraPedido cabeceraPedido) throws KrakeDevException {

		Connection con = null;
		ResultSet rsClave = null;
		int codigoCabecera = 0;
		PreparedStatement psDet=null;
		try {
			con = ConexionBDD.obtenerConexion();

			Date fechaActual = new Date();
			java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());

			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO public.cabecera_pedido (prov_identificador, cabp_fecha, est_cod) VALUES (?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, cabeceraPedido.getProvIdentificador().getProvIdentificador());
			ps.setDate(2, fechaSQL);
			ps.setString(3, "S");

			ps.executeUpdate();
			rsClave = ps.getGeneratedKeys();

			if (rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}

			ArrayList<DetallePedido> detallesPedido = cabeceraPedido.getDetalles();
			DetallePedido det;
			for(int i=0;i<detallesPedido.size();i++) {
				det = detallesPedido.get(i);
				psDet=con.prepareStatement("INSERT INTO detalle_pedido( "
						+ "	cabp_id, prod_id, det_cantidadsolicitada, det_subtotal, det_cantidadrecibida) "
						+ "	VALUES (?, ?, ?, ?, ?)");
				
				psDet.setInt(1, codigoCabecera);
				psDet.setInt(2, det.getProdId().getProId());
				psDet.setInt(3, det.getDetCantidadSolicitada());
				BigDecimal pv = det.getProdId().getProdPrecioVenta();
				BigDecimal cantidad = new BigDecimal(det.getDetCantidadSolicitada());
				BigDecimal subtotal= pv.multiply(cantidad);
				psDet.setBigDecimal(4, subtotal);
				psDet.setInt(5, 0);
				
				psDet.executeUpdate();
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar clientes. Detalle: " + e.getMessage());
		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
