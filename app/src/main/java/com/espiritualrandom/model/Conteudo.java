package com.espiritualrandom.model;

public class Conteudo {

    private String mensagem;
    private String tarefa;
    private String penitencia;
    private String dataGeracao;

    public Conteudo(String mensagem,String tarefa,String penitencia,String dataGeracao){
        this.setMensagem(mensagem);
        this.setTarefa(tarefa);
        this.setPenitencia(penitencia);
        this.setDataGeracao(dataGeracao);
    }

    public String getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(String dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public String getPenitencia() {
        return penitencia;
    }

    public void setPenitencia(String penitencia) {
        this.penitencia = penitencia;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
