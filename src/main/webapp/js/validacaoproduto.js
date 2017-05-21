function enviardados(){
 
if(document.produto.nome.value=="" || document.produto.tx_nome.value.length < 3)
{
alert( "Preencha campo NOME DO PRODUTO corretamente!" );
document.produto.tx_nome.focus();
return false;
}

//<input name="tx_nome" type="text" class="formbutton" id="tx_nome" size="52" maxlength="150">

//<input type="text" name="valorunit" id="valorunit" style="width: 90%; height:35px"  
if( document.produto.valorunit.value=="")
{
alert( "Preencha campo VALOR UNITÁRIO corretamente!" );
document.produto.valorunit.focus();
return false;
}
 
if (document.produto.quant.value=="")
{
alert( "Preencha o campo QUANTIDADE!" );
document.produto.quant.focus();
return false;
}
 
if (document.produto.tam.value=="")
{
alert( "Preencha o campo tam!" );
document.produto.tam.focus();
return false;
}

alert("Produto salvo com sucesso");
return true;
}