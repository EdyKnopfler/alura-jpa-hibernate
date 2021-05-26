package br.com.pip.loja;

import javax.persistence.EntityManager;

import br.com.pip.loja.modelo.Categoria;
import br.com.pip.loja.util.JPAUtil;

public class CicloDeVidaDelete {

	public static void main(String[] args) {
		// Transient
		Categoria limpeza = new Categoria("Limpeza");
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		// Managed
		em.persist(limpeza);
		
		// Removed: gera um DELETE (a ser executado no flush ou commit)
		em.remove(limpeza);
		
		em.getTransaction().commit();
	}

}
