package br.com.pip.loja;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pip.loja.dao.ProdutoDao;
import br.com.pip.loja.modelo.Produto;
import br.com.pip.loja.util.JPAUtil;

public class ConsultarPorNomeDaCategoria {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		List<Produto> produtos = dao.buscarPorNomeDaCategoria("Celulares");
		
		for (Produto produto : produtos) {
			System.out.println(produto.getNome());
			System.out.println(produto.getPreco());
			System.out.println(produto.getDescricao());
			System.out.println(produto.getDataCadastro());
			System.out.println(produto.getProcedencia());
			System.out.println(produto.getCategoria().getNome());
			System.out.println();
		}
		
		
		em.close();
	}

}
