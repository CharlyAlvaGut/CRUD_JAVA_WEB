/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Charly Alvarado
 */
public class ClienteDaoJDBC {
    
   private static final String SQL_SELECT="SELECT id_cliente, nombre,apellido,email, telefono,saldo FROM cliente"; //Llama a todos los campos de la tabla
   private static final String SQL_SELECT_BY_ID="SELECT id_cliente, nombre,apellido,email, telefono,saldo FROM cliente WHERE id_cliente=?"; //Llama a todos los datos de un solo registro
   private static final String SQL_INSERT="INSERT INTO cliente(nombre,apellido,email,telefono,saldo) VALUES (?,?,?,?,?)"; //Inserta Valores a un determinado registro
   private static final String SQL_UPDATE="UPDATE cliente SET nombre=?, apellido=?, email=?, telefono=?, saldo=? WHERE id_cliente=?"; //Update o actualizar registros
   private static final String SQL_DELETE="DELETE FROM cliente WHERE id_cliente=?"; //Delete o eliminar cliente
   
   public List<Cliente> listar(){ //SQL_SELECT
       Connection conn=null;
       PreparedStatement stmt=null;
       ResultSet rs=null;
       List<Cliente> clientes=new ArrayList<Cliente>();
       
       try {
           conn=Conexion.getConnection();
           stmt=conn.prepareStatement(SQL_SELECT);
           rs=stmt.executeQuery();
           while(rs.next()){
               int idCliente=rs.getInt("id_cliente");
               String nombre=rs.getString("nombre");
               String apellido=rs.getString("apellido");
               String email=rs.getString("email");
               String telefono=rs.getString("telefono");
               double saldo=rs.getDouble("saldo");
               
               System.out.println(nombre+" "+apellido+" "+saldo);
               
               clientes.add(new Cliente(idCliente,nombre, apellido,email,telefono,saldo));
           }
           
       } catch (Exception e) {
           e.printStackTrace(System.out);
       }finally{
            Conexion.Close(rs);
            Conexion.Close(stmt);
            Conexion.Close(conn);
       }
       
       return clientes;
   }
   
   public Cliente buscar(Cliente cliente){ //SQL_SELECT_BY_ID
       Connection conn=null;
       PreparedStatement stmt=null;
       ResultSet rs=null;
       
       try {
           conn=Conexion.getConnection();
           stmt=conn.prepareStatement(SQL_SELECT_BY_ID);
           stmt.setInt(1, cliente.getId_Cliente());
           rs=stmt.executeQuery();
           rs.next();
               String nombre=rs.getString("nombre");
               String apellido=rs.getString("apellido");
               String email=rs.getString("email");
               String telefono=rs.getString("telefono");
               double saldo=rs.getDouble("saldo");
    
           cliente.setNombre(nombre);
           cliente.setApellido(apellido);
           cliente.setEmail(email);
           cliente.setTelefono(telefono);
           cliente.setSaldo(saldo);
           
       } catch (Exception e) {
           e.printStackTrace(System.out);
       }finally{
            Conexion.Close(rs);
            Conexion.Close(stmt);
            Conexion.Close(conn);
       }
       
       return cliente;
   }
   
   public int insertar(Cliente cliente){ //SQL_INSERT
       Connection conn=null;
       PreparedStatement stmt=null;
       int rows=0;
       
       try {
           conn=Conexion.getConnection();
           stmt=conn.prepareStatement(SQL_INSERT);
           
           stmt.setString(1, cliente.getNombre());
           stmt.setString(2, cliente.getApellido());
           stmt.setString(3, cliente.getEmail());
           stmt.setString(4, cliente.getTelefono());
           
           stmt.setDouble(5, cliente.getSaldo());
           
           rows=stmt.executeUpdate();
       
           
       } catch (Exception e) {
           e.printStackTrace(System.out);
       }finally{
           
            Conexion.Close(stmt);
            Conexion.Close(conn);
       }
       
       return rows;
   }
   
   public int actualizar(Cliente cliente){ //SQL_UPDATE
       Connection conn=null;
       PreparedStatement stmt=null;
       int rows=0;
       
       try {
           conn=Conexion.getConnection();
           stmt=conn.prepareStatement(SQL_UPDATE);
           
           stmt.setString(1, cliente.getNombre());
           stmt.setString(2, cliente.getApellido());
           stmt.setString(3, cliente.getEmail());
           stmt.setString(4, cliente.getTelefono());
           stmt.setDouble(5, cliente.getSaldo());
           stmt.setInt(6, cliente.getId_Cliente());
           
           rows=stmt.executeUpdate();
       
           
       } catch (Exception e) {
           e.printStackTrace(System.out);
       }finally{
           
            Conexion.Close(stmt);
            Conexion.Close(conn);
       }
       
       return rows;
   }
   
   public int eliminar(Cliente cliente){ //SQL_DELETE
       Connection conn=null;
       PreparedStatement stmt=null;
       int rows=0;
       
       try {
           conn=Conexion.getConnection();
           stmt=conn.prepareStatement(SQL_DELETE);
           
           stmt.setInt(1, cliente.getId_Cliente());
           
           rows=stmt.executeUpdate();
       
           
       } catch (Exception e) {
           e.printStackTrace(System.out);
       }finally{
           
            Conexion.Close(stmt);
            Conexion.Close(conn);
       }
       
       return rows;
   }
    public static void main(String[] args) {
        ClienteDaoJDBC cliente = new ClienteDaoJDBC();
        cliente.listar();
    }
}
