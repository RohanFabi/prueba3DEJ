<%@page contentType="text/html" pageEncoding="UTF-8"%>

<a id="logo" href="index.jsp">
    <img src="img/logo.jpg" alt="logotipo" style="width: 200px; height: auto;" />
</a>

<br /><br />
<c><body BACKGROUND="img/fondo.jpg"></c>
<div style="background-color: #DB7093" class="text-right">Bienvenido ${trabajador.nombre} <a href="login?op=logout">Cerrar sesión</a></div>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="${pageContext.request.servletPath=='/index.jsp'?'active ':''}nav-link" href="index.jsp">Inicio</a>
            </li>  
            <li class="nav-item">
                <a class="${pageContext.request.servletPath=='/WEB-INF/jsp/trabajador/listar.jsp'?'active ':''}nav-link" href="trabajadores">Trabajadores</a>
            </li>             
            <li class="nav-item">
                <a class="${pageContext.request.servletPath=='/WEB-INF/jsp/ficha/crear.jsp'?'active ':''}nav-link" href="fichaAtencion">Nueva atencion</a>
            </li>
            <li class="nav-item">
                <a class="${pageContext.request.servletPath=='/WEB-INF/jsp/ficha/listar.jsp'?'active ':''}nav-link" href="listaAtenciones">Ver atenciones</a>
            </li> 
            <li class="nav-item">
                <a class="${pageContext.request.servletPath=='/WEB-INF/jsp/cliente/listar.jsp'?'active ':''}nav-link" href="clientes">Clientes</a>
            </li>          
        </ul>

    </div>
</nav>
<br />