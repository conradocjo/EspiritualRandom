package com.espiritualrandom

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.espiritualrandom.model.Conteudo
import com.espiritualrandom.persistencia.ConteudoServiceImpl
import com.espiritualrandom.util.DateUtilImpl
import com.espiritualrandom.util.ServiceConstants.LBL_PENITENCIA
import com.espiritualrandom.util.ServiceConstants.LBL_TAREFA
import com.espiritualrandom.util.ServiceConstants.LBL_MENSAGEM
import java.util.Objects.isNull

class MainActivity : AppCompatActivity() {

    lateinit var conteudos: List<Conteudo>
    lateinit var botao: Button
    lateinit var mensagem: TextView
    lateinit var tarefa: TextView
    lateinit var penitencia: TextView
    lateinit var lblMensagem: TextView
    lateinit var lblTarefa: TextView
    lateinit var lblPenitencia: TextView
    lateinit var conteudo: Conteudo
    lateinit var conteudoRecuperado: Conteudo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        carregarDadosIniciais()
        botao.setOnClickListener(object : View.OnClickListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onClick(v: View?) {
                carregaLabelsAoClicarSobreBotao()
                insereConteudoQueSeraUsado();
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insereConteudoQueSeraUsado() {
        conteudoRecuperado = ConteudoServiceImpl().retornaConteudoAtual()
        if (isNull(conteudoRecuperado)) {
            this.conteudo = conteudos.random()
            ConteudoServiceImpl().salvaConteudoGeradoDoDiaCorrente(conteudo);
        } else if (conteudoRecuperado.dataGeracao.toString().equals(DateUtilImpl().dataAtualFormatada)) {
            this.conteudo = conteudoRecuperado
        } else if (!conteudoRecuperado.dataGeracao.toString().equals(DateUtilImpl().dataAtualFormatada)) {
            this.conteudo = ConteudoServiceImpl().retornaNovaListaDeConteudos(conteudos,conteudoRecuperado).random()
            ConteudoServiceImpl().salvaConteudoGeradoDoDiaCorrente(conteudo);
        }
        mensagem.setText(conteudo.mensagem)
        tarefa.setText(conteudo.tarefa)
        penitencia.setText(conteudo.penitencia)
    }

    fun carregarDadosIniciais () {
        this.conteudos = ConteudoServiceImpl().retornaListaConteudo();

        botao = findViewById(R.id.button)
        mensagem = findViewById(R.id.mensagem)
        lblMensagem = findViewById(R.id.mensagem2)
        tarefa = findViewById(R.id.tarefa)
        lblTarefa = findViewById(R.id.tarefa2)
        penitencia = findViewById(R.id.penitencia)
        lblPenitencia = findViewById(R.id.penitencia2)
    }

    fun carregaLabelsAoClicarSobreBotao () {
        lblPenitencia.setText(LBL_PENITENCIA);
        lblTarefa.setText(LBL_TAREFA);
        lblMensagem.setText(LBL_MENSAGEM)
    }

}