<%-- 
    Document   : Categoria
    Created on : 25/08/2017, 12:45:54 PM
    Author     : Katia Ortiz
--%>
<%@page import="java.sql.Array"%>
<%@page import="com.betel.modelo.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.CategoriaDaoImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Categorias</title>
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
          <h4><i class="glyphicon glyphicon-tags"></i> Categorias </h4>
        </div>
    </div>
        
    <div class="container clear_both padding_fix">         
        <div class="row">
            <div class="col-md-12">
                    
            <div class="panel panel-info">
		
            <div class="panel-body">
                        
            <div class="porlets-content">
                    
            <!--######### Boton Nueva Categoria ########-->
            <div class="clearfix">
                <div class="btn-group pull-right">
                    <button href="javascript:void(0);" class="btn btn-info" data-toggle="modal" data-target="#nuevaCategoria" id="nBtn"><i class="fa fa-plus-square"></i> Nuevo</button>
                </div>
            </div>
            
            <form class="form-inline" action="CategoriaSvl" method="get">                
                <select class="form-control" id="inlineFormCustomSelect" name="idBusqueda">
                    <option value selected>- Seleccione -</option>                    
                    <option value="0">Nombre</option>                  
                    <option value="1">Estado</option>
                    <option value="2">Todos</option>

                </select>                
                <input type="text" class="form-control" id="inputBuscar" name="valor" placeholder="Busqueda Avanzada" required>
                <button type="submit" class="btn btn-primary" name="btnBuscar" value="buscar"><i class="glyphicon glyphicon-search"></i> Buscar</button>
            </form>

            <div class="margin-top-10"></div>
            
            <div class="table-responsive">
                
            <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline" role="grid">                                               

            <!--###### Inicio de la Tabla Categoria ######-->
            <table class="display table table-bordered table-striped dataTable" id="dynamic-table" aria-describedby="dynamic-table_info">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Estado</th>
                        <th> </th>
                    </tr>
                </thead>

                <tbody>               
                
                    <!--##### Fila inicial ####-->
                    <%
                        if(request.getAttribute("listCategoria")!=null){
                            List<Categoria> listCategoria = new ArrayList();
                            listCategoria = (List<Categoria>) request.getAttribute("listCategoria");                          
                                               
                        for(Categoria c : listCategoria){  
                    %>

                    <tr>
                        <td><%= c.getIdCategoria() %></td>
                        <td><%= c.getNombre() %></td>
                        <td><%= c.getEstado() %></td>
                        <td>                            

                            <!--Botón Modificar-->
                            <div class="col-1 col-md-1">

                                <form action="CategoriaSvl" method="get">

                                    <a href="javascript:;" class="btn btn-info" onclick="parentNode.submit();" name="btnModificar">
                                      <span class="glyphicon glyphicon-edit"></span>
                                    </a>
                                    
                                    <input type="hidden" name="btnModificar" value="modificar"/>
                                    <input type="hidden" name="idCat" value='<%= c.getIdCategoria() %>'/>
                                </form>

                            </div>

                            <!--Botón Eliminar-->
                            <div class="col-1 col-md-1">

                                <form action="CategoriaSvl" method="get">

                                    <a href="javascript:;" class="btn btn-danger" onclick="return confirm('Desea Eliminar este reguistro?') && parentNode.submit();" name="btnEliminar">
                                       <span class="glyphicon glyphicon-trash"></span>
                                    </a>
                                    
                                    <input type="hidden" name="btnEliminar" value="eliminar"/>
                                    <input type="hidden" name="idCat" value='<%= c.getIdCategoria() %>'/>
                                </form>
                            </div>
                                
                        </td>
                    </tr>
                    <%  } //Cerramos el for
                        }
                    %>
                    <!--##### Termina la Fila Inicial #####-->

                </tbody> 
                </table>                                     
                </div>
              </div>
            </div>
        </div>
    </div>
</div>
                   
                        
                    
    <!--############## Modal Nueva Categoria #######################-->
    <div class="modal fade" id="nuevaCategoria" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content modal-md">
                
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                  <h4 class="modal-title page_title theme_color" id="myModalLabel"><i class="fa fa-plus-square"></i> Nueva Categoría</h4>
                </div>
                
                <form  action="CategoriaSvl" method="get" class="form-horizontal">
                    <div class="modal-body"> 

                        <div class="form-group">
                            <label for="exampleInputEmail2" class="col-sm-3 control-label">Nombre</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="nombreCategoria" name="nombre" required autofocus maxlength="30">
                            </div>
                        </div>
                                      
                    </div>
                    
                    <!-- ## Botones Cancelar y Guardar ###-->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-info" name="btnRegistrar" value="Registrar"><i class="glyphicon glyphicon-ok"></i> Guardar</button>
                    </div>
                </form>
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
