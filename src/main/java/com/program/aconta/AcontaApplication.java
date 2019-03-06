package com.program.aconta;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.program.aconta.domain.Categoria;
import com.program.aconta.repositories.CategoriaRepository;

@SpringBootApplication
public class AcontaApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(AcontaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Bebidas");
		Categoria cat2 = new Categoria(null,"Lanches");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
	}
	
	

}

