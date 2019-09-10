package com.example.clubactivity.Network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestHttpURLConnection {

    public String Request(String _url, String data){

        String result = null;
        BufferedReader reader = null;
        String postParameters = data;

        try{
            URL url = new URL(_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(10000); // 10초동안 서버로 부터반응 없으면 에러
            httpURLConnection.setConnectTimeout(10000); // 접속하는 커넥션 타임 10초 동안 접속 안되면 접속안되는 것으로 간주
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
            httpURLConnection.setRequestProperty("Accept", "text/html");
            httpURLConnection.setFixedLengthStreamingMode(data.getBytes().length);

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();


            OutputStream outputStream = httpURLConnection.getOutputStream();

            outputStream.write(postParameters.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();


            int responseStatusCode = httpURLConnection.getResponseCode();
            Log.e("RequestHttp","Post response code - " + responseStatusCode);

            InputStream inputStream;
            // 서버와 연결이 에러면 에러 코드를 저장 아니면 제대로 된 코드 저장.
            if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
            }
            else{
                inputStream = httpURLConnection.getErrorStream();
                Log.e("Connect", "error");
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);

            StringBuffer buffer = new StringBuffer();
            String line = null;

            while((line = reader.readLine()) != null){
                buffer.append(line);
            }
            if(reader != null)
                reader.close();

            result = buffer.toString();

            if(httpURLConnection != null)
                httpURLConnection.disconnect();

            return result;

        }catch(Exception e){
            Log.e("RequestError", "InsertData: Error ", e);
            e.printStackTrace();
            return null;
        }

    }
}
