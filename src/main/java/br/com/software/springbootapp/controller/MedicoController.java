package br.com.software.springbootapp.controller;

import br.com.software.springbootapp.domain.MedicoEntity;
import br.com.software.springbootapp.enums.EnumTipoCR;
import br.com.software.springbootapp.exceptions.RegistroNaoEncontradoException;
import br.com.software.springbootapp.model.MedicoModel;
import br.com.software.springbootapp.model.MessageModel;
import br.com.software.springbootapp.service.MedicoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medico")
@Slf4j
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("/criar")
    public MedicoModel criarPessoa(@RequestBody MedicoModel model) {
        return new MedicoModel(medicoService.save(new MedicoEntity(model)));
    }

    @PutMapping("/alterar/{id}")
    public MedicoModel alterarPessoa(@PathVariable Long id, @RequestBody MedicoModel model) {

        var pessoa = medicoService.findById(id);

        pessoa.ifPresentOrElse(p -> p.atualiza(model), () -> {
            throw new RegistroNaoEncontradoException("Registro não encontrado");
        });

        return new MedicoModel(medicoService.save(pessoa.get()));
    }

    @DeleteMapping("/remover/{id}")
    public MessageModel removerPessoa(@PathVariable Long id) {

        var pessoa = medicoService.findById(id);

        pessoa.ifPresentOrElse(p -> medicoService.delete(p), () -> {
            throw new RegistroNaoEncontradoException("Registro não encontrado");
        });

        return new MessageModel("Removido com sucesso!");
    }

    @GetMapping("/lista-todos")
    public List<MedicoModel> listaTodos() {
        return medicoService.findAll().stream().map(MedicoModel::new).collect(Collectors.toList());
    }

    @GetMapping("/busca-medico-por-cpf/{cpf}")
    public MedicoModel buscaMedicoPorCpf(@PathVariable String cpf) {
        return medicoService.buscaMedicoPorCpf(cpf);
    }

    @GetMapping("/busca-medico-por-cr/{tipoCr}/{numeroCr}")
    public MedicoModel buscaMedicoPorCr(@PathVariable EnumTipoCR tipoCr, @PathVariable Integer numeroCr) {
        return medicoService.buscaMedicoPorCr(tipoCr, numeroCr);
    }

    @GetMapping("/lista-medicos-por-tipo-cr/{tipoCr}")
    public List<MedicoModel> listaMedicosPorTipoCr(@PathVariable EnumTipoCR tipoCr) {
        return medicoService.listaMedicosPorTipoCr(tipoCr);
    }

    @GetMapping("/lista-medicos-por-tele-medicina/{aceitaTeleMedicina}")
    public List<MedicoModel> listaMedicosPorTipoCr(@PathVariable Boolean aceitaTeleMedicina) {
        return medicoService.listaMedicosAtendentesTeleMedicina(aceitaTeleMedicina);
    }
}
