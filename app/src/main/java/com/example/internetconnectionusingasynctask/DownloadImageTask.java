package com.example.internetconnectionusingasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String,Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... strings) {

        String s=strings[0];

        InputStream inputStream;


        try {
            URL url = new URL(s);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            inputStream=httpURLConnection.getInputStream();//Image is now inside inputStream.

            Bitmap myMap= BitmapFactory.decodeStream(inputStream);

            return  myMap;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {   // doInBackground will return a bitMap which will com into onPostExecute.
        MainActivity.imageView.setImageBitmap(bitmap);
    }
}
