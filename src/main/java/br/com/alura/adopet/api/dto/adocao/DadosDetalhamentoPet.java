package br.com.alura.adopet.api.dto.adocao;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;

public record DadosDetalhamentoPet(Long id, TipoPet tipo, String nome, String raca, Integer idade) {
    public DadosDetalhamentoPet(Pet pet) {
        this(pet.getId(), pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade());
    }
}
