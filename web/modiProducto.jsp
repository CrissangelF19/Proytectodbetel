<%-- 
    Document   : producto
    Created on : 25/08/2017, 12:45:54 PM
    Author     : Katia Ortiz
--%>

<%@page import="com.betel.dao.ProveedorDaoImpl"%>
<%@page import="com.betel.modelo.Proveedor"%>
<%@page import="com.betel.dao.CategoriaDaoImpl"%>
<%@page import="com.betel.modelo.Categoria"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.betel.modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.betel.dao.ProductoDaoImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Modificar Producto</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />

</head>
    
<%
    CategoriaDaoImpl daoi = new CategoriaDaoImpl();
    List<Categoria> listCategoria =  new ArrayList();
    listCategoria = daoi.listar();
%>

<%
    ProveedorDaoImpl daop = new ProveedorDaoImpl();
    List<Proveedor> listProveedor =  new ArrayList();
    listProveedor = daop.listar();
%>
    
        
<body class="fixed_header left_nav_fixed atm-spmenu-push dark_theme blue_thm" style="background-color: #f1f1f1">
       
    <!--######## Titulo #######-->
    <div class="pull-left breadcrumb_admin clear_both">
        <div class="pull-left page_title theme_color">
          <h4><i class="glyphicon glyphicon-th-large"></i> Productos</h4>
        </div>
    </div>

    <div class="container clear_both padding_fix">         
        <div class="row">
            
            <div class="col-lg-2"></div>
            
            <div class="col-md-8">

            <div class="panel panel-info">

                <div class="panel-body">

                <div class="porlets-content">

                <div class="modal-header">                    
                    <h4 class="modal-title page_title theme_color"><i class="glyphicon glyphicon-edit"></i> Modificar Producto</h4>

                </div>

                <%
                    Producto prod = (Producto) request.getAttribute("producto");             
                %>
                        
                        
        <!--############## Modal ver Producto #######################-->              
        <form action="ProductoSvl"  method="get" class="form-horizontal">
             
            <div class="modal-body">
                
                <div class="nav-tabs-custom">                                
                <div class="tab-content">               
                
                <div class="tab-pane active" id="details">
                    
                        <div class="form-group ">
                            <label for="product_code" class="col-sm-2 control-label">Código</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control"  name="codigo"  value='<%= prod.getCodProducto()%>' disabled>                            
                            </div>
                            
                             <input type="hidden" name="idCod" readonly="readonly" value='<%= prod.getIdProducto()%>'/>
                             <input type="hidden" name="codigo" readonly="readonly" value='<%= prod.getCodProducto()%>'/>

                            
                            <label for="model" class="col-sm-2 control-label">Nombre</label>
                            <div class="col-sm-4">
                               <input type="text" class="form-control"  name="nombre"  value='<%= prod.getNombre() %>' required maxlength="30">
                            </div>
                        </div>

                        <div class="form-group">
                            
                            <label for="stock" class="col-sm-2 control-label">Categoria</label>
                            <div class="col-sm-4">
                                <select class="form-control"  name="idCategoria">                                    
                                    <%
                                    String valor1 = String.valueOf(prod.getIdCategoria());
                                    for(Categoria catg : listCategoria){

                                      %><option value="<%= catg.getIdCategoria() %>"<%

                                    if(catg.getIdCategoria().equals(valor1)){

                                    %>
                                        selected
                                    <%

                                    }
                                    %>><%= catg.getNombre()%> </option> <%

                                    }                              
                                    %>                                
                                </select>
                            </div>                              
                                                       
                            <div class="has-feedback">
                                <label for="presentation" class="col-sm-2 control-label">Und. por Caja</label>
                                <div class="col-sm-4">
                                    <i class="fa fa-archive form-control-feedback"></i>
                                    <input type="number" class="form-control"  name="unidadesCaja" value='<%= prod.getUnidadesCaja() %>' max="999">
                                </div> 
                            </div>
                        </div>

                        <div class="form-group">
                            
                            <div class="has-feedback">
                                <label for="exampleInputAmount" class="col-sm-2 control-label">Precio de Venta</label>
                                <div class="col-sm-4">
                                    <i class="glyphicon glyphicon-usd form-control-feedback"></i>
                                    <input type="number" class="form-control"  name="precioVenta" placeholder="En Pesos"  value='<%= prod.getPrecioVenta() %>' required max="9999999">
                                </div> 
                            </div> 
                            
                            <div class="has-feedback">
                                <label for="stock" class="col-sm-2 control-label">Stock Min</label>
                                <div class="col-sm-4">                               
                                    <i class="fa fa-th-large form-control-feedback"></i>
                                    <input type="number" class="form-control"  name="stockMin"  value='<%= prod.getStockMin() %>' required max="99">  
                                </div>
                            </div>    
                            
                        </div>

                        <div class="form-group">
                            
                            <label for="exampleInputAmount" class="col-sm-2 control-label">% de Ganancia</label>
                            <div class="col-sm-4">                                                                           
                                <input type="number" class="form-control" name="pGanancia"  value='<%= prod.getpGanancia() %>' max="999">                                
                            </div>
                            
                            <label for="stock" class="col-sm-2 control-label">Proveedor</label>
                            <div class="col-sm-4">
                                <select class="form-control"  name="idProveedor">
                                    <%
                                    String valor2 = String.valueOf(prod.getIdProveedor());
                                    for(Proveedor prov : listProveedor){

                                    %><option value="<%= prov.getIdProveedor()%>"<%

                                    if(prov.getIdProveedor().equals(valor2)){
                                    %>
                                        selected
                                    <%

                                    }
                                    %>><%= prov.getRazonSocial()%> </option> <%

                                    }   

                                    %>                                   
                                </select>
                            </div>                           
                            
                        </div>
                                    
                        <div class="form-group">
                            
                            <label for="" class="col-sm-2 control-label">Descripción</label>
                            <div class="col-sm-4">
                               <textarea class="form-control"  name="descripcion" maxlength="50"><%= prod.getDescripcion() %></textarea>
                            </div>
                            
                            <input type="hidden" name="fechaRegistro" readonly="readonly" value='<%=prod.getFechaRegistro() %>'/>
                           
                            <label for="stock" class="col-sm-2 control-label">Estado</label>
                            <div class="col-sm-4">
                                <select class="form-control" name="estado">
                             
                                <option selected><%= prod.getEstado() %></option>
                                    <%
                                        if(prod.getEstado().equals("Activo")){
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
                    </div>
                    </div>
         
                    <!-- ## Boton Actualizar ###-->
                    <div class="modal-footer">
                        <a type="button" href="producto.jsp" class="btn btn-danger">Cancelar</a>
                        <button type="submit" class="btn btn-info" name="btnActualizar" value="Actualizar"><i class="glyphicon glyphicon-refresh"></i> Actualizar</button>
                    </div>
                    
                </form>
            </div>
            </div>
            </div>
            </div>
                                    
            <div class="col-lg-2"></div>                       
        </div>
    </div>
                                    

<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>
