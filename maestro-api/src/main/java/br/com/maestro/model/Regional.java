package br.com.maestro.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
@Cacheable
public class Regional extends PanacheEntity {

    @NotBlank(message = "O nome da regional é obrigatório")
    @Column(unique = true)
    public String nome;

    public String descricao;

    // Método auxiliar para buscar por nome (útil na importação)
    public static Regional findByName(String nome) {
        return find("nome", nome).firstResult();
    }
}
