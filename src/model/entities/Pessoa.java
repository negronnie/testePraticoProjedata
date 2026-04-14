package model.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Pessoa {

    private long id;
    private String nome;
    private LocalDate dataNascimento;

    public Pessoa() {}

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return getId() == pessoa.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
