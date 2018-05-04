package br.edu.ucsal.lanchonetedelecia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private Produto[] produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lista
        ListView listView = (ListView)this.findViewById(R.id.listLista);

        final ArrayList<Produto> produto_list = new ArrayList<>();
//        produto_list.add("Suco Onda Tropical");
//        produto_list.add("Vitamina Planetaria");
//        produto_list.add("Hamburguer Exagerado");
//        produto_list.add("Pastel Super");
//        produto_list.add("Empada Olho Grande");
//        produto_list.add("Boliviano Quente");
//        produto_list.add("Quibe POP");
//        produto_list.add("Pao de Nuvem");

        final ArrayAdapter<Produto> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                produtos
        );

        listView.setAdapter(adapter);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(

                "https://patra-backend.appspot.com/produtos", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                        Log.d("AsyncHttpClient", "reponse = " + response);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {
                        Log.d("AsyncHttpClient", "reponse = " + response);

                        Gson gson = new GsonBuilder().create();
                        produtos = gson.fromJson(response, Produto[].class);
                        adapter.clear();
                        for (Produto p : produtos
                                ) {
                            adapter.add(p);
                        }

                    }
                }
        );

        // Aqui o this puro nao funciona pois está dentro da classe anonima
        // O i é a posição na lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent detalheIntent = new Intent(MainActivity.this,DetalheActivity.class);
                detalheIntent.putExtra("produto_nome", produtos[i].getNome());

                detalheIntent.putExtra("produto", (Serializable) produtos[i]);

                startActivity(detalheIntent);
            }
        });
    }
}
