
package com.betel.controlador;

import com.betel.dao.PagoCompraDaoImpl;
import com.betel.dao.PagoVentaDaoImpl;
import com.betel.dao.VentaDaoImpl;
import com.betel.modelo.PagoVenta;
import com.betel.modelo.Venta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "PagoVentaSvl", urlPatterns = {"/PagoVentaSvl"})
public class PagoVentaSvl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            VentaDaoImpl daoVenta = new VentaDaoImpl();
            Venta venta = new Venta();
           
            List<PagoVenta> listaPVenta = new ArrayList<>();
            
            PagoVentaDaoImpl daoPVenta = new PagoVentaDaoImpl();
            PagoVenta pVenta = new PagoVenta();
            
            String respuestaR, respuestaE, respuestaM, respuestaV, respuestaB = null;
            RequestDispatcher rd = null;
            
            try {
                
                if(request.getParameter("btnlistaVenta")!=null){
                    
                    venta = (Venta) daoVenta.buscarPorID(request.getParameter("nVenta"));
                    request.setAttribute("venta", venta);
                    
                    rd = request.getRequestDispatcher("realizaPagoV.jsp");
                    
                }else if(request.getParameter("btnRegistrar")!=null){
                    
                    pVenta.setIdVenta(request.getParameter("idVenta"));
                    pVenta.setMonto(Double.parseDouble(request.getParameter("monto")));
                    pVenta.setObservacion(request.getParameter("observacion"));
                    pVenta.setFecha(Date.valueOf(request.getParameter("fecha")));
                    pVenta.setEstado("Pagado");
                    
                    respuestaR = daoPVenta.insertar(pVenta);                            
                    request.setAttribute("respuestaR", respuestaR);                    
                    rd = request.getRequestDispatcher("pago.jsp"); 
                    
                }else if(request.getParameter("btnHistorial")!=null){
                    
                    listaPVenta = (List<PagoVenta>) daoPVenta.listarPagoVenta(request.getParameter("idVenta"));                    
                    request.setAttribute("listaPVenta", listaPVenta);
                    
                    rd = request.getRequestDispatcher("historialPago.jsp");                    
                }                
                
            } catch (Exception e) {
                System.out.println("Problemas en el server PagoVenta: " + e.toString());
            }
            
            rd.forward(request, response);
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
