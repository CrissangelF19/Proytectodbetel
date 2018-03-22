
package com.betel.controlador;

import com.betel.dao.RutaDaoImpl;
import com.betel.modelo.Ruta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RutaSvl", urlPatterns = {"/RutaSvl"})
public class RutaSvl extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
             
            RutaDaoImpl rutDao = new RutaDaoImpl();
            Ruta rut= new Ruta();
            List<Ruta> listRuta = new ArrayList();
            
            String respuestaR, respuestaE, respuestaM, respuestaV = null;
            
            RequestDispatcher rd = null;
            try {
                if(request.getParameter("btnRegistrar")!=null){                    
                    rut.setNombre(request.getParameter("nombre"));                   
                    rut.setEstado("Activo");
                    
                    if (rutDao.existe(rut)) {
                        respuestaV = "El nombre " + rut.getNombre() + " ya existe.";
                        request.setAttribute("respuestaV", respuestaV);
                        rd = request.getRequestDispatcher("ruta.jsp");
                        
                    } else {
                        respuestaR = rutDao.insertar(rut);
                        request.setAttribute("respuestaR", respuestaR);
                        rd = request.getRequestDispatcher("ruta.jsp");                        
                    }
                  
                }else if(request.getParameter("btnModificar")!=null){
                    rut = (Ruta) rutDao.buscarPorID(request.getParameter("idRuta"));                    
                    request.setAttribute("rut", rut);
                    
                    rd = request.getRequestDispatcher("modiRuta.jsp");
                    
                }else if(request.getParameter("btnActualizar")!=null){                    
                    rut.setIdRuta(request.getParameter("idRuta"));
                    rut.setNombre(request.getParameter("nombre"));
                    rut.setEstado(request.getParameter("estado"));
                    
                    respuestaM =  rutDao.modificar(rut);
                    request.setAttribute("respuestaM", respuestaM);
                    rd = request.getRequestDispatcher("ruta.jsp"); 
                    
                }else if(request.getParameter("btnEliminar")!=null){
                    rut.setIdRuta(request.getParameter("idRuta"));
                    
                    respuestaE = rutDao.eliminar(rut); 
                    request.setAttribute("respuestaE", respuestaE);
                    rd = request.getRequestDispatcher("ruta.jsp");
                    
                }else if(request.getParameter("btnBuscar")!=null){
                    int id = Integer.valueOf(request.getParameter("idBusqueda").trim());
                    switch (id) {
                       case 0:
                            rut.setNombre(request.getParameter("valor"));
                            break;
                        case 1:
                            rut.setEstado(request.getParameter("valor"));
                            break;
                        case 2:
                             rut.setIdRuta(request.getParameter("valor"));
                            break;  
                        default:
                            throw new AssertionError();
                    }
                    
                    listRuta = (List<Ruta>) rutDao.busquedaPorParametro(request.getParameter("idBusqueda"), rut);
                    request.setAttribute("listRuta", listRuta);
                    
                    rd = request.getRequestDispatcher("buscar_ruta.jsp");
                }
                
            } catch (Exception e) {
                System.out.println("Problemas en el server: " + e.toString());
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RutaSvl.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RutaSvl.class.getName()).log(Level.SEVERE, null, ex);
        }
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
