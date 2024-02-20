package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.tutor.DadosAtualizarTutorDTO;
import br.com.alura.adopet.api.dto.tutor.DadosCadastroTutorDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public void cadastrar(DadosCadastroTutorDTO dto){
        boolean jaCadastrado = repository.existsByTelefoneOrEmail(dto.telefone(), dto.email());

        if (jaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        }

        repository.save(new Tutor(dto));
    }

    public void atualizar(DadosAtualizarTutorDTO dto){
        Tutor tutor = repository.getReferenceById(dto.id());
        tutor.atualizar(dto);
    }
}
