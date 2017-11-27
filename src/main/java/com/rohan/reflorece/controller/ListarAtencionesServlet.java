/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rohan.reflorece.controller;

import com.rohan.reflorece.entity.Cliente;
import com.rohan.reflorece.entity.FichaAtencion;
import com.rohan.reflorece.services.FichaAtencionService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fabi
 */
@WebServlet(name = "ListarAtencionesServlet", urlPatterns = {"/listaAtenciones"})
public class ListarAtencionesServlet extends HttpServlet {

    @EJB
    FichaAtencionService fichaAtencionService;
    
    public final String JSP_LISTAR = "/WEB-INF/jsp/ficha/listar.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         List<FichaAtencion> atenciones = fichaAtencionService.getFichaAtenciones();
        request.setAttribute("atenciones", atenciones);
        request.getRequestDispatcher(JSP_LISTAR).forward(request, response);    }
    
}
