package com.example.clubactivity.Network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

import cz.msebera.android.httpclient.NameValuePair;

public class NetworkTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private String url;
    private String data;

    public NetworkTask(Context _context, String url, String data, int action){
        this.context = _context;
        this.url = url;
        this.data = data;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;

        try {
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.Request(url, data);

        }catch (Exception e){
            result = "Error";
        }

        return result;
    }

    @Override
    protected void onPreExecute(){
        // 상태에 맞는 안내문 넣어주는 부분
        // 메인스레드에서는 응답을 기다리는 안내 메세지를 띄워주면 될 것같음
    }

    @Override
    protected void onPostExecute(String result){

        try{
            //경우에 따라 결과 값을 받아 일어났으면 하는 작업
            Toast.makeText(this.context,result,Toast.LENGTH_LONG).show();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
