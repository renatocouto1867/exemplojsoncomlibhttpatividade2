package com.example.exemplojsoncomlibhttpatividade2.view;
//Crie uma base de dados que represente uma lista de 5 alunos. Cada aluno deve ter nome, idade, nota[3] e frequência[10]. Frequência é um vetor de 10 posições de booleanos que indica 0 como ausência e 1 como presença;
//
//        Disponibilize esses dados na API JsonServer;
//
//        Seu app deve consumir esses dados e deve imprimir a média das idades; os nomes dos alunos que foram aprovados. Considere a nota acima de 60 e frequência superior a 75%.
//
//        Use a classe ExecutorService.
//
//        VALE ponto

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exemplojsoncomlibhttpatividade2.R;
import com.example.exemplojsoncomlibhttpatividade2.clientehttp.Conexao;
import com.example.exemplojsoncomlibhttpatividade2.entity.Aluno;
import com.example.exemplojsoncomlibhttpatividade2.entity.AlunosResponse;
import com.example.exemplojsoncomlibhttpatividade2.entity.Turma;
import com.example.exemplojsoncomlibhttpatividade2.util.Auxilia;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    // URL da API
    private final String URL = "https://my-json-server.typicode.com/renatocouto1867/exemplojsoncomlibhttpatividade2/db";

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper()); // Handler para a UI thread

    private AlunosResponse dadosBaixados=null;
    private List<Aluno> alunoList = new ArrayList<>();
    private Turma turma;

    private ListView listView;
    private TextView textViewMediaIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listaAlunos);
        textViewMediaIdade = findViewById(R.id.mediaIdade);


        obterDados();


    }//onCreate

    private void obterDados() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Conexao conexao = new Conexao();
                InputStream inputStream = conexao.obterRespostaHTTP(URL);
                Auxilia auxilia =new Auxilia();
                String textoJSON = auxilia.converter(inputStream);

                Gson gson = new Gson();

                if (textoJSON !=null){

                    Type type = new TypeToken<AlunosResponse>() {
                    }.getType();
                    dadosBaixados = gson.fromJson(textoJSON, type);

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Não foi possível obter JSON", Toast.LENGTH_SHORT).show();
                        }
                    });
                }//if

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        alunoList.addAll(dadosBaixados.getAlunos());
                        turma=new Turma(alunoList);
                        String valorFormatado= "Media de idade da truma: "+ String.format("%.1f",turma.getMediaIdade())+" anos";
                        textViewMediaIdade.setText(valorFormatado);

                        ItemAdapter adapter = new ItemAdapter(MainActivity.this,turma.getAlunosAprovados());
                        listView.setAdapter(adapter);
                    }
                });
            }
        });
    }


}