package br.com.pip.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.pip.loja.modelo.Produto;

public class ProdutoDao {
	
	EntityManager entityManager;

	public ProdutoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Produto produto) {
		this.entityManager.persist(produto);
	}
	
	public void atualizar(Produto produto) {
		// Garantir que vai ter uma Managed com estes dados
		// (somente para o caso de ter sido detached)
		// Os atributos já estão alterados :)
		this.entityManager.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = entityManager.merge(produto);
		entityManager.remove(produto);
	}

	public Produto buscarPorId(Long id) {
		return entityManager.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos() {
		// Usa o nome da entidade
		String jpql = "SELECT p FROM Produto p";
		
		// Somente a query acima gera N+1! Como resolver:
		// https://vladmihalcea.com/n-plus-1-query-problem/
		jpql += " JOIN FETCH p.categoria c";
		
		return entityManager.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p " +
					  "JOIN FETCH p.categoria c " +
					  "WHERE p.nome = :nome";  // Nome do atributo!
		return entityManager.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> buscarPorNomeDaCategoria(String nome) {
		// Consulta orientada a objeto :)
		String jpql = "SELECT p FROM Produto p " +
					  "JOIN FETCH p.categoria c " +
					  "WHERE p.categoria.nome = :nome";
		return entityManager.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public BigDecimal buscarPreco(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return entityManager.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}

}
