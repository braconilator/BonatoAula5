package br.usjt.ads20.appfilmes.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HeroNetwork {
    public static ArrayList<Hero> buscarHerois(String url) throws IOException {

        ArrayList<Hero> heroes = new ArrayList<>();
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();

        String json = response.body().string();

        try {
            JSONObject retorno = new JSONObject(json);
            JSONObject jsonResponse = retorno.getJSONObject("data");
            JSONArray lista = jsonResponse.getJSONArray("results");

            for (int i = 0; i < lista.length(); i++) {
                Hero hero = new Hero();
                JSONObject item = (JSONObject) lista.get(i);

                hero.setDescricao(item.get("description").toString());
                hero.setTitulo(item.get("name").toString());

                heroes.add(hero);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return heroes;
    }
}


