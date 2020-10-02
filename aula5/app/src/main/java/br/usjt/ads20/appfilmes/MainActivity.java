package br.usjt.ads20.appfilmes;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.ads20.appfilmes.model.Hero;
import br.usjt.ads20.appfilmes.model.HeroNetwork;

public class MainActivity extends AppCompatActivity {
    private EditText txtNome;

    public static final String NOME = "br.usjt.ads20.appfilmes.nome";

    public static final String HEROES = "br.usjt.ads20.appfilmes.herois";

    private String url = "https://gateway.marvel.com/v1/public/characters?ts=1601573778451&apikey=db1c3deff5b31a36ddce2ff3ca75da38&hash=209226e69ba26e2777c62415ef41b1ed";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNome = (EditText) findViewById(R.id.busca_fila);

        context = this;
    }

    public void searchHeroes(View view) {
        new DownloadJsonHeroes().execute(url);
    }

    private class DownloadJsonHeroes extends AsyncTask<String, Void, ArrayList<Hero>> {
        @Override
        protected ArrayList<Hero> doInBackground(String... strings) {
            ArrayList<Hero> heroes = new ArrayList<>();

            try {
                heroes = HeroNetwork.buscarHerois(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return heroes;
        }

        @Override
        protected void onPostExecute(ArrayList<Hero> heroes) {
            Intent intent = new Intent(context, ListHeroesActivity.class);
            String nome = txtNome.getText().toString();
            intent.putExtra(NOME, nome);
            intent.putExtra(HEROES, heroes);
            startActivity(intent);
        }
    }
}