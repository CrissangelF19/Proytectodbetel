
package com.betel.controlador;

import com.betel.dao.CategoriaDaoImpl;
import com.betel.modelo.Categoria;
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


@WebServlet(name = "CategoriaSvl", urlPatterns = {"/CategoriaSvl"})
public class CategoriaSvl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            CategoriaDaoImpl catgDao = new CategoriaDaoImpl();
            Categoria categoria = new Categoria();
            List<Categoria> listCategoria = new ArrayList();
            
            String respuestaR, respuestaE, respuestaM, respuestaV, respuestaB = null;
            RequestDispatcher rd = null;
            
            try {
                if(request.getParameter("btnRegistrar")!=null){                    
                    categoria.setNombre(request.getParameter("nombre"));                   
                    categoria.setEstado("Activo");
                    
                    if (catgDao.existe(categoria)) {
                        respuestaV = "El nombre " + categoria.getNombre() + " ya existe.";
                        request.setAttribute("respuestaV", respuestaV);
                        rd = request.getRequestDispatcher("categoria.jsp");
                        
                    } else {
                        respuestaR = catgDao.insertar(categoria);
                        request.setAttribute("respuestaR", respuestaR);
                        rd = request.getRequestDispatcher("categoria.jsp");
                        
                    }
                   
                }else if(request.getParameter("btnEliminar")!=null){
                    categoria.setIdCategoria(request.getParameter("idCat"));
                    respuestaE = catgDao.eliminar(categoria);
                    
                    request.setAttribute("respuestaE", respuestaE);
                    rd = request.getRequestDispatcher("categoria.jsp");
                    
                }else if(request.getParameter("btnModificar")!=null){
                    categoria = (Categoria) catgDao.buscarPorID(request.getParameter("idCat"));
                    request.setAttribute("categoria", categoria);
                    rd = request.getRequestDispatcher("modiCategoria.jsp"); 
                    
                }else if(request.getParameter("btnActualizar")!=null){
                    categoria.setIdCategoria(request.getParameter("idCategoria"));
                    categoria.setNombre(request.getParameter("nombre"));
                    categoria.setEstado(request.getParameter("estado"));
                                        
                    respuestaM =  catgDao.modificar(categoria);
                    request.setAttribute("respuestaM", respuestaM);
                    rd = request.getRequestDispatcher("categoria.jsp");
                    
                }else if(request.getParameter("btnBuscar")!=null){
                    int id = Integer.valueOf(request.getParameter("idBusqueda").trim());
                    switch (id) {                        
                        case 0:
                            categoria.setNombre(request.getParameter("valor"));
                            break;
                        case 1:
                            categoria.setEstado(request.getParameter("valor"));
                            break;
                        case 2:
                            categoria.setIdCategoria(request.getParameter("valor"));                            
                            break;    
                        
                        default:
                            throw new AssertionError();
                    }
                    
                    listCategoria = (List<Categoria>)catgDao.busquedaPorParametro(request.getParameter("idBusqueda"), categoria);                    
                    
                    if (listCategoria.size()>0) {
                        request.setAttribute("listCategoria", listCategoria);
                        rd = request.getRequestDispatcher("buscar_categoria.jsp");
                        
                    } else {
                        respuestaB = "Información invalida";
                        request.setAttribute("respuestaB", respuestaB);
                        rd = request.getRequestDispatcher("categoriaPrueba.jsp");
                    }
                    
                    
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
