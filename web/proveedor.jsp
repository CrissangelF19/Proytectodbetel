<%--
    Document   : Cliente
    Created on : 25/08/2017, 01:09:19 PM
    Author     : Christian
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.betel.dao.ProveedorDaoImpl"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.modelo.Proveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Proveedor</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>

</head>

<%
    ProveedorDaoImpl dao = new ProveedorDaoImpl();
    List<Proveedor> listProveedor =  new ArrayList();
%>

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background: #f1f1f1">
                
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
            <h4><i class="glyphicon glyphicon-briefcase"></i> Proveedores </h4>
        </div>
    </div>
        
    <div class="container clear_both padding_fix">
            
        <div class="row">
        <div class="col-md-12">

        <div class="panel panel-info">                
        <div class="panel-body">        
        <div class="porlets-content">
                    
        <!--######### Boton Nuevo Proveedor########-->
        <div class="clearfix">
            <div class="btn-group pull-right">
                <button href="javascript:void(0);" class="btn btn-info" data-toggle="modal" data-target="#nuevoProv" id="nBtn"><i class="fa fa-plus-square"></i> Nuevo</button>
            </div>
        </div> 
                <!--+++++++++BOTON BUSCAR AVANZADO++++++-->
        <form class="form-inline" action="proveedorsvl" method="get">                
            <select class="form-control" id="inlineFormCustomSelect" name="idBusqueda" required>
                <option value selected>- Seleccione -</option>                    
                <option value="0">Nit</option>                  
                <option value="1">RazonSocial</option>
                <option value="2">Estado</option>
                <option value="3" hidden=""></option>
            </select>                
            <input type="text" class="form-control" id="inputBuscar" name="valor" placeholder="Busqueda Avanzada" required>
            <button type="submit" class="btn btn-primary" name="btnBuscar" value="buscar"><i class="glyphicon glyphicon-search"></i> Buscar</button>
        </form>   

        <div class="margin-top-10"></div>
        
        <div class="table-responsive">
                
        <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline" role="grid">                       
                        
        <!--###### Inicio de la Tabla Proveedor ######-->
        <table class="display table table-bordered table-striped dataTable" id="dynamic-table" aria-describedby="dynamic-table_info">
            <thead>
                <tr>
                    <th>Nit</th>
                    <th>Razón social</th>
                    <th>Correo Empresarial</th> 
                    <th>Teléfono</th>
                    <th>Fecha</th>
                    <th>Estado</th>
                    <th> </th>
                </tr>
            </thead>

            <tbody>
                      <!--+++++++++++Alertas+++++++++++-->
                      
                  <%         
                        if(request.getAttribute("respuestaR")!=null){
                    %>
                        <div class="alert alert-success content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Bien hecho! El Proveedor ha sido creado con éxito."); %> 
                        </div> 
                    <%
                        }else if(request.getAttribute("respuestaE")!=null){                        
                    %>
                        <div class="alert alert-success content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Bien hecho! El Proveedor se elimino satisfactoriamente."); %>                            
                        </div>                        
                    <%
                        }else if(request.getAttribute("respuestaM")!=null){                        
                    %>
                        <div class="alert alert-success content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Bien hecho! El Proveedor ha sido actualizado con éxito."); %>  
                        </div>                
                    <%
                        }else if(request.getAttribute("nit")!=null){                        
                    %>
                        <div class="alert alert-danger content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Error! " + request.getAttribute("nit")); %>  
                        </div>                
                    <%
                        }else if(request.getAttribute("razonSocial")!=null){                        
                    %>
                        <div class="alert alert-danger content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Error! " + request.getAttribute("razonSocial")); %>  
                        </div>                
                    <%
                        }else if(request.getAttribute("nIdentidad")!=null){                        
                    %>
                        <div class="alert alert-danger content2" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%out.println("¡Error! " + request.getAttribute("nIdentidad")); %>  
                        </div>                
                    <%
                        }
                    %>                    
                
                <!--##### Fila inicial ####-->
                <%
                    listProveedor = dao.listar();
                    for(Proveedor pr : listProveedor){
                %>
                <tr>
                    <td><%= pr.getNit() %></td>
                    <td><%= pr.getRazonSocial()%></td>
                    <td><%= pr.getEmail()%></td>
                    <td><%= pr.getTelefono()%></td>
                    <td><%= pr.getFechaIngreso()%></td>
                    <td><%= pr.getEstadop()%></td>

                    <td>
                        
                        <!--Boton ver -->
                        <div class="col-1 col-md-1">

                            <form action="proveedorsvl" method="get">

                                <a href="javascript:;" class="btn btn-success" onclick="parentNode.submit();" name="Ver" >
                                    <span class="glyphicon glyphicon-eye-open" ></span>
                                </a>

                                <input type="hidden" name="btnVer" value="ver"/>
                                <input type="hidden" name="id" value='<%= pr.getIdProveedor() %>'/>
                            </form>
                        </div>

                        <!--Botón Modificar-->
                        <div class="col-1 col-md-1">

                            <form action="proveedorsvl" method="get">
                                <a href="javascript:;" onclick="parentNode.submit();" class="btn btn-info " name="btnModificar">
                                    <span class="glyphicon glyphicon-edit"></span>
                                </a>

                                <input type="hidden" name="btnModificar" value="modificar"/>
                                <input type="hidden" name="id" value='<%= pr.getIdProveedor() %>'/>
                            </form>

                        </div>

                        <!--Botón Eliminar-->
                        <div class="col-1 col-md-1">

                            <form action="proveedorsvl" method="get">

                                <a href="javascript:;" class="btn btn-danger" onclick="return confirm('Desea Eliminar este reguistro?') && parentNode.submit();" name="btnEliminar">
                                    <span class="glyphicon glyphicon-trash" ></span>
                                </a>

                                <input type="hidden" name="btnEliminar" value="eliminar"/>
                                <input type="hidden" name="id" value='<%= pr.getIdProveedor() %>'/>
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
</div>
    
    
<!-- ######### Modal Nuevo ########333-->
<div class="panel-body">
    <div class="modal fade in" id="nuevoProv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title page_title theme_color" id="myModalLabel"><i class="fa fa-plus-square"></i> Nuevo Proveedor</h4>
                </div>
                
                <form class="form-horizontal" method="post" action="proveedorsvl">
                
                    <div class="modal-body"> 
                        
                        <div class="basic-wizard" id="progressWizard">
                            
                            <ul class="nav nav-pills nav-justified">
                                <li class="active"><a data-toggle="tab" href="#Empresa">Empresa</a></li>
                                <li class=""><a data-toggle="tab" href="#Datos">Datos Personales</a></li>
                                <li class=""><a data-toggle="tab" href="#Direc">Dirección</a></li>
                            </ul>                            
                            
                        <div class="tab-content">
                            
                            <!--############### Empresa ############-->
                            <div id="Empresa" class="tab-pane fade active in">
                          
                                <div class="form-group">                                        
                                    <label for="exampleInputName2" class="col-sm-3 control-label">NIT</label>
                                     <div class="col-sm-8">
                                         <input type="text" class="form-control" id="Nit" value="" name="nit" maxlength="20" required autofocus>
                                    </div>    
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Razon Social</label>
                                    <div class="col-sm-8">
                                      <input type="text" class="form-control" id="RazonSocial"  name="razonsocial"  maxlength="30" required />
                                    </div>
                                </div>                                                                           


                                <div class="form-group has-feedback">
                                    <label for="exampleInputAmount" class="col-sm-3 control-label">Correo Empresarial</label>
                                    <div class="col-sm-8">
                                      <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
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
                                
                                <div class="form-group has-feedback">
                                    <label for="exampleInputAmount" class="col-sm-3 control-label">Fecha </label>
                                    <div class="col-sm-8">
                                        <span class="fa fa-calendar form-control-feedback" ></span>
                                        <input type="date" name="fechaIngreso" id="fecha" class="form-control datepicker" required>
                                    </div>
                                </div>
                                    
                            </div>
                                                     
                            <!--############### Datos Personales ############-->                            
                            <div id="Datos" class="tab-pane">
                            
                                <div class="form-group">                                        
                                    <label for="exampleInputName2" class="col-sm-3 control-label">Nombre Completo</label>
                                    <div class="col-sm-8">                                                    
                                        <input type="text" class="form-control" name="nombres" maxlength="45">                                      
                                    </div>    
                                </div> 
                                               
                                <div class="form-group">                                        
                                    <label for="exampleInputName2" class="col-sm-3 control-label">Tipo Documento</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" name="tipodocidentidad" autocomplete required >
                                        <option value="">- Seleccione -</option>
                                        <option value="Cedula de Ciudadania">Cedula de Ciudadania</option>
                                        <option value="Cedula de Extranjeria">Cedula de Extranjeria</option>
                                     </select>
                                    </div>    
                                </div>  
                              
                                <div class="form-group">
                                    <label for="exampleInputEmail2" class="col-sm-3 control-label">N° Documento</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="Nurdocumento" name="numerodocumento" maxlength="15" required>
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
                            
                        
                                <div class="form-group has-feedback">
                                    <label for="exampleInputAmount" class="col-sm-3 control-label">Celular</label>
                                    <div class="col-sm-8">
                                        <span class="glyphicon glyphicon-phone form-control-feedback" ></span>
                                        <input type="text" class="form-control" id="celular" name="celular" maxlength="20">                                        
                                    </div>
                                </div>
                                
                            </div>
                  
                            <!--############## Direcccion ####################--> 
                            <div id="Direc" class="tab-pane" >
                          
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
                                        <input type="text" class="form-control" id="Direccion" name="direccion" maxlength="45">
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
</div>
<!-- Final del Modal Nuevo -->
  
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
