<%-- 
    Document   : modiCategoria
    Created on : 17/10/2017, 01:10:45 PM
    Author     : Estudiante
--%>

<%@page import="com.betel.modelo.Proveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  lang="es">
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Proveedor</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />

</head>

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">     
            
    <div class="pull-left breadcrumb_admin clear_both">
       <div class="pull-left page_title theme_color">
         <h4><i class="glyphicon glyphicon-briefcase"></i> Proveedor </h4>
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
                                   
                <h4 class="modal-title page_title theme_color" id="myModalLabel"> <i class="glyphicon glyphicon-edit"></i> Modificar Proveedor</h4>
            </div>
                
            <%
                Proveedor prov = (Proveedor) request.getAttribute("proveed");
            %>    
                                    
            <div class="row">
                
                <form action="proveedorsvl" class="form-horizontal" method="get" >  
                                   
                <div class="modal-body"> 
                                   
                    <div class="modal-body">                
                        <div class="basic-wizard" id="progressWizard">
                            
                            <ul class="nav nav-pills nav-justified">
                            <li class="active"><a data-toggle="tab" href="#Empresa">Empresa</a></li>
                            <li class=""><a data-toggle="tab" href="#Datos">Datos personales</a></li>
                            <li class=""><a data-toggle="tab" href="#Direc">Dirección</a></li>
                            </ul>
                            
                            
                          <div class="tab-content">
                            <div id="Empresa" class="tab-pane fade active in">
                                <input type="hidden" name="id" value="<%= prov.getIdProveedor()%>" /> 
                                <div class="form-group">                                        
                                    <label for="exampleInputName2" class="col-sm-3 control-label">NIT</label>
                                     <div class="col-sm-8">
                                        <input type="text" class="form-control" id="Nit" value="<%= prov.getNit()%>" name="nit" maxlength="20" required autofocus>
                                    </div>    
                                </div>
                                    

                                    <div class="form-group">
                                        <label for="exampleInputEmail2" class="col-sm-3 control-label">Razon Social</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="RazonSocial"  name="razonsocial" value="<%= prov.getRazonSocial()%>" maxlength="30" required />
                                        </div>
                                    </div>                                                                           

                                   <div class="form-group has-feedback">
                                    <label for="exampleInputAmount" class="col-sm-3 control-label">Correo Empresarial</label>
                                      <div class="col-sm-8">
                                       <span class="glyphicon glyphicon-envelope form-control-feedback" ></span>
                                      <input type="text" class="form-control" id="exampleInputAmount" name="email" value="<%= prov.getEmail()%>" maxlength="45">                                        
                                    </div>
                                  </div>
                                    
                                   <div class="form-group has-feedback">
                                        <label for="exampleInputAmount" class="col-sm-3 control-label">Telefono</label>
                                        <div class="col-sm-8">                                            
                                            <span class="glyphicon glyphicon-earphone form-control-feedback" ></span>
                                            <input type="text" class="form-control" id="Telefono" name="telefono"  value="<%= prov.getTelefono()%>" maxlength="20">                                            
                                        </div>
                                   </div>
                                 

                         
                                       
                                    <div class="form-group">                                        
                                        <label for="exampleInputName2" class="col-sm-3 control-label">Estado</label>
                                        <div class="col-sm-8">
                                            <select class="form-control" name="estadop">
                                            <option selected><%= prov.getEstadop() %></option>
                                                <%
                                                    if(prov.getEstadop().equals("Activo")){
                                                        %>
                                                        <option value="Inactivo">Inactivo</option> 
                                                        <%
                                                    }else{
                                                        %>
                                                        <option value="Activo">Activo</option> <%
                                                    }
                                                %>
                                            </select>
                                        </div>    
                                    </div>  
                                
                            </div>
                             
                            <!--###############Datos Personales############-->
                            
                        <div id="Datos" class="tab-pane">
                            
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label">Nombre Completo</label>
                                <div class="col-sm-8">                                                    
                                    <input type="text" class="form-control" id="Nombres" name="nombres" value="<%= prov.getNombreCompleto()%>"  maxlength="45">                                      
                                </div>    
                            </div>
                                
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label">Tipo Documento</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="tipodocidentidad"  autocomplete required >
                                    <%
                                        if(prov.getTipoDocIdentidad().equals("Cedula de Ciudadania")){
                                            %> 
                                            <option selected><%= prov.getTipoDocIdentidad()%></option>
                                               <option>Cedula de Extranjeria</option>
                                            <%
                                        }else if(prov.getTipoDocIdentidad().equals("Cedula de Extranjeria")){
                                            %> 
                                            <option selected><%= prov.getTipoDocIdentidad()%></option>
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
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">N° Documento</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Nurdocumento" name="numerodocumento" value="<%= prov.getNroIdentidad()%>"  maxlength="15" required>
                                </div>
                            </div>
                           
                            <div class="form-group">                                        
                                  <label for="exampleInputName2" class="col-sm-3 control-label" >Genero</label>
                                <div class="col-sm-8" >
                                <select class="form-control" name="genero">
                                    <%
                                    if(prov.getGenero().equals("Masculino")){
                                        %> 
                                        <option selected><%= prov.getGenero()%></option>
                                    <option>Femenino</option>
                                    <%
                                    }else if (prov.getGenero().equals("Femenino")){
                                         %> 
                                         <option selected><%= prov.getGenero()%></option>
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
                             
                           
                            <div class="form-group has-feedback">
                                <label for="exampleInputAmount" class="col-sm-3 control-label">Celular</label>
                                <div class="col-sm-8">
                                    <span class="glyphicon glyphicon-phone form-control-feedback" ></span>
                                    <input type="text" class="form-control" id="celular" name="celular" value="<%= prov.getCelular()%>" maxlength="20">                                        
                                </div>
                            </div>
                                      
          
                        </div>
                        
                  
                        <!--##############Direcccion####################-->            
                             
                            <div id="Direc" class="tab-pane" >
                          
                                <div class="form-group">
                                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Barrio</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="exampleInputEmail2" name="barrio" value="<%= prov.getBarrio()%>" maxlength="30">
                                    </div>
                                </div>      

                                <div class="form-group">
                                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Ciudad</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="exampleInputEmail2" name="ciudad" value="<%= prov.getCiudad()%>" maxlength="30">
                                    </div>
                                </div> 
                          
                                <div class="form-group">
                                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Direccion</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="Direccion" name="direccion" value="<%= prov.getDireccion()%>" maxlength="45">
                                    </div>
                                </div>
                             </div>
                           </div>
                        </div>
                    </div>
                    
                </div>
            
                <div class="modal-footer">
                    <a type="button" href="proveedor.jsp" class="btn btn-danger">Cancelar</a>
                    <button type="submit" class="btn btn-info"  name="btnActualizar" value="Actualizar">Actualizar</button>
                </div>
            
            </form>
                            
            </div>
                                        
                                        
           
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
