package br.com.software.springbootapp.repository;

import br.com.software.springbootapp.domain.MedicoEntity;
import br.com.software.springbootapp.enums.EnumTipoCR;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends BaseRepository<MedicoEntity, Long> {

    MedicoEntity findByCpf(final String cpf);
    MedicoEntity findByTipoCrAndNumeroCr(final EnumTipoCR tipoCr, final Integer numeroCr);
    List<MedicoEntity> findAllByTipoCr(final EnumTipoCR tipoCr);
    List<MedicoEntity> findAllByAtendeTeleMedicina(final Boolean atendeTeleMedicina);

}
