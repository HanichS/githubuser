package com.sabrinahanich.githubuser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversor {


    public Pessoa getInformacao(String end){
        String json = ConexaoApi.getJsonFromApi(end);
        Pessoa retorno = parseJson(json);
        return retorno;
    }

    private Pessoa parseJson(String json){
        try {
            Pessoa pessoa = new Pessoa();
            JSONObject jsonObj = new JSONObject(json);
            pessoa.setLogin(jsonObj.getString("login"));
            pessoa.setId(jsonObj.getString("id"));
            pessoa.setUrl(jsonObj.getString("url"));
            pessoa.setFoto(baixarImagem(jsonObj.getString("avatar_url")));
            return pessoa;

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap baixarImagem(String url) {
        //converte a imagem para o formato Bitmap
        try {
            URL endereco = new URL(url);
            InputStream inputStream = endereco.openStream();
            Bitmap imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}