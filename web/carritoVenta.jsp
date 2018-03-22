<%-- 
    Document   : Venta
    Created on : 6/09/2017, 04:29:41 PM
    Author     : Pedro Florez
--%>

<%@page import="com.betel.dao.VentaDaoImpl"%>
<%@page import="com.betel.modelo.Cliente"%>
<%@page import="com.betel.dao.ClienteDaoImpl"%>
<%@page import="com.betel.modelo.Empresa"%>
<%@page import="com.betel.dao.EmpresaDaoImpl"%>
<%@page import="com.betel.modelo.Empleado"%>
<%@page import="com.betel.dao.EmpleadoDaoImpl"%>
<%@page import="com.betel.modelo.Articulo"%>
<%@page import="com.betel.dao.ProveedorDaoImpl"%>
<%@page import="com.betel.modelo.Proveedor"%>
<%@page import="java.util.Date"%>
<%@page import="com.betel.modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.ProductoDaoImpl"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession sesion = request.getSession(true);
    ArrayList<Articulo> articulos = sesion.getAttribute("carrito") == null ? null : (ArrayList) sesion.getAttribute("carrito");
%>

<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Venta</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />

<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
    
</head>

<%
    VentaDaoImpl dao = new VentaDaoImpl();    
    String codigo =  dao.generarCodigo(); 
%>

<%
    ClienteDaoImpl daoC = new ClienteDaoImpl();
    List<Cliente> listCliente =  new ArrayList();
    listCliente = daoC.listar();
%>


<%
    EmpleadoDaoImpl daoE = new EmpleadoDaoImpl();
    List<Empleado> listEmpleado = new ArrayList();
    listEmpleado = daoE.listar();
