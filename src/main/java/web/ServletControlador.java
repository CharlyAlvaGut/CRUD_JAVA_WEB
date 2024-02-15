/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import Datos.ClienteDaoJDBC;
import Dominio.Cliente;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Charly Alvarado
 */
@WebServlet(
   name = "ServletControlador",
   urlPatterns = {"/ServletControlador"}
)
public class ServletControlador extends HttpServlet {
   public ServletControlador() {
   }

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String accion = request.getParameter("accion");
      if (accion != null) {
         switch (accion) {
            case "editar":
               this.editarCliente(request, response);
               break;
            case "eliminar":
               this.eliminarCliente(request, response);
               break;
            default:
               this.accionDefault(request, response);
         }
      } else {
         this.accionDefault(request, response);
      }

   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String accion = request.getParameter("accion");
      if (accion != null) {
         switch (accion) {
            case "insertar":
               this.insertarCliente(request, response);
               break;
            case "modificar":
               this.modificarCliente(request, response);
               break;
            case "eliminar":
               this.eliminarCliente(request, response);
               break;
            default:
               this.accionDefault(request, response);
         }
      } else {
         this.accionDefault(request, response);
      }

   }

   protected void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      List<Cliente> clientes = (new ClienteDaoJDBC()).listar();
      System.out.println("clientes = " + clientes);
      HttpSession sesion = request.getSession();
      sesion.setAttribute("clientes", clientes);
      sesion.setAttribute("totalClientes", clientes.size());
      sesion.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
      response.sendRedirect("clientes.jsp");
   }

   private double calcularSaldoTotal(List<Cliente> clientes) {
      double saldoTotal = 0.0;

      Cliente cliente;
      for(Iterator var4 = clientes.iterator(); var4.hasNext(); saldoTotal += cliente.getSaldo()) {
         cliente = (Cliente)var4.next();
      }

      return saldoTotal;
   }

   protected void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int id_Cliente = Integer.parseInt(request.getParameter("id_cliente"));
      Cliente cliente = (new ClienteDaoJDBC()).buscar(new Cliente(id_Cliente));
      request.setAttribute("cliente", cliente);
      String jspEditar = "/WEB-INF/paginas/clientes/editarCliente.jsp";
      request.getRequestDispatcher(jspEditar).forward(request, response);
   }

   protected void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int id_Cliente = Integer.parseInt(request.getParameter("id_cliente"));
      String nombre = request.getParameter("nombre");
      String apellido = request.getParameter("apellido");
      String email = request.getParameter("email");
      String telefno = request.getParameter("telefono");
      double saldo = 0.0;
      String saldoString = request.getParameter("saldo");
      if (saldoString != null && !"".equals(saldoString)) {
         saldo = Double.parseDouble(saldoString);
      }

      Cliente cliente = new Cliente(id_Cliente, nombre, apellido, email, telefno, saldo);
      int resgistrosModificados = (new ClienteDaoJDBC()).actualizar(cliente);
      this.accionDefault(request, response);
   }

   protected void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int id_Cliente = Integer.parseInt(request.getParameter("id_cliente"));
      Cliente cliente = new Cliente(id_Cliente);
      int registrosModificados = (new ClienteDaoJDBC()).eliminar(cliente);
      this.accionDefault(request, response);
   }

   protected void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String nombre = request.getParameter("nombre");
      String apellido = request.getParameter("apellido");
      String email = request.getParameter("email");
      String telefno = request.getParameter("telefono");
      double saldo = 0.0;
      String saldoString = request.getParameter("saldo");
      if (saldoString != null && !"".equals(saldoString)) {
         saldo = Double.parseDouble(saldoString);
      }

      Cliente cliente = new Cliente(nombre, apellido, email, telefno, saldo);
      int resgistrosModificados = (new ClienteDaoJDBC()).insertar(cliente);
      this.accionDefault(request, response);
   }
}
