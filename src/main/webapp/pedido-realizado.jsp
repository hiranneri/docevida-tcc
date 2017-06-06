<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
 <head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 <title>Pesquisa de Produtos</title>
 <link rel="stylesheet" href="css/bootstrap.min.css"></link>
  <link rel="stylesheet" href="css/estilo-pesquisaproduto.css"></link>
  <script src="js/jquery.min.js"></script>
 <script src="js/bootstrap.min.js"></script>
 <script>
 	function voltar(){
 		location.href="home.jsp";
 	}
 </script>
<body>
	<h1>O pedido ${pedido.id} para o(a) cliente ${pedido.cliente.nome} foi realizado com sucesso!</h1>
	
	<button></button>
	
	
</body>
</html>