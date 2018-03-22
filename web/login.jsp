<%-- 
    Document   : login
    Created on : 4/09/2017, 02:27:00 PM
    Author     : Pedro Florez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html lang="es">    
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">
<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/animate.css" rel="stylesheet" type="text/css" />
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
</head>

<body class="light_theme  fixed_header left_nav_fixed">
    
    <div class="container">
        <div class="card card-container">
            <img id="profile-img" class="profile-img-card" src="images/avatar_2x.png">
            <p id="profile-name" class="profile-name-card"></p>
            
            <form method="post" accept-charset="utf-8" action="usuarioSvl" autocomplete="off" role="form" class="form-signin">
                <span id="reauth-email" class="reauth-email"></span>
                
                <!--##### Alert Login ####-->
                <%         
                    if(request.getAttribute("respuestaL")!=null){
                %>
                    <div class="alert alert-warning content2" role="alert">                        
                        <%out.println("¡Avertencia! Usuario y/o contraseña no coinciden."); %> 
                    </div> 
                <%
                    }else if(request.getAttribute("respuestaS")!=null){
                %>
                    <div class="alert alert-success content2" role="alert">                        
                        <%out.println("¡Aviso! " + request.getAttribute("respuestaS")); %> 
                    </div> 
                <%
                    }                       
                %>

                <input class="form-control" placeholder="Usuario" name="usuario" type="text" value="" autofocus="" required maxlength="20">
                
                <input class="form-control" placeholder="Contraseña" name="clave" type="password" value="" autocomplete="off" required maxlength="50">
                               
                <!-- ## Botone Entrar ###-->
                <button type="submit" class="btn btn-lg btn-primary btn-block btn-signin" name="btnLogin" value="Entrar">Iniciar Sesión</button>
                
            </form><!-- /form -->
            
        </div><!-- /card-container -->
    </div>    
    
<script src="js/tiempoAlert.js" type="text/javascript"></script>

<script src="js/bootstrap.min.js"></script>
<script src="js/common-script.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>

</body>
</html>
