<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ficha Atencion</title>

        <jsp:include page="/WEB-INF/jspf/header.jsp" />
        <style>
            fieldset {
                border: 1px solid #ccc;
                padding: 10px;
            }

            legend {
                display: inline;
                padding: 0 10px;
                width: auto;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />
            <jsp:include page="/WEB-INF/jspf/mensajes.jsp" />



            <h1>Ficha Atencion</h1>

            <form method="post" action="fichaAtencion">
                <fieldset>
                    <legend>Datos del Cliente</legend>

                   
                    
                    <div class="form-row">
                        <div class="form-group col-md-2">
                            <label>RUT</label>
                            <input type="text" id="rut" name="rut" class="form-control" />    
                        </div>
                        <div class="form-group col-md-5">
                            <label>Nombre Completo</label>
                            <input type="text" name="nombre" id="nombre" class="form-control" />    
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-3">
                            <label>Telefono</label>
                            <input type="text" name="telefono" id="telefono" class="form-control" />    
                        </div>
                        <div class="form-group col-md-3">
                            <label>Correo</label>
                            <input type="text" name="correo" id="correo" class="form-control" />    
                        </div>

                    </div>
                    
                    <div class="form-row">
                        <div class="form-group col-md-3">
                            <label>Motivo</label>
                            <input type="text" name="motivo" id="motivo" class="form-control" />    
                        </div>
                        <div class="form-group col-md-3">
                            <label>Indicaciones</label>
                            <input type="text" name="indicaciones" id="indicaciones" class="form-control" />    
                        </div>

                        
                    </div>
                    <div>
                        <div class="form-group col-md-2">
                            <label>Fecha</label>
                            <input type="text" class="form-control" name="fecha" id="fecha" />
                        </div>
                        <div>
                            <div class="form-group col-md-3">
                            <label>Valor Atencion</label>
                            <input type="text" name="valor" id="valor" class="form-control" />    
                        </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <button type="submit" class="btn btn-success">Guardar</button>
                        </div>
                    </div>
                    
                </fieldset>
                <input type="hidden" name="op" value="guardar" />

            </form>



        </div>

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />

        <script type="text/javascript">
            jQuery(function () {
                jQuery("#fecha").datepicker({
                    changeMonth: true
                    , changeYear: true
                    , dateFormat: 'yy-mm-dd'
                    , minDate: new Date(1940, 1, 1)
                    , maxDate: new Date()
                });

                jQuery("#rut").blur(function () {
                    var url = "${pageContext.request.contextPath}/clientes";
                    var d = {op: "ws", rut: jQuery(this).val()};
                    jQuery.ajax({
                        type: "GET"
                        , url: url
                        , data: d
                        , dataType: "json"
                    }).done(function (res) {
                        console.log("procesando respuesta JSON");
                       
                        jQuery("#nombre").val(res.nombre);
                        jQuery("#telefono").val(res.telefono);
                        jQuery("#correo").val(res.correo);
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                        console.log("AJAX call failed: " + textStatus + ", " + errorThrown);
                    });
                });
            });

        </script>
    </body>
</html>
