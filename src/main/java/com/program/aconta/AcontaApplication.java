package com.program.aconta;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.program.aconta.domain.Categoria;
import com.program.aconta.domain.Cidade;
import com.program.aconta.domain.Endereco;
import com.program.aconta.domain.Estado;
import com.program.aconta.domain.ItemPedido;
import com.program.aconta.domain.Pagamento;
import com.program.aconta.domain.Pedido;
import com.program.aconta.domain.Produto;
import com.program.aconta.domain.Usuario;
import com.program.aconta.domain.enums.EstadoPagamento;
import com.program.aconta.domain.enums.TipoUsuario;
import com.program.aconta.repositories.CategoriaRepository;
import com.program.aconta.repositories.CidadeRepository;
import com.program.aconta.repositories.EnderecoRepository;
import com.program.aconta.repositories.EstadoRepository;
import com.program.aconta.repositories.ItemPedidoRepository;
import com.program.aconta.repositories.PagamentoRepository;
import com.program.aconta.repositories.PedidoRepository;
import com.program.aconta.repositories.ProdutoRepository;
import com.program.aconta.repositories.UsuarioRepository;

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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itempedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AcontaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//teste categoria e produto
		Categoria cat1 = new Categoria(null,"Bebidas");
		Categoria cat2 = new Categoria(null,"Lanches");
		Categoria cat3 = new Categoria(null,"Sobremesa");
		Categoria cat4 = new Categoria(null,"Elmachips");
		Categoria cat5 = new Categoria(null,"Doces");
		Categoria cat6 = new Categoria(null,"Sucos");
		Categoria cat7 = new Categoria(null,"Light");
		
		Produto p1 = new Produto(null,"x-burguer",13.00);
		Produto p2 = new Produto(null,"suco de laranja",5.00);
		Produto p3 = new Produto(null,"suco de limao",4.00);
		
		
		
		
		cat1.getProdutos().addAll(Arrays.asList(p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p1));
		
		p1.getCategorias().addAll(Arrays.asList(cat2));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
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
		
		//Teste Pedido
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null,sdf.parse("08/03/2019 08:40"),usu1);
		Pedido ped2= new Pedido(null,sdf.parse("10/03/2019 08:50"), usu1);
		
		Pagamento pag1 = new Pagamento(null, EstadoPagamento.PENDENTE, ped1);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new Pagamento(null, EstadoPagamento.QUITADO, ped2);
		ped2.setPagamento(pag2);
		
		usu1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		
		//Items de pedido teste
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 3, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itempedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}
	
	

}

