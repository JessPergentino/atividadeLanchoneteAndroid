package br.edu.ucsal.lanchonetedelecia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lista
        ListView listView = (ListView)this.findViewById(R.id.listLista);

        final ArrayList<String> produto_list = new ArrayList<>();
        produto_list.add("Suco Onda Tropical");
        produto_list.add("Vitamina Planetaria");
        produto_list.add("Hamburguer Exagerado");
        produto_list.add("Pastel Super");
        produto_list.add("Empada Olho Grande");
        produto_list.add("Boliviano Quente");
        produto_list.add("Quibe POP");
        produto_list.add("Pao de Nuvem");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                produto_list
        );

        listView.setAdapter(adapter);

        // Aqui o this puro nao funciona pois está dentro da classe anonima
        // O i é a posição na lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detalheIntent = new Intent(MainActivity.this,DetalheActivity.class);
                detalheIntent.putExtra("produto_nome", produto_list.get(i));
                startActivity(detalheIntent);
            }
        });
    }
}
