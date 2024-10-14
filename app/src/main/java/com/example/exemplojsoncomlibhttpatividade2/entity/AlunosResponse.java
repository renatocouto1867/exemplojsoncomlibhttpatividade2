package com.example.exemplojsoncomlibhttpatividade2.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class AlunosResponse {
    @SerializedName("alunos")
    @Expose
    private List<Aluno> alunos;

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
