/**
 * 
 */
function mensagem(){
	alert("Produto alterado com sucesso");
}
function voltar(){
	window.history.go(-1);
}
function cadastrarProduto () {
	// body...
	location.href="cadastro_produtos.html";
}
function pesquisarProduto () {
	// body...
	location.href="pesquisar-produtos.jsp";
}
function cadastraCliente () {
	// body...
	location.href="cadastro_clientes.html";
}
function pesquisarCliente () {
	// body...
	location.href="pesquisar-clientes.jsp";
}
function efetuarPedido(){
	location.href="principal-pedido.html";
}
function sair() {
	location.href="index.html";
	
}
