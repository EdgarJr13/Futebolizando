package com.tj.edgarjr.futebolizei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView pergunta;
    RadioButton rbResposta1, rbResposta2, rbResposta3, rbResposta4;
    RadioGroup rgRespostas;
    int respostaCerta = R.id.rbResposta2;
    int pontos = 0;

    List<Questao> questoes = new ArrayList<Questao>(){
        {
            add(new Questao("Quem foi mais vezes o melhor jogador africano da temporada?", R.id.rbResposta1, "Yaya Touré", "Samuel Eto'o", "Mohamed Salah", "Roger Milla"));
            add(new Questao("Quem é o jogador sub-21 mais valioso do mundo?", R.id.rbResposta2, "Gabriel Jesus", "Kylian Mbappé", "Osman Dembélé", "Delle Alli"));
            add(new Questao("O técnico mais vitorioso da história do Brasileirão é...", R.id.rbResposta3, "Tite", "Cuca", "Muricy Ramalho", "Vanderlei Luxemburgo"));
            add(new Questao("O jogador mais caro da história de times brasileiros:", R.id.rbResposta4, "Neymar Jr.", "Lucas Moura", "Pelé", "Vinícius Jr."));
            add(new Questao("Quem é o atual ganhador do Prêmio Puskás?", R.id.rbResposta2, "James Rodríguez", "Olivier Giroud", "Wendell Lira", "Kevin De Bruyne"));
            add(new Questao("Qual time é recordista em conquistas na Libertadores da América?", R.id.rbResposta2, "São Paulo", "Independiente", "Peñarol", "Boca Juniors"));
            add(new Questao("Qual time NUNCA ganhou uma Copa do Brasil em sua história?", R.id.rbResposta4, "Santo André", "Vasco da Gama", "Juventude", "São Paulo"));
            add(new Questao("Qual desses times NÃO é Bi-campeão do Mundial de Clubes da FIFA?", R.id.rbResposta1, "Flamengo", "Real Madrid", "Corinthians", "Barcelona"));
            add(new Questao("Jogador mais caro da história do futebol:", R.id.rbResposta3, "Cristiano Ronaldo", "Paul Pogba", "Neymar Jr.", "Kylian Mbappé"));
            add(new Questao("Quem foi o primero técnico brasileiro a conquistar uma Copa do Mundo?", R.id.rbResposta2, "Felipão", "Vicente Feola", "Aimoré Moreira", "Mário Jorge Lobo Zagallo"));
            add(new Questao("O jogador brasileiro mais vezes eleito o Melhor Jogador do Mundo foi:", R.id.rbResposta4, "Ronaldinho", "Rivaldo", "Romário", "Ronaldo"));

        }
    };

    private void carregarQuestao(){
        if (questoes.size() > 0) {
            Questao q = questoes.remove(0);
            pergunta.setText(q.getPergunta());
            List<String> resposta = q.getRespostas();
            rbResposta1.setText(resposta.get(0));
            rbResposta2.setText(resposta.get(1));
            rbResposta3.setText(resposta.get(2));
            rbResposta4.setText(resposta.get(3));
            respostaCerta = q.getRespostaCerta();
            rgRespostas.setSelected(false);
        } else {
            Intent intent = new Intent(this, RespostaActivity.class);
            intent.putExtra("pontos", pontos);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();

        pergunta = (TextView)findViewById(R.id.pergunta);
        rbResposta1 = (RadioButton)findViewById(R.id.rbResposta1);
        rbResposta2 = (RadioButton)findViewById(R.id.rbResposta2);
        rbResposta3 = (RadioButton)findViewById(R.id.rbResposta3);
        rbResposta4 = (RadioButton)findViewById(R.id.rbResposta4);
        rgRespostas = (RadioGroup)findViewById(R.id.rgRespostas);
        carregarQuestao();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarQuestao();
    }

    public void btnResponderOnClick(View view) {
        RadioButton rb = (RadioButton) findViewById(rgRespostas.getCheckedRadioButtonId());
        Intent intent = new Intent(this, RespostaActivity.class);
        if (rgRespostas.getCheckedRadioButtonId() == respostaCerta) {
            intent.putExtra("acertou", true);
            pontos+=10;
        } else
            intent.putExtra("acertou", false);
            intent.putExtra("pontos", pontos);
            startActivity(intent);
            rb.setChecked(false);
            rb.setSelected(false);
     }
}
