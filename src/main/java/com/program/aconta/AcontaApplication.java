package com.program.aconta;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.program.aconta.domain.Categoria;
import com.program.aconta.domain.Produto;
import com.program.aconta.repositories.CategoriaRepository;
import com.program.aconta.repositories.ProdutoRepository;

@SpringBootApplication
public class AcontaApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AcontaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Bebidas");
		Categoria cat2 = new Categoria(null,"Lanches");
		
		Produto p1 = new Produto(null,"x-burguer",13.00);
		Produto p2 = new Produto(null,"suco de laranja",5.00);
		Produto p3 = new Produto(null,"suco de limao",4.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p1));
		
		p1.getCategorias().addAll(Arrays.asList(cat2));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat1));
	
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
	}
	
	

}

