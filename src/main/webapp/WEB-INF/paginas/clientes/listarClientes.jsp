<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX" />

<section id="clientes">
    <div class="container">
        <div class="row">

            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Clientes</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Email</th>
                                <th>Telefono</th>
                                <th>Saldo</th>
                            </tr>

                        </thead>
                        <tbody>

                            <c:forEach var="cliente" items="${clientes}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${cliente.nombre}</td>
                                    <td> ${cliente.apellido}</td>
                                    <td> ${cliente.email}</td>
                                    <td> ${cliente.telefono}</td>
                                    <td><fmt:formatNumber value="${cliente.saldo}" type="currency" /> </td>
                                     
                                    <td>
                                              <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&id_cliente=${cliente.id_Cliente}"
                                               class ="btn btn-secondary">
                                                <i class="fas fa-angle-double-right"></i> Editar

                                            </a>  
                                        </td>    
                                    </tr>   
                                      
                            </c:forEach>

                        </tbody>
                    </table>
                </div>

            </div>
                    <!-- Totales -->
          <div class="col-md-3">
              <div class="card text-center bg-danger text-white mb-3">
                  <div class="card-body">
                      <h3>Saldo Total</h3>
                      <h4 class="display-4">
                          <fmt:formatNumber value="${saldoTotal}" type="currency" />
                      </h4>
                  </div>
              </div>
                  
             <div class="card text-center bg-success text-white mb-3">
                  <div class="card-body">
                      <h3>Total Clientes</h3>
                      <h4 class="display-4">
                          <i class="fas fa-user"> </i>${totalClientes}
                      </h4>
                  </div>
              </div>
          </div>
                      
          </div>
                      
 

        </div>    

</section>
            <jsp:include page="agregarCliente.jsp" />          