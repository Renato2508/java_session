<%
    String nom = (String)request.getAttribute("nom");
    String heure =  (String)request.getAttribute("h_connex");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RENATO</title>
</head>
<body>
    <%
        if(nom == null){
    %>
        <form action="login" method = "get">
            Votre nom: <input type="text" name="nom">
            <input type="submit" value = "OK">
        </form>
    <%
    }else{
    %>
        <h1>SERVEUR DE RENATO</h1>
        <div>BIENVENUE A <% out.print(nom); %></div>
        <div>HEURE DE CONNEXION <% out.print(heure); %><div>
        <a href = 'logout'>DECONNEXION</a>"
    <%
    }
    %>

    
</body>
</html>