<%-- 
    Document   : erro400
    Created on : 18/02/2013, 15:24:33
    Author     : Administrador
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ecommerce Devmedia</title>
        <style type="text/css">
            .error{
                color: #c00;
                font-style: italic;
            }
        </style>
    </head>
    <body>
        <h3><p class="error">            
                <fmt:message key="error404_text"/>
            </p>
        </h3>
    </body>
</html>
