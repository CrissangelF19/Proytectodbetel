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
                        
                    
                    <h4 class="modal-title page_title theme_color" id="myModalLabel"> <i class="glyphicon glyphicon-eye-open"></i> Detalle de Proveedor</h4>
                <%
                    Proveedor prov = (Proveedor) request.getAttribute("proveed");  
                %>
                    
                   </div>                 
                                    
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
                               <fieldset disabled >      
                                <input type="hidden" name="id" value="<%= prov.getIdProveedor()%>" /> 
                                <div class="form-group">                                        
                                    <label for="exampleInputName2" class="col-sm-3 control-label">NIT</label>
                                     <div class="col-sm-8">
                                        <input type="text" class="form-control" id="Nit" value="<%= prov.getNit()%>" name="nit" >
                                    </div>    
                                </div>
                                    

                                    <div class="form-group">
                                        <label for="exampleInputEmail2" class="col-sm-3 control-label">Razon Social</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="RazonSocial"  name="razonsocial" value="<%= prov.getRazonSocial()%>"/>
                                        </div>
                                    </div>                                                                           

                                   <div class="form-group has-feedback">
                                    <label for="exampleInputAmount" class="col-sm-3 control-label">Correo Empresarial</label>
                                      <div class="col-sm-8">
                                       <span class="glyphicon glyphicon-envelope form-control-feedback" ></span>
                                      <input type="text" class="form-control" id="exampleInputAmount" name="email" value="<%= prov.getEmail()%>">                                        
                                    </div>
                                  </div>
                                    
                                   <div class="form-group has-feedback">
                                        <label for="exampleInputAmount" class="col-sm-3 control-label">Telefono</label>
                                        <div class="col-sm-8">                                            
                                            <span class="glyphicon glyphicon-earphone form-control-feedback" ></span>
                                            <input type="text" class="form-control" id="Telefono" name="telefono"  value="<%= prov.getTelefono()%>">                                            
                                        </div>
                                   </div>
                                 
                                   <div class="form-group has-feedback">
                                        <label for="exampleInputAmount" class="col-sm-3 control-label">Fecha ingreso</label>
                                        <div class="col-sm-8">
                                              <span class="fa fa-calendar form-control-feedback" ></span>
                                              <input type="date" name="fechaIngreso" id="fecha" class="form-control datepicker"  value="<%= prov.getFechaIngreso()%>">
                                       </div>
                                    </div>
                         
                                   <div class="form-group ">
                                        <label for="exampleInputAmount" class="col-sm-3 control-label">Estado</label>
                                        <div class="col-sm-8">                                            
                                          <input type="text" class="form-control" id="estado" name="estadop"  value="<%= prov.getEstadop()%>">                                            
                                        </div>
                                   </div>

                              </fieldset>
                     
                            </div>
                             
                            <!--###############Datos Personales############-->
                            
                        <div id="Datos" class="tab-pane">
                            <fieldset disabled > 
                            <div class="form-group">                                        
                                <label for="exampleInputName2" class="col-sm-3 control-label">Nombre Completo</label>
                                <div class="col-sm-8">                                                    
                                    <input type="text" class="form-control" id="Nombres" name="nombres" value="<%= prov.getNombreCompleto()%>">                                      
                                </div>    
                            </div>
                                
                            <div class="form-group">
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">Tipo Documento</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="tipodocidentidad" name="tipodocidentidad" value="<%= prov.getTipoDocIdentidad()%>">
                                </div>
                            </div>
                                
                                

                              
                            <div class="form-group">
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">N° Documento</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="Nurdocumento" name="numerodocumento" value="<%= prov.getNroIdentidad()%>">
                                </div>
                            </div>
                


                            <div class="form-group">
                                <label for="exampleInputEmail2" class="col-sm-3 control-label">Genero</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="genero" name="genero" value="<%= prov.getGenero()%>">
                                </div>
                            </div>

                             
                           
                               <div class="form-group has-feedback">
                                        <label for="exampleInputAmount" class="col-sm-3 control-label">Celular</label>
                                        <div class="col-sm-8">
                                            <span class="glyphicon glyphicon-phone form-control-feedback" ></span>
                                            <input type="text" class="form-control" id="celular" name="celular" value="<%= prov.getCelular()%>">                                        
                                        </div>
                                    </div>

                             </fieldset>          
                        </div>
                        
                  
                        <!--##############Direcccion####################-->            
                             
                            <div id="Direc" class="tab-pane" >
                                <fieldset disabled >
                                <div class="form-group">
                                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Barrio</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="exampleInputEmail2" name="barrio" value="<%= prov.getBarrio()%>">
                                    </div>
                                </div>      

                                <div class="form-group">
                                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Ciudad</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="exampleInputEmail2" name="ciudad" value="<%= prov.getCiudad()%>">
                                    </div>
                                </div> 
                          
                                <div class="form-group">
                                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Direccion</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="Direccion" name="direccion" value="<%= prov.getDireccion()%>">
                                    </div>
                                </div>
                                </fieldset>
                             </div>
                           </div>
                        </div>
                    </div>
                                    
                    <!-- ## Botone Cerrar ###-->
                    <div class="modal-footer">                
                        <a type="button" href="proveedor.jsp" class="btn btn-info">Cerrar</a>                    
                    </div>                 
                    
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
