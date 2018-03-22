<%-- 
    Document   : Admin Ventas
    Created on : 6/09/2017, 04:29:41 PM
    Author     : Pedro Florez
--%>

<%@page import="com.betel.modelo.Cliente"%>
<%@page import="com.betel.dao.ClienteDaoImpl"%>
<%@page import="com.betel.modelo.Ruta"%>
<%@page import="com.betel.dao.RutaDaoImpl"%>
<%@page import="com.betel.modelo.Venta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.VentaDaoImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Admin Ventas</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-2.1.0.js"></script>

<script src="js/tooltip.js" type="text/javascript"></script>   

</head>

<%
    VentaDaoImpl dao = new VentaDaoImpl();
%>

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">    
    
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
          <h4><i class="glyphicon glyphicon-list-alt"></i> Historial de Ventas</h4>
        </div>       
    </div>
        
    <div class="container clear_both padding_fix">         
     
        <div class="row">
            
        <div class="col-md-12">
            <div class="panel panel-info">
            <div class="panel-body">
            <div class="porlets-content">                 
                       
                       
            <!--Boton Nueva Factura-->             
            <div class="clearfix">
                <div class="btn-group pull-right">
                    <a href="venta.jsp" type="button" class="btn btn-info"><i class="fa fa-plus-square"></i> Nueva Venta</a>
                </div>
            </div>
            
            <form class="form-inline" action="VentaSvl" method="get">                
                <select class="form-control" id="inlineFormCustomSelect" name="idBusqueda" required>
                    <option value selected>- Seleccione -</option>                    
                    <option value="0">N°Venta</option>                  
                    <option value="1">Cliente</option>
                    <option value="2">Empleado</option>
                    <option value="3">Pago</option>
                    <option value="4">Estado</option>
                </select>                
                <input type="text" class="form-control" id="inputBuscar" name="valor" placeholder="Busqueda Avanzada">
                <button type="submit" class="btn btn-primary" name="btnBuscarVenta" value="buscarVenta"><i class="glyphicon glyphicon-search"></i> Buscar</button>
            </form>
                    
            <div class="margin-top-10"></div>
                    
            <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline" role="grid">                        
                        
            <!--###### Inicio de la Tabla Admin Ventas ######-->
            <table class="display table table-bordered table-striped dataTable" id="dynamic-table" aria-describedby="dynamic-table_info">

                <thead>
                    <tr>
                        <th>N°Venta</th>
                        <th>Cliente</th>
                        <th>Empleado</th>
                        <th>Total</th>
                        <th>Fecha</th>
                        <th>Pago</th>
                        <th>Estado</th>
                        <th> </th>
                    </tr>
                 </thead>

                <tbody>
                    
                    <!--##### Fila inicial ####-->
                    <%
                        if (request.getAttribute("listVenta")!=null) {
                            List<Venta> listVenta = new ArrayList();
                            listVenta = (List<Venta>) request.getAttribute("listVenta");
                            
                            for(Venta v : listVenta){
                                
                            Cliente client = (Cliente) dao.buscarPorIDCliente(v.getIdCliente());                                                    
                    %>                    

                    <tr>
                        <td><%= v.getnVenta() %></td>
                        <td>                            
                            <a href="#" class="btn btn-secondary" data-toggle="tooltip" data-placement="top" title=" <%= client.getIdRuta() %> " style="padding-top: 1px;padding-bottom: 0px;padding-right: 0px;padding-left: 0px;">
                                <%= client.getNombreCompleto() %>
                            </a>
                        </td>
                        <td><%= v.getIdEmpleado() %></td>
                        <td>$ <%= v.getTotalPagar() %></td>
                        <td><%= v.getFecha() %></td>
                        <td>
                            <%
                                if (v.getPago().equals("Pagado")) {
                            %>        
                                    <i class="label label-success"> <%= v.getPago() %> </i>
                            <%            
                                }else {
                            %>        
                                    <i class="label label-warning"> <%= v.getPago() %> </i>
                            <%        
                                }                            
                            %>                                                        
                        </td>
                        <td><%= v.getEstado() %></td>
                        <td> 
                            
                            <!--Botón Ver y Descargar-->
                            <div class="col-1 col-md-1">

                                <form >

                                    <a href="javascript:;" class="btn btn-default" title="Descargar" onclick="parentNode.submit();" name="btnModificar">
                                      <span class="glyphicon glyphicon-save"></span>
                                    </a>
                                    <input type="hidden" name="btnModificar" value="modificar"/>
                                    <input type="hidden" name="" value=''/>
                                </form>

                            </div>

                            <!--Botón Modificar-->
                            <div class="col-1 col-md-1">

                                <form >                                    

                                    <a href="javascript:;" class="btn btn-info" title="Modificar" onclick="parentNode.submit();" name="btnModificar">
                                      <span class="glyphicon glyphicon-edit"></span>
                                    </a>
                                    <input type="hidden" name="btnModificar" value="modificar"/>
                                    <input type="hidden" name="" value=''/>
                                </form>

                            </div>

                            <!--Botón Eliminar-->
                            <div class="col-1 col-md-1">

                                <form action="VentaSvl" method="get">
                                    
                                    <%
                                        if (v.getPago().equals("Pagado")) {
                                    %>        
                                        <a href="javascript:;" class="btn btn-danger" title="Eliminar" onclick="return confirm('Desea Eliminar esta Venta?') && parentNode.submit();" name="btnEliminar">
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </a>
                                    <%            
                                        }else {
                                    %>        
                                        <a href="javascript:;" class="btn btn-danger" title="Eliminar" onclick="return alert('No se Puede Eliminar La Venta Pendiente')" name="btnEliminar">
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </a>
                                    <%        
                                        }                            
                                    %>
                                    
                                    <input type="hidden" name="btnEliminar" value="eliminar"/>
                                    <input type="hidden" name="idVenta" value='<%= v.getIdVenta() %>'/>
                                    
                                </form>
                            </div>
                            
                        </td>
                    </tr>
                    
                    <% } } %>
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
 
<script src="js/tiempoAlert.js" type="text/javascript"></script>                    

<script src="js/jquery-2.1.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common-script.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="plugins/data-tables/jquery.dataTables.js"></script>
<script src="plugins/data-tables/DT_bootstrap.js"></script>
<script src="plugins/data-tables/dynamic_table_init.js"></script>

</body>
</html>

