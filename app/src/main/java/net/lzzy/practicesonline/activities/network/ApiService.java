package net.lzzy.practicesonline.activities.network;

import android.text.TextUtils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lzzy_gxy on 2019/4/19.
 * Description:
 */
public class ApiService {
    private static final OkHttpClient CLIENT=new OkHttpClient();

    public static String get(String address)throws IOException {
        URL url = new URL(address);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try {
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6*1000);
            conn.setReadTimeout(6*1000);
            BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder builder=new StringBuilder();
            String line;
            while ((line=reader.readLine())!=null){
                builder.append(line).append("\n");
            }
            reader.close();
            return builder.toString();
        }finally {
            conn.disconnect();
        }
    }

    public static void  post(String address, JSONObject json) throws IOException {
        URL url = new URL(address);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setChunkedStreamingMode(0);
            conn.setRequestProperty("Content-Type","application/json");
            byte[] data=json.toString().getBytes(StandardCharsets.UTF_8);
            conn.setRequestProperty("Content-Length",String.valueOf(data.length));
            conn.setUseCaches(false);
            try(OutputStream stream=conn.getOutputStream()){
                stream.write(data);
                stream.flush();
        }finally {
            conn.disconnect();
        }
    }
    public static String okGet(String address)throws IOException{
        Request request=new Request.Builder().url(address).build();
        try(Response response=CLIENT.newCall(request).execute()){
            if(response.isSuccessful()){
                return response.body().string();
            }else {
                throw new IOException("错误码："+response.code());
            }
        }
    }
    public static String okGet(String address, String args, HashMap<String,Object> headers)throws IOException{
        if (!TextUtils.isEmpty(args)){
            address=address.concat("?").concat(args);
        }
        Request.Builder builder=new Request.Builder().url(address);
        if (headers !=null&&headers.size()>0){
            for (Object o:headers.entrySet()){
                Map.Entry entry=(Map.Entry) o;
                String key=entry.getKey().toString();
                Object val=entry.getValue();
                if (val instanceof String){
                    builder=builder.header(key,val.toString());
                }else if(val instanceof List) {
                    for (String v:ApiService.<List<String>>cast(val)){
                        builder=builder.addHeader(key,v);
                    }
                }
            }
        }
        Request request=builder.build();
        try(Response response=CLIENT.newCall(request).execute()){
            if(response.isSuccessful()){
                return response.body().string();
            }else {
                throw new IOException("错误码："+response.code());
            }
        }
    }
    @SuppressWarnings("unchecked")
    private static <T> T cast(Object obj){
        return (T) obj;
    }

    public static int okPost(String address,JSONObject json)throws IOException{
        RequestBody body=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),
                json.toString());
        Request request=new Request.Builder()
                .url(address)
                .post(body)
                .build();
        try (Response response=CLIENT.newCall(request).execute()){
            return response.code();
        }
    }
    public static String okRequest(String address,JSONObject json)throws IOException{
        RequestBody body=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),
                json.toString());
        Request request=new Request.Builder()
                .url(address)
                .post(body)
                .build();
        try (Response response=CLIENT.newCall(request).execute()){
            return response.body().string();
        }
    }
}
