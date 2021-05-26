package br.com.pip.loja;

import javax.persistence.EntityManager;

import br.com.pip.loja.modelo.Categoria;
import br.com.pip.loja.util.JPAUtil;

public class CicloDeVidaUpdate {

	public static void main(String[] args) {
		// Transient
		Categoria brinquedos = new Categoria("Brinquedos");
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		// Managed
		em.persist(brinquedos);
		brinquedos.setNome("XPTO");  // Gera um UPDATE
		em.flush();  // executa o UPDATE
		
		// Detached
		em.clear();
		
		// brinquedos continua detached, novaRef está Managed
		// O merge faz um SELECT e recupera um novo
		Categoria novaRef = em.merge(brinquedos);
		
		// Gerando mais um UPDATE
		novaRef.setNome("Sex Shop");
		em.flush();
		
		em.getTransaction().commit();
		
		// Detached: deixou de ser gerenciada
		//em.clear();
		em.close();
	}

}
