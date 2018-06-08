package br.unibh.loja.negocio;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.unibh.loja.entidades.Cliente;

@Stateless
@LocalBean
public class ServicoCliente implements DAO<Cliente, Long> {
	
	@Inject
	EntityManager em;
	
	@Inject
	private Logger log;

	public Cliente insert(Cliente t) throws Exception {
		log.info("Persistindo " + t);
		
		DateTime hoje = new DateTime();
		DateTime cadastro = new DateTime(t.getDataCadastro());
		int dias = Days.daysBetween(cadastro, hoje).getDays();

		if (t.getPerfil().equals("Standard") && dias < 365) {
			em.persist(t);
		} else {
			throw new Exception("o cliente precisa ser criado com o perfil Standard");
		}
		return t;
	}

	public Cliente update(Cliente t) throws Exception {
		log.info("Atualizando " + t);

		DateTime hoje = new DateTime();
		DateTime cadastro = new DateTime(t.getDataCadastro());
		int dias = Days.daysBetween(cadastro, hoje).getDays();

		if (t.getPerfil().equals("Standard") && dias < 365) {
			em.merge(t);
		} else if ((t.getPerfil().equals("Standard")||t.getPerfil().equals("Premium")) && (dias >= 365 &&  dias <= 5 * 365)) {
			em.merge(t);
		} else if ((t.getPerfil().equals("Standard")||t.getPerfil().equals("Standard")||t.getPerfil().equals("Gold") ) && (dias > 5 * 365)) {
			em.merge(t);
		} else {
			throw new Exception("sdfsdfsd");
		}
		return t;
	}

	public void delete(Cliente t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	public Cliente find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Cliente.class, k);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Cliente").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findByName(String name) throws Exception {
		log.info("Encontrando o " + name);
		return em.createNamedQuery("Cliente.findByName").setParameter("nome", "%" + name + "%").getResultList();
	}
}
