package br.com.alura.springdata.orm;

import javax.persistence.*;

@Entity
@Table(name = "cargos")
public class Cargo {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

    @Override
    public String toString() {
        return "Cargo{" +
                "Id=" + Id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
