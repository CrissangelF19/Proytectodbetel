<%-- 
    Document   : ConfigMenu
    Created on : 6/09/2017, 04:29:41 PM
    Author     : Pedro Florez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Roles</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<link href="css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<link href="plugins/kalendar/kalendar.css" rel="stylesheet">
<link rel="stylesheet" href="plugins/scroll/nanoscroller.css">
<link href="plugins/morris/morris.css" rel="stylesheet" />
</head>



<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
    
   <!--\\\\\\\Inicio de Body \\\\\\-->
    <div class="">
        
        <!--######## Titulo #######-->
        <div class="pull-left breadcrumb_admin clear_both">
            <div class="pull-left page_title theme_color">
              <h4><i class="fa fa-gears"></i> Roles</h4>
            </div>       
        </div>
        
        <div class="container clear_both padding_fix">
        <div class="row">
                
            <!--#####################-->        
            <div class="col-lg-3"></div>
            
            <!--#####################-->            
            <div class="col-lg-6">
            <div class="panel panel-info">
            <div class="panel-body">
            <div class="porlets-content">
                                     
                                    
            <div class="modal-header">
                <div class="btn-group pull-right">
                    <a href="rol.jsp" class="btn btn-info" > <i class="fa fa-reply"></i> <span> Volver</span></a> 
                </div>
                <h4 class="modal-title page_title theme_color"><i class="glyphicon glyphicon-edit"></i> Modificar Rol</h4>
            </div>

            <form action="RolSvl" class="form-horizontal" method="get" >
                
            <div class="modal-body">
                
                <table class="table table-hover table-nomargin">
                    <thead>
                    <tr class="warning">
                    <th>Módulo</th>
                    <th><input name="Todos" type="checkbox" value="1" id="all_ver" class="check_ver"> Ver</th>
                    <th><input name="Todos" type="checkbox" value="1" id="all_mod" class="check_mod"> Modificar</th>
                    <th><input name="Todos" type="checkbox" value="1" id="all_del" class="check_del"> Eliminar</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                    <td>
                    Inicio <input type="hidden" name="permisos_1" value="Inicio">
                    </td>
                    <td><input type="checkbox" name="view_1" value="1" class="ck"></td>
                    <td><input type="checkbox" name="edit_1" value="1" class="ck1"></td>
                    <td><input type="checkbox" name="del_1" value="1" class="ck2"></td>
                    </tr>
                    <tr>
                    <td>
                    Entradas <input type="hidden" name="permisos_2" value="Entradas">
                    </td>
                    <td><input type="checkbox" name="view_2" value="1" class="ck"></td>
                    <td><input type="checkbox" name="edit_2" value="1" class="ck1"></td>
                    <td><input type="checkbox" name="del_2" value="1" class="ck2"></td>
                    </tr>
                    <tr>
                    <td>
                    Productos <input type="hidden" name="permisos_3" value="Productos">
                    </td>
                    <td><input type="checkbox" name="view_3" value="1" class="ck"></td>
                    <td><input type="checkbox" name="edit_3" value="1" class="ck1"></td>
                    <td><input type="checkbox" name="del_3" value="1" class="ck2"></td>
                    </tr>
                    <tr>
                    <td>
                    Categorias <input type="hidden" name="permisos_4" value="Categorias">
                    </td>
                    <td><input type="checkbox" name="view_4" value="1" class="ck"></td>
                    <td><input type="checkbox" name="edit_4" value="1" class="ck1"></td>
                    <td><input type="checkbox" name="del_4" value="1" class="ck2"></td>
                    </tr>
                    <tr>
                    <td>
                    Proveedores <input type="hidden" name="permisos_5" value="Proveedores">
                    </td>
                    <td><input type="checkbox" name="view_5" value="1" class="ck"></td>
                    <td><input type="checkbox" name="edit_5" value="1" class="ck1"></td>
                    <td><input type="checkbox" name="del_5" value="1" class="ck2"></td>
                    </tr>
                    <tr>
                    <td>
                    Clientes <input type="hidden" name="permisos_6" value="Clientes">
                    </td>
                    <td><input type="checkbox" name="view_6" value="1" class="ck"></td>
                    <td><input type="checkbox" name="edit_6" value="1" class="ck1"></td>
                    <td><input type="checkbox" name="del_6" value="1" class="ck2"></td>
                    </tr>
                    <tr>
                    <td>
                    Empleados <input type="hidden" name="permisos_7" value="Empleados">
                    </td>
                    <td><input type="checkbox" name="view_7" value="1" class="ck"></td>
                    <td><input type="checkbox" name="edit_7" value="1" class="ck1"></td>
                    <td><input type="checkbox" name="del_7" value="1" class="ck2"></td>
                    </tr>
                    <tr>
                    <td>
                    Despachos <input type="hidden" name="permisos_8" value="Despachos">
                    </td>
                    <td><input type="checkbox" name="view_8" value="1" class="ck"></td>
                    <td><input type="checkbox" name="edit_8" value="1" class="ck1"></td>
                    <td><input type="checkbox" name="del_8" value="1" class="ck2"></td>
                    </tr>
                    <tr>
                    <td>
                    Inventario <input type="hidden" name="permisos_9" value="Inventario">
                    </td>
                    <td><input type="checkbox" name="view_9" value="1" class="ck"></td>
                    <td><input type="checkbox" name="edit_9" value="1" class="ck1"></td>
                    <td><input type="checkbox" name="del_9" value="1" class="ck2"></td>
                    </tr>
                    <tr>
                    <td>
                    Configuracion  <input type="hidden" name="permisos_10" value="Configuracion ">
                    </td>
                    <td><input type="checkbox" name="view_10" value="1" class="ck"></td>
                    <td><input type="checkbox" name="edit_10" value="1" class="ck1"></td>
                    <td><input type="checkbox" name="del_10" value="1" class="ck2"></td>
                    </tr>
                    <tr>
                    <td>
                    Administrar Accesos <input type="hidden" name="permisos_11" value="Administrar Accesos">
                    </td>
                    <td><input type="checkbox" name="view_11" value="1" class="ck"></td>
                    <td><input type="checkbox" name="edit_11" value="1" class="ck1"></td>
                    <td><input type="checkbox" name="del_11" value="1" class="ck2"></td>
                    </tr>
                    <tr>
                    <td>
                    Pagos <input type="hidden" name="permisos_11" value="Pagos">
                    </td>
                    <td><input type="checkbox" name="view_12" value="1" class="ck"></td>
                    <td><input type="checkbox" name="edit_12" value="1" class="ck1"></td>
                    <td><input type="checkbox" name="del_12" value="1" class="ck2"></td>
                    </tr>
                    <tr>
                    <td>
                    Devolucion <input type="hidden" name="permisos_11" value="Devolucion">
                    </td>
                    <td><input type="checkbox" name="view_13" value="1" class="ck"></td>
                    <td><input type="checkbox" name="edit_13" value="1" class="ck1"></td>
                    <td><input type="checkbox" name="del_13" value="1" class="ck2"></td>
                    </tr>
                    </tbody>
                </table>
                
                </div>
                
            <!--##### Boton Actualizar#########-->    
            <div class="modal-footer">
                <button type="submit" class="btn btn-info" name="btnActualizar" value="Actualizar"><i class="glyphicon glyphicon-refresh"></i> Actualizar</button>
            </div>

            </form>
                            
            </div>              
            </div> 
            </div> 
            </div>	
        </div>
            <!--#####################-->
            <div class="col-lg-3"></div>
            
        </div>        
    </div>
   
 
<script src="js/jquery-2.1.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common-script.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="js/jquery.sparkline.js"></script>
<script src="js/sparkline-chart.js"></script>
<script src="js/graph.js"></script>
<script src="js/edit-graph.js"></script>
<script src="plugins/kalendar/kalendar.js" type="text/javascript"></script>
<script src="plugins/kalendar/edit-kalendar.js" type="text/javascript"></script>

<script src="plugins/sparkline/jquery.sparkline.js" type="text/javascript"></script>
<script src="plugins/sparkline/jquery.customSelect.min.js" ></script> 
<script src="plugins/sparkline/sparkline-chart.js"></script> 
<script src="plugins/sparkline/easy-pie-chart.js"></script>
<script src="plugins/morris/morris.min.js" type="text/javascript"></script> 
<script src="plugins/morris/raphael-min.js" type="text/javascript"></script>  
<script src="plugins/morris/morris-script.js"></script> 

<script src="plugins/demo-slider/demo-slider.js"></script>
<script src="plugins/knob/jquery.knob.min.js"></script> 

<script src="js/jPushMenu.js"></script> 
<script src="js/side-chats.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="plugins/scroll/jquery.nanoscroller.js"></script>

</body>
</html>

