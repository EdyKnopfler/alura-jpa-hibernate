package br.com.pip.loja;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.pip.loja.dao.ProdutoDao;
import br.com.pip.loja.util.JPAUtil;

public class ConsultarPreco {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		BigDecimal preco = dao.buscarPreco("Samsumg Galaxy");
		System.out.println("Custa R$ " + preco);
		em.close();
	}

}
