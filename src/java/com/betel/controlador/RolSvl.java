
package com.betel.controlador;

import com.betel.dao.RolDaoImpl;
import com.betel.modelo.Rol;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RolSvl", urlPatterns = {"/RolSvl"})
public class RolSvl extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            RolDaoImpl rolDao = new RolDaoImpl();
            Rol rol = new Rol();
            List<Rol> listRol = new ArrayList(); 
            
            String respuestaR, respuestaE, respuestaM, respuestaV = null;
            RequestDispatcher rd = null;
            
            try {
                if(request.getParameter("btnRegistrar")!=null){                    
                   rol.setNombre(request.getParameter("nombre"));                   
                   rol.setEstado("Activo");
                   
                    if (rolDao.existe(rol)) {
                        respuestaV =  "El nombre " + rol.getNombre() + " ya existe";
                        request.setAttribute("respuestaV", respuestaV);
                        rd = request.getRequestDispatcher("rol.jsp");                        
                        
                    } else {
                        respuestaR = rolDao.insertar(rol);
                        request.setAttribute("respuestaR", respuestaR);

                        rd = request.getRequestDispatcher("rol.jsp");
                    }
                   
                }else if(request.getParameter("btnEliminar")!=null){
                    
                    rol.setIdRol(request.getParameter("idRol"));
                    
                    respuestaE = rolDao.eliminar(rol);                  
                    request.setAttribute("respuestaE", respuestaE);
                    
                    rd = request.getRequestDispatcher("rol.jsp");
                    
                }else if(request.getParameter("btnModificar")!=null){
                    rol = (Rol) rolDao.buscarPorID(request.getParameter("idRol"));                    
                    request.setAttribute("rol", rol);
                    
                    rd = request.getRequestDispatcher("modiRol.jsp"); 
                    
                }else if(request.getParameter("btnActualizar")!=null){
                    rol.setIdRol(request.getParameter("idRol"));
                    rol.setNombre(request.getParameter("nombre"));
                    rol.setEstado(request.getParameter("estado"));
                    
                    respuestaM =  rolDao.modificar(rol);
                    request.setAttribute("respuestaM", respuestaM);
                    rd = request.getRequestDispatcher("rol.jsp");
                    
                }else if(request.getParameter("btnBuscar")!=null){
                    int id = Integer.valueOf(request.getParameter("idBusqueda").trim());
                    switch (id) {
                        case 0:
                            rol.setNombre(request.getParameter("valor"));                                                        
                            break;
                        case 1:
                            rol.setEstado(request.getParameter("valor"));
                            break;
                        case 2:
                            rol.setIdRol(request.getParameter("valor"));
                            break;                             
                        
                        default:
                            throw new AssertionError();
                    }
                    
                    listRol = (List<Rol>)rolDao.busquedaPorParametro(request.getParameter("idBusqueda"), rol);                    
                    request.setAttribute("listRol", listRol);
                    rd = request.getRequestDispatcher("buscar_rol.jsp");
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
