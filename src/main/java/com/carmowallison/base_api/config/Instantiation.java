package com.carmowallison.base_api.config;

import java.util.Arrays;

import com.carmowallison.base_api.domain.Linguagem;
import com.carmowallison.base_api.domain.Pagina;
import com.carmowallison.base_api.domain.Traducao;
import com.carmowallison.base_api.domain.enums.Perfil;
import com.carmowallison.base_api.repositoties.LinguagemRepository;
import com.carmowallison.base_api.repositoties.PaginaRepository;
import com.carmowallison.base_api.repositoties.TraducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.carmowallison.base_api.domain.User;
import com.carmowallison.base_api.repositoties.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LinguagemRepository linguagemRepository;
	@Autowired
	private PaginaRepository paginaRepository;
	@Autowired
	private TraducaoRepository traducaoRepository;

	@Autowired
	private BCryptPasswordEncoder bc;

	@Override
	public void run(String... args) throws Exception {

		// EXCLUIR TODA A BASE
		userRepository.deleteAll();

		// EXCLUIR TODA A BASE
		linguagemRepository.deleteAll();
//
//		// EXCLUIR TODA A BASE
		paginaRepository.deleteAll();
//
//		// EXCLUIR TODA A BASE
		traducaoRepository.deleteAll();


		System.out.println("======================================================");
		System.out.println("CRIA NOVA BASE");

		// BLOCO USUARIOS
		User wallison = new User(null, "Wallison do Carmo Costa", "admin@email.com", true, bc.encode("123"));
		wallison.addPerfil(Perfil.ADMIN);

		User yasmin = new User(null, "yasmin", "yasmin@gmail.com", false, bc.encode("123"));
		User lucy = new User(null, "lucy", "lucy@gmail.com", true, bc.encode("123"));

		System.out.println("Usuários basicos");

		// BLOCO LINGUAGEM
		Linguagem pt = new Linguagem(null,"PT");
		Linguagem en = new Linguagem(null,"EN");
		Linguagem es = new Linguagem(null,"ES");
		Linguagem fr = new Linguagem(null,"FR");

		System.out.println("Linguagens [PT] [EN] [ES] [FR]");

		// BLOCO TRADUÇÕES
		Traducao bemVindo = new Traducao(null,"BEM_VINDO","perfil",pt);
		Traducao nome = new Traducao(null,"NOME","nome",pt);
		Traducao sobrenome = new Traducao(null,"SOBRENOME","sobrenome",pt);
		Traducao email = new Traducao(null,"EMAIL","e-mail",pt);
		Traducao senha = new Traducao(null,"SENHA","senha",pt);
		Traducao salvar = new Traducao(null,"SALVAR","salvar",pt);
		Traducao voltar = new Traducao(null,"VOLTAR","voltar",pt);
		Traducao perfil = new Traducao(null,"PERFIL","perfil",pt);
		System.out.println("Traduções [BEM_VINDO] [NOME] [SOBRENOME] [SENHA] [SALVAR] [VOLTAR] [PERFIL]");

		// BLOCO PÁGINAS
		Pagina home = new Pagina(null,"HOME",Arrays.asList(bemVindo));
		Pagina user = new Pagina(null,"USER",Arrays.asList(nome,sobrenome,email,senha,salvar,voltar,perfil));

		userRepository.saveAll(Arrays.asList(wallison, yasmin, lucy));
		linguagemRepository.saveAll(Arrays.asList(pt,en,es,fr));
		traducaoRepository.saveAll(Arrays.asList(bemVindo,nome,sobrenome,email,senha,salvar,voltar,perfil));
		paginaRepository.saveAll(Arrays.asList(home,user));

		System.out.println("Páginas [HOME] [USER]");
		System.out.println("======================================================");
	}

}
