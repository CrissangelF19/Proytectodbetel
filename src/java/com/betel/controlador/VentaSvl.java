
package com.betel.controlador;

import com.betel.dao.ClienteDaoImpl;
import com.betel.dao.DetalleVentaDaoImpl;
import com.betel.dao.VentaDaoImpl;
import com.betel.modelo.Articulo;
import com.betel.modelo.Cliente;
import com.betel.modelo.DetalleVenta;
import com.betel.modelo.Producto;
import com.betel.modelo.Venta;
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

@WebServlet(name = "VentaSvl", urlPatterns = {"/VentaSvl"})
public class VentaSvl extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            Cliente client = new Cliente();
            ClienteDaoImpl cliDao = new ClienteDaoImpl();
            
            Producto producto = new Producto();
            List<Producto> listProducto = new ArrayList<>();
            
            VentaDaoImpl ventaDao = new VentaDaoImpl();
            Venta objVenta = new Venta();
            List<Venta> listVenta = new ArrayList<>();
            
            List<DetalleVenta> listaDetalle = new ArrayList<>();
            DetalleVenta dv = new DetalleVenta();
            DetalleVentaDaoImpl dvDao = new DetalleVentaDaoImpl();
            
            String respuestaR, respuestaE, respuestaM, respuestaB = null;
            
            RequestDispatcher rd = null;
            
            try {
                
                if(request.getParameter("btnBuscar")!=null){
                    int id = Integer.valueOf(request.getParameter("idBusqueda").trim());
                    switch (id) {
                        case 0:
                            producto.setCodProducto(request.getParameter("valor"));                                                        
                            break;
                        case 1:
                            producto.setNombre(request.getParameter("valor"));
                            break;   
                            
                        default:
                            throw new AssertionError();
                    }
                    
                    listProducto = (List<Producto>)ventaDao.busquedaPorParametroProducto(request.getParameter("idBusqueda"), producto);                    
                    
                    if (listProducto.size()>0) {                        
                        request.setAttribute("listProducto", listProducto);
                        rd = request.getRequestDispatcher("carritoVenta.jsp");
                        
                    }else {
                        respuestaB = "Información invalida";
                        request.setAttribute("respuestaB", respuestaB);
                        rd = request.getRequestDispatcher("carritoVenta.jsp");
                    }
                    
                }else if(request.getParameter("btnRegistrar")!=null){
                    
                    HttpSession sesion = request.getSession(true);
                    ArrayList<Articulo> articulos = sesion.getAttribute("carrito") == null ? null : (ArrayList) sesion.getAttribute("carrito");
                    
                    for (Articulo articulo : articulos) {
                        
                        listaDetalle.add(new DetalleVenta(null, null, articulo.getIdProducto(), articulo.getCajas(), articulo.getUnid(), 0, articulo.getPrecioUnid(), articulo.getDscto()));
                                                
                    }
                    
                    sesion.removeAttribute("carrito");
                    
                    objVenta.setnVenta(request.getParameter("nVenta"));
                    objVenta.setIdCliente(request.getParameter("idCliente"));
                    objVenta.setIdEmpleado(request.getParameter("idEmpleado"));
                    objVenta.setIdEmpresa("1");
                    objVenta.setEfectivo(Double.parseDouble(request.getParameter("efectivo")));
                    objVenta.setObservacion(request.getParameter("observ"));
                    objVenta.setSubtotal(Double.parseDouble(request.getParameter("subtotal")));
                    objVenta.setIva(Double.parseDouble(request.getParameter("iva")));
                    objVenta.setTotalPagar(Double.parseDouble(request.getParameter("totalP")));
                    objVenta.setSaldoPendiente(Double.parseDouble(request.getParameter("saldo")));
                    objVenta.setFecha(Date.valueOf(request.getParameter("fecha")));
                    objVenta.setDetalleVenta(listaDetalle);
                    objVenta.setPago(request.getParameter("pago"));
                    objVenta.setEstado("Activo");
                    
                    respuestaR = ventaDao.insertar(objVenta);                    
                    request.setAttribute("respuestaR", respuestaR);
                    
                    rd = request.getRequestDispatcher("admin_Venta.jsp");
                    
                }else if(request.getParameter("btnEliminar")!=null){
                    
                    objVenta.setIdVenta(request.getParameter("idVenta"));
                    respuestaE = ventaDao.eliminar(objVenta);
                    
                    request.setAttribute("respuestaE", respuestaE);
                    rd = request.getRequestDispatcher("admin_Venta.jsp");
                    
                }else if(request.getParameter("btnBuscarVenta")!=null){
                    int id = Integer.valueOf(request.getParameter("idBusqueda").trim());
                    
                    switch (id) {
                        case 0:
                            objVenta.setnVenta(request.getParameter("valor"));                                                        
                            break;
                        case 1:
                            objVenta.setIdCliente(request.getParameter("valor"));
                            break;
                        case 2:
                            objVenta.setIdEmpleado(request.getParameter("valor"));
                            break;    
                        case 3:
                            objVenta.setPago(request.getParameter("valor"));
                            break;
                        case 4:
                            objVenta.setEstado(request.getParameter("valor"));
                            break;    
                            
                        default:
                            throw new AssertionError();
                    } 
                    
                    listVenta = (List<Venta>)ventaDao.busquedaPorParametro(request.getParameter("idBusqueda"), objVenta);
                    
                    request.setAttribute("listVenta", listVenta);
                    rd = request.getRequestDispatcher("buscar_Venta.jsp");                    
                }
                
            } catch (Exception e) {
                System.out.println("Problemas en el server Venta: " + e.toString());
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
