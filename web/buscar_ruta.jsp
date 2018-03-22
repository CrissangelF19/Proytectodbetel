<%-- 
    Document   : rutas
    Created on : 6/09/2017, 04:29:41 PM
    Author     : Pedro Florez
--%>
<%@page import="com.betel.modelo.Ruta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.RutaDaoImpl"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Rutas</title>
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
          <h4><i class="fa fa-dashboard"></i> Rutas</h4>
        </div>       
    </div>
        
    <div class="container clear_both padding_fix">         
     
        <div class="row">
            
            <div class="col-md-12">

             <div class="panel panel-info">

                <div class="panel-body">

                <div class="porlets-content">
                       
                <!--Boton Nuevo-->             
                <div class="clearfix">
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#ruta" id="nBtn">
                          <span class="fa fa-plus-square"></span> Nuevo 
                        </button>
                    </div>                    
                </div> 
                
                <form class="form-inline" action="RutaSvl" method="get">                
                    <select class="form-control" id="inlineFormCustomSelect" name="idBusqueda" required>
                        <option value selected>- Seleccione -</option>                    
                        <option value="0">Nombre</option>                  
                        <option value="1">Estado</option>
                        <option value="2">Todos</option>
                    </select>                
                    <input type="text" class="form-control" id="inputBuscar" name="valor" placeholder="Busqueda Avanzada" required>
                    <button type="submit" class="btn btn-primary" name="btnBuscar" value="buscar"><i class="glyphicon glyphicon-search"></i> Buscar</button>
                </form> 
                
                <div class="margin-top-10"></div>
                    
                <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline" role="grid">                       
                        
                <!--###### Inicio de la Tabla Ruta ######-->
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
                            if(request.getAttribute("listRuta")!=null){
                            List<Ruta> listRuta = new ArrayList();  
                            listRuta = (List<Ruta>) request.getAttribute("listRuta");
                           
                            for (Ruta rut : listRuta) {
                        %>
                        <tr>
                            <td><%= rut.getIdRuta()%></td>
                            <td><%= rut.getNombre()%></td>
                            <td><%= rut.getEstado()%></td>
                            <td>                                    

                                <!--Botón Modificar-->
                                <div class="col-1 col-md-1">

                                    <form action="RutaSvl" method="get">
                                        <a href="javascript:;" onclick="parentNode.submit();" class="btn btn-info " name="btnModificar">
                                            <span class="glyphicon glyphicon-edit"></span>
                                        </a>

                                        <input type="hidden" name="btnModificar" value="modificar"/>
                                        <input type="hidden" name="idRuta" value='<%= rut.getIdRuta() %>'/>
                                    </form>
                                </div>

                                <!--Botón Eliminar-->
                                <div class="col-1 col-md-1">

                                    <form action="RutaSvl" method="get">
                                        <a href="javascript:;" onclick="return confirm('Desea Eliminar este reguistro?') && parentNode.submit();" class="btn btn-danger" name="btnEliminar">
                                            <span class="glyphicon glyphicon-trash"></span>
                                        </a>

                                        <input type="hidden" name="btnEliminar" value="eliminar"/>
                                        <input type="hidden" name="idRuta" value='<%= rut.getIdRuta() %>'/>
                                    </form>                                        
                                </div>

                            </td>
                        </tr>
                        <% } 
                        }else{
                         out.println("No se encontraron registros");
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
</div>             
                            
      <!---- Final de la Tabla Ruta ---->               
             
                   
                    
    <!--############## Modal Nuevo Ruta #######################-->  
    
    <div class="modal fade" id="ruta" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content modal-md">
                
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                  <h4 class="modal-title page_title theme_color" id="myModalLabel"><i class="fa fa-plus-square"></i> Nueva Ruta</h4>
                </div>
                
                <form  action="RutaSvl" method="post" class="form-horizontal">
                    
                    <div class="modal-body">
                    
                        <div class="form-group">
                            <label for="exampleInputEmail2" class="col-sm-3 control-label">Nombre</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="nombre"  maxlength="30" required>
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

