package com.espiritualrandom

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.espiritualrandom.dao.ConteudoDao
import com.espiritualrandom.model.Conteudo

class MainActivity : AppCompatActivity() {

    lateinit var conteudos: List<Conteudo>
    lateinit var botao : Button
    lateinit var mensagem : TextView
    lateinit var tarefa : TextView
    lateinit var penitencia: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.conteudos = ConteudoDao().retornaListaConteudo();

        botao = findViewById(R.id.button)
        mensagem = findViewById(R.id.mensagem)
        tarefa = findViewById(R.id.tarefa)
        penitencia = findViewById(R.id.penitencia)

        botao.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val conteudo = conteudos.random()
                mensagem.setText(conteudo.mensagem)
                tarefa.setText(conteudo.tarefa)
                penitencia.setText(conteudo.penitencia)
            }
        })
    }
}