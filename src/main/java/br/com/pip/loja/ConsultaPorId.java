package br.com.pip.loja;

import javax.persistence.EntityManager;

import br.com.pip.loja.dao.ProdutoDao;
import br.com.pip.loja.modelo.Produto;
import br.com.pip.loja.util.JPAUtil;

public class ConsultaPorId {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		Long id = 15l;
		ProdutoDao dao = new ProdutoDao(em);
		Produto produto = dao.buscarPorId(id);
		
		System.out.println(produto.getNome());
		System.out.println(produto.getPreco());
		System.out.println(produto.getDescricao());
		System.out.println(produto.getDataCadastro());
		System.out.println(produto.getProcedencia());
		System.out.println(produto.getCategoria().getNome());
		
		em.close();
	}

}
