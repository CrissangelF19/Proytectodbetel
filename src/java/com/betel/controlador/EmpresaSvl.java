
package com.betel.controlador;

import com.betel.dao.EmpresaDaoImpl;
import com.betel.modelo.Empresa;
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


@WebServlet(name = "EmpresaSvl", urlPatterns = {"/EmpresaSvl"})
public class EmpresaSvl extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            EmpresaDaoImpl empDao = new EmpresaDaoImpl();
            Empresa emp = new Empresa();
            List<Empresa> listEmp = new ArrayList();
            String respuesta = null;
            RequestDispatcher rd = null;
            
            try {
                
                if(request.getParameter("btnModificar")!=null){
                    emp = (Empresa) empDao.buscarPorID(request.getParameter("idEmpresa"));                    
                    request.setAttribute("emp", emp);
                    
                    rd = request.getRequestDispatcher("modiPerfilEmpresa.jsp"); 
                    
                }else if(request.getParameter("btnActualizar")!=null){                    
                    emp.setIdEmpresa(request.getParameter("idEmpresa"));
                    emp.setNombre(request.getParameter("nombre"));
                    emp.setNit(request.getParameter("nit"));
                    emp.setPropietario(request.getParameter("propi"));
                    emp.setEmail(request.getParameter("email"));
                    emp.setTelefono(request.getParameter("telefono"));
                    emp.setIva(request.getParameter("iva"));
                    emp.setDireccion(request.getParameter("direccion"));
                    emp.setCiudad(request.getParameter("ciudad"));
                    emp.setBarrio(request.getParameter("barrio"));
                    emp.setCodigoPostal(request.getParameter("codPostal"));
                    emp.setLogo(request.getParameter("logo"));
                    
                    respuesta = empDao.modificar(emp);
                    request.setAttribute("respuesta", respuesta);
                    rd = request.getRequestDispatcher("perfilEmpresa.jsp");
                    
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
