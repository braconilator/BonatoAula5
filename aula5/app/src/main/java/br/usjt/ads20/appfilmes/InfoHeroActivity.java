package br.usjt.ads20.appfilmes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.usjt.ads20.appfilmes.model.Hero;

public class InfoHeroActivity extends AppCompatActivity {
    private TextView titulo;
    private TextView descricao;
    private Hero hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_hero);
        Intent intent = getIntent();

        hero = (Hero) intent.getSerializableExtra(ListHeroesActivity.DESCRICAO);

        titulo = findViewById(R.id.titulo);

        descricao = findViewById(R.id.descricao);

        titulo.setText(hero.getTitulo());

        descricao.setText(hero.getDescricao());
    }
}