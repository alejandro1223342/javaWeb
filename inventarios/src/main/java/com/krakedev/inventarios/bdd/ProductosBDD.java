package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Catalogo;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.UnidadesMedida;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProductosBDD {
	public ArrayList<Producto> buscarP(String subcadena) throws KrakeDevException {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Producto producto = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("select producto.prod_id,producto.prod_nombre,unidades_medida.udm_id, "
					+ "unidades_medida.udm_nombre,unidades_medida.udm_descripcion, "
					+ "cast(producto.prod_precioventa as decimal(6,2)),producto.prod_tieneiva, "
					+ "cast(producto.prod_coste as decimal (5,4)), producto.cat_id_tipoproducto, "
					+ "catalogo.cat_nombre,producto.prod_stock " + "from producto,unidades_medida,catalogo "
					+ "where producto.udm_id = unidades_medida.udm_id and producto.cat_id_tipoproducto=catalogo.cat_id "
					+ "and upper(producto.prod_nombre) like ?");

			ps.setString(1, "%" + subcadena.toUpperCase() + "%"); // Convierto a mayusculas
			rs = ps.executeQuery();

			while (rs.next()) {

				int productoId = rs.getInt("prod_id");
				String productoNombre = rs.getString("prod_nombre");
				int UnidadMedidaId = rs.getInt("udm_id");
				String nombreUnidadMedida = rs.getString("udm_nombre");
				String descripcionUnidadMedida = rs.getString("udm_descripcion");
				BigDecimal precioVenta = rs.getBigDecimal("prod_precioventa");
				boolean tieneIva = rs.getBoolean("prod_tieneiva");
				BigDecimal productoCoste = rs.getBigDecimal("prod_coste");
				int catalogoTipoProducto = rs.getInt("cat_id_tipoproducto");
				String nombreTipoProducto = rs.getString("cat_nombre");
				int productoStock = rs.getInt("prod_stock");

				UnidadesMedida udm = new UnidadesMedida();
				udm.setUdmId(UnidadMedidaId);
				udm.setUdmNombre(nombreTipoProducto);
				udm.setUdmDescripcion(descripcionUnidadMedida);

				Catalogo catalogo = new Catalogo();
				catalogo.setCatId(catalogoTipoProducto);
				catalogo.setCatNombre(nombreTipoProducto);

				producto = new Producto();
				producto.setProId(productoId);
				producto.setProdNombre(productoNombre);
				producto.setUnidadesMedida(udm);
				producto.setProdPrecioVenta(precioVenta);
				producto.setProdTieneIva(tieneIva);
				producto.setProdCoste(productoCoste);
				producto.setCatIdTipoProducto(catalogo);
				producto.setProdStock(productoStock);

				productos.add(producto);
			}

		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle: " + e.getMessage());
		}

		return productos;
	}

	public void insertarProducto(Producto producto) throws KrakeDevException {

		Connection con = null;

		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO producto(prod_nombre, udm_id, prod_precioventa, prod_tieneiva, prod_coste, cat_id_tipoproducto, prod_stock) "
							+ "	VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, producto.getProdNombre());
			ps.setInt(2, producto.getUnidadesMedida().getUdmId());
			ps.setBigDecimal(3, producto.getProdPrecioVenta());
			ps.setBoolean(4, producto.isProdTieneIva());
			ps.setBigDecimal(5, producto.getProdCoste());
			ps.setInt(6, producto.getCatIdTipoProducto().getCatId());
			ps.setInt(7, producto.getProdStock());

			ps.executeUpdate();

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

	public void actualizar(Producto producto) throws KrakeDevException {

		Connection con = null;

		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con.prepareStatement("UPDATE producto "
					+ "	SET prod_nombre=?, udm_id=?, prod_precioventa=?, prod_tieneiva=?, prod_coste=?, cat_id_tipoproducto=?, prod_stock=? "
					+ "	WHERE prod_id= ?");
			ps.setString(1, producto.getProdNombre());
			ps.setInt(2, producto.getUnidadesMedida().getUdmId());
			ps.setBigDecimal(3, producto.getProdPrecioVenta());
			ps.setBoolean(4, producto.isProdTieneIva());
			ps.setBigDecimal(5, producto.getProdCoste());
			ps.setInt(6, producto.getCatIdTipoProducto().getCatId());
			ps.setInt(7, producto.getProdStock());
			ps.setInt(8, producto.getProId());


			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al actualizar producto. Detalle: " + e.getMessage());
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

}
