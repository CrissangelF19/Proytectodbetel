
package com.betel.controlador;

import com.betel.dao.EmpleadoDaoImpl;
import com.betel.modelo.Empleado;
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


@WebServlet(name = "EmpleadoSvl", urlPatterns = {"/EmpleadoSvl"})
public class EmpleadoSvl extends HttpServlet {


    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
         
            EmpleadoDaoImpl daoEmpl =  new EmpleadoDaoImpl();
            Empleado empl = new Empleado();
            List<Empleado> listEmpleado= new ArrayList();
            String respuestaR, respuestaE, respuestaM, respuestaV = null;
            RequestDispatcher rd = null;
            
            try {
                
                if (request.getParameter("btnRegistrar")!=null) {
                    empl.setIdRol(request.getParameter("idRol"));
                    empl.setFechaIngreso(Date.valueOf(request.getParameter("fechaingreso")));
                    empl.setEstadoz("Activo");
                    empl.setNombreCompleto(request.getParameter("nombres"));                    
                    empl.setTipoDocIdentidad(request.getParameter("tipodocidentidad"));
                    empl.setNroIdentidad(request.getParameter("numerodocumento"));                   
                    empl.setGenero(request.getParameter("genero"));
                    empl.setDireccion(request.getParameter("direccion"));
                    empl.setCiudad(request.getParameter("ciudad"));
                    empl.setBarrio(request.getParameter("barrio"));
                    empl.setEmail(request.getParameter("email"));
                    empl.setCelular(request.getParameter("celular"));
                    empl.setTelefono(request.getParameter("telefono"));
                    empl.setEstado("Activo");
                    
                    if (daoEmpl.existe(empl)) {
                        respuestaV = "El N°Identidad " + empl.getNroIdentidad() + " ya existe.";;
                        request.setAttribute("respuestaV", respuestaV);
                        rd = request.getRequestDispatcher("empleado.jsp");
                        
                    } else {
                        respuestaR = daoEmpl.insertar(empl);
                        request.setAttribute("respuestaR", respuestaR);
                        rd = request.getRequestDispatcher("empleado.jsp");                        
                    }
                    
                }else if (request.getParameter("btnModificar")!=null){
                    List<Empleado> listaEmpleado = new ArrayList<>();
                    empl= daoEmpl.consultaEmpleado(request.getParameter("id"));                          
           
                    request.setAttribute("Empl", empl);
                    rd = request.getRequestDispatcher("modiEmpleado.jsp");
                    
                }else if (request.getParameter("btnVer")!=null){
                    List<Empleado> listaEmpleado = new ArrayList<>();
                    empl= daoEmpl.consultaEmpleado(request.getParameter("id"));                          
           
                    request.setAttribute("Empl", empl);
                    rd = request.getRequestDispatcher("verEmpleado.jsp");
                    
                }else if(request.getParameter("btnEliminar")!=null){
                    empl.setIdEmpleado(request.getParameter("id"));
                       
                     respuestaE = daoEmpl.eliminar(empl);  
                    request.setAttribute("respuestaE", respuestaE);
                    rd = request.getRequestDispatcher("empleado.jsp");
                    
                }else if (request.getParameter("btnActualizar")!=null){
                    empl.setIdEmpleado(request.getParameter("id"));
                    empl.setIdRol(request.getParameter("idRol"));
                    empl.setEstadoz(request.getParameter("estadoz"));
                    empl.setNombreCompleto(request.getParameter("nombres"));                    
                    empl.setTipoDocIdentidad(request.getParameter("tipodocidentidad"));
                    empl.setNroIdentidad(request.getParameter("numerodocumento"));                    
                    empl.setGenero(request.getParameter("genero"));
                    empl.setDireccion(request.getParameter("direccion"));
                    empl.setCiudad(request.getParameter("ciudad"));
                    empl.setBarrio(request.getParameter("barrio"));
                    empl.setEmail(request.getParameter("email"));
                    empl.setCelular(request.getParameter("celular"));
                    empl.setTelefono(request.getParameter("telefono"));
               
                    
                    respuestaM = daoEmpl.modificar(empl);
                    request.setAttribute("respuestaM", respuestaM);
                    rd = request.getRequestDispatcher("empleado.jsp");
                }else if(request.getParameter("btnBuscar")!=null){
                    int id = Integer.valueOf(request.getParameter("idBusqueda").trim());
                    switch (id) {
                        case 0:
                            empl.setNombreCompleto(request.getParameter("valor"));                                                        
                            break;
                        case 1:
                            empl.setIdRol(request.getParameter("valor"));
                            break;
                        case 2:
                            empl.setEstadoz(request.getParameter("valor"));
                            break; 
                        case 3:
                            empl.setIdEmpleado(request.getParameter("valor"));
                            break;                 
                        
                        default:
                            throw new AssertionError();
                    }
                    
                    listEmpleado= (List<Empleado>)daoEmpl.busquedaPorParametro(request.getParameter("idBusqueda"), empl);                    
                    request.setAttribute("listEmpleado", listEmpleado);
                    rd = request.getRequestDispatcher("buscar_empleado.jsp");
                }  
                
                
            } catch (Exception e) {
                System.out.println("Problemas en el server3: " + e.toString());
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
