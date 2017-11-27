<%-- 
    Document   : crear
    Created on : 27-11-2017, 7:38:53
    Author     : Fabi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Creación de Trabajadores</title>
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
                    <h1>Cree trabajadores</h1>
                    <form method="post" action="trabajadores">
                        <c:if test="${!empty t}">
                            <div class="form-group">
                                <label for="id">ID</label>
                                <input value="${t.id}" type="number" class="form-control" id="id" name="id" readonly="readonly" aria-describedby="id-help">
                                <small id="id-help" class="form-text text-muted">El ID del trabajador que se autogenera, solo se cargará cuando se edita una categoría de manera informativa</small>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label for="rut">Rut</label>
                            <input type="text" name="rut" id="rut" class="form-control" aria-describedby="rut-help" value="${!empty t?t.rut:''}">
                            <small id="rut-help" class="form-text text-muted">Rut numerico antes del guion</small>

                        </div>
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" name="nombre" id="nombre" class="form-control" aria-describedby="nombre-help" value="${!empty t?t.nombre:''}">
                            <small id="nombre-help" class="form-text text-muted">Ingrese nombre</small>
                        </div>
                        <div class="form-group">
                            <label for="telefono">Telefono</label>
                            <input type="text" name="telefono" id="telefono" class="form-control" aria-describedby="telefono-help" value="${!empty t?t.telefono:''}">
                            <small id="telefono-help" class="form-text text-muted">Ingrese telefono</small>
                        </div>
                        <div class="form-group">
                            <label for="tipoTrabajador">Tipo Trabajador</label>
                            <select class="form-control" name="tipoTrabajador" id="tipoTrabajador">
                                <option value="0">Seleccione una categoría</option>
                                <c:forEach items="${tipos}" var="c">
                                    <option value="${c.id}">${c.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="usuario">Usuario</label>
                            <input type="text" name="usuario" id="usuario" class="form-control" aria-describedby="usuario-help" value="${!empty t?t.usuario:''}">
                            <small id="usuario-help" class="form-text text-muted">Ingrese usuario</small>
                        </div>
                        <div class="form-group">
                            <label for="contrasena">Clave</label>
                            <input type="text" name="contrasena" id="contrasena" class="form-control" aria-describedby="contrasena-help" value="${!empty t?t.contrasena:''}">
                            <small id="contrasena-help" class="form-text text-muted">Ingrese contrasena</small>
                        </div>

                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </form>
                </div>
            </div><!-- end col-->
        </div><!-- end row-->

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>
