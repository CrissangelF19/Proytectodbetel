<%-- 
    Document   : Venta
    Created on : 6/09/2017, 04:29:41 PM
    Author     : Pedro Florez
--%>

<%@page import="com.betel.dao.CategoriaDaoImpl"%>
<%@page import="com.betel.modelo.Categoria"%>
<%@page import="java.util.Date"%>
<%@page import="com.betel.modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.ProductoDaoImpl"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            
            <div class="margin-top-10"></div>
            
            <div class="panel panel-info">
                                
                <div class="panel-body">  
                                        
                <div class="table-responsive">                 

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
                        <td class="col-lg-1"><input type="number" class="form-control"  name="cajas" max="99999" value="0" required autofocus></td>
                        <td class="col-lg-1"><input type="number" class="form-control" name="unid" max="99999" value="0" required></td>
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
            </div>
                            
            </div>
            </div>			
        </div>
    </div>
</div>
            
<script src="js/bootstrap.min.js"></script>

</body>
</html>

