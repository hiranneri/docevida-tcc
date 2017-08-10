package br.com.doceVida.testes;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.doceVida.model.Item;
import br.com.doceVida.model.Pedido;
import br.com.doceVida.model.Produto;
import org.junit.Assert;

public class PedidoTest {

	Pedido pedido;
	Item itemUm;
	Item itemDois;
	@Before
	public void setUp() throws Exception {
		pedido = new Pedido();
		itemUm= new Item();
		itemDois = new Item();
	}

	@Test
	public void testCalcularValorTotal() {
		
		//Cenário
		
		BigDecimal valorUnitario = new BigDecimal("3.00");
		BigDecimal valorUnitarioDois = new BigDecimal("4.00");
		
		itemUm.setProduto(new Produto("Bolo de Cereja", "Pequeno", valorUnitario));
		itemDois.setProduto(new Produto("Bolo de Laranja","Pequeno",valorUnitarioDois));
		//Ação
		pedido.getItensDoPedido().add(itemUm);
		pedido.getItensDoPedido().add(itemDois);
		
		//Validação
		BigDecimal valorTotal = new BigDecimal("7.00");
		Assert.assertEquals(valorTotal, pedido.calcularValorTotal());
	}

	

}
