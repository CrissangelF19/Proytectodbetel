
package com.betel.controlador;

import com.betel.modelo.DetalleVenta;
import com.betel.dao.DetalleVentaDaoImpl;
import com.betel.dao.DetalleCompraDaoImpl;
import com.betel.dao.ProductoDaoImpl;
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

@WebServlet(name = "ProductoSvl", urlPatterns = {"/ProductoSvl"})
public class ProductoSvl extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            DetalleVentaDaoImpl daoVenta = new DetalleVentaDaoImpl();
            DetalleVenta dv = new DetalleVenta();
            
            DetalleCompraDaoImpl daoCompra = new DetalleCompraDaoImpl();
            DetalleCompra dc = new DetalleCompra();
            
            ProductoDaoImpl prodDao =  new ProductoDaoImpl();
            Producto producto = new Producto();
            List<Producto> listProducto = new ArrayList();
            
            String respuestaR, respuestaE, respuestaM, respuestaV = null;
            RequestDispatcher rd = null; 
            
            try {
                
                if(request.getParameter("btnRegistrar")!=null){
                    producto.setCodProducto(request.getParameter("codigo"));
                    producto.setNombre(request.getParameter("nombre"));                    
                    producto.setUnidadesCaja(Integer.parseInt(request.getParameter("unidadesCaja")));                    
                    producto.setPrecioVenta(Double.parseDouble(request.getParameter("precioVenta")));
                    producto.setpGanancia(Double.parseDouble(request.getParameter("pGanancia")));                    
                    producto.setStockMin(Integer.parseInt(request.getParameter("stockMin")));
                    producto.setDescripcion(request.getParameter("descripcion"));
                    producto.setIdCategoria(request.getParameter("idCategoria"));
                    producto.setIdProveedor(request.getParameter("idProveedor"));                    
                    producto.setFechaRegistro(Date.valueOf(request.getParameter("fechaRegistro")));
                    producto.setUniDisponible(Integer.parseInt(request.getParameter("uniDisponible")));
                    producto.setEstado("Activo"); 
                    
                    
                    if(prodDao.existe(producto)){                        
                        respuestaV =  "El Producto " + producto.getNombre() + " ya existe.";
                        request.setAttribute("respuestaV", respuestaV);
                        rd = request.getRequestDispatcher("producto.jsp");
                        
                    }else{                        
                        respuestaR =  prodDao.insertar(producto);
                        request.setAttribute("respuestaR", respuestaR);
                        rd = request.getRequestDispatcher("producto.jsp");
                    }
                   
                    
                }else if(request.getParameter("btnEliminar")!=null){
                    producto.setIdProducto(request.getParameter("codProd"));
                    prodDao.eliminar(producto);
                    
                    respuestaE =  prodDao.eliminar(producto);
                    request.setAttribute("respuestaE", respuestaE);
                    rd = request.getRequestDispatcher("producto.jsp");
                                        
                }else if(request.getParameter("btnVerDetalle")!=null){
                    producto = (Producto) prodDao.buscarPorID(request.getParameter("codProd"));
                    request.setAttribute("producto", producto);
                    rd = request.getRequestDispatcher("verProducto.jsp");
                    
                }else if(request.getParameter("btnModificar")!=null){
                    producto = (Producto) prodDao.buscarPorID(request.getParameter("codProd"));
                    request.setAttribute("producto", producto);
                    rd = request.getRequestDispatcher("modiProducto.jsp");
                    
                }else if(request.getParameter("btnActualizar")!=null){
                    producto.setIdProducto(request.getParameter("idCod"));
                    producto.setCodProducto(request.getParameter("codigo"));
                    producto.setNombre(request.getParameter("nombre"));                    
                    producto.setUnidadesCaja(Integer.parseInt(request.getParameter("unidadesCaja")));                    
                    producto.setPrecioVenta(Double.parseDouble(request.getParameter("precioVenta")));
                    producto.setpGanancia(Double.parseDouble(request.getParameter("pGanancia")));
                    producto.setStockMin(Integer.parseInt(request.getParameter("stockMin")));
                    producto.setDescripcion(request.getParameter("descripcion"));
                    producto.setIdCategoria(request.getParameter("idCategoria"));
                    producto.setIdProveedor(request.getParameter("idProveedor"));
                    producto.setFechaRegistro(Date.valueOf(request.getParameter("fechaRegistro")));
                    producto.setEstado(request.getParameter("estado"));

                    respuestaM =  prodDao.modificar(producto);
                    request.setAttribute("respuestaM", respuestaM);
                    rd = request.getRequestDispatcher("producto.jsp");
                    
                }else if(request.getParameter("btnBuscar")!=null){
                    int id = Integer.valueOf(request.getParameter("idBusqueda").trim());
                    switch (id) {
                        case 0:
                            producto.setCodProducto(request.getParameter("valor"));                                                        
                            break;
                        case 1:
                            producto.setNombre(request.getParameter("valor"));
                            break;
                        case 2:
                            producto.setIdCategoria(request.getParameter("valor"));
                            break;
                        case 3:
                            producto.setEstado(request.getParameter("valor"));
                            break;
                        case 4:
                           producto.setIdProducto(request.getParameter("valor"));
                            break; 
                                       
                        
                        default:
                            throw new AssertionError();
                    }
                    
                    listProducto = (List<Producto>)prodDao.busquedaPorParametro(request.getParameter("idBusqueda"), producto);                    
                    request.setAttribute("listProducto", listProducto);
                    rd = request.getRequestDispatcher("buscar_producto.jsp");
                    
                }else if(request.getParameter("btnHistorial")!=null){                    
                
                    producto = (Producto) prodDao.buscarPorID(request.getParameter("codProd"));
                    request.setAttribute("producto", producto);
                    
                    rd = request.getRequestDispatcher("historialProducto.jsp");  
                }   
                    

                } catch (Exception e) {
                    System.out.println("Problemas en el server producto: " + e.toString());
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
