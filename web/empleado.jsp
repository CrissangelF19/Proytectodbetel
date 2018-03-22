
<%-- 
    Document   : Cliente
    Created on : 25/08/2017, 01:09:19 PM
    Author     : Pedro Florez
--%>

<%@page import="com.betel.dao.RolDaoImpl"%>
<%@page import="com.betel.modelo.Rol"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.EmpleadoDaoImpl"%>
<%@page import="com.betel.modelo.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Empleados</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="js/tooltip.js" type="text/javascript"></script>

</head>

<%
    EmpleadoDaoImpl dao = new EmpleadoDaoImpl();
    List<Empleado> listEmpleado =  new ArrayList();
%>

 <%       
    RolDaoImpl daor = new RolDaoImpl();
    List<Rol> listRol=  new ArrayList();
    listRol= daor.listar();  
 %>
 
<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background: #f1f1f1">
   
             
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
            <h4><i  class="fa fa-thumb-tack"></i> Empleados </h4>
        </div>
    </div>
        
    <div class="container clear_both padding_fix">
            
        <div class="row">
            <div class="col-md-12">
                
            <div class="panel panel-info">                
            <div class="panel-body">            
            <div class="porlets-content">
                
                                    
            <!--######### Boton Nuevo Empleado ########-->
            <div class="clearfix">
                <div class="btn-group pull-right">
                    <button href="javascript:void(0);" class="btn btn-info" data-toggle="modal" data-target="#nuevoEmpleado" id="nBtn"><i class="fa fa-plus-square"></i>  Nuevo</button>
                </div>
            </div> 
               <!--########BOTON BUSCAR AVANZADO############-->
            <form class="form-inline" action="EmpleadoSvl" method="get">                
                <select class="form-control" id="inlineFormCustomSelect" name="idBusqueda" required>
                    <option value selected>- Seleccione -</option>                    
                    <option value="0">Nombre Completo</option>
                    <option value="1">Rol</option>
                    <option value="2">Estado</option>
                    <option value="3" hidden=""></option>
                </select>                
                <input type="text" class="form-control" id="inputBuscar" name="valor" placeholder="Busqueda Avanzada" autocomplete required>
                <button type="submit" class="btn btn-primary" name="btnBuscar" value="buscar"><i class="glyphicon glyphicon-search"></i> Buscar</button>
            </form>
            
            <div class="margin-top-10"></div>
                
            <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline" role="grid">                

            <!--###### Inicio de la Tabla Empleado ######-->
            <table class="display table table-bordered table-striped dataTable" id="dynamic-table" aria-describedby="dynamic-table_info">
                <thead>
                    <tr>
                        <th>Nombre Completo</th>                        
                        <th>Rol</th>
                        <th>N°Documento</th>
                        <th>Celular</th>
                        <th>Fecha</th>
                        <th>Estado</th>
                        <th> </th>
                     </tr>
                </thead>
                    
                <tbody>
                    
                    <!--#############ALERTAS##############-->
                    <%         
                        if(request.getAttribute("respuestaR")!=null){
                    %>
                        <div class="alert alert-success content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Bien hecho! El Empleado ha sido creado con éxito."); %> 
                        </div> 
                    <%
                        }else if(request.getAttribute("respuestaE")!=null){                        
                    %>
                        <div class="alert alert-success content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Bien hecho! El Empleado se elimino satisfactoriamente."); %>                            
                        </div>                        
                    <%
                        }else if(request.getAttribute("respuestaM")!=null){                        
                    %>
                        <div class="alert alert-success content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Bien hecho! El Empleado ha sido actualizado con éxito."); %>  
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
                        listEmpleado = dao.listar();
                        for(Empleado e : listEmpleado){
                    %>
                    
                    <tr>
                        <td><%= e.getNombreCompleto()%></td>                        
                        <td><%= e.getIdRol()%></td>
                        <td>
                            <a href="#" class="btn btn-secondary" data-toggle="tooltip" data-placement="top" title="<%= e.getTipoDocIdentidad()%>" style="padding-top: 1px;padding-bottom: 0px;padding-right: 0px;padding-left: 0px;">
                                <%= e.getNroIdentidad() %>
                            </a>                            
                        </td>
                        <td><%= e.getCelular()%></td>
                        <td><%= e.getFechaIngreso()%></td>
                        <td><%= e.getEstadoz()%></td>
                        <td>                           

                            <!--Botón Ver-->
                            <div class="col-1 col-md-1">

                                <form action="EmpleadoSvl" method="get">

                                    <a href="javascript:;" class="btn btn-success" onclick="parentNode.submit();" name="btnVer">
                                      <span class="glyphicon glyphicon-eye-open"></span>
                                    </a>
                                    
                                    <input type="hidden" name="btnVer" value="ver"/>
                                    <input type="hidden" name="id" value='<%= e.getIdEmpleado()%>'/>
                                </form>

                            </div>

                            <!--Botón Modificar-->
                            <div class="col-1 col-md-1">

                                <form action="EmpleadoSvl" method="get">

                                    <a href="javascript:;" class="btn btn-info" onclick="parentNode.submit();" name="btnModificar">
                                      <span class="glyphicon glyphicon-edit"></span>
                                    </a>
                                    
                                    <input type="hidden" name="btnModificar" value="modificar"/>
                                    <input type="hidden" name="id" value='<%= e.getIdEmpleado()%>'/>
                                </form>

                            </div>

                            <!--Botón Eliminar-->
                            <div class="col-1 col-md-1">

                                <form action="EmpleadoSvl" method="get">

                                    <a href="javascript:;" class="btn btn-danger" onclick="return confirm('Desea Eliminar este reguistro?') && parentNode.submit();" name="btnEliminar">
                                        <span class="glyphicon glyphicon-trash" ></span>
                                    </a>
                                    
                                    <input type="hidden" name="btnEliminar" value="eliminar"/>
                                    <input type="hidden" name="id" value='<%= e.getIdEmpleado()%>'/>
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
                
    <!---------- Modal Nuevo Empleado --------->            
    <div class="modal fade in" id="nuevoEmpleado" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">                
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title page_title theme_color" id="myModalLabel"> <i class="fa fa-plus-square"></i> Nuevo Empleado</h4>
                </div>
                
                <form action="EmpleadoSvl" class="form-horizontal" method="post">
                
                    <div class="modal-body"> 
                        
                        <div class="basic-wizard">
                            
                            <ul class="nav nav-pills nav-justified">                                
                                <li class="active"><a data-toggle="tab" href="#Datos">Datos</a></li>
                                <li class=""><a data-toggle="tab" href="#Mas">Mas...</a></li>
                            </ul>
                            
                        <div class="tab-content">                                               
                             
                        <!--###############Datos Personales############-->                            
                        <div id="Datos" class="tab-pane fade active in">
                            
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label">Nombre Completo *</label>
                                <div class="col-sm-8">                                                    
                                    <input type="text" class="form-control" id="Nombres" name="nombres" maxlength="45" required autofocus>                                      
                                </div>    
                            </div>
                            
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label">Rol *</label>
                                <div class="col-sm-8" >
                                    <select class="form-control" name="idRol" autocomplete required>
                                        <option value selected>- Selecione -</option>
                                        <%                                       
                                            for(Rol rol : listRol){
                                                if(rol.getEstado().equals("Activo")){

                                                    %>
                                                    <option value="<%= rol.getIdRol()%>">
                                                        <%= rol.getNombre() %>
                                                    </option> <%                                                
                                                }
                                            }                              
                                        %>   
                                    </select>
                                </div>    
                            </div>
                            
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label">Tipo Documento *</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="tipodocidentidad" autocomplete required>
                                        <option value="">- Seleccione -</option>
                                        <option value="Cedula de Ciudadania">Cedula de Ciudadania</option>
                                        <option value="Cedula de Extranjeria">Cedula de Extranjeria</option>
                                    </select>
                                </div>    
                            </div>  

                            <div class="form-group">
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">N° Documento *</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Nurdocumento" name="numerodocumento"  maxlength="15" required>
                                </div>
                            </div>
                            
                            <div class="form-group has-feedback">
                                <label for="exampleInputAmount" class="col-sm-3 control-label">Celular</label>
                                <div class="col-sm-8">
                                    <span class="glyphicon glyphicon-phone form-control-feedback" ></span>
                                    <input type="text" class="form-control" id="celular" name="celular" maxlength="20">                                        
                                </div>
                            </div>
                                                        
                            <div class="form-group has-feedback">
                                <label for="exampleInputAmount" class="col-sm-3 control-label">Fecha *</label>
                                <div class="col-sm-8">
                                    <span class="fa fa-calendar form-control-feedback" ></span>
                                    <input type="date" name="fechaingreso" id="fecha"  class="form-control datepicker" required>
                                </div>
                            </div> 

                        </div>
                        
                  
                        <!--##############Direcccion####################-->            
                             
                        <div id="Mas" class="tab-pane" >
                            
                            <div class="form-group has-feedback">
                                <label for="exampleInputAmount" class="col-sm-3 control-label">Correo Electronico</label>
                                <div class="col-sm-8">
                                    <span class="glyphicon glyphicon-envelope form-control-feedback" ></span>
                                    <input type="email" class="form-control" id="exampleInputAmount" name="email" maxlength="45">
                                </div>
                            </div>

                            <div class="form-group has-feedback">
                                <label for="exampleInputAmount" class="col-sm-3 control-label">Telefono</label>
                                <div class="col-sm-8">                                            
                                    <span class="glyphicon glyphicon-earphone form-control-feedback" ></span>
                                    <input type="text" class="form-control" id="Telefono" name="telefono" maxlength="20">                                            
                                </div>
                            </div>
                            
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label" >Genero</label>
                                <div class="col-sm-8" >
                                    <select class="form-control" name="genero">
                                        <option value="">- Seleccione -</option>
                                         <option value="Femenino">Femenino </option>
                                         <option value="Masculino">Masculino</option>
                                    </select>
                                </div>    
                            </div>

                            <div class="form-group">
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">Barrio</label>
                                <div class="col-sm-8">
                                  <input type="text" class="form-control" id="exampleInputEmail2" name="barrio" maxlength="30">
                                </div>
                            </div>      

                            <div class="form-group">
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">Ciudad</label>
                                <div class="col-sm-8">
                                   <input type="text" class="form-control" id="exampleInputEmail2" name="ciudad" maxlength="30">
                                </div>
                            </div> 

                            <div class="form-group">
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">Direccion</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Direccion" name="direccion"  maxlength="45" >
                                </div>
                            </div>

                        </div>
                    </div>
                 </div>
             </div>
                    
                                    
                <!--####################### Botones Guadar y Cancelar #################-->
                <div class="modal-footer">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
                  <button type="submit" class="btn btn-info" onclick="parentNode.submit();"  name="btnRegistrar" value="Registrar"><i class="glyphicon glyphicon-ok"></i> Guardar</button>
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
