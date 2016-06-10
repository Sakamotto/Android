package com.testes.agenda.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cristian on 30/05/16.
 */
public class Contato implements Serializable{
    private String nome;
    private String email;
    private String dataNascimento;
    private String celular;
    private String endereco;
    private String genero;
    private int nivelProximidade;
    private int idade;
    private List<Gosto> gosto = new ArrayList<>();
    private GrupoContato grupoContato;

    public void print(){
        System.out.println("Nome: " + this.getNome());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Data de Nascimento: " + this.getDataNascimento());
        System.out.println("Celular: " + this.getCelular());
        System.out.println("Endereço: " + this.getEndereco());
        System.out.println("Gênero: " + this.getGenero());
        System.out.println("Proximidade: " + this.getNivelProximidade());
    }

    public void imprimeDados(){
    }

    public String toString(){
        return "Nome: " + this.getNome() + "\n" + "Email: " + this.getEmail() + "\n" + "Celular: " + this.getCelular();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNivelProximidade() {
        return nivelProximidade;
    }

    public void setNivelProximidade(int nivelProximidade) {
        this.nivelProximidade = nivelProximidade;
    }

    public List<Gosto> getGosto() {
        return gosto;
    }

    public void setGosto(List<Gosto> gosto) {
        this.gosto = gosto;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public GrupoContato getGrupoContato() {
        return grupoContato;
    }

    public void setGrupoContato(GrupoContato grupoContato) {
        this.grupoContato = grupoContato;
    }
}
