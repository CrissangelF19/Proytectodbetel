<%-- 
    Document   : modiCategoria
    Created on : 17/10/2017, 01:10:45 PM
    Author     : Estudiante
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.RutaDaoImpl"%>
<%@page import="com.betel.modelo.Cliente"%>
<%@page import="com.betel.modelo.Ruta"%>
<%@page import="com.betel.modelo.Proveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Clientes</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />

</head>
<%
    RutaDaoImpl daof = new RutaDaoImpl();
    List<Ruta> listRuta =  new ArrayList();
    listRuta = daof.listar();  
%>
<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
        
            
    <div class="pull-left breadcrumb_admin clear_both">
       <div class="pull-left page_title theme_color">
         <h4><i class="fa fa-male"></i> Cliente </h4>
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
                <h4 class="modal-title page_title theme_color" id="myModalLabel"> <i class="glyphicon glyphicon-edit"></i> Modificar Cliente</h4>
            </div>
                
            <%
                Cliente client = (Cliente) request.getAttribute("Client");
            %>                        
                                    
          
                
            <form action="ClienteSvl" class="form-horizontal" method="get">
                
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
                            <input type="hidden" name="id" value="<%= client.getIdCliente()%>" /> 
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label">Nombre Completo  *</label>
                                <div class="col-sm-8">                                                    
                                    <input type="text" class="form-control" id="Nombres" name="nombres" value="<%= client.getNombreCompleto()%>"  maxlength="45" required autofocus>                                      
                                </div>    
                            </div>
                            
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label" >Ruta *</label>
                                <div class="col-sm-8" >
                                    <select class="form-control" name="idRuta" autocomplete required>
                                         <%
                                  String valor = String.valueOf(client.getIdRuta());
                                 for(Ruta rut : listRuta){
                                 
                                  %>
                                  <option value="<%= rut.getIdRuta()%>"
                                          <%
                      
                                if(rut.getIdRuta().equals(valor)){
                                     %>
                                    selected
                                 <%
                                     
                                     }
                                     %>>
                                      <%= rut.getNombre()%> </option>
                                  <%
                                     
                                }
                              
                                %>
                                    </select>
                                </div>    
                            </div>
                            
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label">Tipo Documento *</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="tipodocidentidad"  autocomplete required>
                                     <%
                                        if(client.getTipoDocIdentidad().equals("Cedula de Ciudadania")){
                                            %> 
                                            <option selected><%= client.getTipoDocIdentidad()%></option>
                                               <option>Cedula de Extranjeria</option>
                                               <%
                                        }else if(client.getTipoDocIdentidad().equals("Cedula de Extranjeria")){
                                            %> 
                                            <option selected><%= client.getTipoDocIdentidad()%></option>
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
                                    <input type="text" class="form-control" id="Nurdocumento" name="numerodocumento" value="<%= client.getNroIdentidad()%>"  maxlength="15" required>
                                </div>
                            </div>        
                                                        
                            <div class="form-group has-feedback">
                                <label for="exampleInputAmount" class="col-sm-3 control-label">Celular</label>
                                <div class="col-sm-8">
                                    <span class="glyphicon glyphicon-phone form-control-feedback" ></span>
                                    <input type="text" class="form-control" id="celular" name="celular" value="<%= client.getCelular()%>" maxlength="20">                                        
                                </div>
                            </div>

                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label">Estado</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="estadoc">
                                        <option selected><%= client.getEstadoc() %></option>
                                        <%
                                            if(client.getEstadoc().equals("Activo")){
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
                                    <input type="email" class="form-control" id="exampleInputAmount" name="email" value="<%= client.getEmail()%>"  maxlength="45">
                                </div>
                            </div>    
                     
                            <div class="form-group has-feedback">
                                <label for="exampleInputAmount" class="col-sm-3 control-label">Telefono</label>
                                <div class="col-sm-8">                                            
                                    <span class="glyphicon glyphicon-earphone form-control-feedback" ></span>
                                    <input type="text" class="form-control" id="Telefono" name="telefono" value="<%= client.getTelefono()%>" maxlength="20">                                            
                                </div>
                            </div>    
                    
                            <div class="form-group">                                        
                                  <label for="exampleInputName2" class="col-sm-3 control-label" >Genero</label>
                                <div class="col-sm-8" >
                                    <select class="form-control" name="genero">
                                  <%
                                    if(client.getGenero().equals("Masculino")){
                                        %> 
                                        <option selected><%= client.getGenero()%></option>
                                    <option>Femenino</option>
                                    <%
                                    }else if (client.getGenero().equals("Femenino")){
                                         %> 
                                         <option selected><%= client.getGenero()%></option>
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
                                    <input type="text" class="form-control" id="exampleInputEmail2" name="barrio" value="<%= client.getBarrio()%>" maxlength="30">
                                </div>
                            </div>      

                            <div class="form-group">
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">Ciudad</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="exampleInputEmail2" name="ciudad" value="<%= client.getCiudad()%>" maxlength="30">
                                </div>
                            </div> 

                            <div class="form-group">
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">Direccion</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Direccion" name="direccion" value="<%= client.getDireccion()%>" maxlength="45">
                                </div>
                            </div>
                     
                                            
                        </div>
                    </div>
                 </div>
             </div>
                             
                    <!--#######################BOTONES CANCELAR Y GUARDAR#################-->
                    <div class="modal-footer">
                        <a type="button" href="cliente.jsp" class="btn btn-danger">Cancelar</a>
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
