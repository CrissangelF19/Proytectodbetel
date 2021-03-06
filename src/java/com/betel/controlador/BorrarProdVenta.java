
package com.betel.controlador;

import com.betel.dao.EmpresaDaoImpl;
import com.betel.dao.ProductoDaoImpl;
import com.betel.modelo.Articulo;
import com.betel.modelo.Empresa;
import com.betel.modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "BorrarProdVenta", urlPatterns = {"/BorrarProdVenta"})
public class BorrarProdVenta extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String idproducto = request.getParameter("idproducto");
        
        HttpSession sesion = request.getSession(true);
        ArrayList<Articulo> articulos = sesion.getAttribute("carrito") == null ? null : (ArrayList) sesion.getAttribute("carrito");
        
        if(articulos != null){
            for(Articulo a : articulos){                
                if(a.getIdProducto().equals(idproducto)){
                    articulos.remove(a);
                    break;
                }
            }
        }
        
        EmpresaDaoImpl daom = new EmpresaDaoImpl();                        
        Empresa emp = new Empresa();

        try {
            emp = (Empresa) daom.buscarPorID("1");
        } catch (SQLException ex) {
            Logger.getLogger(BorrarProdVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        double empIva = Double.valueOf(emp.getIva());
        
        double subtotal = 0;
        double iva = 0;
        double total = 0;
        int totalUnd = 0;       
        
        
        ProductoDaoImpl pc = new ProductoDaoImpl();
        
        for(Articulo a : articulos){                
            try {
                Producto producto = (Producto) pc.buscarPorID(a.getIdProducto());
                            
                totalUnd = a.getCajas() * producto.getUnidadesCaja() + a.getUnid();

                subtotal += totalUnd * a.getPrecioUnid() - a.getDscto();
                
                iva = subtotal * empIva; 
                
                total = iva + subtotal;
                
            } catch (SQLException ex) {
                Logger.getLogger(BorrarProdCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
        } 
        
        response.getWriter().print(Math.round(subtotal * 100.00)/100.0);        
        
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
