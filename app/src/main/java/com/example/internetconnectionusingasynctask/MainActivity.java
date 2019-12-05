package com.example.internetconnectionusingasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button downloadimage,downloadtext;
    ScrollView scrolltext,scrollimage;
    static TextView downloadedText;
    static ImageView imageView;
    DownloadTextTask downloadTextTask;
    DownloadImageTask downloadImageTask;

    ConnectivityManager connectivityManager;
    NetworkInfo myInfo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        downloadtext = findViewById(R.id.downloadtext);
        downloadimage=findViewById(R.id.downloadimage);
        downloadedText=findViewById(R.id.downloadedText);
        imageView=findViewById(R.id.imageView);

        scrollimage=findViewById(R.id.scrollImage);
        scrolltext=findViewById(R.id.scrolltext);

        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        myInfo=connectivityManager.getActiveNetworkInfo();


        downloadtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                downloadTextTask = new DownloadTextTask(MainActivity.this);
                downloadTextTask.execute("https://www.google.com");

            }
        });



        downloadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myInfo!=null && myInfo.isConnected()){

                    downloadImageTask = new DownloadImageTask();
                    downloadImageTask.execute("https://www.pixelstalk.net/wp-content/uploads/2016/08/Android-Backgrounds-Cool-HD-Free-Download.jpg");
                }else{
                    Toast.makeText(MainActivity.this,"Internet not connected",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
