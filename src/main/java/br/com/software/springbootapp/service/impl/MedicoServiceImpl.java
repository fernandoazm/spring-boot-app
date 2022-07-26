package br.com.software.springbootapp.service.impl;

import br.com.software.springbootapp.domain.MedicoEntity;
import br.com.software.springbootapp.enums.EnumTipoCR;
import br.com.software.springbootapp.model.MedicoModel;
import br.com.software.springbootapp.repository.MedicoRepository;
import br.com.software.springbootapp.service.MedicoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class MedicoServiceImpl extends BaseServiceImpl<MedicoEntity, Long, MedicoRepository> implements MedicoService {

    @Override
    public MedicoModel buscaMedicoPorCpf(final String cpf) {
        return new MedicoModel(getRepository().findByCpf(cpf));
    }

    @Override
    public MedicoModel buscaMedicoPorCr(final EnumTipoCR tipoCr, final Integer numeroCr) {
        return new MedicoModel(getRepository().findByTipoCrAndNumeroCr(tipoCr, numeroCr));
    }

    @Override
    public List<MedicoModel> listaMedicosPorTipoCr(final EnumTipoCR tipoCr) {
        return getRepository().findAllByTipoCr(tipoCr).stream().map(MedicoModel::new).collect(Collectors.toList());
    }

    @Override
    public List<MedicoModel> listaMedicosAtendentesTeleMedicina(final Boolean atendeTeleMedicina) {
        return getRepository().findAllByAtendeTeleMedicina(atendeTeleMedicina).stream().map(MedicoModel::new).collect(Collectors.toList());
    }

}
