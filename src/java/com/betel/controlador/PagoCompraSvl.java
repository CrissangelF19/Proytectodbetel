
package com.betel.controlador;

import com.betel.dao.CompraDaoImpl;
import com.betel.dao.PagoCompraDaoImpl;
import com.betel.modelo.Compra;
import com.betel.modelo.PagoCompra;
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


@WebServlet(name = "PagoCompraSvl", urlPatterns = {"/PagoCompraSvl"})
public class PagoCompraSvl extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            CompraDaoImpl daoCompra = new CompraDaoImpl();
            Compra compra = new Compra();
            
            List<PagoCompra> listaPCompra = new ArrayList<>();
            
            PagoCompraDaoImpl daoPCompra = new PagoCompraDaoImpl();
            PagoCompra pCompra = new PagoCompra();
            
            String respuestaR, respuestaE, respuestaM, respuestaV, respuestaB = null;
            RequestDispatcher rd = null;
            
            try {
                
                if(request.getParameter("btnlistaCompra")!=null){
                  
                    compra = (Compra) daoCompra.buscarPorID(request.getParameter("nCompra"));
                    request.setAttribute("compra", compra);
                    
                    rd = request.getRequestDispatcher("realizaPagoC.jsp");   
                    
                }else if(request.getParameter("btnRegistrar")!=null){
                    
                    pCompra.setIdCompra(request.getParameter("idCompra"));
                    pCompra.setMonto(Double.parseDouble(request.getParameter("monto")));
                    pCompra.setObservacion(request.getParameter("observacion"));
                    pCompra.setFecha(Date.valueOf(request.getParameter("fecha")));
                    pCompra.setEstado("Pagado");
                    
                    respuestaR = daoPCompra.insertar(pCompra);
                    request.setAttribute("respuestaR", respuestaR);
                    rd = request.getRequestDispatcher("pago.jsp");
                    
                }else if(request.getParameter("btnHistorial")!=null){
                    
                    listaPCompra = (List<PagoCompra>) daoPCompra.listarPagoCompra(request.getParameter("idCompra"));                    
                    request.setAttribute("listaPCompra", listaPCompra);
                    
                    rd = request.getRequestDispatcher("historialPago.jsp");                    
                }
                
            } catch (Exception e) {
                System.out.println("Problemas en el server PagoCompra: " + e.toString());
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
