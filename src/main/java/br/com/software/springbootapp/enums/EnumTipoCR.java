package br.com.software.springbootapp.enums;

import br.com.software.springbootapp.helpper.PersistableEnum;
import lombok.Getter;

@Getter
public enum EnumTipoCR implements PersistableEnum<String> {

    BIOMEDICOS(1, "CRB", "Biomédicos"),
    FARMACEUTICOS(2, "CRF", "Farmaceuticos"),
    MEDICINA(3, "CRM", "Medicina"),
    VETERINAIA(4, "CRMV", "Veterinária"),
    NUTRICIONISTAS(5, "CRN", "Nutricionistas"),
    ODONTOLOGIA(6, "CRO", "Odontologia"),
    REGISTRO_MS(7, "RMS", "Registro MS");

    private final Integer codigo;
    private final String sigla;
    private final String nome;

    EnumTipoCR(final Integer codigo, final String sigla, final String nome) {
        this.codigo = codigo;
        this.sigla = sigla;
        this.nome = nome;
    }

    public static EnumTipoCR getBySigla(final String sigla) {
        for (EnumTipoCR value : values()) {
            if (value.getNome().equals(sigla)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String getValue() {
        return sigla;
    }

}
