<%-- 
    Document   : Modificar Perfil de La Empresa
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

</head>

<%
    EmpresaDaoImpl dao = new EmpresaDaoImpl();
    List<Empresa> listEmpresa =  new ArrayList();
    listEmpresa = dao.listar();
%>

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
    
<!--\\\\\\\Inicio de Body \\\\\\-->
    <div class="">
        
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
                Empresa emp = (Empresa) request.getAttribute("emp");                
            %>
            
            <form action="EmpresaSvl" class="form-horizontal" method="get">
                
            <!--######## Logo #######-->    
            <div class="col-md-3 col-lg-3" align="center">
                
                <img class="img-responsive" src="<%= emp.getLogo()%>" alt="Logo">                    
                                
            </div>

            <!--######## Tabla Formulario #######-->
            <div class="col-md-9 col-lg-9">
                
                <input type="hidden" name="logo" readonly="readonly" value='<%= emp.getLogo()%>'/>
                
                <input type="hidden" name="idEmpresa" readonly="readonly" value='<%= emp.getIdEmpresa() %>'/>

                <div class="form-group">                                        
                    <label for="exampleInputName2" class="col-sm-3 control-label">Nombre de la Empresa *</label>
                    <div class="col-sm-8">                                                    
                        <input type="text" class="form-control" name="nombre" value="<%= emp.getNombre()%>" autofocus required maxlength="40">                                      
                    </div>    
                </div>  

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Nit *</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="nit" value="<%= emp.getNit()%>" required maxlength="15">
                    </div>
                </div>

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Propietario *</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="propi" value="<%= emp.getPropietario()%>" required maxlength="45">
                    </div>
                </div>

                <div class="form-group has-feedback">
                    <label for="exampleInputAmount" class="col-sm-3 control-label">Correo Electronico *</label>
                    <div class="col-sm-8">
                        <span class="glyphicon glyphicon-envelope form-control-feedback" ></span>
                        <input type="email" class="form-control" name="email" value="<%= emp.getEmail()%>" required maxlength="50">
                    </div>
                </div>

                <div class="form-group has-feedback">
                    <label for="exampleInputAmount" class="col-sm-3 control-label">Telefono *</label>
                    <div class="col-sm-8">                                            
                        <span class="glyphicon glyphicon-earphone form-control-feedback" ></span>
                        <input type="text" class="form-control" name="telefono" value="<%= emp.getTelefono()%>" required maxlength="20">                                            
                    </div>
                </div>

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label">IVA (%)</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="iva" value="<%= emp.getIva()%>" required maxlength="4" max="4">
                    </div>
                </div>                                          

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Direcciòn *</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="direccion" value="<%= emp.getDireccion()%>" required maxlength="50">
                    </div>
                </div>

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Ciudad *</label>
                    <div class="col-sm-8">
                       <input type="text" class="form-control"  name="ciudad" value="<%= emp.getCiudad()%>" required maxlength="30">
                    </div>
                </div> 

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Barrio *</label>
                    <div class="col-sm-8">
                      <input type="text" class="form-control"  name="barrio" value="<%= emp.getBarrio()%>" required maxlength="30">
                    </div>
                </div>

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Còdigo Postal</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control"  name="codPostal" value="<%= emp.getCodigoPostal()%>" maxlength="10" max="10">
                    </div>
                </div>

            </div>
            
            <!-- ## Boton Acttualizar ###-->               
            <div class="panel-body text-center">
                <a type="button" href="perfilEmpresa.jsp" class="btn btn-danger">Cancelar</a>
                <button type="submit" class="btn btn-info" name="btnActualizar" value="Actualizar"><i class="glyphicon glyphicon-refresh"></i> Actualizar Datos</button>
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


<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>
