package com.example.administrator.tangcco048_40;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.text111);
    }

    public void button(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket=new Socket("192.168.1.81",9001);
                    OutputStream outputStream = socket.getOutputStream();

                    outputStream.write("嘿嘿".getBytes("utf-8"));
                    outputStream.flush();

                    //接收服务器发送的消息
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    final String result = reader.readLine();
                    //handler  异步  .post Rxjava
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.append("\r\n" + result);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
