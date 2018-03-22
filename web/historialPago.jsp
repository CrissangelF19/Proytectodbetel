<%-- 
    Document   : historialPago
    Created on : 17/09/2017, 05:26:28 PM
    Author     : Pedro Florez
--%>

<%@page import="com.betel.modelo.PagoVenta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.betel.modelo.PagoCompra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Historial de Pago</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>

</head>

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
                
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
          <h4><i class="glyphicon glyphicon-list"></i> Historial de Pagos </h4>
        </div>
    </div>
        
        <div class="container clear_both padding_fix">
           
        <div class="row">
                
            <div class="col-md-12">
            <div class="panel panel-info">
            <div class="panel-body">
            <div class="porlets-content">
                    
            <!--######### Boton Volver ########-->
            <div class="clearfix">
                <div class="btn-group pull-right">
                    <a href="pago.jsp" class="btn btn-info"><i class="fa fa-reply"></i> Volver</a>
                </div>
            </div>                

            <div class="margin-top-10"></div>
                
            <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline" role="grid">

            <!--###### Inicio de la Tabla Categoria ######-->
            <table class="display table table-bordered table-striped dataTable" id="dynamic-table" aria-describedby="dynamic-table_info">

                <thead>
                    <tr>
                        <th>Cantidad</th>
                        <th>Con Factura</th>
                        <th>Monto Pagado</th>
                        <th>Fecha</th>
                        <th> </th>
                    </tr>
                </thead>

                <tbody>
                    <!--##### Fila inicial Pago Compra####-->
                    <%
                        if(request.getAttribute("listaPCompra")!=null){
                            List<PagoCompra> listPago = new ArrayList();
                            listPago = (List<PagoCompra>) request.getAttribute("listaPCompra");                       
                        
                        for(PagoCompra p : listPago){  
                    %>
                    
                    <tr>
                        <td><%= p.getIdCompra() %></td>
                        <td> si</td>
                        <td>$ <%= p.getMonto() %></td>
                        <td><%= p.getFecha() %></td>
                        <td>
                          <a  href="javascript:void(0);" class="btn btn-danger" title="Eliminar Historial" onclick=""><i class="glyphicon glyphicon-trash"></i></a>                      
                        </td>
                    </tr>
                    <% } } %>
                    <!--##### Termina la Fila Inicial Pago Compra #####-->
                    
                    <!--#####################################################-->
                    
                    <!--##### Fila inicial Pago Venta ####-->
                    <%
                        if(request.getAttribute("listaPVenta")!=null){
                            List<PagoVenta> listPago = new ArrayList();
                            listPago = (List<PagoVenta>) request.getAttribute("listaPVenta");                       
                        
                        for(PagoVenta p : listPago){  
                    %>
                    
                    <tr>
                        <td><%= p.getIdVenta() %></td>
                        <td> si</td>
                        <td>$ <%= p.getMonto() %></td>
                        <td><%= p.getFecha() %></td>
                        <td>
                          <a  href="javascript:void(0);" class="btn btn-danger" title="Eliminar Historial" onclick=""><i class="glyphicon glyphicon-trash"></i></a>                      
                        </td>
                    </tr>
                    <% } } %>
                    <!--##### Termina la Fila Inicial Pago Venta #####-->

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
