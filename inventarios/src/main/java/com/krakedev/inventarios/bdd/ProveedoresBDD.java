package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Catalogo;
import com.krakedev.inventarios.entidades.CatalogoTipoDocumentos;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.entidades.UnidadesMedida;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProveedoresBDD {

	public ArrayList<Proveedor> buscar(String subcadena) throws KrakeDevException {
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proveedor proveedor = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(
					"select prov_identificador,cat_doc.cat_id_tipodoc,cat_doc.cat_nombre,prov_nombre,prov_telefono, prov_correo, prov_direccion "
							+ "from proveedores, catalogo_tipo_documentos as cat_doc where proveedores.cat_id_tipodoc = cat_doc.cat_id_tipodoc "
							+ "and upper(prov_nombre) like ?");
			ps.setString(1, "%" + subcadena.toUpperCase() + "%"); // Convierto a mayusculas
			rs = ps.executeQuery();

			while (rs.next()) {

				String identificador = rs.getString("prov_identificador");
				String codigoTipoDocumento = rs.getString("cat_id_tipodoc");
				String nombreTipoDocumento = rs.getString("cat_nombre");
				String nombre = rs.getString("prov_nombre");
				String telefono = rs.getString("prov_telefono");
				String correo = rs.getString("prov_correo");
				String direccion = rs.getString("prov_direccion");

				CatalogoTipoDocumentos td = new CatalogoTipoDocumentos(codigoTipoDocumento, nombreTipoDocumento);
				proveedor = new Proveedor(identificador, td, nombre, telefono, correo, direccion);
				proveedores.add(proveedor);
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

		return proveedores;
	}

	public ArrayList<CatalogoTipoDocumentos> recuperarTodos() throws KrakeDevException {
		ArrayList<CatalogoTipoDocumentos> catalagoDoc = new ArrayList<CatalogoTipoDocumentos>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CatalogoTipoDocumentos catDoc = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("select cat_id_tipodoc,cat_nombre from catalogo_tipo_documentos ");
			rs = ps.executeQuery();

			while (rs.next()) {
				String tipoDoc = rs.getString("cat_id_tipodoc");
				String nombre = rs.getString("cat_nombre");

				catDoc = new CatalogoTipoDocumentos(tipoDoc, nombre);
				catalagoDoc.add(catDoc);
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

		return catalagoDoc;
	}

	public void insertar(Proveedor proveedor) throws KrakeDevException {

		Connection con = null;

		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO proveedores (prov_identificador, cat_id_tipodoc, prov_nombre, prov_telefono, prov_correo, prov_direccion) VALUES (?, ?, ?, ?, ?, ?);");
			ps.setString(1, proveedor.getProvIdentificador());
			ps.setString(2, proveedor.getTipoDocumento().getCatIdTipoDc());
			ps.setString(3, proveedor.getProvNombre());
			ps.setString(4, proveedor.getProvTelefono());
			ps.setString(5, proveedor.getProvCorreo());
			ps.setString(6, proveedor.getProvDireccion());

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
					+ "catalogo.cat_nombre,producto.prod_stock "
					+ "from producto,unidades_medida,catalogo "
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
}
