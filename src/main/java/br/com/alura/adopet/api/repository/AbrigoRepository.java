package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {

    Optional<Abrigo> findByNome(String nome);

    boolean existsByNomeOrEmailOrTelefone(String nome, String email, String telefone);
}
