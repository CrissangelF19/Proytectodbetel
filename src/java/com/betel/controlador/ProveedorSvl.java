
package com.betel.controlador;
import java.sql.Date;

import com.betel.dao.ProveedorDaoImpl;

import com.betel.modelo.Proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
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


@WebServlet(name = "proveedorSvl", urlPatterns = {"/proveedorsvl"})
public class ProveedorSvl extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            ProveedorDaoImpl daoProv =  new ProveedorDaoImpl();
            Proveedor prov = new Proveedor();
            List<Proveedor> listProveedor = new ArrayList();
            
            String respuestaR, respuestaE, respuestaM, respuestaV = null;
            
            String nit, razonSocial, nIdentidad = null;            
            RequestDispatcher rd = null;         
            
            try {
                if (request.getParameter("btnRegistrar")!=null) {
           
                    prov.setNit(request.getParameter("nit"));
                    prov.setRazonSocial(request.getParameter("razonsocial"));
                    prov.setFechaIngreso(Date.valueOf(request.getParameter("fechaIngreso")));
                    prov.setEstadop("Activo");
                    prov.setNombreCompleto(request.getParameter("nombres"));                    
                    prov.setTipoDocIdentidad(request.getParameter("tipodocidentidad"));
                    prov.setNroIdentidad(request.getParameter("numerodocumento"));                
                    prov.setGenero(request.getParameter("genero"));
                    prov.setDireccion(request.getParameter("direccion"));
                    prov.setCiudad(request.getParameter("ciudad"));
                    prov.setBarrio(request.getParameter("barrio"));
                    prov.setEmail(request.getParameter("email"));
                    prov.setCelular(request.getParameter("celular"));
                    prov.setTelefono(request.getParameter("telefono"));                
                    prov.setEstado("Activo");
                    
                    
                    if (daoProv.existe(prov)) {
                        nit = "El Nit " + prov.getNit() + " ya existe.";
                        request.setAttribute("nit", nit);
                        rd = request.getRequestDispatcher("proveedor.jsp");                       
                        
                    } else if (daoProv.existeRSocial(prov)){
                        razonSocial = "La Razon Social " + prov.getRazonSocial() + " ya existe.";
                        request.setAttribute("razonSocial", razonSocial);
                        rd = request.getRequestDispatcher("proveedor.jsp");
                        
                    }else if (daoProv.existeNIdentidad(prov)){
                        nIdentidad = "El N°Identidad " + prov.getNroIdentidad() + " ya existe.";
                        request.setAttribute("nIdentidad", nIdentidad);
                        rd = request.getRequestDispatcher("proveedor.jsp");
                        
                    }else{
                        respuestaR = daoProv.insertar(prov);
                        request.setAttribute("respuestaR", respuestaR);
                        rd = request.getRequestDispatcher("proveedor.jsp");                        
                    } 
                    
                }else if (request.getParameter("btnModificar")!=null){
                    List<Proveedor> listaProveedor = new ArrayList<>();
                    prov= daoProv.consultaProveedor(request.getParameter("id"));                          
          
                    request.setAttribute("proveed", prov);
                    rd = request.getRequestDispatcher("modiProveedor.jsp");
                    
                }else if (request.getParameter("btnVer")!=null){
                    List<Proveedor> listaProveedor = new ArrayList<>();
                    prov= daoProv.consultaProveedor(request.getParameter("id"));                          
           
                    request.setAttribute("proveed", prov);
                    rd = request.getRequestDispatcher("verProveedor.jsp");
                    
                }else if(request.getParameter("btnEliminar")!=null){
                    
                    prov.setIdProveedor(request.getParameter("id"));
                     respuestaE =  daoProv.eliminar(prov); 
                    request.setAttribute("respuestaE", respuestaE);           
                    rd = request.getRequestDispatcher("proveedor.jsp");
                   
                }else if (request.getParameter("btnActualizar")!=null){
                    prov.setIdProveedor(request.getParameter("id"));
                    prov.setNit(request.getParameter("nit"));
                    prov.setRazonSocial(request.getParameter("razonsocial"));
                    prov.setEstadop(request.getParameter("estadop"));
                    prov.setNombreCompleto(request.getParameter("nombres"));
                    prov.setTipoDocIdentidad(request.getParameter("tipodocidentidad"));
                    prov.setNroIdentidad(request.getParameter("numerodocumento"));                    
                    prov.setGenero(request.getParameter("genero"));
                    prov.setDireccion(request.getParameter("direccion"));
                    prov.setCiudad(request.getParameter("ciudad"));
                    prov.setBarrio(request.getParameter("barrio"));
                    prov.setEmail(request.getParameter("email"));
                    prov.setCelular(request.getParameter("celular"));
                    prov.setTelefono(request.getParameter("telefono"));                
                    prov.setEstado(request.getParameter("estado"));
                    
                    respuestaM = daoProv.modificar(prov);
                    request.setAttribute("respuestaM", respuestaM);
                    rd = request.getRequestDispatcher("proveedor.jsp");
                }else if(request.getParameter("btnBuscar")!=null){
                    int id = Integer.valueOf(request.getParameter("idBusqueda").trim());
                    switch (id) {
                        case 0:
                            prov.setNit(request.getParameter("valor"));                                                        
                            break;
                        case 1:
                            prov.setRazonSocial(request.getParameter("valor"));
                            break;
                        case 2:
                          prov.setEstadop(request.getParameter("valor"));
                            break;
                        case 3:
                            prov.setIdProveedor(request.getParameter("valor"));
                            break;
                       
                        default:
                            throw new AssertionError();
                    }
                    
                    listProveedor = (List<Proveedor>)daoProv.busquedaPorParametro(request.getParameter("idBusqueda"), prov);                    
                    request.setAttribute("listProveedor", listProveedor);
                    rd = request.getRequestDispatcher("buscar_proveedor.jsp");
                }  
                
            } catch (NumberFormatException | SQLException e ) {
                System.out.println("Problemas en el server: " + e.toString());
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ProveedorSvl.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(ProveedorSvl.class.getName()).log(Level.SEVERE, null, ex);
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
