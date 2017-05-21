<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
 <head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <title>Edição de Clientes</title>
 <link rel="stylesheet" href="css/bootstrap.min.css"></link>
  <link rel="stylesheet" href="css/estilo-editarcliente.css"></link>
  <script src="js/jquery.min.js"></script>
 <script src="js/bootstrap.min.js"></script>
 <script src="js/controllereditarcliente.js"></script>
</head>
<body>
	<header>
		<img src="img/docevida.png" alt="Logo Doce Vida" id="logoDoceVida">
		<span id="cliente">EDIÇÃO DE CLIENTES</span>
	</header>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
				
					<form action="EditarClienteServlet" onsubmit="validar();" method="post" name="cliente">
						
			
						(*) Campos Obrigatórios<br><br>
			
						<div class="form-group">
							<label for="id" class="col-xs-2 control-label">*ID: </label>
							<div class="col-xs-12">
								<input type="text" id="id" name="id" class="form-control" value="${cliente.id}" disabled="disabled"/> 
							</div>
						</div>	

						<div class="form-group">
							<label for="nome" class="col-xs-2 control-label">*Nome Completo: </label>
							<div class="col-xs-12">
								<input type="text" id="nome" name="nome" class="form-control" placeholder="Nome"value="${cliente.nome}"  required/> 
							</div>
						</div>			
						
						
						<div class="form-group">
							<label for="cpf" class="col-xs-12 control-label">*CPF: </label>
							<div class="col-xs-12">
								<input type="text" name="cpf" id="cpf" class="form-control" size="11" placeholder="CPF" value="${cliente.cpf}" required/>
							</div>
						</div>
						
						<div class="form-group">
							<label for="endereco" class="col-xs-12 control-label">*Endereço: </label>
							<div class="col-xs-12">
								<input type="text" id="endereco" name="endereco" size="20" class="form-control" value="${cliente.endereco}"placeholder="Endereço" required />
							</div>
						</div>

						<div class="form-group">
							<label for="num" class="col-xs-12 control-label">*Número: </label>
							<div class="col-xs-12">
								<input type="number" name="numero" id="numero" min="1" size="5"  value="${cliente.numero}"class="form-control"required/> 
							</div>
						</div>

						<div class="form-group">		
							<label for="bairro" class="col-xs-12 control-label">*Bairro: </label>
							<div class="col-xs-12">
								<input type="text" name="bairro" id="bairro" size="30" placeholder="Bairro" value="${cliente.bairro}"class="form-control" required/>
							</div>
						</div>

						<div class="form-group">
							<label for="cidade" class="col-xs-12 control-label">*Cidade: </label>
							<div class="col-xs-12">
								<input type="text" name="cidade" id="cidade" size="20" class="form-control" value="${cliente.cidade}"placeholder="Cidade" required/>
							</div>
						</div>


						<div class="form-group">
							<label for="estado" class="col-xs-12 control-label">*Estado: </label>
							<div class="col-xs-12">
								<input type="text" name="estado" id="estado" size="30" class="form-control" value="${cliente.estado}"placeholder="Estado" required/> 
							</div>
						</div>

						<div class="form-group">
							<label for="telefone" class="col-xs-12 control-label">Telefone: </label>
							<div class="col-xs-12">
								<input type="text" name="telefone" id="telefone" size="20" class="form-control" value="${cliente.telefone}" placeholder="Telefone" required/>
							</div>
						</div>

						<div class="form-group">
							<label for="celular" class="col-xs-12 control-label">*Celular: </label>
							<div class="col-xs-12">
								<input type="text" name="celular" id="celular" size="20" class="form-control" value="${cliente.celular}" placeholder="Celular" required/>
							</div>
						</div>

						<div class="form-group">
							<label for="cEmail" class="col-xs-12 control-label">E-mail: </label>
							<div class="col-xs-12">
								<input type="email" name="email" id="email" size="25" class="form-control" value="${cliente.email}" placeholder="Email"/>
							</div>
						</div>
							
				
						<button type="submit" value="Salvar" onsubmit="enviardados();" class="btn btn-success btn-lg">Salvar</button>
						
						<button type="button" value="Cancelar" class="btn btn-danger btn-lg" onclick="voltar();">Cancelar</button>
					
					</form>
					
						</div>
					</div>
				</div>
		</section>
		<div id="imgFooter">
			<img src="img/reload.png" alt="Logo Reload" id="logoReload">
		</div>
	<footer>
		© 2016 | RELOAD – Todos os direitos reservados
		
		
	</footer>
	
</body>
</html>