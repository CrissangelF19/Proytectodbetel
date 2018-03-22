<%-- 
    Document   : modiCategoria
    Created on : 17/10/2017, 01:10:45 PM
    Author     : Estudiante
--%>

<%@page import="com.betel.modelo.Ruta"%>
<%@page import="com.betel.modelo.Proveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  lang="es">
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Rutas</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />

</head>

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">      
            
    <div class="pull-left breadcrumb_admin clear_both">
       <div class="pull-left page_title theme_color">
         <h4><i class="fa fa-dashboard"></i> Rutas </h4>
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
                                   
                <h4 class="modal-title page_title theme_color" id="myModalLabel"> <i class="glyphicon glyphicon-edit"></i> Modificar Ruta</h4>
            </div>
                
            <%
                Ruta rut = (Ruta) request.getAttribute("rut");            
            %> 
                
            <form action="RutaSvl" class="form-horizontal" method="get" >  
                                   
                <div class="modal-body"> 
                                   
                    <div class="modal-body">
                        <input type="hidden" name="idRuta" value="<%= rut.getIdRuta()%>" /> 
                        <div class="form-group">
                            <label for="exampleInputEmail2" class="col-sm-3 control-label">Nombre</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="nombreCategoria" name="nombre" value="<%= rut.getNombre()%>" required maxlength="30">
                            </div>
                        </div>

                        
                        <div class="form-group">                                        
                            <label for="exampleInputName2" class="col-sm-3 control-label">Estado</label>
                            <div class="col-sm-8">
                                <select class="form-control" name="estado">
                                    <option selected><%= rut.getEstado() %></option>
                                    
                                    <%
                                       if(rut.getEstado().equals("Activo")){
                                           %><option value="Inactivo">Inactivo</option> <%
                                       }else{
                                           %><option value="Activo">Activo</option> <%
                                       }
                                    %>
                                        

                                </select>
                            </div>    
                        </div>  
                        
                    </div>

                </div>
                                    
                <!-- ## Botone Guardar y Cancelar###-->                       
                <div class="modal-footer">
                    <a type="button" href="ruta.jsp" class="btn btn-danger">Cancelar</a>
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
