<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
 <head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <title>Edição de Produtos</title>
 <link rel="stylesheet" href="css/bootstrap.min.css"></link>
  <link rel="stylesheet" href="css/estilo-editarproduto.css"></link>
  <script src="js/jquery.min.js"></script>
 <script src="js/bootstrap.min.js"></script>
 <script src="js/controlleralterarProduto.js"></script>
</head>
<body>
	<header>
		<img src="img/docevida.png" alt="Logo Doce Vida" id="logoDoceVida">
		<br>
		<br>
		<span id="cliente">EDIÇÃO DE PRODUTOS</span>
	</header>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
				
					<form action="EditarProdutoServlet" onsubmit="mensagem();" method="post" name="produto">
						
			
						(*) Campos Obrigatórios<br><br>
			
						<div class="form-group">
							<label for="id" class="col-xs-2 control-label labels" >*ID: </label>
							<div class="col-xs-12">
								<input type="text" id="id" name="id" class="form-control" value="${produto.id}" disabled="disabled"/> 
							</div>
						</div>	

						<div class="form-group">
							<label for="nmproduto" class="col-xs-2 control-label labels">*Nome do Produto: </label>
							<div class="col-xs-12">
								<input type="text" id="nmproduto" name="nmproduto" class="form-control" placeholder="Nome do produto"
									value="${produto.nmProduto}"  required/> 
							</div>
						</div>			
						
						
						<div class="form-group">
							<label for="vlunit" class="col-xs-12 control-label labels">*Valor Unitário: </label>
							<div class="col-xs-12">
								<input type="text" name="vlunit" id="vlunit" class="form-control" size="11" placeholder="R$" 
									value="${produto.valorUnitario}" required/>
							</div>
						</div>
						
						<div class="form-group">
							<label for="tam" class="col-xs-12 control-label labels">*Tamanho: </label>
							<div class="col-xs-12">
								 <select class="form-control" name="tam" value="${produto.tamanho}">
									<option value="Pequeno">Pequeno</option>
									<option value="Médio">Médio</option>
									<option value="Grande">Grande</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="quant" class="col-xs-12 control-label labels">*Quantidade: </label>
							<div class="col-xs-12">
								<input type="number" name="quant" id="quant" min="1" size="5"  value="${produto.quantidadeEstoque}"
									class="form-control" required/> 
							</div>
						</div>

						<div class="form-group">
						    <label for="obs" class="col-xs-12 control-label labels">Observação</label>
						    <div class="col-xs-12">
						    <% String textAreaValue = (String) session.getAttribute("observacao"); %>
						    <textarea name="obs" id="obs" cols="5" rows="5" class="form-control"><%=textAreaValue%></textarea>
						    </div>
						</div>							
				
						<button type="submit" value="Salvar" onsubmit="enviardados();" class="btn btn-success btn-lg">Salvar</button>
						
						<button type="button" value="Cancelar" class="btn btn-danger btn-lg" onclick="voltar();">Cancelar</button>
						
					</form>
					
						</div>
					</div>
				</div>
		</section>
		
	
</body>
</html>