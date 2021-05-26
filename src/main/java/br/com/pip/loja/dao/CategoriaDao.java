package br.com.pip.loja.dao;

import javax.persistence.EntityManager;

import br.com.pip.loja.modelo.Categoria;

public class CategoriaDao {
	
	EntityManager entityManager;

	public CategoriaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Categoria categoria) {
		this.entityManager.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		// Garantir que vai ter uma Managed com estes dados
		// (somente para o caso de ter sido detached)
		// Os atributos já estão alterados :)
		this.entityManager.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = entityManager.merge(categoria);
		entityManager.remove(categoria);
	}

}
