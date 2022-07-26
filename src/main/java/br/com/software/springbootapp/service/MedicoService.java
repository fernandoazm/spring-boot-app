package br.com.software.springbootapp.service;

import br.com.software.springbootapp.domain.MedicoEntity;
import br.com.software.springbootapp.enums.EnumTipoCR;
import br.com.software.springbootapp.model.MedicoModel;

import java.util.List;

public interface MedicoService extends BaseService<MedicoEntity, Long>  {

    MedicoModel buscaMedicoPorCpf(final String cpf);
    MedicoModel buscaMedicoPorCr(final EnumTipoCR tipoCr, final Integer numeroCr);
    List<MedicoModel> listaMedicosPorTipoCr(final EnumTipoCR tipoCr);
    List<MedicoModel> listaMedicosAtendentesTeleMedicina(final Boolean atendeTeleMedicina);

}
