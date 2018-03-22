 
<%-- 
    Document   : home
    Created on : 1/09/2017, 02:31:23 PM
    Author     : Pedro Florez
--%>
<%@page import="com.betel.dao.ProductoDaoImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession sesion = request.getSession(true);
    Object user = sesion.getAttribute("user") == null ? null : sesion.getAttribute("user");    
%>

<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Home</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/betel.css" rel="stylesheet" type="text/css"/>
<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-2.1.0.js"></script>
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>

</head>

    <style type="text/css">
        iframe { margin:0; padding:0; height:100%; }
        iframe { display:block; width:100%; border:none;}
    </style>


    <script type="text/javascript">
        function mostrar(url){
            document.frames.miIframe.location=url
        }
    </script>
    
    
    
<%
    ProductoDaoImpl proDao = new ProductoDaoImpl();
%> 

        
<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background: rgb(255, 255, 255);">  
    
    <%
        if (user != null) {
    %> 
    
<div class="wrapper">
  <!--\\\\\\\ wrapper Start \\\\\\-->
  <div class="header_bar">
    <!--\\\\\\\ header Start \\\\\\-->
    <div class="brand">
      <!--\\\\\\\ brand Start \\\\\\-->
      <div class="logo" style="display:block"><img src="images/logo.png" width="190" height="45" /></div>
      
    </div>
    <!--\\\\\\\ brand end \\\\\\-->
    <div class="header_top_bar">
      
      <a href="javascript:void(0);" class="menutoggle"><i class="fa fa-bars"></i></a>     
      
      <div class="top_right_bar">
          
        <div class="top_right">
          <div class="top_right_menu">
            <ul>
                <li> 
                    <a href="#"><% out.print(" " + request.getAttribute("listUsuario")); %></a>                
                </li> 
                <li> 
                    <% 
                        if(proDao.contarStock() == 0){
                    %>
                        
                        <a href="alertaInventario.jsp" target="formulario" title="Notificación">
                            <span class="badge badge"><i class="fa fa-bell"></i></span>                            
                        </a>                        
                        
                    <%
                        }else {
                    %>
                        <a href="alertaInventario.jsp" target="formulario" title="Notificación">
                            <span class="badge badge"><i class="fa fa-bell"></i></span>
                            <span class="label label-danger"> <%= proDao.contarStock() %> </span>
                        </a>
                    <%
                        }                    
                    %>
                                    
                </li>                
                <li>
                    <a href="devolucion.jsp" target="formulario"> Devoluciónes <span class="badge badge"><i class="fa fa-refresh"></i></span></a>                
                </li>                                
                <li> 
                    <form action="usuarioSvl" method="post">

                        <a href="javascript:;" onclick="return confirm('Desea Salir de la Aplicaciòn?') && parentNode.submit();" name="btnCerrarSesion"> 
                            Salir <span class="badge badge"><i class="fa fa-power-off"></i></span>
                        </a>

                        <input type="hidden" name="btnCerrarSesion" value="CerrarSesion"/>
                    </form>                    
                </li>
            </ul>
          </div>
        </div>
       
      </div>
    </div>
    <!--\\\\\\\ header top bar end \\\\\\-->  
  <!--\\\\\\\ header end \\\\\\-->
  </div>
  
  <div class="inner">
    <!--\\\\\\\ inner start \\\\\\-->
    <div class="left_nav">

      <!--\\\\\\\left_nav start \\\\\\-->
      
        <div class="left_nav_slidebar">
          
            <div class="sidebar-menu">
              <li class="header">MENÚ</li>              
            </div>
          
        <ul>            

            <li>
              <a href="inicio.jsp" target="formulario"><i class="glyphicon glyphicon-home"></i> Inicio </a>            
            </li>
          
            <li> 
              <a href="javascript:void(0);"> <i class="fa fa-shopping-cart"></i> Compras <i class="fa fa-angle-down pull-right"></i></a>
                <ul>
                    <li> <a href="compra.jsp" target="formulario"><i class="glyphicon glyphicon-barcode"></i> <b>Nueva Compra</b> </a> </li>
                  <li> <a href="admin_Compra.jsp" target="formulario"><i class="glyphicon glyphicon-list-alt"></i> <b>Historial de Compras</b> </a> </li>
                </ul>
            </li>
            
            <li> 
              <a href="javascript:void(0);"> <i class="fa fa-truck"></i> Ventas <i class="fa fa-angle-down pull-right"></i></a>
                <ul>
                  <li> <a href="venta.jsp" target="formulario"><i class="glyphicon glyphicon-usd"></i> <b>Nueva Venta</b> </a> </li>                  
                  <li> <a href="admin_Venta.jsp" target="formulario"><i class="glyphicon glyphicon-list-alt"></i> <b>Historial de Ventas</b> </a> </li>
                </ul>
            </li>
          
            <li> 
                <a href="categoria.jsp" target="formulario"> <i class="glyphicon glyphicon-tags"></i> Categorias </a>           
            </li>
            
            <li> <a href="ruta.jsp" target="formulario"><i class="fa fa-dashboard"></i> Rutas </a></li>
            
            <li> 
              <a href="producto.jsp" target="formulario"> <i class="glyphicon glyphicon-th-large"></i> Productos </a>            
            </li>
            
            <li> 
              <a href="proveedor.jsp" target="formulario"> <i class="glyphicon glyphicon-briefcase"></i> Proveedores </a>            
            </li>
          
            <li> 
              <a href="cliente.jsp" target="formulario"> <i class="fa fa-male"></i> Clientes </a>            
            </li>
          
            <li> 
               <a href="empleado.jsp" target="formulario"> <i class="fa fa-thumb-tack"></i> Empleados </a>            
            </li>
            
            <li> 
               <a href="pago.jsp" target="formulario"> <i class="fa fa-money"></i> Creditos </a>            
            </li>
          
            <li> 
              <a href="inventario.jsp" target="formulario"> <i class="glyphicon glyphicon-signal"></i> Inventario </a>            
            </li>
          
            <li> 
                <a href="perfilEmpresa.jsp" target="formulario"> <i class="glyphicon glyphicon-cog"></i> Configuración </a>            
            </li>
          
            <li> 
              <a href="javascript:void(0);" target="formulario"> <i class="glyphicon glyphicon-lock"></i> Administrar Accesos <i class="fa fa-angle-down pull-right"></i></a>
                <ul>
                  <li> <a href="rol.jsp" target="formulario"><i class="fa fa-gears"></i> <b>Roles</b> </a> </li>
                  <li> <a href="usuario.jsp" target="formulario"><i class="fa fa-group"></i> <b>Usuarios</b> </a> </li>
                </ul>
            </li>
          
        </ul>
      </div>
    </div>
    <!--\\\\\\\left_nav end \\\\\\-->
    
            
    <!--\\\\\\\ Iframe \\\\\\-->
    <div class="contentpanel">        
                
        <iframe src="inicio.jsp" name="formulario" frameborder="0"  scrolling="auto"></iframe>
                            
    </div>
    
    </div>
  <!--\\\\\\\ inner end\\\\\\-->  
</div>
<!--\\\\\\\ wrapper end\\\\\\-->

<script src="js/modalStatico.js" type="text/javascript"></script>
<script src="js/tiempoAlert.js" type="text/javascript"></script>

<script src="js/jquery-2.1.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common-script.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="plugins/data-tables/jquery.dataTables.js"></script>
<script src="plugins/data-tables/DT_bootstrap.js"></script>
<script src="plugins/data-tables/dynamic_table_init.js"></script>       
    
    
    <%        
        }else { 
            response.sendRedirect("login.jsp");

            /*out.println("<br>");
            out.println("Por Favor Inicie Sesion.... ");
            out.println("<a href='login.jsp'> Iniciar Sesion</a>");*/
        }           
    %>    

</body>
</html>
