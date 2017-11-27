/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rohan.reflorece.controller;

import com.rohan.reflorece.entity.Cliente;
import com.rohan.reflorece.entity.FichaAtencion;
import com.rohan.reflorece.entity.TipoTrabajador;
import com.rohan.reflorece.entity.Trabajador;
import com.rohan.reflorece.services.ClienteService;
import com.rohan.reflorece.services.FichaAtencionService;
import com.rohan.reflorece.services.TipoTrabajadorService;
import com.rohan.reflorece.services.TrabajadorService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
@WebServlet(name = "SetupServlet", urlPatterns = {"/setup"})
public class SetupServlet extends HttpServlet {

    @EJB
    ClienteService clienteService;
    @EJB
    TrabajadorService trabajadorService;
    @EJB
    TipoTrabajadorService tipoTrabajadorService;
    @EJB
    FichaAtencionService fichaAtencionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> mensajes = new ArrayList<>();
        List<String> errores = new ArrayList<>();
        

            //creacion tipo trabajador
            TipoTrabajador tt = new TipoTrabajador("Nutricionista");
            tt = tipoTrabajadorService.crearTipoTrabajador(tt);
            mensajes.add(String.format("Tipo trabajador %s creado con ID %s", tt.getNombre(), tt.getId()));
            TipoTrabajador tt2 = new TipoTrabajador("Masajista");
            tt2 = tipoTrabajadorService.crearTipoTrabajador(tt2);
            mensajes.add(String.format("Tipo trabajador %s creado con ID %s", tt2.getNombre(), tt2.getId()));
            TipoTrabajador tt3 = new TipoTrabajador("Manicurista");
            tt3 = tipoTrabajadorService.crearTipoTrabajador(tt3);
            mensajes.add(String.format("Tipo trabajador %s creado con ID %s", tt3.getNombre(), tt3.getId()));
            TipoTrabajador tt4 = new TipoTrabajador("Cirujano Plastico");
            tt4 = tipoTrabajadorService.crearTipoTrabajador(tt4);
            mensajes.add(String.format("Tipo trabajador %s creado con ID %s", tt4.getNombre(), tt4.getId()));
            TipoTrabajador tt5 = new TipoTrabajador("Dermatologo");
            tt5 = tipoTrabajadorService.crearTipoTrabajador(tt5);
            mensajes.add(String.format("Tipo trabajador %s creado con ID %s", tt5.getNombre(), tt5.getId()));
            //creacion trabajador
            //int rut, String nombre, String telefono, TipoTrabajador tipoTrabajador, String usuario, String contrasena
            Trabajador t = new Trabajador(123456789, "Roco Chan", "+56978775314", tt, "rchan", "1234desu");
            t = trabajadorService.crearTrabajador(t);

            //creacion clientes
            Cliente cl = new Cliente(187264862, "Kiara desu", "kdesu@gmail.com", "976538746");
            cl = clienteService.crearCliente(cl);
            mensajes.add(String.format("Cliente %s creada con ID %s", cl.getNombre(), cl.getId()));

            //fecha
            String stringFecha ="2017-11-27";
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            try {
                Date fn = simpleDateFormat.parse(stringFecha);
                fecha.setTime(fn);
                //creacion ficha atencion ejemplo
                FichaAtencion f = new FichaAtencion(cl, t, "sobrepeso y resistencia insulinica",
                        "Dieta A1: Aumento consumo frutas y verduras,"
                        + "\n eliminacion de azucar refinada y reemplazo por endulzante"
                        + "\n ejercicio 30 min diarios"
                        + "\n volver en una semana",
                        20000,fecha);
                f = fichaAtencionService.crearFichaAtencion(f);

                mensajes.add(String.format("Trabajador %s creado con ID %s", t.getNombre(), t.getId(), " clave: ", t.getContrasena()));
                t = new Trabajador(76549873, "Voli Volito", "+5498763458", tt3, "vvolito", "volito123");
                t = trabajadorService.crearTrabajador(t);
                mensajes.add(String.format("Trabajador %s creado con ID %s", t.getNombre(), t.getId(), " clave: ", t.getContrasena()));
                t = new Trabajador(987689874, "Emi miau", "+56992157066", tt2, "miau", "0987");
                t = trabajadorService.crearTrabajador(t);
                mensajes.add(String.format("Trabajador %s creado con ID %s", t.getNombre(), t.getId(), " clave: ", t.getContrasena()));
                //cliente
                cl = new Cliente(986543452, "Lucas Skywalker", "lwmiau@gmail.com", "+56976542347");
                cl = clienteService.crearCliente(cl);
                mensajes.add(String.format("Cliente %s creada con ID %s", cl.getNombre(), cl.getId()));
                //creacion ficha atencion ejemplo
                f = new FichaAtencion(cl, t, "evaluacion exceso de piel post baja peso",
                        "Masaje descongestionante en brazos,"
                        + "\n 10 sesiones masaje reductor piernas"
                        + "\n 10 sesiones masaje reafirmante zona abdominal",
                        20000,fecha);
                f = fichaAtencionService.crearFichaAtencion(f);

            } catch (Exception e) {
                errores.add(e.getMessage());
            }
            req.setAttribute("mensajes", mensajes);
            req.setAttribute("errores", errores);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        

    }
}
