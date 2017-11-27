/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rohan.reflorece.controller;

import com.rohan.reflorece.entity.Cliente;
import com.rohan.reflorece.entity.FichaAtencion;
import com.rohan.reflorece.entity.Trabajador;
import com.rohan.reflorece.services.ClienteService;
import com.rohan.reflorece.services.FichaAtencionService;
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
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Fabi
 */
@WebServlet(name = "FichaAtencionServlet", urlPatterns = {"/fichaAtencion"})
public class FichaAtencionServlet extends HttpServlet {

    @EJB
    FichaAtencionService fichaAtencionService;
    @EJB
    ClienteService clienteService;

    private final static String JSP_ATENCION = "/WEB-INF/jsp/ficha/crear.jsp";
    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher(JSP_ATENCION).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacion = request.getParameter("op");

        HttpSession session = request.getSession();

        List<String> errores = new ArrayList<>();
        List<String> mensajes = new ArrayList<>();
        String error = "";
        String mensaje = "";

        String motivo = request.getParameter("motivo");
        String indicaciones = request.getParameter("indicaciones");

        int valor = 0;
        try {
            valor = Integer.parseInt(request.getParameter("valor"));
        } catch (NumberFormatException nfe) {
            error=String.format("no se pudo convertir correctamente el valor", valor);
            logger.log(Level.SEVERE, "No se pudo convertir el string a valor");
        }

        String stringFecha = request.getParameter("fecha");
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date fn = simpleDateFormat.parse(stringFecha);
            fecha.setTime(fn);
        } catch (ParseException ex) {
            error=String.format("no se pudo convertir correctamente la fecha", valor);
            logger.log(Level.SEVERE, "El forma de la fecha  no coincide con el esperado yyyy-mm-dd");
        }

        request.setAttribute("errores", errores);
        request.setAttribute("mensajes", mensajes);
        if ("guardar".equals(operacion)) {
            Trabajador trabajador = (Trabajador) session.getAttribute("trabajador");
            Cliente cliente = buildCliente(request, response);
            //Cliente cliente, Trabajador trabajador, String motivoConsulta, String indicaciones, int valorAtencion
            FichaAtencion fichaAtencion = new FichaAtencion(cliente, trabajador, motivo, indicaciones, valor, fecha);
            
            //validaciones
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<FichaAtencion>> listaValidaciones = validator.validate(fichaAtencion);
            for (ConstraintViolation<FichaAtencion> v : listaValidaciones) {
                errores.add(v.getPropertyPath().toString() + ":" + v.getMessage());
            }

            if (errores.size() > 0) {
                request.setAttribute("errores", errores);
            } else {
                 fichaAtencion = fichaAtencionService.crearFichaAtencion(fichaAtencion);
             fichaAtencion = fichaAtencionService.crearFichaAtencion(fichaAtencion);
            request.setAttribute("mensajes", new String[]{String.format("Atencion guardada con exito")});
            request.setAttribute("fichaAtencion", fichaAtencion);
            }
            
           
            request.getRequestDispatcher(JSP_ATENCION).forward(request, response);
        } else {
            request.getRequestDispatcher(JSP_ATENCION).forward(request, response);
        }
    }

    private Cliente buildCliente(HttpServletRequest request, HttpServletResponse response) {
        String stringRut = request.getParameter("rut");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        // conversiones
        int rut = 0;

        try {
            rut = Integer.parseInt(stringRut);
        } catch (NumberFormatException nfe) {
            logger.log(Level.SEVERE, "No se pudo convertir el string a RUT");
        }

        // creación de cliente
        Cliente cliente = clienteService.getClienteByRut(rut);
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setRut(rut);
            cliente.setNombre(nombre);
            cliente.setRut(rut);
            cliente.setTelefono(telefono);
            cliente.setCorreo(correo);
            cliente = clienteService.crearCliente(cliente);
        }

        return cliente;
    }

}
