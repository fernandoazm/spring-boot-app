package br.com.software.springbootapp.domain;

import br.com.software.springbootapp.enums.EnumSexo;
import br.com.software.springbootapp.enums.EnumTipoCR;
import br.com.software.springbootapp.model.MedicoModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
@ToString
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"tipo_cr", "numero_cr"})
}, name = "medico")
public class MedicoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", length = 150)
    private String nome;

    @NotNull
    @Column(name = "cpf", length = 14, unique = true)
    private String cpf;

    @NotNull
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @NotNull
    @Type(type = "enum")
    @Column(name = "sexo", length = 1)
    private EnumSexo sexo;

    @NotNull
    @Type(type = "enum")
    @Column(name = "tipo_cr", length = 4)
    private EnumTipoCR tipoCr;

    @NotNull
    @Column(name = "numero_cr")
    private Integer numeroCr;

    @NotNull
    @Column(name = "email", length = 200)
    private String email;

    @NotNull
    @Column(name = "tele_medicina")
    private Boolean atendeTeleMedicina;

    public MedicoEntity(MedicoModel model) {
        super();
        this.nome = model.getNome();
        this.cpf = model.getCpf();
        this.dataNascimento = model.getDataNascimento();
        this.sexo = model.getSexo();
        this.tipoCr = model.getTipoCr();
        this.numeroCr = model.getNumeroCr();
        this.email = model.getEmail();
        this.atendeTeleMedicina = model.getAtendeTeleMedicina();
    }

    public void atualiza(MedicoModel model) {
        this.nome = model.getNome();
        this.cpf = model.getCpf();
        this.dataNascimento = model.getDataNascimento();
        this.sexo = model.getSexo();
        this.tipoCr = model.getTipoCr();
        this.numeroCr = model.getNumeroCr();
        this.email = model.getEmail();
        this.atendeTeleMedicina = model.getAtendeTeleMedicina();
    }
}
