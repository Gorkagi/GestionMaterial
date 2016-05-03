package persistencia;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

import dominio.Persona;
import dominio.Recurso;

public class DAORecursos
{ 
	public static Recurso[] getListaRecursos() throws Exception
	{
		Statement stmt;
		ResultSet result;
		String strSQL;
		int n,i;
		Recurso[] listaRec=null;

		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT COUNT(*) as cuenta"+
					" FROM Recurso";
			result = stmt.executeQuery(strSQL);
			result.next();
			n=result.getInt("cuenta");
			result.close();
			if(n!=0)
			{
				listaRec=new Recurso[n];
				stmt=PoolConexiones.getConexion().createStatement();
				strSQL="SELECT idRecurso, nombre, descripcion, ubicacion,dniResponsable, idCategoriaRecurso"+
						" FROM Recurso";
				result = stmt.executeQuery(strSQL);
				result.next();
				for(i=0;i<n;i++)
				{
					listaRec[i]=new Recurso(result.getInt("idRecurso"),result.getString("nombre"),
							result.getString("descripcion"),result.getString("ubicacion"),
							result.getInt("dniResponsable"), identificarTipoRecurso(result.getInt("idCategoriaRecurso")));
					result.next();          
				}
				result.close();
			}
			return listaRec;
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<String> obtenerTiposRecursos(){
		Statement stmt;
		ResultSet result;
		String strSQL;
		ArrayList<String> tipos = null;
		try
		{

			tipos = new ArrayList<String>();
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT descripcion"+
					" FROM categoriaRecurso";
			result = stmt.executeQuery(strSQL);
			while (result.next()){
				tipos.add(result.getString("descripcion"));
			}
			result.close();
		} catch (Exception e ){
			e.printStackTrace();
		}
		return tipos;
	}
	public static String identificarTipoRecurso(int idCategoriaRecurso) {
		Statement stmt;
		ResultSet result;
		String strSQL;
		String descrip;
		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="SELECT descripcion "+
					" FROM categoriaRecurso"+
					" WHERE idCategoriaRecurso="+idCategoriaRecurso;
			result = stmt.executeQuery(strSQL);
			if(!result.next()) return null;
			descrip = result.getString("descripcion");
			result.close();
			return descrip;

		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	public static void InsertarRecurso ( String nombre, String ubicacion, String descripcion, int dniResponsable, int tipoRecurso){

		Statement stmt;
		int result;
		String strSQL;
		try
		{
			stmt=PoolConexiones.getConexion().createStatement();
			strSQL="INSERT INTO RECURSO "+
					" VALUES (0,'"+nombre+"','"+descripcion+"','"+ubicacion+"',"+dniResponsable+","+tipoRecurso+")";
			result = stmt.executeUpdate(strSQL);


		}catch(SQLException e) {
			e.printStackTrace();
			return;
		}
	}
	public static void eliminarRecurso(int id) throws SQLException {
		Statement stmt;
		int result;
		String strSQL;
		stmt=PoolConexiones.getConexion().createStatement();
		strSQL="DELETE FROM  RECURSO "+
				" WHERE idRecurso = " +id;
		result = stmt.executeUpdate(strSQL);
	}
	public static void modificarRecurso(int id, String nombre, String ubicacion, 
			String descripcion, int idResponsable, int tipoRecurso) throws SQLException {

		Statement stmt;
		int result;
		String strSQL;
		stmt=PoolConexiones.getConexion().createStatement();
		strSQL="UPDATE  RECURSO "+
				"SET NOMBRE = '"+nombre+"',"+
				"DESCRIPCION='"+descripcion+"',"+
				"UBICACION='"+ubicacion+"',"+
				"DNIRESPONSABLE = " + idResponsable +","+
				"idCategoriaRecurso="+tipoRecurso+	
				" WHERE idRecurso = " +id ;

		result = stmt.executeUpdate(strSQL);
	}


}

