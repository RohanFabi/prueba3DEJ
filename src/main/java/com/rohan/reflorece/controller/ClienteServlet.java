/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rohan.reflorece.controller;

import com.rohan.reflorece.entity.Cliente;
import com.rohan.reflorece.entity.TipoTrabajador;
import com.rohan.reflorece.exception.ClienteNoEncontradoException;
import com.rohan.reflorece.services.ClienteService;
import com.rohan.reflorece.services.TipoTrabajadorService;
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
@WebServlet(name = "ClienteServlet", urlPatterns = {"/clientes"})
public class ClienteServlet extends HttpServlet {

    @EJB
    ClienteService clienteService;

    @EJB
    TipoTrabajadorService tipoTrabajadorService;

    public final String JSP_LISTAR = "/WEB-INF/jsp/cliente/listar.jsp";
    public final String JSP_WS = "/WEB-INF/jsp/cliente/ws.jsp";
    public final String JSP_CREAR = "/WEB-INF/jsp/cliente/crear.jsp";

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
            case "eliminar": {
                try {
                    eliminar(request, response);
                } catch (ClienteNoEncontradoException ex) {
                    Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "listar":
            default:
                listar(request, response);
        }

    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteService.getClientes();
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher(JSP_LISTAR).forward(request, response);
    }

    private void crear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JSP_CREAR).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringId = request.getParameter("id");
        if (stringId == null || stringId.isEmpty()) {
            postCrear(request, response);
        } else {
            postEditar(request, response);
        }
    }

    private void postCrear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errores = new ArrayList<>();
        List<String> mensajes = new ArrayList<>();
        String error = "";
        String mensaje = "";

        int rut = Integer.parseInt(request.getParameter("rut"));
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        try {

            Cliente cl = new Cliente(rut, nombre, correo, telefono);
            //validaciones
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Cliente>> listaValidaciones = validator.validate(cl);
            for (ConstraintViolation<Cliente> v : listaValidaciones) {
                errores.add(v.getPropertyPath().toString() + ":" + v.getMessage());
            }

            if (errores.size() > 0) {
                request.setAttribute("errores", errores);
            } else {
                cl = clienteService.crearCliente(cl);
                request.setAttribute("mensajes", new String[]{String.format("Cliente %s creado correctamente con ID %s", cl.getNombre(), cl.getId())});

            }

        } catch (Exception ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher(JSP_CREAR).forward(request, response);
    }

    private void postEditar(HttpServletRequest request, HttpServletResponse response) {

    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClienteNoEncontradoException {
        List<String> errores = new ArrayList<>();
        List<String> mensajes = new ArrayList<>();
        String error = "";
        String mensaje = "";

        String stringId = request.getParameter("id");
        Long clienteId = 0L;
        try {
            clienteId = Long.parseLong(stringId);
            clienteService.eliminarCliente(clienteId);
            mensaje = String.format("Se ha eliminado correctamente el cliente con ID %s", clienteId);
            logger.log(Level.INFO, mensaje);
            request.setAttribute("mensajes", mensajes);
            mensajes.add(mensaje);
        } catch (NumberFormatException nfe) {
            error = String.format("Formato de ID inválido");
            logger.log(Level.SEVERE, error);
            errores.add(error);
        } catch (ClienteNoEncontradoException ex) {
            error = String.format("No se pudo encontrar el cliente con el ID especificado");
            logger.log(Level.SEVERE, error);
            errores.add(error);
        }

        listar(request, response);
    }

    private void ws(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringRut = request.getParameter("rut");
        int rut = 0;
        Cliente cliente = null;
        try {
            rut = Integer.parseInt(stringRut);
            cliente = clienteService.getClienteByRut(rut);
        } catch (NumberFormatException nfe) {
            logger.log(Level.SEVERE, "Formato del RUT incorrecto");
        }
        request.setAttribute("cliente", cliente);
        request.getRequestDispatcher(JSP_WS).forward(request, response);
    }

}
