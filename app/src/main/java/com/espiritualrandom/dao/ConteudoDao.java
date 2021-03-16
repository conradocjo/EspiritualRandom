package com.espiritualrandom.dao;

import androidx.annotation.NonNull;

import com.espiritualrandom.model.Conteudo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConteudoDao {

    private DatabaseReference database;
    private Conteudo conteudo;

    public List<Conteudo> retornaListaConteudo() {
        List<Conteudo> conteudos = new ArrayList<>();
        this.database = FirebaseDatabase.getInstance().getReference();
        this.database.child("conteudos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        conteudos.add(new Conteudo(ds.child("mensagem").getValue().toString(), ds.child("tarefa").getValue().toString(), ds.child("penitencia").getValue().toString()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return conteudos;
    }

}
