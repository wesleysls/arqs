package loja;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.entidades.Cliente;
import br.unibh.loja.entidades.Produto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestValidation {
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		System.out.println("Inicializando validador...");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testeValidacaoCliente1() {
		long l = 1;
		Cliente o = new Cliente(l, "wesley", "wesleysls", "12345", "perfil", "103.523.906-03", "(99)9999-9999",
				"wesley.sls21@gmail.com", new Date(), new Date());
		System.out.println(o);
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Cliente> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoCliente2() {
		long l = 1;
		Cliente o = new Cliente(l, "wesley", "wesleysls", "12345", "perfil", "1234567897@4", "(99)9999-9999",
				"wesley.sls21@gmail.com", new Date(), new Date());
		System.out.println(o);
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Cliente> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}
	
	@Test
	public void testeValidacaoCategoria1() {
		long l = 1;
		Categoria c1 = new Categoria(l, "categoria");
		System.out.println(c1);
		Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate(c1);
		for (ConstraintViolation<Categoria> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}
	
	@Test
	public void testeValidacaoCategoria2() {
		long l = 1;
		Categoria c1 = new Categoria(l, "");
		System.out.println(c1);
		Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate(c1);
		for (ConstraintViolation<Categoria> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}
	
	@Test
	public void testeValidacaoProduto1() {
		long l = 1;
		BigDecimal b1 = new BigDecimal(10000);
		Categoria c1 = new Categoria(l, "categoria1");
		Produto p1 = new Produto(l, "carro", "gol", c1, b1, "vw");
		System.out.println(p1);
		Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(p1);
		for (ConstraintViolation<Produto> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}
	
	@Test
	public void testeValidacaoProduto2() {
		long l = 1;
		BigDecimal b1 = new BigDecimal(10000);
		Categoria c1 = new Categoria(l, "categoria1");
		Produto p1 = new Produto(l, "carro", "", c1, b1, "vw");
		System.out.println(p1);
		Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(p1);
		for (ConstraintViolation<Produto> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

}
