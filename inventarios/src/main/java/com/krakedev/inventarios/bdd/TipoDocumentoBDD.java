package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.CatalogoTipoDocumentos;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class TipoDocumentoBDD {
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
}
