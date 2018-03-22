<%-- 
    Document   : Pagos
    Created on : 6/09/2017, 04:29:41 PM
    Author     : Pedro Florez
--%>
<%@page import="com.betel.modelo.Cliente"%>
<%@page import="com.betel.modelo.Venta"%>
<%@page import="com.betel.dao.VentaDaoImpl"%>
<%@page import="com.betel.modelo.Compra"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.CompraDaoImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Creditos</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>

</head>

<%
    CompraDaoImpl dao = new CompraDaoImpl();
    List<Compra> listCompra = new ArrayList();    
%>

<%
    VentaDaoImpl daoV = new VentaDaoImpl();
    List<Venta> listVenta = new ArrayList();
%>

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
               
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
          <h4><i class="fa fa-money"></i> Creditos</h4>
        </div>       
    </div>
        
    <div class="container clear_both padding_fix">         
     
        <div class="row">
            
            <div class="col-md-12">

            <div class="panel panel-info">

            <div class="panel-body">

            <div class="porlets-content">
                
            <!--######### Boton Descargar ########-->
            <div class="clearfix">
                <div class="btn-group pull-right">
                    <button href="javascript:void(0);" class="btn btn-info"> <i class="glyphicon glyphicon-save"></i> Descargar</button>
                </div>
            </div>    
                        
            <div class="margin-top-10"></div>
                    
            <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline" role="grid">

            <!--###### Inicio de la Tabla Pagos ######-->
            <table class="display table table-bordered table-striped dataTable" id="dynamic-table" aria-describedby="dynamic-table_info">

                <thead>
                    <tr>
                      <th>NºFactura</th>
                      <th>Nombre</th>
                      <th>Tipo</th>                      
                      <th>Saldo Pendiente</th>
                      <th> </th>
                    </tr>
                 </thead>

                <tbody>
                    <!--##### Alert REM ####-->
                    <%         
                        if(request.getAttribute("respuestaR")!=null){
                    %>
                        <div class="alert alert-success content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Bien hecho! El Pago ha sido Realizado con éxito."); %> 
                        </div> 
                    <%
                        }                        
                    %>
                    
                    <!--##### Fila inicial Compra ####--> 
                    <%
                        listCompra = dao.listarPago();
                        for(Compra c : listCompra){  
                    %>

                    <tr>
                        <td><%= c.getnCompra() %></td>
                        <td><%= c.getIdProveedor() %></td>
                        <td>Compra</td>                        
                        <td>
                            <%
                                if (c.getSaldoPendiente() == 0 ) {
                            %>        
                                    <i class="label label-success"> <%= c.getPago() %> </i>
                            <%            
                                }else {
                            %>        
                                    <strong>$ <%= c.getSaldoPendiente() %></strong></td>
                            <%        
                                }                            
                            %>                            
                        <td>                            
                            <!--Botón Realizar Pago Compra-->
                            <div class="col-3 col-md-1">
                            
                                <form action="PagoCompraSvl" method="post">

                                    <a href="javascript:;" class="btn btn-info btn-sm" onclick="parentNode.submit();" name="btnlistaCompra">
                                        <i class="fa fa-plus-square"></i> Realizar Pago 
                                    </a>    

                                    <input type="hidden" name="btnlistaCompra" value="listaCompra"/>
                                    <input type="hidden" name="nCompra" value='<%= c.getnCompra() %>'/>
                                </form>                            
                            </div>
                            <!--Botón Historial de Pago Compra--> 
                            
                            <div class="col-3 col-md-1">
                            
                                <form action="PagoCompraSvl" method="post">

                                    <a href="javascript:;" class="btn btn-success btn-sm" onclick="parentNode.submit();" name="btnHistorial">
                                        <i class="glyphicon glyphicon-list"></i> Historial 
                                    </a>
                                    
                                    <input type="hidden" name="btnHistorial" value="hisrotialCompra"/>
                                    <input type="hidden" name="idCompra" value='<%= c.getIdCompra() %>'/>                                    
                                </form>                            
                            </div>
                            
                        </td>
                    </tr>
                    
                    <% } %>
                    <!--##### Termina la Fila Inicial #####-->
                    
                    <!--##### Fila inicial Venta####--> 
                    <%
                        listVenta = daoV.listarPago();
                        for(Venta v : listVenta){

                        Cliente client = (Cliente) daoV.buscarPorIDCliente(v.getIdCliente());
                    %> 

                    <tr>
                        <td><%= v.getnVenta() %></td>
                        <td><%= client.getIdRuta() %></td>
                        <td>Venta</td>                        
                        <td>
                            <%
                                if (v.getSaldoPendiente() == 0 ) {
                            %>        
                                    <i class="label label-success"> <%= v.getPago() %> </i>
                            <%            
                                }else {
                            %>        
                                    <strong>$ <%= v.getSaldoPendiente() %></strong></td>
                            <%        
                                }                            
                            %> 
                            
                        <td>                            
                            <!--Botón Realizar Pago Venta-->
                            <div class="col-3 col-md-1">
                            
                                <form action="PagoVentaSvl" method="post">

                                    <a href="javascript:;" class="btn btn-info btn-sm" onclick="parentNode.submit();" name="btnlistaVenta">
                                        <i class="fa fa-plus-square"></i> Realizar Pago 
                                    </a>
                                    
                                    <input type="hidden" name="btnlistaVenta" value="listaVenta"/>
                                    <input type="hidden" name="nVenta" value='<%= v.getnVenta() %>'/>
                                </form>                            
                            </div>
                            <!--Botón Historial de pago Venta--> 
                            
                            <div class="col-3 col-md-1">
                            
                                <form action="PagoVentaSvl" method="post">

                                    <a href="javascript:;" class="btn btn-success btn-sm" onclick="parentNode.submit();" name="btnHistorial">
                                        <i class="glyphicon glyphicon-list"></i> Historial 
                                    </a>
                                    
                                    <input type="hidden" name="btnHistorial" value="historialVenta"/>
                                    <input type="hidden" name="idVenta" value='<%= v.getIdVenta() %>'/>                                    
                                </form>                            
                            </div>
                            
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
<!---- Final de la Tabla Pagos ----> 
    
<script src="js/modalStatico.js" type="text/javascript"></script>

<script src="js/bootstrap.min.js"></script>
<script src="js/common-script.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="plugins/data-tables/jquery.dataTables.js"></script>
<script src="plugins/data-tables/DT_bootstrap.js"></script>
<script src="plugins/data-tables/dynamic_table_init.js"></script>

</body>
</html>

