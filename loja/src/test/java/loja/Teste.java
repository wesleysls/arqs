package loja;

import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;

import org.junit.*;

import br.unibh.loja.entidades.*;

public class Teste {

	@Test
	public void testeProduto() {
		Categoria automoveis = new Categoria();
		long id = 1;
		BigDecimal preco = new BigDecimal(15000);
		Produto p1 = new Produto(id, "carro", "celta", automoveis, preco, "GM");

		p1.toString();

		long id2 = 2;
		BigDecimal preco2 = new BigDecimal(20000);
		Produto p2 = new Produto(id, "carro", "prisma", automoveis, preco, "GM");

		p2.toString();
		assertFalse(p1.equals(p2));
	}
}
