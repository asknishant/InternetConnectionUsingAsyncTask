package com.example.internetconnectionusingasynctask;

import android.content.Context;
import android.os.AsyncTask;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

public class DownloadTextTask extends AsyncTask<String,Void,String> {
                                    //Params is String here(The URI to which you want to connect).It will take parameter from
                                    // execute() which will be called by object of this class.
                                    //Progress is Void as no progress bar will be created by me.
                                    //Result is the downloaded string which will be shown on the click of downloadText button.
    Context ctx;

    DownloadTextTask(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected String doInBackground(String... strings) {  //Parameter is array of strings.

        String s1=strings[0];
        InputStream inputStream;

        try {
            URL url = new URL(s1);
            HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setRequestMethod("GET");  //A we want to download data.
            httpURLConnection.connect();

            //Taking the data after setting the connection inside an InputStream.

            inputStream=httpURLConnection.getInputStream();//inputStream takes the data.

            BufferedReader myBuff = new BufferedReader(new InputStreamReader(inputStream));//Takes the data after connection is established.
            StringBuilder s = new StringBuilder();
            String Line="";
            while((Line = myBuff.readLine())!=null){
                s.append(Line+" \n");
            }
            myBuff.close();
            inputStream.close();
            return  s.toString();//After return it will go to postExecute().



        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        MainActivity.downloadedText.setText(s);
    }
}
