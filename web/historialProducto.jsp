<%-- 
    Document   : historialProducto
    Created on : 17/09/2017, 05:26:28 PM
    Author     : Pedro Florez
--%>

<%@page import="com.betel.dao.CompraDaoImpl"%>
<%@page import="com.betel.dao.DetalleVentaDaoImpl"%>
<%@page import="com.betel.modelo.DetalleVenta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.betel.modelo.DetalleCompra"%>
<%@page import="com.betel.dao.DetalleCompraDaoImpl"%>
<%@page import="com.betel.modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Historial de Producto</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>

</head>

<%
    Producto prod = (Producto) request.getAttribute("producto"); 
    
    DetalleCompraDaoImpl daoDC = new DetalleCompraDaoImpl(); 
    List<DetalleCompra> listDCompra = new ArrayList();
    
    DetalleVentaDaoImpl daoDV = new DetalleVentaDaoImpl();
    List<DetalleVenta> listDVenta = new ArrayList();
%>

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
                
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
          <h4><i class="glyphicon glyphicon-list"></i> Historial de Producto </h4>
        </div>
    </div>
        
        <div class="container clear_both padding_fix">
           
        <div class="row">
                
            <div class="col-md-12">
            <div class="panel panel-info">
            <div class="panel-body">
            <div class="porlets-content">
            
            <form action="ProductoSvl"  method="get" class="form-horizontal">                
             
            <!--######### Boton Volver ########-->
            <div class="modal-header">
                <div class="btn-group pull-right">
                    <a href="inventario.jsp" class="btn btn-info"> <i class="fa fa-reply"></i> <span> Volver</span></a> 
                </div>
                <h4 class="modal-title"> <%= prod.getNombre() %> </h4>
            </div>
            
            <div class="margin-top-10"></div>
            
            
                <div class="row">                   
             
                    <div class="col-md-4 col-sm-12 col-xs-12">
                        <div class="panel panel-primary text-center no-boder bg-color-green">
                            <div class="panel-body">
                                <i class="fa fa-shopping-cart fa-4x"></i>
                                <h4> <%= daoDC.buscarPorIDHistCompra(prod.getIdProducto()) %> </h4>
                            </div>
                            <div class="footer_blue">
                                <strong>Compras</strong>  
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-4 col-sm-12 col-xs-12">
                        <div class="panel panel-primary text-center no-boder bg-color-blue">
                            <div class="panel-body">
                                <i class="fa fa-archive fa-4x"></i>
                                <h4> <%= prod.getUniDisponible() %> </h4>
                            </div>
                            <div class="footer_green">
                                <strong>Disponible</strong>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-4 col-sm-12 col-xs-12">
                        <div class="panel panel-primary text-center no-boder bg-color-red">
                            <div class="panel-body">
                                <i class="fa fa-truck fa-4x"></i>
                                <h4> <%= daoDV.buscarPorIDHistVenta(prod.getIdProducto()) %> </h4>
                            </div>
                            <div class="footer_purple">
                                <strong>Ventas</strong>
                            </div>
                        </div>
                    </div>

                </div>          
            </form>    
                
            <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline" role="grid">

            <!--###### Inicio de la Tabla Historial ######-->
            <table class="display table table-bordered table-striped dataTable" id="dynamic-table" aria-describedby="dynamic-table_info">

                <thead>
                    <tr>
                        <th> </th>
                        <th>Cantidad Unid.</th>                        
                        <th>Tipo</th>
                        <th>Fecha</th>
                        <th> </th>
                    </tr>
                </thead>

                <tbody>
                    
                    <!--##### Fila inicial ####-->
                    <%
                        listDCompra = daoDC.listarDC(prod.getIdProducto());                        
                        for(DetalleCompra dc : listDCompra){  
                    %>
                    
                    <tr>
                        <td> </td>
                        <td><%= dc.getTotalUnidades() %></td>
                        <td>Compra</td>                        
                        <td><%= dc.getIdCompra() %></td>
                        <td>
                          <a  href="javascript:void(0);" class="btn btn-danger" title="Eliminar Historial" onclick=""><i class="glyphicon glyphicon-trash"></i></a>                      
                        </td>
                    </tr>
                    
                    <% } %>
                    
                    <%
                        listDVenta = daoDV.listarDV(prod.getIdProducto());
                        for(DetalleVenta dv : listDVenta){  
                    %>
                    
                    <tr>
                        <td> </td>
                        <td><%= dv.getTotalUnidades() %></td>
                        <td>Venta</td>                        
                        <td><%= dv.getIdVenta() %></td>
                        <td>
                          <a  href="javascript:void(0);" class="btn btn-danger" title="Eliminar Historial" onclick=""><i class="glyphicon glyphicon-trash"></i></a>                      
                        </td>
                    </tr>
                    
                    <% } %>
                    
                    <!--##### Termina la Fila Inicial #####-->

                </tbody>

            </table> 
            
            </div>
            </div>
            </div>
            </div>                
        </div>
    </div>
</div> 
    
    
<script src="js/modalStatico.js" type="text/javascript"></script>

<script src="js/jquery-2.1.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common-script.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="plugins/data-tables/jquery.dataTables.js"></script>
<script src="plugins/data-tables/DT_bootstrap.js"></script>
<script src="plugins/data-tables/dynamic_table_init.js"></script>

</body>
</html>
