<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head bgcolor="#C71585">
        <title>Bienvenido a la Plataforma de Gestión de atenciones</title>        
        <jsp:include page="/WEB-INF/jspf/header.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />

            <p>
                Bienvenido a la plataforma de gestión de atenciones
            </p>
            <img class="img-fluid" src="img/home.png" alt="Sistema de Gestión de Atenciones" />
        </div>

        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>