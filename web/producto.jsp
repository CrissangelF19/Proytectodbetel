<%-- 
    Document   : producto
    Created on : 25/08/2017, 12:45:54 PM
    Author     : Katia Ortiz
--%>

<%@page import="com.betel.dao.ProveedorDaoImpl"%>
<%@page import="com.betel.modelo.Proveedor"%>
<%@page import="com.betel.dao.CategoriaDaoImpl"%>
<%@page import="com.betel.modelo.Categoria"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.betel.modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.ProductoDaoImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Producto</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />

<script src="js/jquery-3.2.1.js" type="text/javascript"></script>

</head>

<%
    ProductoDaoImpl dao = new ProductoDaoImpl();
    List<Producto> listProducto = new ArrayList();
    String codigo =  dao.generarCodigo();            
%>

<%
    CategoriaDaoImpl daoi = new CategoriaDaoImpl();
    List<Categoria> listCategoria =  new ArrayList();
    listCategoria = daoi.listar();
%>

<%
    ProveedorDaoImpl daop = new ProveedorDaoImpl();
    List<Proveedor> listProveedor =  new ArrayList();
    listProveedor = daop.listar();
%>
    
        
<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
       
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
          <h4><i class="glyphicon glyphicon-th-large"></i> Productos</h4>
        </div>
    </div>
     
    <div class="container clear_both padding_fix">         
        <div class="row">
            <div class="col-md-12">

            <div class="panel panel-info">

            <div class="panel-body">

            <div class="porlets-content">
            
            <!--######### Boton Nuevo producto ########-->
            <div class="clearfix">
                <div class="btn-group pull-right">
                    <button href="javascript:void(0);" class="btn btn-info" data-toggle="modal" data-target="#nuevoProducto" id="nBtn"><i class="fa fa-plus-square"></i> Nuevo</button>
                </div>
            </div>
            
            <form class="form-inline" action="ProductoSvl" method="get">                
                    <select class="form-control" id="inlineFormCustomSelect" name="idBusqueda" required>
                        <option value selected>- Seleccione -</option>
                        <option value="0">Código</option>                  
                        <option value="1">Nombre</option>                  
                        <option value="2">Categoria</option>
                        <option value="3">Estado</option>
                        <option value="4" hidden=""></option>
                    </select>                
                  <input type="text" class="form-control" id="inputBuscar" name="valor" placeholder="Busqueda Avanzada" autocomplete required>
                  <button type="submit" class="btn btn-primary" name="btnBuscar" value="buscar"><i class="glyphicon glyphicon-search"></i> Buscar</button>
            </form>
            
            <div class="margin-top-10"></div>
            
            <div class="table-responsive">
            
            <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline" role="grid">
                
            <!--###### Inicio de la Tabla Producto ######-->
            <table class="display table table-bordered table-striped dataTable" id="dynamic-table" aria-describedby="dynamic-table_info">
                <thead>
                    <tr>
                        <th>Còdigo</th>
                        <th>Nombre</th>                        
                        <th>Precio Venta</th>                            
                        <th>Categoria</th>
                        <th>Fecha</th>
                        <th>Estado</th>
                        <th> </th>
                     </tr>
                </thead>

                <tbody>                   
                            
                    <!--##### Alert REMV ####-->
                    <%         
                        if(request.getAttribute("respuestaR")!=null){
                    %>
                        <div class="alert alert-success content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Bien hecho! El Producto ha sido creado con éxito."); %> 
                        </div> 
                    <%
                        }else if(request.getAttribute("respuestaE")!=null){                        
                    %>
                        <div class="alert alert-success content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Bien hecho! El Producto se elimino satisfactoriamente."); %>                            
                        </div>                        
                    <%
                        }else if(request.getAttribute("respuestaM")!=null){                        
                    %>
                        <div class="alert alert-success content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Bien hecho! El Producto ha sido actualizado con éxito."); %>  
                        </div>                
                    <%
                        }else if(request.getAttribute("respuestaV")!=null){
                    %>
                        
                        <div class="alert alert-danger content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Error! " + request.getAttribute("respuestaV")); %>   
                        </div>
                
                    <%
                        }
                    %> 
                
                    <!--##### Fila inicial ####-->
                    <%
                      listProducto = dao.listar();
                      for(Producto p : listProducto){
                    %>

                    <tr>
                        <td><%= p.getCodProducto() %></td>
                        <td><%= p.getNombre()%></td>                        
                        <td> $ <%= p.getPrecioVenta() %></td>
                        <td><%= p.getIdCategoria()%></td>
                        <td><%= p.getFechaRegistro()%></td>
                        <td><%= p.getEstado()%></td>
                        <td>                          

                            <!--Botón ver-->
                            <div class="col-1 col-md-1">

                                <form action="ProductoSvl" method="get">
                                    <a href="javascript:;" class="btn btn-success" onclick="parentNode.submit();" name="btnVerDetalle">
                                      <span class="glyphicon glyphicon-eye-open"></span>
                                    </a>
                                    
                                    <input type="hidden" name="btnVerDetalle" value="ver"/>
                                    <input type="hidden" name="codProd" value='<%= p.getIdProducto()  %>'/>
                                </form>
                            </div>

                            <!--Botón Modificar-->
                            <div class="col-1 col-md-1">

                                <form action="ProductoSvl" method="get">

                                    <a href="javascript:;" class="btn btn-info" onclick="parentNode.submit();" name="btnModificar">
                                      <span class="glyphicon glyphicon-edit"></span>
                                    </a>
                                    
                                    <input type="hidden" name="btnModificar" value="modificar"/>
                                    <input type="hidden" name="codProd" value='<%= p.getIdProducto()  %>'/>
                                </form>

                            </div>

                            <!--Botón Eliminar-->
                            <div class="col-1 col-md-1">

                                <form action="ProductoSvl" method="get">

                                    <a href="javascript:;" class="btn btn-danger" onclick="return confirm('Desea Eliminar este registro?') && parentNode.submit();" name="btnEliminar">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </a>
                                    
                                    <input type="hidden" name="btnEliminar" value="eliminar"/>
                                    <input type="hidden" name="codProd" value='<%= p.getIdProducto() %>'/>
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
                        
                        
    <!--############## Modal Nuevo Producto #######################-->         
    <div class="modal fade" id="nuevoProducto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
              <h4 class="modal-title page_title theme_color" id="myModalLabel"><i class="fa fa-plus-square"></i> Nuevo Producto</h4>
            </div>
              
            <form action="ProductoSvl"  method="get" class="form-horizontal" >

            <div class="modal-body">
                
                <div class="nav-tabs-custom">                                
                <div class="tab-content">               
                
                <div class="tab-pane active">
                    
                    <div class="form-group ">

                        <label for="product_code" class="col-sm-2 control-label">Código</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control"  name="codigo" value="<%= codigo %>" readonly>                            
                        </div>
                        
                        <input type="hidden" name="uniDisponible" value="0" />

                        <label for="model" class="col-sm-2 control-label">Nombre <strong>*</strong></label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control"  name="nombre" autofocus required maxlength="30">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="stock" class="col-sm-2 control-label">Categoria <strong>*</strong></label>
                        <div class="col-sm-4">
                            <select class="form-control"  name="idCategoria" autocomplete required>
                                <option value selected>- Seleccione -</option>
                                <%                                       
                                    for(Categoria catg : listCategoria){
                                        if(catg.getEstado().equals("Activo")){

                                            %><option value="<%= catg.getIdCategoria() %>"><%= catg.getNombre() %> </option> <%                                                
                                        }
                                    }                              
                                %>
                            </select>
                        </div>

                        <div class="has-feedback">
                            <label for="presentation" class="col-sm-2 control-label">Und. por Caja <strong>*</strong></label>
                            <div class="col-sm-4">
                                <i class="fa fa-archive form-control-feedback"></i>
                                <input type="number" class="form-control"  name="unidadesCaja" required max="999">
                            </div> 
                        </div> 
                        
                    </div>

                    <div class="form-group">                        

                        <div class="has-feedback">
                            <label for="exampleInputAmount" class="col-sm-2 control-label">Precio de Venta <strong>*</strong></label>
                            <div class="col-sm-4">
                                <i class="glyphicon glyphicon-usd form-control-feedback"></i>
                                <input type="number" class="form-control"  name="precioVenta" placeholder="En Pesos" required max="9999999">
                            </div> 
                        </div>
                        
                        <div class="has-feedback">
                            <label for="stock" class="col-sm-2 control-label">Stock Min <strong>*</strong></label>
                            <div class="col-sm-4">                               
                                <i class="fa fa-th-large form-control-feedback"></i>
                                <input type="number" class="form-control"  name="stockMin" required max="99">  
                            </div>
                        </div>

                    </div>                    

                    <div class="form-group">
                        
                        <div class="has-feedback">
                            <label for="stock" class="col-sm-2 control-label">% de Ganancia <strong>*</strong></label>
                            <div class="col-sm-4">                               
                                <i class="form-control-feedback"><strong> % </strong></i>
                                <input type="number" class="form-control"  name="pGanancia" required max="999">  
                            </div>
                        </div>
                        
                        <label for="stock" class="col-sm-2 control-label">Proveedor <strong>*</strong></label>
                        <div class="col-sm-4">
                            <select class="form-control"  name="idProveedor" autocomplete required>
                                <option value selected>- Selecione -</option>
                                    <%                                       
                                        for(Proveedor prov : listProveedor){
                                            if(prov.getEstado().equals("Activo")){

                                                %><option value="<%= prov.getIdProveedor() %>"><%= prov.getRazonSocial() %> </option> <%                                                
                                            }
                                        }                              
                                    %>

                            </select>
                        </div>

                    </div>

                    <div class="form-group">
                        
                        <label for="descrp" class="col-sm-2 control-label">Descripción</label>
                        <div class="col-sm-4">
                           <textarea class="form-control" name="descripcion" maxlength="50"></textarea>
                        </div>
                        
                        <div class="has-feedback">
                            <label for="model" class="col-sm-2 control-label">Fecha Ingreso <strong>*</strong></label>
                            <div class="col-sm-4">                                     
                                <i class="fa fa-calendar form-control-feedback" ></i>                                      
                                <input type="date" class="form-control datepicker" name="fechaRegistro" required>                                               
                            </div>
                        </div>        

                    </div>
                    
                </div>
                </div>                            
                </div>
            </div>
                                      
                <!-- ## Botones Cancelar y Guardar ###-->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
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
    </div>
</div>

<script src="js/modalStatico.js" type="text/javascript"></script>
<script src="js/tiempoAlert.js" type="text/javascript"></script>

<script src="js/bootstrap.min.js"></script>
<script src="js/common-script.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="plugins/data-tables/jquery.dataTables.js"></script>
<script src="plugins/data-tables/DT_bootstrap.js"></script>
<script src="plugins/data-tables/dynamic_table_init.js"></script>

</body>
</html>
