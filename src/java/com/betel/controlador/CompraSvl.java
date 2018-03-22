
package com.betel.controlador;

import com.betel.dao.CompraDaoImpl;
import com.betel.dao.DetalleCompraDaoImpl;
import com.betel.modelo.Articulo;
import com.betel.modelo.Compra;
import com.betel.modelo.DetalleCompra;
import com.betel.modelo.Producto;
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


@WebServlet(name = "CompraSvl", urlPatterns = {"/CompraSvl"})
public class CompraSvl extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
                        
            Producto producto = new Producto();
            List<Producto> listProducto = new ArrayList<>();
            
            CompraDaoImpl compDao = new CompraDaoImpl();
            Compra objCompra = new Compra();
            List<Compra> listCompra = new ArrayList<>();
            
            List<DetalleCompra> listaDetalle = new ArrayList<>();
            DetalleCompra dc = new DetalleCompra();
            DetalleCompraDaoImpl dcDao = new  DetalleCompraDaoImpl();
            
            RequestDispatcher rd = null;
            
            String respuestaR, respuestaE, respuestaM, respuestaV, respuestaB = null;
            
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
                    
                    listProducto = (List<Producto>)compDao.busquedaPorParametroProducto(request.getParameter("idBusqueda"), producto);                    
                    
                    if (listProducto.size()>0) {                        
                        request.setAttribute("listProducto", listProducto);
                        rd = request.getRequestDispatcher("carritoCompra.jsp");
                        
                    }else {
                        respuestaB = "Información invalida";
                        request.setAttribute("respuestaB", respuestaB);
                        rd = request.getRequestDispatcher("carritoCompra.jsp");
                    }
                    
                }else if(request.getParameter("btnRegistrar")!=null){
                    HttpSession sesion = request.getSession(true);
                    ArrayList<Articulo> articulos = sesion.getAttribute("carrito") == null ? null : (ArrayList) sesion.getAttribute("carrito");
                    
                    for (Articulo articulo : articulos) {
                        
                        listaDetalle.add(new DetalleCompra(null,null,articulo.getIdProducto(),articulo.getPrecioUnid(),articulo.getDscto(),articulo.getCajas(),articulo.getUnid(), 0));                        
                       
                        //System.out.println("IdProducto: " + articulo.toString() );
                    }
                    
                    objCompra.setnCompra(request.getParameter("nCompra"));
                    objCompra.setIdProveedor(request.getParameter("idProveedor"));                    
                    objCompra.setObservacion(request.getParameter("observ"));
                    objCompra.setNeto(Double.parseDouble(request.getParameter("neto")));;
                    objCompra.setIva(Double.parseDouble(request.getParameter("iva")));
                    objCompra.setTotalPagar(Double.parseDouble(request.getParameter("totalP")));
                    objCompra.setSaldoPendiente(Double.parseDouble(request.getParameter("saldo")));
                    objCompra.setFecha(Date.valueOf(request.getParameter("fecha")));
                    objCompra.setDetalleCompra(listaDetalle);
                    objCompra.setPago(request.getParameter("pago"));
                    objCompra.setEstado("Activo");
                    
                    if (compDao.existe(objCompra)) {
                        respuestaV =  "El N°Compra " + objCompra.getnCompra() + " ya existe.";
                        request.setAttribute("respuestaV", respuestaV);
                        rd = request.getRequestDispatcher("carritoCompra.jsp");
                        
                    } else {
                        respuestaR = compDao.insertar(objCompra);
                        request.setAttribute("respuestaR", respuestaR);
                        rd = request.getRequestDispatcher("admin_Compra.jsp");
                        
                        //Cierra la Session del Carrito
                        sesion.removeAttribute("carrito");
                    }
                    
                }else if(request.getParameter("btnEliminar")!=null){
                    objCompra.setIdCompra(request.getParameter("idCompra"));
                    respuestaE = compDao.eliminar(objCompra);
                    
                    request.setAttribute("respuestaE", respuestaE);
                    rd = request.getRequestDispatcher("admin_Compra.jsp");
                    
                }else if(request.getParameter("btnBuscarCompra")!=null){
                    int id = Integer.valueOf(request.getParameter("idBusqueda").trim());                  
                    
                    switch (id) {
                        case 0:
                            objCompra.setnCompra(request.getParameter("valor"));                                                        
                            break;
                        case 1:
                            objCompra.setIdProveedor(request.getParameter("valor"));
                            break;
                        case 2:
                            objCompra.setPago(request.getParameter("valor"));
                            break;
                        case 3:
                            objCompra.setEstado(request.getParameter("valor"));
                            break;    
                            
                        default:
                            throw new AssertionError();
                    }
                    
                    listCompra = (List<Compra>)compDao.busquedaPorParametro(request.getParameter("idBusqueda"), objCompra);
                                                               
                    request.setAttribute("listCompra", listCompra);
                    rd = request.getRequestDispatcher("buscar_Compra.jsp");                    
                }
                
            } catch (Exception e) {
                System.out.println("Problemas en el server compra: " + e.toString());
                throw new RuntimeException(e);
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
