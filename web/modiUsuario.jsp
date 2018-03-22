<%-- 
    Document   : modiUsuario
    Created on : 17/10/2017, 01:10:45 PM
    Author     : Pedro Florez
--%>

<%@page import="com.betel.modelo.Usuario"%>
<%@page import="com.betel.dao.UsuarioDaoImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.RolDaoImpl"%>
<%@page import="com.betel.modelo.Rol"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Usuarios</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />

</head>

<%
    UsuarioDaoImpl dao = new UsuarioDaoImpl();
    List<Usuario> listUsuario = new ArrayList();
    listUsuario = dao.listar();
%>

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

        <div class="col-lg-3"></div>

            <div class="col-lg-6">
                
            <div class="panel panel-info">
            <div class="panel-body">
            <div class="porlets-content">


            <div class="modal-header">
                
                <h4 class="modal-title page_title theme_color"><i class="glyphicon glyphicon-edit"></i> Modificar Usuario</h4>
            </div>
                                
            <% 
                Usuario usuario =  (Usuario) request.getAttribute("usuario");
            %>
                                
            <!-- Inicia el formulario -->              
            <form  action="usuarioSvl" method="get" class="form-horizontal">
                    
                <div class="modal-body">
                    
                    <input type="hidden" name="idUsuario" readonly="readonly" value='<%= usuario.getIdUsuario() %>'/> 

                    <div class="form-group">
                        <label for="exampleInputEmail2" class="col-sm-3 control-label">Nombre Completo</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"  name="nombre" autofocus value='<%= usuario.getNombreCompleto() %>' required maxlength="45">
                        </div>
                    </div>
                        
                    <div class="form-group">
                        <label for="exampleInputEmail2" class="col-sm-3 control-label">Usuario</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"  name="usuario" value='<%= usuario.getUsuario() %>' placeholder="Alias" required maxlength="20">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="fullname" class="col-sm-3 control-label">Contraseña</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" name="clave" placeholder="*******" value='<%= usuario.getClave() %>' required maxlength="20">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="modal" class="col-sm-3 control-label">Rol</label>
                        <div class="col-sm-8">
                            <select class="form-control"  name="idRol">
                                <%                                    
                                    String valor = String.valueOf(usuario.getIdRol());
                                    for(Rol rol : listRol){

                                        %><option value="<%= rol.getIdRol() %>"<%

                                        if(rol.getIdRol().equals(valor)){
                                        %>
                                          selected
                                        <%

                                        }
                                        %>><%= rol.getNombre() %> </option> <% 

                                    }
                                %>
                           </select>
                        </div>
                    </div>

                    <input type="hidden" name="fecha" readonly="readonly" value='<%= usuario.getFechaIngreso() %>'/>                
                            
                    <div class="form-group">                                                                                   
                        <label for="estado" class="col-sm-3 control-label">Estado</label>
                        <div class="col-sm-8">                               
                            <select class="form-control" name="estado">                                    
                                <option selected><%= usuario.getEstado() %></option>
                                <%
                                    if(usuario.getEstado().equals("Activo")){
                                        %><option value="Inactivo">Inactivo</option> <%
                                    }else{
                                        %><option value="Activo">Activo</option> <%
                                    }
                                %>
                            </select>                   
                        </div>
                    </div>        
                            

                </div>

               <!-- ## Botone Guardar y Cancelar###-->   
                <div class="modal-footer">
                    <a type="button" href="usuario.jsp" class="btn btn-danger">Cancelar</a>
                    <button type="submit" class="btn btn-info" name="btnActualizar" value="Actualizar"><i class="glyphicon glyphicon-refresh"></i> Actualizar</button>
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
