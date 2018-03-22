
package com.betel.controlador;

import com.betel.dao.UsuarioDaoImpl;
import com.betel.modelo.Usuario;
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
import javax.servlet.http.HttpSession;


@WebServlet(name = "UsuarioSvl", urlPatterns = {"/usuarioSvl"})
public class UsuarioSvl extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            Usuario usuario = new Usuario();
            UsuarioDaoImpl usDao = new UsuarioDaoImpl();
            List<Usuario> listUsuario = new ArrayList();
            
            String respuestaL, respuestaR, respuestaE, respuestaM, respuestaV, respuestaS = null;
            RequestDispatcher rd = null;
            
            try {
                if(request.getParameter("btnLogin")!=null){
                    String user = request.getParameter("usuario");
                    String clave = request.getParameter("clave");
                    
                    listUsuario = usDao.existeUsuario(user, clave);
                    if(listUsuario.size()>0){
                        
                        HttpSession sesion = request.getSession(true);                        
                        sesion.setAttribute("user", user);                        
                        
                        request.setAttribute("listUsuario", listUsuario.get(0).getNombreCompleto());
                        //request.setAttribute("listUsuario", listUsuario);
                        rd = request.getRequestDispatcher("home.jsp");
                        
                    }else {                        
                        respuestaL = "Información invalida";
                        request.setAttribute("respuestaL", respuestaL);
                        rd = request.getRequestDispatcher("login.jsp");
                    }
                    
                }else if(request.getParameter("btnRegistrar")!=null){
                    
                   usuario.setNombreCompleto(request.getParameter("nombre"));                   
                   usuario.setUsuario(request.getParameter("usuario"));
                   usuario.setClave(request.getParameter("clave"));
                   usuario.setIdRol(request.getParameter("idRol"));                   
                   usuario.setFechaIngreso(Date.valueOf(request.getParameter("fecha")));
                   usuario.setEstado("Activo");
                   
                    if (usDao.existe(usuario)) {  
                        
                        respuestaV =  "El Usuario " + usuario.getUsuario() + " ya existe";                                              
                        request.setAttribute("respuestaV", respuestaV);
                        rd = request.getRequestDispatcher("usuario.jsp");
                       
                    } else {
                        
                        respuestaR = usDao.insertar(usuario);                   
                        request.setAttribute("respuestaR", respuestaR);

                        rd = request.getRequestDispatcher("usuario.jsp");                                             
                    }
                   
                }else if(request.getParameter("btnEliminar")!=null){
                    
                    usuario.setIdUsuario(request.getParameter("idUsuario"));                    
                    
                    respuestaE = usDao.eliminar(usuario);                    
                    request.setAttribute("respuestaE", respuestaE); 
                    
                    rd = request.getRequestDispatcher("usuario.jsp");
                    
                }else if(request.getParameter("btnModificar")!=null){
                    usuario = (Usuario) usDao.buscarPorID(request.getParameter("idUsuario"));                                       
                    request.setAttribute("usuario", usuario);
                    
                    rd = request.getRequestDispatcher("modiUsuario.jsp");
                    
                }else if(request.getParameter("btnActualizar")!=null){
                    usuario.setIdUsuario(request.getParameter("idUsuario"));
                    usuario.setNombreCompleto(request.getParameter("nombre"));                    
                    usuario.setUsuario(request.getParameter("usuario"));
                    usuario.setClave(request.getParameter("clave"));
                    usuario.setIdRol(request.getParameter("idRol"));
                    usuario.setFechaIngreso(Date.valueOf(request.getParameter("fecha")));
                    usuario.setEstado(request.getParameter("estado"));                   
                    
                    respuestaM = usDao.modificar(usuario);                    
                    request.setAttribute("respuestaM", respuestaM);
                    rd = request.getRequestDispatcher("usuario.jsp");
                    
                }else if(request.getParameter("btnBuscar")!=null){
                    int id = Integer.valueOf(request.getParameter("idBusqueda").trim());
                    switch (id) {
                        case 0:
                            usuario.setNombreCompleto(request.getParameter("valor"));                                                        
                            break;                        
                        case 1:
                            usuario.setUsuario(request.getParameter("valor"));
                            break;
                        case 2:
                            usuario.setIdRol(request.getParameter("valor"));
                            break;
                        case 3:
                            usuario.setEstado(request.getParameter("valor"));
                            break; 
                        case 4:
                            usuario.setIdUsuario(request.getParameter("valor"));
                            break;                 
                        
                        default:
                            throw new AssertionError();
                    }
                    
                    listUsuario = (List<Usuario>)usDao.busquedaPorParametro(request.getParameter("idBusqueda"), usuario);                    
                    request.setAttribute("listUsuario", listUsuario);
                    rd = request.getRequestDispatcher("buscar_usuario.jsp");
                    
                }else if(request.getParameter("btnCerrarSesion")!=null){
                    HttpSession sesion = request.getSession(true);
                    sesion.removeAttribute("user");
                    
                    respuestaS =  "Has Cerrado Sesion.";                                              
                    request.setAttribute("respuestaS", respuestaS);
                    
                    rd = request.getRequestDispatcher("login.jsp");
                }  
                
            } catch (Exception e) {
                out.println("Error en el usuario servlet: " + e.toString());
                e.printStackTrace();
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
