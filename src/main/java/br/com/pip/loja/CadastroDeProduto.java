package br.com.pip.loja;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.pip.loja.dao.CategoriaDao;
import br.com.pip.loja.dao.ProdutoDao;
import br.com.pip.loja.modelo.Categoria;
import br.com.pip.loja.modelo.Procedencia;
import br.com.pip.loja.modelo.Produto;
import br.com.pip.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("Celulares");
		
		Produto celular = new Produto();
		celular.setNome("Xiaomi");
		celular.setDescricao("Xing-Ling");
		celular.setPreco(new BigDecimal(800.0));
		celular.setProcedencia(Procedencia.IMPORTADO);
		celular.setCategoria(celulares);
		
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();
		
		em.close();
	}

}
