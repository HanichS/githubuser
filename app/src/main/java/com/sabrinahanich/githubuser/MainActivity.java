package com.sabrinahanich.githubuser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView nome;
    private TextView id;
    private TextView url;
    private ImageView foto;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadPessoa download = new DownloadPessoa();
        nome = (TextView)findViewById(R.id.login);
        id = (TextView)findViewById(R.id.id);
        url = (TextView)findViewById(R.id.url);
        foto = (ImageView)findViewById(R.id.imageView);

        download.execute();

    }

    private class DownloadPessoa extends AsyncTask<Void, Void, Pessoa> {

        @Override
        protected void onPreExecute() {
            //inicia o dialog
            load = ProgressDialog.show(MainActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected Pessoa doInBackground(Void... params) {
            Conversor util = new Conversor();
            return util.getInformacao("https://api.github.com/users/hanichs");
        }

        @Override
        protected void onPostExecute(Pessoa pessoa) {
            nome.setText(pessoa.getLogin());
            id.setText(pessoa.getId());
            url.setText(pessoa.getUrl());
            foto.setImageBitmap(pessoa.getFoto());
            load.dismiss();
        }
    }
}