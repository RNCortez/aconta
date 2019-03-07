package com.program.aconta;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.program.aconta.domain.Categoria;
import com.program.aconta.domain.Cidade;
import com.program.aconta.domain.Usuario;
import com.program.aconta.domain.Endereco;
import com.program.aconta.domain.Estado;
import com.program.aconta.domain.Produto;
import com.program.aconta.domain.enums.TipoUsuario;
import com.program.aconta.repositories.CategoriaRepository;
import com.program.aconta.repositories.CidadeRepository;
import com.program.aconta.repositories.UsuarioRepository;
import com.program.aconta.repositories.EnderecoRepository;
import com.program.aconta.repositories.EstadoRepository;
import com.program.aconta.repositories.ProdutoRepository;

@SpringBootApplication
public class AcontaApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(AcontaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//teste categoria e produto
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
		
		//Teste Estado e cidade
		Estado est1 = new Estado(null,"São Paulo");
		Estado est2 = new Estado(null,"Rio de Jeneiro");
		
		Cidade cid1 = new Cidade(null,"Presidente Prudente",est1);
		Cidade cid2 = new Cidade(null,"Rio de Janeiro",est2);
		
		est1.getCidade().addAll(Arrays.asList(cid1));
		est2.getCidade().addAll(Arrays.asList(cid2));
		
		//Respeitar a dependencia, primeiro salvar o estado e depois salvar a cidade
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2));
		
		//Teste Usuario
		
		Usuario usu1 = new Usuario(null, "Rafael Cortez","rafael.cortez@hotmail.com",
				"38948876805","18988086086",TipoUsuario.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null, "Rua YosikoAkamine",
				"283", "B", "Vitoria Regia", "19025-566", usu1, cid1);
		
		Endereco e2 = new Endereco(null, "Rua Teste",
				"23", "C", "Lapa", "19000-000", usu1, cid2);
		
		usu1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		usuarioRepository.saveAll(Arrays.asList(usu1));	
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	
		
	}
	
	

}

