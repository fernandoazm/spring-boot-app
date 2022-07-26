package br.com.software.springbootapp.model;

import br.com.software.springbootapp.domain.MedicoEntity;
import br.com.software.springbootapp.enums.EnumSexo;
import br.com.software.springbootapp.enums.EnumTipoCR;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class MedicoModel {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    private EnumSexo sexo;

    @NotNull
    private EnumTipoCR tipoCr;

    @NotNull
    private Integer numeroCr;

    @NotNull
    private String email;

    @NotNull
    private Boolean atendeTeleMedicina;

    public MedicoModel(MedicoEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.dataNascimento = entity.getDataNascimento();
        this.sexo = entity.getSexo();
        this.tipoCr = entity.getTipoCr();
        this.numeroCr = entity.getNumeroCr();
        this.email = entity.getEmail();
        this.atendeTeleMedicina = entity.getAtendeTeleMedicina();
    }
}
