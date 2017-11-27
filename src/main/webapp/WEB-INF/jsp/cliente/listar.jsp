<%-- 
    Document   : listar
    Created on : 27-11-2017, 12:27:52
    Author     : Fabi
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Clientes</title>
        
        <jsp:include page="/WEB-INF/jspf/header.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />
            <jsp:include page="/WEB-INF/jspf/mensajes.jsp" />


            <div class="row">
                <div class="col">
                    <a href="clientes?op=crear" class="btn btn-success fa fa-plus"> Crear</a>
                </div>
            </div>


            <c:if test="${empty clientes}">
                No hay clientes para mostrar.
            </c:if>            

            <c:if test="${!empty clientes}">
                <!-- tabla con clientes -->
                <table class="table table-striped">
                    <thead class="thead-inverse">
                        <tr>
                            <th>ID</th>
                            <th>RUT</th>
                            <th>Nombre</th>
                            <th>Telefono</th>
                            <th>Correo</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${clientes}" var="c">
                            <tr>
                                <td>${c.id}</td>
                                <td>${c.rut}</td>
                                <td>${c.nombre}</td>
                                <td>${c.telefono}</td>
                                <td>${c.correo}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>


        </div>

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>
