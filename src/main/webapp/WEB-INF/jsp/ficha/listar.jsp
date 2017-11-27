<%-- 
    Document   : listar
    Created on : 27-11-2017, 12:31:44
    Author     : Fabi
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Atenciones</title>
        
        <jsp:include page="/WEB-INF/jspf/header.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />
            <jsp:include page="/WEB-INF/jspf/mensajes.jsp" />



            <c:if test="${empty atenciones}">
                No hay atenciones para mostrar.
            </c:if>            

            <c:if test="${!empty atenciones}">
                <!-- tabla con atenciones -->
                <table class="table table-striped">
                    <thead class="thead-inverse">
                        <tr>
                            <th>Cliente:</th>
                            <th>RUT</th>
                            <th>Nombre</th>
                            <th>Motivo</th>
                            <th>Indicaciones</th>
                            <th>Fecha</th>
                            <th>Valor Atencion</th>
                            <th>Fue atendido por:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${atenciones}" var="c">
                            <tr>
                                
                                <td></td>
                                <td>${c.cliente.rut}</td>
                                <td>${c.cliente.nombre}</td>
                                <td>${c.motivoConsulta}</td>
                                <td>${c.indicaciones}</td>
                                <td><fmt:formatDate value="${c.fechaAtencion.time}" pattern="dd 'de' MMMM YYYY" /></td>
                                <td>${c.valorAtencion}</td>
                                <th>${c.trabajador.nombre}</th>
                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>


        </div>

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>

