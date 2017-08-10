window.onload = function(){
	document.getElementById("valor").style.display="none";
	document.getElementById("btnPesquisar").style.display="none";
	
}

function exibeOutros(){
	if(document.getElementById("nome").value="Nome"){
		document.getElementById("valor").style.display="inline"
		document.getElementById("btnPesquisar").style.display="inline";
	}else if(document.getElementById("endereco").value="Endereço"){
		document.getElementById("valor").style.display="inline"
			document.getElementById("btnPesquisar").style.display="inline";
	}
}

function exibeTabela(){
	document.getElementById("tabelaClientes").style.display="block";
}
function voltar(){
		location.href="../home.jsp";
	}
