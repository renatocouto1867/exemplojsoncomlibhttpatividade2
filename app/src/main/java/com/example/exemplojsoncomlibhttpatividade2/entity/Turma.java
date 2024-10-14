package com.example.exemplojsoncomlibhttpatividade2.entity;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private final List<Aluno> alunoList;
    private final double mediaIdade;

    public Turma(List<Aluno> alunoList) {
        this.alunoList = alunoList;
        this.mediaIdade = calcularMediaIdade();
    }

    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public double getMediaIdade() {
        return mediaIdade;
    }

    public List<Aluno> getAlunosAprovados() {
        List<Aluno> alunosAprovados = new ArrayList<>();

        if (alunoList != null && !alunoList.isEmpty()) {
            for (Aluno aluno : alunoList) {
                if (isAprovado(aluno)) {
                    alunosAprovados.add(aluno);
                }
            }
        }
        return alunosAprovados;
    }//lista aprovados


    private boolean isAprovado(Aluno aluno) {
        // Calcula a média das notas
        double somaNotas = 0;
        for (double nota : aluno.getNota()) {
            somaNotas += nota;
        }
        double mediaNotas = somaNotas / aluno.getNota().size();

        // Calcula a frequência
        int presencas = 0;
        for (boolean presente : aluno.getFrequencia()) {
            if (presente) {
                presencas++;
            }
        }
        double percentualFrequencia = (presencas / (double) aluno.getFrequencia().size()) * 100;

        return mediaNotas >= 60 && percentualFrequencia >= 75;
    }//isAprovados


    private double calcularMediaIdade() {
        if (alunoList == null || alunoList.isEmpty()) {
            return 0.0; // caso lista nula
        }

        double somaIdades = 0;
        for (Aluno aluno : alunoList) {
            somaIdades += aluno.getIdade();
        }

        return somaIdades / alunoList.size();
    }//media idade


}
