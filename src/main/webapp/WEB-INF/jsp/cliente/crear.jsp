<%-- 
    Document   : crear
    Created on : 27-11-2017, 11:56:39
    Author     : Fabi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Creación de Clientes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <jsp:include page="/WEB-INF/jspf/header.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />
            <jsp:include page="/WEB-INF/jspf/mensajes.jsp" />
            <div class="row">
                <div class="col col-lg-6">
                    <h1>Cree Clientes</h1>
                    <form method="post" action="clientes">
                        <c:if test="${!empty cliente}">
                            <div class="form-group">
                                <label for="id">ID</label>
                                <input value="${cliente.id}" type="number" class="form-control" id="id" name="id" readonly="readonly" aria-describedby="id-help">
                                <small id="id-help" class="form-text text-muted">El ID del cliente que se autogenera, solo se cargará cuando se edita una categoría de manera informativa</small>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label for="rut">Rut</label>
                            <textarea name="rut" id="rut" class="form-control" aria-describedby="rut-help">${!empty cliente?cliente.rut:''}</textarea>
                            <small id="rut-help" class="form-text text-muted">Rut numerico antes del guion</small>
                            
                        </div>
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <textarea name="nombre" id="nombre" class="form-control" aria-describedby="nombre-help">${!empty cliente?cliente.nombre:''}</textarea>
                            <small id="nombre-help" class="form-text text-muted">Ingrese nombre</small>
                        </div>
                        <div class="form-group">
                            <label for="correo">Correo</label>
                            <textarea name="correo" id="correo" class="form-control" aria-describedby="correo-help">${!empty cliente?cliente.correo:''}</textarea>
                            <small id="correo-help" class="form-text text-muted">Ingrese correo</small>
                        </div>
                        <div class="form-group">
                            <label for="telefono">Telefono</label>
                            <textarea name="telefono" id="telefono" class="form-control" aria-describedby="telefono-help">${!empty cliente?cliente.telefono:''}</textarea>
                            <small id="telefono-help" class="form-text text-muted">Ingrese telefono</small>
                        </div>

                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </form>
                </div>
            </div><!-- end col-->
        </div><!-- end row-->

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>
