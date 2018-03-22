<%-- 
    Document   : inicio
    Created on : 15/09/2017, 02:23:19 PM
    Author     : Pedro Florez
--%>

<%@page import="com.betel.dao.ClienteDaoImpl"%>
<%@page import="com.betel.dao.VentaDaoImpl"%>
<%@page import="com.betel.dao.CompraDaoImpl"%>
<%@page import="com.betel.dao.ProductoDaoImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Inicio</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<link href="css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<link href="plugins/kalendar/kalendar.css" rel="stylesheet">
<link rel="stylesheet" href="plugins/scroll/nanoscroller.css">
<link href="plugins/morris/morris.css" rel="stylesheet" />

<script src="js/jquery-3.2.1.js" type="text/javascript"></script>

</head>

<%
    ProductoDaoImpl proDao = new ProductoDaoImpl();
%>

<%
    CompraDaoImpl compDao = new CompraDaoImpl();
%>

<%
    VentaDaoImpl ventaDao = new VentaDaoImpl();
%>

<%
    ClienteDaoImpl clienteDao = new ClienteDaoImpl();
%>

<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
    
   
        
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
          <h4><i class="glyphicon glyphicon-home"></i> Inicio </h4>
        </div>
    </div>

    <div class="container clear_both padding_fix">

    <div class="row">

        <div class="col-sm-3 col-sm-6">
            <div class="information red_info">
              <div class="information_inner">
              <div class="info red_symbols"><i class="fa fa-male icon"></i></div>
                <span> CLIENTES </span>
                <h2 class="bolded"> <%= clienteDao.contar() %> </h2>                
              </div>
            </div>
        </div>

        <div class="col-sm-3 col-sm-6">
            <div class="information gray_info">
               <div class="information_inner">
               <div class="info gray_symbols"><i class="fa fa-th-large icon"></i></div>
                 <span> PRODUCTOS </span>
                 <h2 class="bolded"> <%= proDao.contar() %> </h2>            
               </div>
             </div>
        </div>

        <div class="col-sm-3 col-sm-6">
            <div class="information blue_info">
              <div class="information_inner">
              <div class="info blue_symbols"><i class="fa fa-shopping-cart icon"></i></div>
                <span> COMPRAS 2018</span>
                <h2 class="bolded"> <%= compDao.contar() %> </h2>                                 
              </div>
            </div>
        </div>     

        <div class="col-sm-3 col-sm-6">
            <div class="information green_info">   
              <div class="information_inner">
                <div class="info green_symbols"><i class="fa fa-truck icon"></i></div>
                <span> VENTAS 2018 </span>
                <h2 class="bolded"> <%= ventaDao.contar() %> </h2>               
              </div>
            </div>
        </div>           

    </div>


    <div class="row">

        <div class="col-md-12">

        <div class="block-web">

        <div class="porlets-content">

            <div id="graph-wrapper-g-area">

                <svg id="ggg" class="graph" style="height: 375px; width: 100%;">

                <g class="grid x-grid" id="xGrid" style="stroke: rgb(243, 241, 241); stroke-width: 1;"></g>

                <g class="grid y-grid" id="yGrid" style="stroke: rgb(243, 241, 241); stroke-width: 1;">
                <line x1="35" x2="1235" y1="345" y2="345"></line>
                <line x1="35" x2="1235" y1="315" y2="315"></line>
                <line x1="35" x2="1235" y1="285" y2="285"></line>
                <line x1="35" x2="1235" y1="255" y2="255"></line>
                <line x1="35" x2="1235" y1="225" y2="225"></line>
                <line x1="35" x2="1235" y1="195" y2="195"></line>
                <line x1="35" x2="1235" y1="165" y2="165"></line>
                <line x1="35" x2="1235" y1="135" y2="135"></line>
                <line x1="35" x2="1235" y1="105" y2="105"></line>
                <line x1="35" x2="1235" y1="75" y2="75"></line>
                <line x1="35" x2="1235" y1="45" y2="45"></line>
                </g>

                <g class="labels x-labels" style="fill: rgb(0, 0, 0); stroke: none; font-family: Arial; font-size: 13px; text-anchor: start;">
                <text x="35" y="365">Enero</text>
                <text x="135" y="365">Febrero</text>
                <text x="235" y="365">Marzo</text>
                <text x="335" y="365">Abril</text>
                <text x="435" y="365">Mayo</text>
                <text x="535" y="365">Junio</text>
                <text x="635" y="365">Julio</text>
                <text x="735" y="365">Agosto</text>
                <text x="835" y="365">Septiembre</text>
                <text x="935" y="365">Octubre</text>
                <text x="1035" y="365">Noviembre</text>
                <text x="1135" y="365">Deciembre</text>
                </g>

                <g class="labels y-labels" style="fill: rgb(0, 0, 0); stroke: none; font-family: Arial; font-size: 13px; text-anchor: end;">
                <text x="15" y="350">0</text>
                <text x="25" y="320">10</text>
                <text x="25" y="290">20</text>
                <text x="25" y="260">30</text>
                <text x="25" y="230">40</text>
                <text x="25" y="200">50</text>
                <text x="25" y="170">60</text>
                <text x="25" y="140">70</text>
                <text x="25" y="110">80</text>
                <text x="25" y="80">90</text>
                <text x="25" y="50">100</text>
                </g>

                <g class="labels title" style="fill: rgb(0, 0, 0); stroke: none; font-family: Arial; font-size: 13px;">
                <text x="35" y="25" font-weight="bold">Reporte de Inventario 2017</text>
                </g>

                <g class="rects" transform="translate(0, 40) scale(1, -1)">
                <rect class="rect-of-0 bar" id="ggg-point-0" x="35" y="-305" width="33.333333333333336" height="21" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(66, 139, 202);">
                <animate attributeName="height" from="0" to="21" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-1 bar" id="ggg-point-1" x="68.33333333333334" y="-305" width="33.333333333333336" height="96" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(28, 175, 154);">
                <animate attributeName="height" from="0" to="96" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-0 bar" id="ggg-point-3" x="135" y="-305" width="33.333333333333336" height="78" style="stroke: rgb(255, 255, 255); fill: rgb(66, 139, 202); opacity: 0.8;">
                <animate attributeName="height" from="0" to="78" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-1 bar" id="ggg-point-4" x="168.33333333333334" y="-305" width="33.333333333333336" height="138" style="stroke: rgb(255, 255, 255); fill: rgb(28, 175, 154); opacity: 0.8;">
                <animate attributeName="height" from="0" to="138" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-0 bar" id="ggg-point-6" x="235" y="-305" width="33.333333333333336" height="99" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(66, 139, 202);">
                <animate attributeName="height" from="0" to="99" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-1 bar" id="ggg-point-7" x="268.33333333333337" y="-305" width="33.333333333333336" height="225" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(28, 175, 154);">
                <animate attributeName="height" from="0" to="225" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-0 bar" id="ggg-point-9" x="335" y="-305" width="33.333333333333336" height="222" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(66, 139, 202);">
                <animate attributeName="height" from="0" to="222" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-1 bar" id="ggg-point-10" x="368.33333333333337" y="-305" width="33.333333333333336" height="114" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(28, 175, 154);">
                <animate attributeName="height" from="0" to="114" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-0 bar" id="ggg-point-12" x="435" y="-305" width="33.333333333333336" height="36" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(66, 139, 202);">
                <animate attributeName="height" from="0" to="36" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-1 bar" id="ggg-point-13" x="468.33333333333337" y="-305" width="33.333333333333336" height="186" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(28, 175, 154);">
                <animate attributeName="height" from="0" to="186" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-0 bar" id="ggg-point-15" x="535" y="-305" width="33.333333333333336" height="147" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(66, 139, 202);">
                <animate attributeName="height" from="0" to="147" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-1 bar" id="ggg-point-16" x="568.3333333333334" y="-305" width="33.333333333333336" height="60" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(28, 175, 154);">
                <animate attributeName="height" from="0" to="60" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-0 bar" id="ggg-point-18" x="635" y="-305" width="33.333333333333336" height="99" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(66, 139, 202);">
                <animate attributeName="height" from="0" to="99" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-1 bar" id="ggg-point-19" x="668.3333333333334" y="-305" width="33.333333333333336" height="156" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(28, 175, 154);">
                <animate attributeName="height" from="0" to="156" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-0 bar" id="ggg-point-21" x="735" y="-305" width="33.333333333333336" height="99" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(66, 139, 202);">
                <animate attributeName="height" from="0" to="99" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-1 bar" id="ggg-point-22" x="768.3333333333334" y="-305" width="33.333333333333336" height="225" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(28, 175, 154);">
                <animate attributeName="height" from="0" to="225" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-0 bar" id="ggg-point-24" x="835" y="-305" width="33.333333333333336" height="222" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(66, 139, 202);">
                <animate attributeName="height" from="0" to="222" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-1 bar" id="ggg-point-25" x="868.3333333333334" y="-305" width="33.333333333333336" height="114" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(28, 175, 154);">
                <animate attributeName="height" from="0" to="114" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-0 bar" id="ggg-point-27" x="935.0000000000001" y="-305" width="33.333333333333336" height="36" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(66, 139, 202);">
                <animate attributeName="height" from="0" to="36" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-1 bar" id="ggg-point-28" x="968.3333333333334" y="-305" width="33.333333333333336" height="186" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(28, 175, 154);">
                <animate attributeName="height" from="0" to="186" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-0 bar" id="ggg-point-30" x="1035" y="-305" width="33.333333333333336" height="147" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(66, 139, 202);">
                <animate attributeName="height" from="0" to="147" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-1 bar" id="ggg-point-31" x="1068.3333333333335" y="-305" width="33.333333333333336" height="60" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(28, 175, 154);">
                <animate attributeName="height" from="0" to="60" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-0 bar" id="ggg-point-33" x="1135" y="-305" width="33.333333333333336" height="99" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(66, 139, 202);">
                <animate attributeName="height" from="0" to="99" dur="1s" fill="freeze"></animate>
                </rect>
                <rect class="rect-of-1 bar" id="ggg-point-34" x="1168.3333333333335" y="-305" width="33.333333333333336" height="156" style="stroke: rgb(255, 255, 255); opacity: 0.8; fill: rgb(28, 175, 154);">
                <animate attributeName="height" from="0" to="156" dur="1s" fill="freeze"></animate>
                </rect>
                </g>

                <g class="legend">
                <g id="legend-ggg" class="legend-of-0">
                <rect class="rect-of-0" x="1197" y="20" width="30" height="30" style="fill: rgb(66, 139, 202);"></rect>
                <text style="cursor:default;" class="legend-of-0" x="1232" y="35">Compras</text>
                <g id="legend-ggg" class="legend-of-1">
                <rect class="rect-of-1" x="1197" y="60" width="30" height="30" style="fill: rgb(28, 175, 154);"></rect>
                <text style="cursor:default;" class="legend-of-1" x="1232" y="75">Ventas</text>                    
                </g>

                </g>
                </g>
                </svg>

             </div>
          </div>
        </div>     
    </div>  
   </div>   
</div>   

    
    
<script src="js/bootstrap.min.js"></script>
<script src="js/common-script.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="plugins/data-tables/jquery.dataTables.js"></script>
<script src="plugins/data-tables/DT_bootstrap.js"></script>
<script src="plugins/data-tables/dynamic_table_init.js"></script>

</body>
</html>