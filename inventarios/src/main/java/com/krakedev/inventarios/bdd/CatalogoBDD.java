package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Catalogo;
import com.krakedev.inventarios.entidades.CatalogoTipoDocumentos;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class CatalogoBDD {
	public void insertarCatalogo(Catalogo catalogo) throws KrakeDevException {

		Connection con = null;

		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO catalogo (cat_nombre, cat_descripcion, cat_padre) VALUES (?, ?, ?)");
			ps.setString(1, catalogo.getCatNombre());
			ps.setString(2, catalogo.getCatDescripcion());
			if (catalogo.getCatPadre() == null || catalogo.getCatPadre().getCatId() <= 0) {
				ps.setNull(3, Types.INTEGER);
			} else {
				ps.setInt(3, catalogo.getCatPadre().getCatId());
			}

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar catalogos. Detalle: " + e.getMessage());
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

	public void actualizar(Catalogo catalogo) throws KrakeDevException {

		Connection con = null;

		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"UPDATE catalogo " + "	SET cat_nombre=?, cat_descripcion=?, cat_padre=? WHERE cat_id= ?;");
			ps.setString(1, catalogo.getCatNombre());
			ps.setString(2, catalogo.getCatDescripcion());
			if (catalogo.getCatPadre() == null || catalogo.getCatPadre().getCatId() <= 0) {
				ps.setNull(3, Types.INTEGER);
			} else {
				ps.setInt(3, catalogo.getCatPadre().getCatId());
			}
			ps.setInt(4, catalogo.getCatId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al actualizar catalogo. Detalle: " + e.getMessage());
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

	public ArrayList<Catalogo> recuperarTodos() throws KrakeDevException {
		ArrayList<Catalogo> catalago = new ArrayList<Catalogo>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Catalogo cat = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("select cat_id,cat_nombre,cat_descripcion,cat_padre from catalogo");
			rs = ps.executeQuery();

			while (rs.next()) {
				int catId = rs.getInt("cat_id");
				String catNombre = rs.getString("cat_nombre");
				String catDes = rs.getString("cat_descripcion");
				int catPadre = rs.getInt("cat_padre");

				Catalogo catalo = new Catalogo();
				catalo.setCatId(catId);
				catalo.setCatNombre(catNombre);
				catalo.setCatDescripcion(catDes);

				
				cat = new Catalogo(catId, catNombre,catDes,catalo);
				catalago.add(cat);
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

		return catalago;
	}

}
