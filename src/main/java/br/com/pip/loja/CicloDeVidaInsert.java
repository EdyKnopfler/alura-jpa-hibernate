package br.com.pip.loja;

import javax.persistence.EntityManager;

import br.com.pip.loja.modelo.Categoria;
import br.com.pip.loja.util.JPAUtil;

public class CicloDeVidaInsert {

	public static void main(String[] args) {
		// Transient: não sendo gerenciada pela JPA
		Categoria eletrodomesticos = new Categoria("Eletrodomésticos");
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		// Managed: JPA observa se mexer na entidade
		em.persist(eletrodomesticos);
		
		
		// Já executa o INSERT (sincroniza com o BD sem finalizar transação) 
		//em.flush();
		
		// Isto vai gerar um UPDATE
		eletrodomesticos.setNome("Maquinaria");
		
		em.getTransaction().commit();
		
		// Detached: deixou de ser gerenciada
		//em.clear();
		em.close();
	}

}
