package com.espiritualrandom.persistencia;

import com.espiritualrandom.model.Conteudo;

import java.util.List;

public interface ConteudoService {
    List<Conteudo> retornaListaConteudo();

    Conteudo retornaConteudoAtual();

    void salvaConteudoGeradoDoDiaCorrente(Conteudo conteudo);

    List<Conteudo> retornaNovaListaDeConteudos(List<Conteudo> conteudosCarregados, Conteudo conteudoRecuperado);
}
