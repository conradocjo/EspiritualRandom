package com.espiritualrandom.persistencia;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.espiritualrandom.model.Conteudo;
import com.espiritualrandom.util.DateUtilImpl;
import com.espiritualrandom.util.FireBaseUtil;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.espiritualrandom.util.ServiceConstants.CONTEUDOS;
import static com.espiritualrandom.util.ServiceConstants.CONTEUDO_ATUAL;
import static com.espiritualrandom.util.ServiceConstants.DATA_GERACAO;
import static com.espiritualrandom.util.ServiceConstants.MENSAGEM;
import static com.espiritualrandom.util.ServiceConstants.PENITENCIA;
import static com.espiritualrandom.util.ServiceConstants.TAREFA;

public class ConteudoServiceImpl extends FireBaseUtil implements ConteudoService {


    protected DatabaseReference database;

    protected void getReferenceDataBase() {
        this.database = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public List<Conteudo> retornaListaConteudo() {
        List<Conteudo> conteudos = new ArrayList<>();
        getReferenceDataBase();
        this.database.child(CONTEUDOS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        conteudos.add(new Conteudo(ds.child(MENSAGEM).getValue().toString(),
                                ds.child(TAREFA).getValue().toString(),
                                ds.child(PENITENCIA).getValue().toString(),
                                ""));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        return conteudos;
    }

    @Override
    public Conteudo retornaConteudoAtual() {
        getReferenceDataBase();

        Task<DataSnapshot> dataSnapshotTask = this.database.child(CONTEUDO_ATUAL).getRoot().get();


        try {
            Thread.currentThread().sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        DataSnapshot dataSnapshot = dataSnapshotTask.getResult().child(CONTEUDO_ATUAL);

        Conteudo conteudo = new Conteudo(dataSnapshot.child(MENSAGEM).getValue().toString(),
                dataSnapshot.child(TAREFA).getValue().toString(),
                dataSnapshot.child(PENITENCIA).getValue().toString(),
                dataSnapshot.child(DATA_GERACAO).getValue().toString());

        return conteudo;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void salvaConteudoGeradoDoDiaCorrente(Conteudo conteudo) {
        getReferenceDataBase();
        conteudo.setDataGeracao(new DateUtilImpl().getDataAtualFormatada());
        this.database.child(CONTEUDO_ATUAL).setValue(conteudo);
    }

    @Override
    public List<Conteudo> retornaNovaListaDeConteudos(List<Conteudo> conteudosCarregados, Conteudo conteudoRecuperado) {
        List<Conteudo> conteudosTemporario = new ArrayList<>();
        for (Conteudo conteudo : conteudosCarregados) {
            if (mensagemRecuperadaDiferenteDasMensagensDaLista(conteudoRecuperado, conteudo)
                    && tarefaRecuperadaDiferenteDasTarefasDaLista(conteudoRecuperado, conteudo)
                    && penitenciaRecuperadaDiferenteDasPenitenciasDaLista(conteudoRecuperado, conteudo))
            {
                conteudosTemporario.add(conteudo);
            }
        }
        return conteudosTemporario;
    }

    private boolean mensagemRecuperadaDiferenteDasMensagensDaLista(Conteudo conteudoRecuperado, Conteudo conteudo) {
        return !conteudoRecuperado.getMensagem().equals(conteudo.getMensagem());
    }

    private boolean tarefaRecuperadaDiferenteDasTarefasDaLista(Conteudo conteudoRecuperado, Conteudo conteudo) {
        return !conteudoRecuperado.getTarefa().equals(conteudo.getTarefa());
    }

    private boolean penitenciaRecuperadaDiferenteDasPenitenciasDaLista(Conteudo conteudoRecuperado, Conteudo conteudo) {
        return !conteudoRecuperado.getPenitencia().equals(conteudo.getPenitencia());
    }

}
