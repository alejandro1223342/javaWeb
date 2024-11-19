package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.CabeceraPedido;
import com.krakedev.inventarios.entidades.Catalogo;
import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.EstadoPedido;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.UnidadesMedida;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class PedidosBDD {
	public void insertar(CabeceraPedido cabeceraPedido) throws KrakeDevException {

		Connection con = null;
		ResultSet rsClave = null;
		int codigoCabecera = 0;
		PreparedStatement psDet = null;
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
			for (int i = 0; i < detallesPedido.size(); i++) {
				det = detallesPedido.get(i);
				psDet = con.prepareStatement("INSERT INTO detalle_pedido( "
						+ "	cabp_id, prod_id, det_cantidadsolicitada, det_subtotal, det_cantidadrecibida) "
						+ "	VALUES (?, ?, ?, ?, ?)");

				psDet.setInt(1, codigoCabecera);
				psDet.setInt(2, det.getProdId().getProId());
				psDet.setInt(3, det.getDetCantidadSolicitada());
				BigDecimal pv = det.getProdId().getProdPrecioVenta();
				BigDecimal cantidad = new BigDecimal(det.getDetCantidadSolicitada());
				BigDecimal subtotal = pv.multiply(cantidad);
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

	public void actualizar(CabeceraPedido cabeceraPedido) throws KrakeDevException {

		Connection con = null;

		PreparedStatement psHistorial = null;
		// Date fechaActual = new Date(); // Fecha actual
		// Timestamp fechaHoraActual = new Timestamp(fechaActual.getTime());

		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con.prepareStatement("update cabecera_pedido set est_cod = ? where cabp_id= ?");

			ps.setString(1, "R");
			ps.setInt(2, cabeceraPedido.getCabpId());
			ps.executeUpdate();

			ArrayList<DetallePedido> detallesPedido = cabeceraPedido.getDetalles();
			DetallePedido det;

			for (int i = 0; i < detallesPedido.size(); i++) {
				det = detallesPedido.get(i);

				PreparedStatement psDetalle = con.prepareStatement(
						"UPDATE detalle_pedido SET det_cantidadrecibida = ?, det_subtotal = ? WHERE det_id = ?");
				psDetalle.setInt(1, det.getDetCantidadRecibida()); // Nueva cantidad recibida
				BigDecimal pv = det.getProdId().getProdPrecioVenta();
				BigDecimal cantidad = new BigDecimal(det.getDetCantidadRecibida());
				BigDecimal subtotal = pv.multiply(cantidad);
				psDetalle.setBigDecimal(2, subtotal); // Nuevo subtotal
				psDetalle.setInt(3, det.getDetId()); // ID del detalle
				psDetalle.executeUpdate();

				psHistorial = con.prepareStatement("INSERT INTO public.historial_stock( "
						+ " his_fecha, his_referencia, prod_id, his_cantidad) " + "	VALUES (?, ?, ?, ?);");

				psHistorial.setDate(1, new java.sql.Date(System.currentTimeMillis()));
				psHistorial.setString(2, "PEDIDO "+det.getDetId()); // Aquí debes definir la referencia que necesitas
				psHistorial.setInt(3, det.getProdId().getProId()); // ID del producto
				psHistorial.setInt(4, det.getDetCantidadRecibida()); // Cantidad recibida
			    psHistorial.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al actualizar clientes. Detalle: " + e.getMessage());
		} catch (KrakeDevException e) {
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
	
	
	public ArrayList<DetallePedido> buscarPorProveedor(String proveedor) throws KrakeDevException {
	    ArrayList<DetallePedido> pedidos = new ArrayList<>();

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        con = ConexionBDD.obtenerConexion();
	        ps = con.prepareStatement("select detalle_pedido.det_id,detalle_pedido.cabp_id, "
	                + "cabecera_pedido.cabp_fecha, estado_pedidos.est_cod, estado_pedidos.est_descripcion, "
	                + "producto.prod_nombre, unidades_medida.udm_nombre, unidades_medida.udm_descripcion, "
	                + "cast(producto.prod_precioventa as decimal(6,2)),producto.prod_tieneiva,cast(producto.prod_coste as decimal (6,2)),catalogo.cat_nombre, "
	                + "producto.prod_stock,detalle_pedido.det_cantidadsolicitada,cast(detalle_pedido.det_subtotal as decimal(6,2)), "
	                + "detalle_pedido.det_cantidadrecibida "
	                + "from detalle_pedido, cabecera_pedido, producto, estado_pedidos, "
	                + "unidades_medida, catalogo "
	                + "where detalle_pedido.cabp_id = cabecera_pedido.cabp_id "
	                + "and detalle_pedido.prod_id = producto.prod_id "
	                + "and estado_pedidos.est_cod = cabecera_pedido.est_cod "
	                + "and unidades_medida.udm_id = producto.udm_id "
	                + "and catalogo.cat_id = producto.cat_id_tipoproducto "
	                + "and cabecera_pedido.prov_identificador = ?");
	        ps.setString(1, proveedor);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            // Recuperar los valores del ResultSet
	            int detId = rs.getInt("det_id");
	            int cabpId = rs.getInt("cabp_id");
	            Date cabpFecha = rs.getDate("cabp_fecha");
	            String estCod = rs.getString("est_cod");
	            String estDesc = rs.getString("est_descripcion");
	            String prodNombre = rs.getString("prod_nombre");
	            String udmNombre = rs.getString("udm_nombre");
	            String udmDesc = rs.getString("udm_descripcion");
	            BigDecimal prodPrecioVenta = rs.getBigDecimal("prod_precioventa");
	            Boolean prodTieneIva = rs.getBoolean("prod_tieneiva");
	            BigDecimal prodCoste = rs.getBigDecimal("prod_coste");
	            String catNombre = rs.getString("cat_nombre");
	            int prodStock = rs.getInt("prod_stock");
	            int detCantidadSoli=rs.getInt("det_cantidadsolicitada");
	            BigDecimal detSubtotal = rs.getBigDecimal("det_subtotal");
	            int detCantidadReci=rs.getInt("det_cantidadrecibida");

	            // Crear objetos relacionados
	            CabeceraPedido cabp = new CabeceraPedido();
	            cabp.setCabpId(cabpId);
	            
	            EstadoPedido estadoP = new EstadoPedido();
	            estadoP.setEstCod(estCod);
	            estadoP.setEstDescripcion(estDesc);

	            UnidadesMedida catUnidadM = new UnidadesMedida();
	            catUnidadM.setUdmNombre(udmNombre);
	            catUnidadM.setUdmDescripcion(udmDesc);

	            Catalogo catalogo = new Catalogo();
	            catalogo.setCatNombre(catNombre);
	            
	            Producto producto = new Producto();
	            producto.setProdNombre(prodNombre);
	            
	            

	            // Crear el objeto CabeceraPedido y asignar los valores
	            DetallePedido detalleP = new DetallePedido();
	            detalleP.setDetId(detId);
	            detalleP.setCabpId(cabp);
	            detalleP.setProdId(producto);
	            detalleP.setDetCantidadSolicitada(detCantidadSoli);
	            detalleP.setDetSubtotal(detSubtotal);
	            detalleP.setDetCantidadRecibida(detCantidadReci);
	            
	            pedidos.add(detalleP);
	        }

	    } catch (KrakeDevException e) {
	        e.printStackTrace();
	        throw e;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new KrakeDevException("Error al consultar. Detalle: " + e.getMessage());
	    } finally {
	        // Cerrar los recursos en el bloque finally
	    	if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    }

	    return pedidos;
	}


}
