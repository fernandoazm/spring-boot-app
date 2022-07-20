package br.com.software.springbootapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import br.com.software.springbootapp.domain.ProdutoEntity;
import br.com.software.springbootapp.exceptions.RegistroNaoEncontradoException;
import br.com.software.springbootapp.helpper.Utils;
import br.com.software.springbootapp.model.ProdutoModel;
import br.com.software.springbootapp.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class ProdutoTests {

    @Autowired
    private ProdutoService produtoService;

    @Test
    @DisplayName("Testa cadastro de 5 produtos por SQL")
    @Order(1)
    @Sql({"classpath:produtos.sql"})
    public void cadastroCincoProdutosPorSQLTest() {
    	var lista = produtoService.findAll();
    	
    	log.info("------------ IMPRIME PRODUTOS ---------");
    	lista.forEach(p -> log.info(p.toString()));
    	
    	int quantidade = lista.size();
    	log.info(String.format("Quantidade de registros: %s", quantidade));
    	assertEquals(5, quantidade);
    }
    

    @Test
    @DisplayName("Testa cadastro de 2 produtos por Repository")
    @Order(2)
    public void cadastroDoisProdutosPorRepositoryTest() {
    	
    	ProdutoModel produto1 = ProdutoModel
    			.builder()
    			.codigoBarra(BigDecimal.valueOf(7892840813015L))
    			.nome("Produto 15")
    			.descricao("Descrição 15")
    			.estoque(15)
    			.build();
    	
    	ProdutoModel produto2 = ProdutoModel
    			.builder()
    			.codigoBarra(BigDecimal.valueOf(7892840813016L))
    			.nome("Produto 16")
    			.descricao("Descrição 16")
    			.estoque(16)
    			.build();    	
    	
    	produtoService.save(new ProdutoEntity(produto1));
    	produtoService.save(new ProdutoEntity(produto2));
    	
    	List<ProdutoEntity> lista = produtoService.findAll();
    	
    	log.info("------------ IMPRIME PRODUTOS ---------");
    	lista.forEach(p -> log.info(p.toString()));
    	
    	int quantidade = lista.size();
    	log.info(String.format("Quantidade de registros: %s", quantidade));
    	assertEquals(7, quantidade);
    }
    
    @Test
    @DisplayName("Testa alteração de 1 produto")
    @Order(3)
    public void alteracaoDeUmProdutoTest() {
    	
    	List<ProdutoEntity> lista = produtoService.findAll();
    	
    	ProdutoEntity produtoSelecionado = lista.stream()
                .sorted((o1, o2) -> ThreadLocalRandom.current().nextInt(-1, 2))
                .findAny()
                .get();    	

    	log.info("##### produtoSelecionado ##### " + produtoSelecionado.toString());
    	
    	ProdutoEntity produtoClonado = Utils.cloneObject(produtoSelecionado);
    	
    	ProdutoModel modelAlteracao = ProdutoModel
    			.builder()
    			.id(produtoSelecionado.getId())
    			.codigoBarra(produtoSelecionado.getCodigoBarra())
    			.nome("Produto Alterado")
    			.descricao("Descrição Alterada")
    			.estoque(produtoSelecionado.getEstoque())
    			.build();
    	
    	produtoSelecionado.atualiza(modelAlteracao);
    	produtoService.save(produtoSelecionado);
    	
    	ProdutoEntity produtoAlterado = produtoService.findById(produtoClonado.getId()).get();
    	log.info("##### produtoAlterado ##### " + produtoAlterado.toString());
    	
    	assertFalse(produtoClonado.getNome().equals(produtoAlterado.getNome()));
    	assertFalse(produtoClonado.getDescricao().equals(produtoAlterado.getDescricao()));
    }    
    
    @Test
    @DisplayName("Exclusão de 1 produto")
    @Order(4)
    public void exclusaoDeUmProdutoTest() {
    	
    	List<ProdutoEntity> lista = produtoService.findAll();
    	
    	ProdutoEntity produtoSelecionado = lista.stream()
                .sorted((o1, o2) -> ThreadLocalRandom.current().nextInt(-1, 2))
                .findAny()
                .get();    	

    	long idExcluido = produtoSelecionado.getId();
    	
    	produtoService.delete(produtoSelecionado);
    	
    	assertThrows(RegistroNaoEncontradoException.class, () -> {
    		produtoService.findByIdNotNull(idExcluido);
        });
    	
    	assertTrue(produtoService.findAll().size() == 6);
    }
    
    
    @Test
    @DisplayName("Exclusão de todos os produtos")
    @Order(5)
    public void exclusaoDeTodosOsProdutosTest() {
    	
    	List<ProdutoEntity> lista = produtoService.findAll();
    	
    	produtoService.deleteAll(lista);
   	
    	assertTrue(produtoService.findAll().size() == 0);
    }    
}
