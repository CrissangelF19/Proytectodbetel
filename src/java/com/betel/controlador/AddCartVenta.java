
package com.betel.controlador;

import com.betel.modelo.Articulo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "AddCartVenta", urlPatterns = {"/AddCartVenta"})
public class AddCartVenta extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        int caja = Integer.parseInt(request.getParameter("cajas"));
        int unidad = Integer.parseInt(request.getParameter("unid"));
        double dsct = Double.parseDouble(request.getParameter("dsct"));
        double precioUnid = Double.parseDouble(request.getParameter("precioUnid"));
        
        String idProducto = request.getParameter("idProd");
        
        HttpSession sesion = request.getSession(true);
        ArrayList<Articulo> articulos = sesion.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList) sesion.getAttribute("carrito");
        
        boolean flag = false;        
        if(articulos.size() > 0){
            for(Articulo a : articulos){
                if(idProducto.equals(a.getIdProducto())){
                    a.setCajas(a.getCajas() + caja);
                    a.setUnid(a.getUnid() + unidad);
                    flag = true;
                    break;
                }
            }
        }
        
        if(!flag){
            articulos.add(new Articulo(idProducto, caja, unidad, precioUnid, dsct));                      
        }
        
        sesion.setAttribute("carrito", articulos);  
        
        response.sendRedirect("carritoVenta.jsp");
        
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
