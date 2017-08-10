<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
 <head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <title>Pesquisa de Clientes</title>
 <link rel="stylesheet" href="../css/bootstrap.min.css"></link>
  <link rel="stylesheet" href="../css/estilo-pesquisacliente.css"></link>
  <script src="../js/jquery.min.js"></script>
 <script src="../js/bootstrap.min.js"></script>
  <script src="../js/controllerpesquisaCliente.js"></script>
 </head>
<body>
	<header>
		<img src="../img/docevida.png" alt="Logo Doce Vida" id="logoDoceVida">
		<span id="cliente">PESQUISA DE CLIENTES</span>
	</header>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
		
					<form action="PesquisarClienteServlet" method="get" id="pesquisaCliente" name="pesquisaCliente">
			                  <span id="pesquisa">Pesquisar Por:</span> 
						<input type="radio" id="nome" name="paramPesquisa" value="Nome" onclick="exibeOutros();">
						<label for="nome" onclick="exibeOutros();" id="nome">Nome</label>
						
						<input type="radio" id="endereco" name="paramPesquisa" value="Endereço" onclick="exibeOutros();">
						<label for="endereco" onclick="exibeOutros();" id="endereco">Endereço<br></label><br>
					 	
					 	 <input type="search" name="valor" id="valor" placeholder="Busca..." required>
			      		<button type="submit" onsubmit="exibeTabela();" class="btn btn-success" value="Pesquisar" id="btnPesquisar">Pesquisar</button>
			      	</form>
			      	
			      </div>
			  </div>
		</div>
      </section>
      
      	<br>
      
      <div class="container">
			<div class="row">
				<div class="col-xs-12">
				
			      <section>
			     
			      	<label id="resultadoPesquisa">${resultadoPesquisa} cliente(s) localizados</label>
			    
			      <div class="table-responsive">
			      	<table class="table table-condensed table-hover" border="1" id="tabelaClientes">
			      		<thead>
			      			<tr class="active">
			      				<th>Nome</th>
			      				<th>Endereço</th>
			      				<th>Cidade</th>
			      				
			      				<th>Celular</th>
			      				<th>Ação</th>
			      				<th>Ação</th>
			      			</tr>
			      		</thead>
			      		<tbody>
			      		<c:forEach var="cliente" items="${listaClientes}" >
			      			<tr>
			      				<td>${cliente.nome}</td>
			      				<td>${cliente.endereco}</td>
			      				<td>${cliente.cidade}</td>
			      				
			      				<td>${cliente.celular}</td>
			      				<td><a href="<c:url value="EditarClienteServlet"/>?id=${cliente.id}" 
						    		title="Cliente ${cliente.id}" >
						    		<button type="button" class="btn btn-success" value="Editar">Editar</button>
						    	</a></td>
						    	<td><a href="<c:url value="ExcluirClienteServlet"/>?id=${cliente.id}" 
						    		title="Cliente ${cliente.id}" >
						    		<button type="button" class="btn btn-danger" onclick="return confirm('Deseja realmente excluir ?')" value="Excluir">Excluir</button>
						    	</a></td>
			      				
			      			</tr>
			      		</c:forEach>
			      			
			      		</tbody>
			      		
			      	</table>
			      	</div>
			      </section>
			      </div>
			    </div>
			 </div>
			 	
			<button id="voltar" class="btn btn-danger btn-lg" value="voltar" onclick="voltar();">Voltar ao Início</button>
			 
		 
     
</body>
</html>