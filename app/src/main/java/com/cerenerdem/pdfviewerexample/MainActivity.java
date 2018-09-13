package com.cerenerdem.pdfviewerexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdfView = (PDFView) findViewById(R.id.pdfView);
        //pdfView.fromAsset("ornek.pdf").load();

        new RetriewPDFStream().execute("http://www.acarindex.com/dosyalar/kitap/1416182732.pdf");
    }

    class RetriewPDFStream extends AsyncTask<String,Void,InputStream>
    {


        @Override
        protected InputStream doInBackground(String... strings) {

            InputStream inputStream = null;

            try
            {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200){

                inputStream = new BufferedInputStream(urlConnection.getInputStream());

                }

            }
            catch (IOException e)
            {
                return null;

            }
          return  inputStream;
        }


        @Override
        protected void onPostExecute(InputStream ınputStream) {
            pdfView.fromStream(ınputStream).load();
        }
    }
}
