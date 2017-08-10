<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
 <head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 <title>Pesquisa de Produtos</title>
 <link rel="stylesheet" href="../css/bootstrap.min.css"></link>
  <link rel="stylesheet" href="../css/estilo-pesquisaproduto.css"></link>
  <script src="../js/jquery.min.js"></script>
 <script src="../js/bootstrap.min.js"></script>
 <script>
 	function voltar(){
 		location.href="../home.jsp";
 	}
 </script>
 </head>
<body>
	<header>
		<img src="../img/docevida.png" alt="Logo Doce Vida" id="logoDoceVida">
		<span id="produto">PESQUISA DE PRODUTOS</span>
	</header>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
		
					<form action="PesquisarProdutosServlet" method="get" id="pesquisaProduto" name="pesquisaProduto">
			               
						<label for="nmproduto" onclick="exibeOutros();" id="nmproduto"> Nome do Produto</label>
	
					 	 <input type="search" name="valor" id="valor" placeholder="Busca..." required><br>
			      		<button type="submit" onsubmit="exibeTabela();" class="btn btn-success" 
			      		value="Pesquisar" id="btnPesquisar">Pesquisar</button>
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
			      <label id="resultadoPesquisa">${resultadoPesquisa} produto(s) localizados</label>
			      <div class="table-responsive">
			      	<table class="table table-condensed table-hover" border="1" id="tabelaProdutos">
			      		<thead>
			      			<tr class="active">
			      				<th>Nome do Produto</th>
			      				<th>(R$) Valor Unitário</th>
			      				<th>Tamanho</th>
			      				
			      				<th>Quantidade</th>
			      				<th>Ação</th>
			      				<th>Ação</th>
			      			</tr>
			      		</thead>
			      		<tbody>
			      		<c:forEach var="produto" items="${listaProdutos}" >
			      			<tr>
			      				<td>${produto.nmProduto}</td>
			      				<td>${produto.valorUnitario}</td>
			      				<td>${produto.tamanho}</td>
			      				
			      				<td>${produto.quantidadeEstoque}</td>
			      				<td><a href="<c:url value="EditarProdutoServlet"/>?id=${produto.id}" 
						    		title="Produto ${produto.id}" >
						    		<button type="button" class="btn btn-success" value="Editar">Editar</button>
						    	</a></td>
						    	<td><a href="<c:url value="ExcluirProdutoServlet"/>?id=${produto.id}" 
						    		title="Produto ${produto.id}" >
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
		  	<button id="voltar" class="btn btn-danger btn-md" value="voltar" onclick="voltar();">Voltar ao Início</button>
		
			 
     
</body>
</html>