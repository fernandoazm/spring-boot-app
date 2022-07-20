package br.com.software.springbootapp.model;

import br.com.software.springbootapp.domain.ProdutoEntity;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class ProdutoModel {

    private Long id;

    @NotNull
    private String nome;

    private String descricao;

    @NotNull
    private BigDecimal codigoBarra;

    @NotNull
    private Integer estoque;

    public ProdutoModel(ProdutoEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
        this.codigoBarra = entity.getCodigoBarra();
        this.estoque = entity.getEstoque();
    }
}
