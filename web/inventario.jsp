<%-- 
    Document   : inventario
    Created on : 17/09/2017, 01:02:19 AM
    Author     : Pedro Florez
--%>

<%@page import="com.betel.modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.ProductoDaoImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Inventario</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-2.1.0.js"></script>

</head>

<%
    ProductoDaoImpl dao = new ProductoDaoImpl();
    List<Producto> listProducto = new ArrayList();                
%>

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
      
        
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
          <h4><i class="glyphicon glyphicon-signal"></i> Inventario de Productos </h4>
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
            
            <div class="table-responsive">  
                
            <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline" role="grid">                        
                        
            <!--###### Inicio de la Tabla Inventario ######-->
            <table class="display table table-bordered table-striped dataTable" id="dynamic-table" aria-describedby="dynamic-table_info">
                <thead>
                    <tr>
                        <th>Còdigo</th>
                        <th>Producto</th>
                        <th>Proveedor</th>
                        <th>Disponible</th>
                        <th> </th>
                    </tr>
                </thead>
                
              <tbody>
                <!--##### Fila inicial ####-->
                <%
                    listProducto = dao.listar();
                    for(Producto p : listProducto){                    
                %>

                <tr>
                    <td><%= p.getCodProducto() %></td>
                    <td><%= p.getNombre()%></td>
                    <td><%= p.getIdProveedor() %></td>
                    <td><%= p.getUniDisponible() %></td>
                    <td>
                        
                        <form action="ProductoSvl" method="get">
                            <a href="javascript:;" class="btn btn-success btn-sm" onclick="parentNode.submit();" name="btnHistorial">
                                <i class="glyphicon glyphicon-list"></i> Historial
                            </a>

                            <input type="hidden" name="btnHistorial" value="ver"/>
                            <input type="hidden" name="codProd" value='<%= p.getIdProducto()  %>'/>
                        </form>
                        
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
