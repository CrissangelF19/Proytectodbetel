<%-- 
    Document   : Perfil de La Empresa
    Created on : 25/08/2017, 12:45:54 PM
    Author     : Pedro Florez
--%>

<%@page import="java.sql.Array"%>
<%@page import="com.betel.modelo.Empresa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.EmpresaDaoImpl"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Perfil Empresa</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />

<script src="js/jquery-3.2.1.js" type="text/javascript"></script>

</head>

<%
    EmpresaDaoImpl dao = new EmpresaDaoImpl();
    List<Empresa> listEmpresa =  new ArrayList();
%>

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
            
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
          <h4><i class="fa fa-folder-open"></i>Perfil de la Empresa</h4>
        </div>
    </div>
        
    <div class="container clear_both padding_fix">         
      <div class="row">
        <div class="col-md-12">                    
        <div class="panel panel-info">		
        <div class="panel-body">                        
        <div class="porlets-content">                
        
                                    
            <div class="row modal-body">
                
            <%         
                if(request.getAttribute("respuesta")!=null){
            %>
                <div class="alert alert-success content2" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <%out.println("¡Bien hecho! Se han Actualizado los Datos con éxito."); %> 
                </div> 
            <%
                }                        
            %>    
             
            <%
                listEmpresa = dao.listar();                  
            %>
            
            <form action="EmpresaSvl" class="form-horizontal" method="get">                
            
            <!--######## Logo #######-->    
            <div class="col-md-3 col-lg-3" align="center"> 
                <div id="load_img">
                    <img class="img-responsive" src="<%= listEmpresa.get(0).getLogo()%>" alt="Logo">
                </div>                
            </div>
             
                <input type="hidden" name="logo" readonly="readonly" value='<%= listEmpresa.get(0).getLogo()%>'/>

            <!--######## Tabla Formulario #######-->
            <div class="col-md-9 col-lg-9">

                <div class="form-group">                                        
                    <label for="exampleInputName2" class="col-sm-3 control-label"><strong> Nombre de la Empresa</strong></label>
                    <div class="col-sm-8">                                                    
                        <input type="text" class="form-control" name="nombre" value="<%= listEmpresa.get(0).getNombre()%>" disabled>                                      
                    </div>    
                </div>                  

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label"><strong> Nit</strong></label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="nit" value="<%= listEmpresa.get(0).getNit()%>" disabled>
                    </div>
                </div>

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label"><strong> Propietario</strong></label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="propi" value="<%= listEmpresa.get(0).getPropietario()%>" disabled>
                    </div>
                </div>

                <div class="form-group has-feedback">
                    <label for="exampleInputAmount" class="col-sm-3 control-label"><strong> Correo Electronico</strong></label>
                    <div class="col-sm-8">
                        <span class="glyphicon glyphicon-envelope form-control-feedback" ></span>
                        <input type="email" class="form-control" id="exampleInputAmount" name="email" value="<%= listEmpresa.get(0).getEmail()%>" disabled>
                    </div>
                </div>

                <div class="form-group has-feedback">
                    <label for="exampleInputAmount" class="col-sm-3 control-label"><strong> Telefono</strong></label>
                    <div class="col-sm-8">                                            
                        <span class="glyphicon glyphicon-earphone form-control-feedback" ></span>
                        <input type="text" class="form-control" id="Telefono" name="telefono" value="<%= listEmpresa.get(0).getTelefono()%>" disabled>                                            
                    </div>
                </div>

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label"><strong> IVA (%)</strong></label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control mask" name="iva" value="<%= listEmpresa.get(0).getIva()%>" disabled>
                    </div>
                </div>                                          

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label"><strong> Direcciòn</strong></label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="direccion" value="<%= listEmpresa.get(0).getDireccion()%>" disabled>
                    </div>
                </div>

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label"><strong> Ciudad</strong></label>
                    <div class="col-sm-8">
                       <input type="text" class="form-control"  name="ciudad" value="<%= listEmpresa.get(0).getCiudad()%>" disabled>
                    </div>
                </div> 

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label"><strong> Barrio</strong></label>
                    <div class="col-sm-8">
                      <input type="text" class="form-control"  name="barrio" value="<%= listEmpresa.get(0).getBarrio()%>" disabled>
                    </div>
                </div>

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label"><strong> Còdigo Postal</strong></label>
                    <div class="col-sm-8">
                      <input type="text" class="form-control"  name="codPostal" value="<%= listEmpresa.get(0).getCodigoPostal()%>" disabled>
                    </div>
                </div>

            </div>
            
            <!--################################# Boton Modificar #########################################-->
            <a href="javascript:;" class="btn btn-primary" onclick="parentNode.submit();" name="btnModificar" style="margin-left: 540px; margin-top: 5px;">
            <i class="glyphicon glyphicon-edit"></i> Editar Datos </a>
            
            <input type="hidden" name="btnModificar" value="modificar"/>
            <input type="hidden" name="idEmpresa" value='<%= listEmpresa.get(0).getIdEmpresa() %>'/>    
            <!--################################# Boton Modificar ##########################################-->
                        
            </form>
                
            </div>            
                    
            </div>
            </div>
        </div>
        </div>
    </div>
</div>
            
<script src="js/tiempoAlert.js" type="text/javascript"></script>
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>
