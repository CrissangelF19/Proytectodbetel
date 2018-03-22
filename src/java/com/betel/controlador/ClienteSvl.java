
package com.betel.controlador;

import com.betel.dao.ClienteDaoImpl;
import com.betel.modelo.Cliente;
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


@WebServlet(name = "ClienteSvl", urlPatterns = {"/ClienteSvl"})
public class ClienteSvl extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        
            ClienteDaoImpl daoClient =  new ClienteDaoImpl();
            Cliente client = new Cliente();
            List<Cliente> listCliente = new ArrayList();
            
            String respuestaR, respuestaE, respuestaM, respuestaV = null;
            RequestDispatcher rd = null;
            
            try {
                if (request.getParameter("btnRegistrar")!=null) {
                    client.setIdRuta(request.getParameter("idRuta"));
                    client.setFechaIngreso(Date.valueOf(request.getParameter("fechaingreso")));
                    client.setEstadoc("Activo");                   
                    client.setNombreCompleto(request.getParameter("nombres"));                    
                    client.setTipoDocIdentidad(request.getParameter("tipodocidentidad"));
                    client.setNroIdentidad(request.getParameter("numerodocumento"));                    
                    client.setGenero(request.getParameter("genero"));
                    client.setDireccion(request.getParameter("direccion"));
                    client.setCiudad(request.getParameter("ciudad"));
                    client.setBarrio(request.getParameter("barrio"));
                    client.setEmail(request.getParameter("email"));
                    client.setCelular(request.getParameter("celular"));
                    client.setTelefono(request.getParameter("telefono"));
                    client.setEstado("Activo");
                    
                    if (daoClient.existe(client)) {
                        respuestaV = "El N°Identidad " + client.getNroIdentidad() + " ya existe.";;
                        request.setAttribute("respuestaV", respuestaV);
                        rd = request.getRequestDispatcher("cliente.jsp");
                        
                    } else {
                        respuestaR = daoClient.insertar(client);
                        request.setAttribute("respuestaR", respuestaR);
                        rd = request.getRequestDispatcher("cliente.jsp");                        
                    }
                    
                }else if (request.getParameter("btnModificar")!=null){
                    List<Cliente> listaCliente = new ArrayList<>();
                    client= daoClient.consultaCliente(request.getParameter("id"));                          
           
                    request.setAttribute("Client", client);
                    rd = request.getRequestDispatcher("modiCliente.jsp");
                    
                }else if (request.getParameter("btnVer")!=null){
                    List<Cliente> listaCliente = new ArrayList<>();
                    client= daoClient.consultaCliente(request.getParameter("id"));                          
           
                    request.setAttribute("Client", client);
                    rd = request.getRequestDispatcher("verCliente.jsp");
                    
                }else if(request.getParameter("btnEliminar")!=null){
                    client.setIdCliente(request.getParameter("id"));
                    respuestaE =  daoClient.eliminar(client);
                    request.setAttribute("respuestaE", respuestaE);
                    rd = request.getRequestDispatcher("cliente.jsp");
                    
                }else if (request.getParameter("btnActualizar")!=null){
                    client.setIdCliente(request.getParameter("id"));
                    client.setIdRuta(request.getParameter("idRuta"));
                    client.setEstadoc(request.getParameter("estadoc"));
                    client.setNombreCompleto(request.getParameter("nombres"));                    
                    client.setTipoDocIdentidad(request.getParameter("tipodocidentidad"));
                    client.setNroIdentidad(request.getParameter("numerodocumento"));                    
                    client.setGenero(request.getParameter("genero"));
                    client.setDireccion(request.getParameter("direccion"));
                    client.setCiudad(request.getParameter("ciudad"));
                    client.setBarrio(request.getParameter("barrio"));
                    client.setEmail(request.getParameter("email"));
                    client.setCelular(request.getParameter("celular"));
                    client.setTelefono(request.getParameter("telefono"));                
                    client.setEstado(request.getParameter("estado"));
                    
                    respuestaM = daoClient.modificar(client);
                    request.setAttribute("respuestaM", respuestaM);
                    rd = request.getRequestDispatcher("cliente.jsp");
                }else if(request.getParameter("btnBuscar")!=null){
                    int id = Integer.valueOf(request.getParameter("idBusqueda").trim());
                    switch (id) {
                        case 0:
                            client.setNombreCompleto(request.getParameter("valor"));                                                        
                            break;                                              
                        case 1:
                            client.setIdRuta(request.getParameter("valor"));
                            break;
                        case 2:
                            client.setEstadoc(request.getParameter("valor"));
                            break; 
                        case 3:
                            client.setIdCliente(request.getParameter("valor"));
                            break;                 
                        
                        default:
                            throw new AssertionError();
                    }
                    
                    listCliente = (List<Cliente>)daoClient.busquedaPorParametro(request.getParameter("idBusqueda"), client);                    
                    request.setAttribute("listCliente", listCliente);
                    rd = request.getRequestDispatcher("buscar_cliente.jsp");
                }  
                
                
            } catch (Exception e) {
                System.out.println("Problemas en el server2: " + e.toString());
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
