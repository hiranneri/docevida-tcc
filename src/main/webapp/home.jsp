<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
 <head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <title>Home</title>
 <link rel="stylesheet" href="css/bootstrap.min.css"></link>
  <link rel="stylesheet" href="css/estilo-home.css"></link>
  <script src="js/jquery.min.js"></script>
 <script src="js/bootstrap.min.js"></script>
 <script src="js/controllerhome.js"></script>
</head>
<body>
	<header>
		<img src="img/docevida.png" alt="Logo Doce Vida" id="logoDoceVida">
		<br>
		<br>
		
	</header>
			<div class="container">
			<div class="row">
				<div id="pedido">
					
					
					<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			
						<button id="btSair" onclick="sair();">Sair</button>
					

				</div>
			</div>
		</div>
		<br>
		</div>
			<div class="container">
			<div class="row">
				<div id="pedido">
					
					<label for="btPedido">Pedido</label><br>
					<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			
						<button id="btPedido" onclick="efetuarPedido();">Pedido</button>
					

				</div>
			</div>
		</div>
		<br>
		</div>
		<div class="container">
			<div class="row">
				<div id="produtos">
					
					<label for="btCadastroProduto">Produtos</label><br>
					
					<div class="col-xs-12 col-sm-6 col-md-12">
						<button id="btCadastrarProduto" onclick="cadastrarProduto();">Cadastrar Produto</button>
					
			
						<button id="btPesquisarProduto" onclick="pesquisarProduto();">Pesquisar Produto</button>
					
					</div>

					
			</div>
		</div>
		</div>
		<br>
		<div class="container">
		<div class="row">
			
				<div id="clientes">
					<label for="btCadastroCliente">Clientes</label><br>
					<div class="col-xs-12 col-sm-6 col-md-12">
						<button id="btCadastrarCliente" onclick="cadastraCliente();">Cadastrar Cliente</button>
					
					
						<button id="btPesquisarCliente"onclick="pesquisarCliente();">Pesquisar Cliente</button>
					
					<br><br>
				</div>
			</div>
		</div>
		</div>
			
</body>
</html>