%>

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">       
        
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
          <h4><i class="fa fa-truck"></i> Ventas </h4>
        </div>       
    </div>
        
    <div class="container clear_both padding_fix">         
     
        <div class="row">
            
            <div class="col-md-12">

            <div class="panel panel-info"> 

            <div class="panel-body">

            <h5 style="margin-left: 5px;"> Buscar productos </h5>
                
            <!--######## Buscar Productos #######-->    
            <form class="form-inline" action="VentaSvl" method="get" style="margin-left: 15px;margin-right: 15px;">                
                <select class="form-control" id="inlineFormCustomSelect" name="idBusqueda" required>
                    <option value selected>- Seleccione -</option>
                    <option value="0">Código</option>                  
                    <option value="1">Nombre</option>
                </select>                
                <input type="text" class="form-control" id="inputBuscar" name="valor" autocomplete required>
                <button type="submit" class="btn btn-primary" name="btnBuscar" value="buscar"><i class="glyphicon glyphicon-search"></i> Buscar</button>
            </form>
                           
            <div class="table-responsive">
                
                <div class="block-web">                   
                    
                <!--##### Alert de Busqueda ####-->
                <%         
                    if(request.getAttribute("respuestaB")!=null){
                %>
                    <div class="alert alert-info content2" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <%out.println("<strong>No se encontraron registros.</strong>"); %> 
                    </div>                             
                <%
                    }                        
                %>    

                <form action="AddCartVenta" method="get">
                
                <!--###### Tabla Productos ######-->
                <table class="table">                    
                    
                    <%
                        if(request.getAttribute("listProducto")!=null){
                            List<Producto> listProducto = new ArrayList();
                            listProducto = (List<Producto>) request.getAttribute("listProducto");

                        for(Producto p : listProducto){
                    %>
                <thead>
                    <tr class="warning">
                        <th>Còdigo</th>
                        <th>Nombre</th>
                        <th>Disponible</th>
                        <th>Caja</th>
                        <th>Unid.</th>
                        <th>Dscto.</th>
                        <th>Precio Unit.</th>
                        <th> </th>
                    </tr>
                </thead>

                <tbody>
                    
                    <tr>
                        <input type="hidden" name="idProd" value="<%= p.getIdProducto() %>" />
                            
                        <td><%= p.getCodProducto() %></td>                            
                        <td><%= p.getNombre()%></td>
                        <td><%= p.getUniDisponible() %></td>
                        <td class="col-lg-1"><input type="number" class="form-control"  name="cajas" id="caja"  max="99999" value="0" required autofocus></td>
                        <td class="col-lg-1"><input type="number" class="form-control" name="unid" id="unid" max="99999" value="0" required></td>
                        <td class="col-lg-1"><input type="number" class="form-control" name="dsct" max="9999999" value="0" required></td>
                        <td class="col-lg-2"><input type="text" class="form-control" name="precioUnid" value="<%= p.getPrecioVenta() %>" readonly></td>
                        <td class="col-lg-1">
                            <button type="submit" class="btn btn-info" title="Agregar">
                                <i class="fa fa-plus-square"></i>                            
                            </button> 
                        </td>
                    </tr>
                    
                </tbody>
                
                    <%  } 
                        }
                    %>
                </table>
                
                </form>
                
                </div>
                
            </div>    
                
            <div class="col-lg-8">
            
            <h4> Lista de la Venta </h4>    
                
            <div class="table-responsive" id="cart-container">
                    
                <!--###### Carrito de Venta ######-->
                <table class="table" id="shop-table">
                    
                <thead>
                    <tr>
                        <th>CODIGO</th>
                        <th>DESCRIPCION</th>
                        <th>CAJA</th>
                        <th>UNID.</th>
                        <th>CANTIDAD</th>
                        <th>DSCT.</th>
                        <th><span class="pull-right">PRECIO UNIT.</span></th>
                        <th><span class="pull-right">PRECIO TOTAL</span></th>                        
                        <th> </th>
                    </tr>
                </thead>

                <tbody>
                    
                    <%
                        EmpresaDaoImpl daom = new EmpresaDaoImpl();                        
                        Empresa emp = new Empresa();

                        emp = (Empresa) daom.buscarPorID("1");

                        double empIva = Double.valueOf(emp.getIva());
                        
                        ProductoDaoImpl pv = new ProductoDaoImpl();
                        
                        double subtotal = 0;                        
                        double iva = 0;
                        double total = 0;
                        int totalUnd = 0;
                        
                        if(articulos != null){
                        for(Articulo a: articulos){
                            Producto producto = (Producto) pv.buscarPorID(a.getIdProducto());
                            
                            totalUnd = a.getCajas() * producto.getUnidadesCaja() + a.getUnid();
                            
                            subtotal += totalUnd * a.getPrecioUnid() - a.getDscto();
                            
                            iva = subtotal * empIva;
                            
                            total = iva + subtotal; 
                        
                    %>
                    
                    <tr>                                                    
                        <td><%= producto.getCodProducto()%></td>                            
                        <td><%= producto.getNombre()%></td>
                        <td class="col-lg-1"><%= a.getCajas() %></td>
                        <td class="col-lg-1"><%= a.getUnid() %></td>
                        <td class="col-lg-1"><%= totalUnd %></td>
                        <td class="col-lg-1"><%= a.getDscto() %></td>
                        <td class="col-lg-2"><p class="pull-right">$ <%= a.getPrecioUnid() %></p></td>
                        <td class="col-lg-2"><p class="pull-right">$ <%= Math.round(totalUnd * a.getPrecioUnid()*100.00) / 100.0 - a.getDscto()%></p></td>
                        <td class="col-lg-1">
                            <span id="idarticulo" style="display:none;"><%= producto.getIdProducto()%></span>
                            <a class="btn btn-danger" id="deleteitem" title="Eliminar">                                
                                <i class="glyphicon glyphicon-trash"></i>                            
                            </a> 
                        </td>
                    </tr>
                    
                    <%}}%>
                                                            
                    <tr>
                        <td colspan="7"><span class="pull-right"><strong>SUBTOTAL</strong></span></td>
                        <td><span class="pull-right" id="txt-subtotal">$ <%= Math.round(subtotal*100.00)/100.0%></span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="7"><span class="pull-right"><strong>IVA (<%= empIva %>) %</strong></span></td>
                        <td><span class="pull-right" id="txt-iva">$ <%= Math.round(iva * 100.00)/100.0%></span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="7"><span class="pull-right"><strong>TOTAL</strong></span></td>
                        <td><span class="pull-right" id="txt-total">$ <%= Math.round(total * 100.00)/100.0%></span></td>
                        <td></td>
                    </tr>                    
                    
                </tbody>                
                
                </table>
                
                <% if (articulos == null){%>
                    <h5>No hay Productos en la Venta</h5>
                <%}%>
                        
            </div>
                
            </div>
            
            <div class="col-lg-4">

            <h3>Resumen</h3>
            
            <form action="VentaSvl" method="get">                
                
                <input type="hidden" name="subtotal" readonly="readonly" value="<%= Math.round(total*100.0)/100.0 %>" />
                <input type="hidden" name="iva" readonly="readonly" value="0.00" />
                <input type="hidden" name="totalP" readonly="readonly" value="<%= Math.round(total*100.0)/100.0 %>" />
                <input type="hidden" name="saldo" value="-1" />
                
            <div class="row">

            <div class="col-md-12">
                <label class="control-label"><strong>N°Venta *</strong></label> 
                <div class="col-lg-12">
                    <input type="text" class="form-control"  name="nVenta" value="<%= codigo %>" readonly>                    
                </div>
            </div>
                
            </div>

            <div class="row">
                
            <div class="col-md-6">
                <label class="control-label"><strong>Cliente *</strong></label>
                <div class="col-lg-12">
                    <select class="form-control" required name="idCliente">
                        <option value selected>- Seleccione -</option>
                        
                        <%                                       
                            for(Cliente clit : listCliente){
                                if(clit.getEstado().equals("Activo")){

                                    %><option value="<%= clit.getIdCliente() %>"><%= clit.getNombreCompleto()%></option> <%                                                
                                }
                            }                              
                        %>                                                                    
                    </select>
                </div>
            </div>
                
            <div class="col-md-6">
                <label class="control-label"><strong>Empleado *</strong></label>

                <div class="col-lg-12">
                    <select name="idEmpleado" class="form-control" required>
                        <option value selected>- Seleccione -</option>  
                        
                        <%                                       
                            for(Empleado empl : listEmpleado){
                                if(empl.getEstado().equals("Activo")){

                                    %><option value="<%= empl.getIdEmpleado() %>"><%= empl.getNombreCompleto()%></option> <%                                                
                                }
                            }                              
                        %>
                    </select>
                </div>
            </div>
                    
            </div>        
                        
            <div class="row">
                
            <div class="col-md-6">
                <label class="control-label"><strong>Fecha *</strong></label>
                <div class="col-lg-12">
                    <input type="date" name="fecha" class="form-control" required>
                </div>
            </div>    
                
            <div class="col-md-6">
                <label class="control-label"><strong>Pago *</strong></label>
                <div class="col-lg-12">
                    <select name="pago" class="form-control" required>
                        <option value selected>- Seleccione -</option>
                        <option value="Pagado">Pagado</option>
                        <option value="Pendiente">Pendiente</option>                      
                    </select>
                </div>
            </div> 
            
            </div>                
            
            <div class="row">

            <div class="col-md-12">
                <label class="control-label"><strong>Observaciòn</strong></label>
                <div class="col-lg-12">
                    <textarea class="form-control"  name="observ" style="margin-top: 0px; margin-bottom: 0px; height: 32px;" placeholder="Observaciòn" maxlength="99"></textarea>
                </div>
            </div>
                
            </div>
            
            <div class="row">

            <div class="col-md-12">
                <label class="control-label"><strong>Efectivo *</strong></label> 
                <div class="col-lg-12">
                    <input type="number" name="efectivo" class="form-control" max="9999999" value="0" required>
                </div>
            </div>
                
            </div>        
                        
            <!-- ## Botones Cancelar y Guardar ###-->
            <div class="modal-footer" style="border-top-width: 0px; border-top-width: 0px;padding-right: 100px;">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button type="submit" class="btn btn-info" name="btnRegistrar" value="Registrar"><i class="glyphicon glyphicon-ok"></i> Guardar</button>
            </div>
            
            </form>
            
            </div>
                
            </div>
            </div>
            </div>			
        </div>
    </div>
</div>   

<script src="js/bootstrap.min.js"></script>
<script src="js/carrito-venta.js"></script>

</body>
</html>

