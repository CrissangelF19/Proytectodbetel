<%-- 
    Document   : Realizar Pagos Compras
    Created on : 17/10/2017, 01:10:45 PM
    Author     : Pedro Florez
--%>

<%@page import="com.betel.modelo.Proveedor"%>
<%@page import="com.betel.dao.CompraDaoImpl"%>
<%@page import="com.betel.modelo.Compra"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  lang="es">
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Creditos</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />

</head>

<%       
    CompraDaoImpl dao = new CompraDaoImpl();    
    
    Compra compra = (Compra) request.getAttribute("compra"); 
    
    Proveedor prov = (Proveedor) dao.buscarPorIDProveedor(compra.getIdProveedor());
%>
 
<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
                    
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
          <h4><i class="fa fa-money"></i> Creditos</h4>
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
                <h4 class="modal-title page_title theme_color" id="myModalLabel"><i class="fa fa-plus-square"></i> Realizar Pago</h4>
            </div>
                
            <form action="PagoCompraSvl" class="form-horizontal" method="post">

            <div class="modal-body">
                
                <input type="hidden" name="idCompra" value="<%= compra.getIdCompra() %>"/>
                
                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label">NºCompra</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="nCompra" value="<%= compra.getnCompra() %>" readonly>
                    </div>
                </div>

                <div class="form-group">
                    <label for="exampleInputEmail2" class="col-sm-3 control-label">Proveedor</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="nombre" value="<%= prov.getRazonSocial() %>" readonly>
                    </div>
                </div>

                <div class="form-group">
                    <div class="has-feedback">
                        <label for="exampleInputAmount" class="col-sm-3 control-label">Saldo Pendiente</label>
                        <div class="col-sm-8">
                            <i class="glyphicon glyphicon-usd form-control-feedback"></i>
                            <input type="number" class="form-control"  name="saldo" value="<%= compra.getSaldoPendiente() %>" readonly>
                        </div> 
                    </div>
                </div>

                <div class="form-group">
                    <div class="has-feedback">
                        <label for="exampleInputAmount" class="col-sm-3 control-label">Monto a Pagar</label>
                        <div class="col-sm-8">
                            <i class="glyphicon glyphicon-usd form-control-feedback"></i>
                            <input type="number" class="form-control"  name="monto" placeholder="En Pesos" autofocus required  max="9999999">
                        </div> 
                    </div>
                </div>
                 
                <div class="form-group">                        
                    <label for="descrp" class="col-sm-3 control-label">Observación</label>
                    <div class="col-sm-8">
                        <textarea class="form-control" name="observacion" maxlength="99" placeholder="Observación"></textarea>
                    </div>
                </div>        

                <div class="form-group">
                    <div class="has-feedback">
                        <label for="model" class="col-sm-3 control-label">Fecha</label>
                        <div class="col-sm-8">                                     
                            <i class="fa fa-calendar form-control-feedback" ></i>                                      
                            <input type="date" class="form-control datepicker" name="fecha" required>                                               
                        </div>
                    </div>                            
                </div>                                                
                        
            </div>

            <!-- ## Botone Guardar y Cancelar###-->
            <div class="modal-footer">                
                <a type="button" href="pago.jsp" class="btn btn-danger">Cancelar</a>
                <button type="submit" class="btn btn-info" name="btnRegistrar" value="Registrar"><i class="glyphicon glyphicon-ok"></i> Guardar</button>
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
