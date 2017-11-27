<%-- 
    Document   : listar
    Created on : 27-11-2017, 7:39:08
    Author     : Fabi
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Trabajadores</title>
        
        <jsp:include page="/WEB-INF/jspf/header.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />
            <jsp:include page="/WEB-INF/jspf/mensajes.jsp" />


            <div class="row">
                <div class="col">
                    <a href="trabajadores?op=crear" class="btn btn-success fa fa-plus"> Crear</a>
                </div>
            </div>


            <c:if test="${empty trabajadores}">
                No hay trabajadores para mostrar.
            </c:if>            

            <c:if test="${!empty trabajadores}">
                <!-- tabla con trabajadores -->
                <table class="table table-striped">
                    <thead class="thead-inverse">
                        <tr>
                            <th>ID</th>
                            <th>RUT</th>
                            <th>Nombre</th>
                            <th>Telefono</th>
                            <th>Tipo Trabajador</th>
                            <th>Usuario</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${trabajadores}" var="t">
                            <tr>
                                <td>${t.id}</td>
                                <td>${t.rut}</td>
                                <td>${t.nombre}</td>
                                <td>${t.tipoTrabajador.nombre}</td>
                                <td>${t.usuario}</td>
                                
                                <td>
                                    <form method="get" action="trabajadores">
                                        <input type="hidden" name="op" value="eliminar" />
                                        <input type="hidden" name="id" value="${t.id}" />
                                        <button type="submit" class="btn btn-danger">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>


        </div>

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>