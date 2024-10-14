package com.example.exemplojsoncomlibhttpatividade2.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.processing.Generated;


@Generated("jsonschema2pojo")
public class Aluno {

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("idade")
    @Expose
    private Integer idade;
    @SerializedName("nota")
    @Expose
    private List<Double> nota;
    @SerializedName("frequencia")
    @Expose
    private List<Boolean> frequencia;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public List<Double> getNota() {
        return nota;
    }

    public void setNota(List<Double> nota) {
        this.nota = nota;
    }

    public List<Boolean> getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(List<Boolean> frequencia) {
        this.frequencia = frequencia;
    }

    public double getMedia() {
        if (nota == null || nota.isEmpty()) {
            return 0.0; // caso lista nula
        }
        double somaNotas = 0;
        for (double nota : nota) {
            somaNotas += nota;
        }
        return somaNotas / nota.size();
    }

    public double getFrenquenciaMedia() {
        if (frequencia == null || frequencia.isEmpty()) {
            return 0.0; // caso lista nula
        }
        int presencas = 0;
        for (boolean presente : frequencia) {
            if (presente) {
                presencas++;
            }
        }
        return (presencas / (double) frequencia.size()) * 100;
    }

}
