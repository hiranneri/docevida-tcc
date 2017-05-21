<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
 <head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <title>Doce Vida</title>
 <link rel="stylesheet" href="css/bootstrap.css">
 <link rel="stylesheet" href="css/estilo-home.css"></link>
 <script src="js/bootstrap.js"></script>
 <script src="js/jquery.min.js"></script> 
 <script src="js/controllerhome.js"></script>
 </head>
<body>

		<header>
			<img src="img/docevida.png" alt="Logo Doce Vida" id="logoDoceVida"/>
		</header>
		<section>
			
			<!--
			<strong>Pedido</strong>
			<center>
			<a href="efetuar_pedido.html"> 
			<input type="submit" name="efetuarpedido" style="width:100%;height:50px" value="Efetuar Pedido">
			</a> </input>
			</center>
			-->
			<label>Usuário: ${nomeUsuario}</label>
			<h3>Pedido</h3>
			<div class="container">
			<div class="col-xs-12 col-md-12">
				<div class="row">
					<button type="button" class="btn btn-primary btn-md" name="efetuarpedido" value="Efetuar Pedido" onclick="efetuarPedido();">Efetuar Pedido</button>
				
				</div>
			</div>
			</div>
		</section>
		<h3>Produto</h3>
			<div class="container">
			<div class="col-xs-12 col-md-12">
				<div class="row">
					<button type="button" class="btn btn-primary btn-md" name="cadastrarproduto" value="Cadastrar Produto" onclick="cadastraProduto();">Cadastrar Produto</button>
				
				</div>
			</div>
			</div>
		<section>
			<div class="container">
				<div class="row">
					<button type="button" class="btn btn-primary btn-md" name="pesquisarproduto" value="Pesquisar Produto" onclick="pesquisaProduto();">Pesquisar Produto</button>
				</div>
			</div>
			
			<h3>Cliente</h3>
			<div class="container">
			<div class="col-xs-12 col-md-12">
				<div class="row">
					<button type="button" class="btn btn-primary btn-md" name="cadastrarcliente"  value="Cadastrar Cliente" onclick="cadastraCliente();">Cadastrar Cliente</button>
					
				</div>
			</div>
			</div>
		</section>
		<section>
			<div class="container">
			<div class="col-xs-12 col-md-12">
				<div class="row">
					<button type="button" class="btn btn-primary btn-md" name="pesquisarcliente" value="Pesquisar Cliente" onclick="pesquisaCliente();">Pesquisar Cliente</button>
					
				</div>
			</div>
			</div>
			
		</section>
		
		
	
</body>
</html>	