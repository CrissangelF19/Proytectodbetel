<%-- 
    Document   : modiCategoria
    Created on : 17/10/2017, 01:10:45 PM
    Author     : Estudiante
--%>

<%@page import="com.betel.modelo.Empleado"%>
<%@page import="com.betel.modelo.Rol"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.RolDaoImpl"%>
<%@page import="com.betel.modelo.Ruta"%>
<%@page import="com.betel.modelo.Proveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  lang="es">
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Empleados</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />

</head>

<%       
    RolDaoImpl daor = new RolDaoImpl();
    List<Rol> listRol=  new ArrayList();
    listRol= daor.listar();   
%>

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
                   
    <div class="pull-left breadcrumb_admin clear_both">
       <div class="pull-left page_title theme_color">
         <h4><i class="fa fa-thumb-tack"></i> Empleados </h4>
       </div>
    </div>
            
    <div class="container clear_both padding_fix">
        <div class="row">
                    
        <div class="col-lg-3"></div>
                    
            <div class="col-lg-6">
                
            <div class="panel panel-info">
            <div class="panel-body">
            <div class="porlets-content">
                                     
                                        
            <div class="modal-header">
                <h4 class="modal-title page_title theme_color" id="myModalLabel"> <i class="glyphicon glyphicon-edit"></i> Modificar Empleado</h4>
            </div> 
                
            <%
                Empleado empl = (Empleado) request.getAttribute("Empl");
            %>                        
                                    
            
            <form action="EmpleadoSvl" class="form-horizontal" method="get">
                
                    <div class="modal-body"> 
                        
                        <div class="basic-wizard">
                            
                            <ul class="nav nav-pills nav-justified">                                
                                <li class="active">
                                    <a data-toggle="tab" href="#Datos">Datos</a>
                                </li>
                                <li class="">
                                    <a data-toggle="tab" href="#Mas">Mas...</a>
                                </li>
                            </ul>
                            
                            
                        <div class="tab-content">                                               
                             
                        <!--###############Datos Personales############-->                            
                        <div id="Datos" class="tab-pane fade active in">
                            <input type="hidden" name="id" value="<%= empl.getIdEmpleado()%>" /> 
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label">Nombre Completo *</label>
                                <div class="col-sm-8">                                                    
                                    <input type="text" class="form-control" id="Nombres" name="nombres" value="<%= empl.getNombreCompleto()%>"  maxlength="45" required autofocus>                                      
                                </div>    
                            </div>
                            
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label">Rol *</label>
                                <div class="col-sm-8" >
                                    <select class="form-control" name="idRol" autocomplete required>
                                             <%
                                  String valor = String.valueOf(empl.getIdRol());
                                 for(Rol rol : listRol){
                                 
                                  %>
                                  <option value="<%= rol.getIdRol()%>"
                                          <%
                      
                                if(rol.getIdRol().equals(valor)){
                                     %>
                                    selected
                                 <%
                                     
                                     }
                                     %>>
                                      <%= rol.getNombre()%> </option>
                                  <%
                                     
                                }
                              
                                %>
                                    </select>
                                </div>    
                            </div>
                            
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label">Tipo Documento *</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="tipodocidentidad" autocomplete required>
                                       <%
                                        if(empl.getTipoDocIdentidad().equals("Cedula de Ciudadania")){
                                            %> 
                                            <option selected><%= empl.getTipoDocIdentidad()%></option>
                                               <option>Cedula de Extranjeria</option>
                                               <%
                                        }else if(empl.getTipoDocIdentidad().equals("Cedula de Extranjeria")){
                                            %> 
                                            <option selected><%= empl.getTipoDocIdentidad()%></option>
                                               <option>Cedula de Ciudadania</option><%
                                        }else{
                                           %> <option value="">- Seleccione -</option>
                                            <option value="Cedula de Ciudadania">Cedula de Ciudadania</option>
                                            <option value="Cedula de Extranjeria">Cedula de Extranjeria</option>
                                           <% 
                                        }
                                    %>
                                    </select>
                                </div>    
                            </div>  

                            <div class="form-group">
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">N° Documento *</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Nurdocumento" name="numerodocumento" value="<%= empl.getNroIdentidad()%>" maxlength="15" required>
                                </div>
                            </div>
                                
                            <div class="form-group has-feedback">
                                <label for="exampleInputAmount" class="col-sm-3 control-label">Celular</label>
                                <div class="col-sm-8">
                                    <span class="glyphicon glyphicon-phone form-control-feedback" ></span>
                                    <input type="text" class="form-control" id="celular" name="celular" value="<%= empl.getCelular()%>" maxlength="20">                                        
                                </div>
                            </div>
                                
                            <input type="hidden" name="fechaingreso" id="fecha"  class="form-control datepicker"  value="<%= empl.getFechaIngreso()%>">
                            
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label">Estado</label>
                                <div class="col-sm-8">
                                <select class="form-control" name="estadoz">
                                    <option selected><%= empl.getEstadoz() %></option>
                                    <%
                                        if(empl.getEstadoz().equals("Activo")){
                                            %><option >Inactivo</option> 
                                      <%
                                        }else{
                                            %><
                                           <option >Activo</option> <%
                                        }
                                    %>

                                </select>
                                </div>    
                            </div> 
                                    
                        </div>
                        
                  
                        <!--##############Direcccion####################-->            
                             
                        <div id="Mas" class="tab-pane" >                               

                            <div class="form-group has-feedback">
                                <label for="exampleInputAmount" class="col-sm-3 control-label">Correo Electronico</label>
                                <div class="col-sm-8">
                                    <span class="glyphicon glyphicon-envelope form-control-feedback" ></span>
                                    <input type="email" class="form-control" id="exampleInputAmount" name="email" value="<%= empl.getEmail()%>" maxlength="45">
                                </div>
                            </div>

                            <div class="form-group has-feedback">
                                <label for="exampleInputAmount" class="col-sm-3 control-label">Telefono</label>
                                <div class="col-sm-8">                                            
                                    <span class="glyphicon glyphicon-earphone form-control-feedback" ></span>
                                    <input type="text" class="form-control" id="Telefono" name="telefono" value="<%= empl.getTelefono()%>" maxlength="20">                                            
                                </div>
                            </div>    
                            
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label" >Genero</label>
                                <div class="col-sm-8" >
                                    <select class="form-control" name="genero">
                                <%
                                    if(empl.getGenero().equals("Masculino")){
                                    %> 
                                      <option selected><%= empl.getGenero()%></option>
                                    <option>Femenino</option>
                                    <%
                                    }else if (empl.getGenero().equals("Femenino")){
                                    %> 
                                      <option selected><%= empl.getGenero()%></option>
                                    <option>Masculino</option><%
                                    }else{
                                   %> <option value="">- Seleccione -</option>
                                      <option value="Femenino">Femenino </option>
                                      <option value="Masculino">Masculino</option>
                                    <%
                                 }
                                %>
                                    </select>
                                </div>    
                            </div>

                            <div class="form-group">
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">Barrio</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="exampleInputEmail2" name="barrio" value="<%= empl.getBarrio()%>"  maxlength="30">
                                </div>
                            </div>      

                            <div class="form-group">
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">Ciudad</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="exampleInputEmail2" name="ciudad" value="<%= empl.getCiudad()%>"  maxlength="30">
                                </div>
                            </div> 

                            <div class="form-group">
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">Direccion</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Direccion" name="direccion" value="<%= empl.getCiudad()%>"  maxlength="45">
                                </div>
                            </div>


                        </div>
                    </div>
                 </div>
             </div>
                             
                    <!--#######################BOTONES CANCELAR Y GUARDAR#################-->
                    <div class="modal-footer">
                        <a type="button" href="empleado.jsp" class="btn btn-danger">Cancelar</a>
                        <button type="submit" class="btn btn-info"  name="btnActualizar" value="Actualizar">Actualizar</button>
                    </div> 
                  
                </form>                             
           
        </div> 
        </div>
                        
            
        </div>
	
        </div>
                                
        <div class="col-lg-3"></div>                    
           
        </div>
        
    </div>         

        
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>
