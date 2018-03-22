<%-- 
    Document   : Usuario
    Created on : 6/09/2017, 04:29:41 PM
    Author     : Pedro Florez
--%>

<%@page import="com.betel.modelo.Rol"%>
<%@page import="com.betel.dao.RolDaoImpl"%>
<%@page import="java.sql.Array"%>
<%@page import="com.betel.modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.UsuarioDaoImpl"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Usuarios</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />

<script src="js/jquery-3.2.1.js" type="text/javascript"></script>

</head>

<%
    RolDaoImpl daor = new RolDaoImpl();
    List<Rol> listRol = new ArrayList();
    listRol = daor.listar();
%> 

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
                
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
          <h4><i class="fa fa-group"></i> Usuarios</h4>
        </div>       
    </div>
        
    <div class="container clear_both padding_fix">         
     
        <div class="row">
            
            <div class="col-md-12">

             <div class="panel panel-info">

                <div class="panel-body">

                <div class="porlets-content">
                <div class="adv-table editable-table ">
                       
                       
                <!--Boton Nuevo-->             
                <div class="clearfix">
                    <div class="btn-group pull-right">
                    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#usuario" id="nBtn">
                      <span class="fa fa-plus-square"></span> Nuevo 
                    </button>
                    </div>
                </div>
                
                <form class="form-inline" action="usuarioSvl" method="get">                
                    <select class="form-control" id="inlineFormCustomSelect" name="idBusqueda" required>
                        <option value selected>- Seleccione -</option>                    
                        <option value="0">Nombre Completo</option>
                        <option value="1">Usuario</option>
                        <option value="2">Rol</option>
                        <option value="3">Estado</option>
                        <option value="4" hidden=""></option>
                    </select>                
                    <input type="text" class="form-control" id="inputBuscar" name="valor" placeholder="Busqueda Avanzada" autocomplete required>
                    <button type="submit" class="btn btn-primary" name="btnBuscar" value="buscar"><i class="glyphicon glyphicon-search"></i> Buscar</button>
                </form>
                    
                <div class="margin-top-10"></div>

                <div class="table-responsive">                    
                <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline" role="grid">                      
                        
                <!--###### Inicio de la Tabla Usuarios ######-->                   
                <table class="display table table-bordered table-striped dataTable" id="dynamic-table" aria-describedby="dynamic-table_info">

                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre Completo</th>
                            <th>Usuario</th>
                            <th>Rol</th>
                            <th>fecha</th>
                            <th>Estado</th>
                            <th> </th>
                        </tr>
                     </thead>

                    <tbody> 
                        
                        <!--##### Fila Inicial ####-->
                        <%
                            if(request.getAttribute("listUsuario")!=null){
                                List<Usuario> listUsuario = new ArrayList();
                                listUsuario = (List<Usuario>) request.getAttribute("listUsuario");
                            
                            for (Usuario u : listUsuario) {
                        %>

                        <tr>
                            <td><%= u.getIdUsuario() %></td>
                            <td><%= u.getNombreCompleto()%></td>                            
                            <td><%= u.getUsuario() %></td>
                            <td><%= u.getIdRol() %></td>
                            <td><%= u.getFechaIngreso() %></td>
                            <td><%= u.getEstado() %></td>
                            <td>

                                <!--Botón Modificar-->
                                <div class="col-1 col-md-1">

                                    <form action="usuarioSvl" method="get">

                                        <a href="javascript:;" class="btn btn-info" title="Modificar" onclick="parentNode.submit();" name="btnModificar">
                                          <span class="glyphicon glyphicon-edit"></span>
                                        </a>
                                        <input type="hidden" name="btnModificar" value="modificar"/>
                                        <input type="hidden" name="idUsuario" value='<%= u.getIdUsuario() %>'/>
                                    </form>

                                </div>

                                <!--Botón Eliminar-->
                                <div class="col-1 col-md-1">

                                    <form action="usuarioSvl" method="get">

                                        <a href="javascript:;" class="btn btn-danger" title="Eliminar" onclick="return confirm('Desea Eliminar este reguistro?') && parentNode.submit();" name="btnEliminar">
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </a>
                                        <input type="hidden" name="btnEliminar" value="eliminar"/>
                                        <input type="hidden" name="idUsuario" value='<%= u.getIdUsuario() %>'/>
                                    </form>
                                </div>

                            </td>
                        </tr>
                        <%      } //Cerramos el for
                            }else{
                                out.println("No se encontraron registros");
                            }
                        %>
                        <!--##### Termina la Fila Inicial #####-->

                    </tbody>                        
                </table>

            </div>
        </div>                
        <!---- Final de la Tabla Roles ---->               
             
                   
                    
    <!--############## Modal Nuevo Usuario #######################-->  
    
    <div class="modal fade" id="usuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content modal-md">
                
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                  <h4 class="modal-title page_title theme_color" id="myModalLabel"><i class="fa fa-plus-square"></i> Nuevo Usuario</h4>
                </div>
                
                <form  action="usuarioSvl" method="get" class="form-horizontal">
                    
                    <div class="modal-body">
                        
                        <div class="form-group">
                            <label for="exampleInputEmail2" class="col-sm-3 control-label">Nombre Completo</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control"  name="nombre"  autofocus required maxlength="45">
                            </div>
                        </div>                        
                    
                        <div class="form-group">
                            <label for="exampleInputEmail2" class="col-sm-3 control-label">Usuario</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control"  name="usuario" placeholder="Alias" required maxlength="20">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="fullname" class="col-sm-3 control-label">Contraseña</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" name="clave" placeholder="*******"  required maxlength="20">
                            </div>
                        </div>
                                                
                        <div class="form-group">
                            <label for="modal" class="col-sm-3 control-label">Rol</label>
                            <div class="col-sm-8">
                                <select class="form-control"  name="idRol" autocomplete required>
                                    <option value selected>- Seleccione -</option>
                                        <%                                       
                                            for(Rol rol : listRol){
                                                if(rol.getEstado().equals("Activo")){

                                                    %><option value="<%= rol.getIdRol() %>"><%= rol.getNombre() %> </option> <%                                                
                                                }
                                            }                              
                                        %>   

                               </select>
                            </div>
                        </div>
                                                                                                
                        <div class="form-group">
                            
                            <div class="has-feedback">
                                <label for="model" class="col-sm-3 control-label">Fecha</label>
                                <div class="col-sm-8">                                     
                                    <i class="fa fa-calendar form-control-feedback"></i>                                      
                                    <input type="date" class="form-control datepicker" name="fecha" required>                                               
                                </div>
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

