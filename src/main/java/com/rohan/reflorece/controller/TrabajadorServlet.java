/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rohan.reflorece.controller;

import com.rohan.reflorece.entity.Cliente;
import com.rohan.reflorece.entity.TipoTrabajador;
import com.rohan.reflorece.entity.Trabajador;
import com.rohan.reflorece.exception.TipoTrabajadorNoEncontradoException;
import com.rohan.reflorece.exception.TrabajadorNoEncontradoException;
import com.rohan.reflorece.services.TipoTrabajadorService;
import com.rohan.reflorece.services.TrabajadorService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Fabi
 */
@WebServlet(name = "TrabajadoresServlet", urlPatterns = {"/trabajadores"})
public class TrabajadorServlet extends HttpServlet {

    @EJB
    TrabajadorService trabajadorService;

    @EJB
    TipoTrabajadorService tipoTrabajadorService;

    public final String JSP_LISTAR = "/WEB-INF/jsp/trabajador/listar.jsp";
    public final String JSP_WS = "/WEB-INF/jsp/trabajador/ws.jsp";
    public final String JSP_CREAR = "/WEB-INF/jsp/trabajador/crear.jsp";

    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacion = request.getParameter("op");
        operacion = operacion != null ? operacion : "";

        switch (operacion) {
            case "crear":
                crear(request, response);
                break;
            case "ws":
                ws(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            case "listar":
            default:
                listar(request, response);
        }

    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Trabajador> trabajadores = trabajadorService.getTrabajadores();
        request.setAttribute("trabajadores", trabajadores);
        request.getRequestDispatcher(JSP_LISTAR).forward(request, response);
    }

    private void crear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("tipos", tipoTrabajadorService.getTipoTrabajadors());
        request.getRequestDispatcher(JSP_CREAR).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringId = request.getParameter("id");
        if (stringId == null || stringId.isEmpty()) {
            try {
                postCrear(request, response);
            } catch (TipoTrabajadorNoEncontradoException ex) {
                Logger.getLogger(TrabajadorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            postEditar(request, response);
        }
    }

    private void postCrear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, TipoTrabajadorNoEncontradoException {
        List<String> errores = new ArrayList<>();
        List<String> mensajes = new ArrayList<>();
        String error = "";
        String mensaje = "";

        int rut = Integer.parseInt(request.getParameter("rut"));
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String tipo = request.getParameter("tipoTrabajador");
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        TipoTrabajador tipoTrabajador = null;
        try {
            Long tipoTrabajadorId = Long.parseLong(tipo);
            tipoTrabajador = tipoTrabajadorService.getTipoTrabajadorById(tipoTrabajadorId);
            if (tipoTrabajador == null) {
                throw new TipoTrabajadorNoEncontradoException("No se encontró el tipo de trabajador asignado al trabajador");
            }

            Trabajador t = new Trabajador(rut, nombre, telefono, tipoTrabajador, usuario, contrasena);
            //validaciones
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Trabajador>> listaValidaciones = validator.validate(t);
            for (ConstraintViolation<Trabajador> v : listaValidaciones) {
                errores.add(v.getPropertyPath().toString() + ":" + v.getMessage());
            }

            if (errores.size() > 0) {
                request.setAttribute("errores", errores);
            } else {
                t = trabajadorService.crearTrabajador(t);
                request.setAttribute("mensajes", new String[]{String.format("trabajador %s creado correctamente con ID %s", t.getNombre(), t.getId())});request.setAttribute("mensajes", new String[]{String.format("Trabajador %s creado correctamente con ID %s", t.getNombre(), t.getId())});

            }
            
        } catch (TipoTrabajadorNoEncontradoException ex) {
            errores.add(ex.getMessage());
        }
        request.getRequestDispatcher(JSP_CREAR).forward(request, response);

    }

    private void postEditar(HttpServletRequest request, HttpServletResponse response) {

    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errores = new ArrayList<>();
        List<String> mensajes = new ArrayList<>();
        String error = "";
        String mensaje = "";

        String stringId = request.getParameter("id");
        Long trabajadorId = 0L;
        try {
            trabajadorId = Long.parseLong(stringId);
            trabajadorService.eliminarTrabajador(trabajadorId);
            mensaje = String.format("Se ha eliminado correctamente el trabajador con ID %s", trabajadorId);
            logger.log(Level.INFO, mensaje);
            request.setAttribute("mensajes", mensajes);
            mensajes.add(mensaje);
        } catch (NumberFormatException nfe) {
            error = String.format("Formato de ID inválido");
            logger.log(Level.SEVERE, error);
            errores.add(error);
        } catch (TrabajadorNoEncontradoException ex) {
            error = String.format("No se pudo encontrar el trabajador con el ID especificado");
            logger.log(Level.SEVERE, error);
            errores.add(error);
        }

        listar(request, response);
    }

    private void ws(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringRut = request.getParameter("rut");
        int rut = 0;
        Trabajador trabajador = null;
        try {
            rut = Integer.parseInt(stringRut);
            trabajador = trabajadorService.getTrabajadorByRut(rut);
        } catch (NumberFormatException nfe) {
            logger.log(Level.SEVERE, "Formato del RUT incorrecto");
        }
        request.setAttribute("trabajador", trabajador);
        request.getRequestDispatcher(JSP_WS).forward(request, response);
    }

}
