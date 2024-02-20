package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.AbrigoDTO;
import br.com.alura.adopet.api.dto.abrigo.CadastroAbrigoDTO;
import br.com.alura.adopet.api.dto.pet.PetDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private PetRepository petRepository;

    public void cadastrar(CadastroAbrigoDTO dto){
        boolean nomeOuTelefoneOuEmailJaCadastrados = repository.existsByNomeOrEmailOrTelefone(dto.nome(), dto.email(), dto.telefone());

        if (nomeOuTelefoneOuEmailJaCadastrados) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        } else {
            repository.save(new Abrigo(dto));
        }
    }

    public List<AbrigoDTO> listar(){
        return repository.findAll()
                .stream()
                .map(AbrigoDTO::new)
                .toList();
    }

    public List<PetDTO> listarPetsDoAbrigo(String idOuNome){
        Abrigo abrigo = carregarAbrigo(idOuNome);

        return petRepository.findByAbrigo(abrigo)
                .stream()
                .map(PetDTO::new)
                .toList();
    }

    public Abrigo carregarAbrigo(String idOuNome) {
        Optional<Abrigo> optional;
        try {
            Long id = Long.parseLong(idOuNome);
            optional = repository.findById(id);
        }catch (NumberFormatException e){
            optional = repository.findByNome(idOuNome);
        }

        return optional.orElseThrow(() -> new ValidacaoException("Abrigo não encontrado"));
    }
}
