package com.github.nkinsp.telegram.bot.api;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.github.nkinsp.telegram.bot.bo.Bot;
import com.github.nkinsp.telegram.bot.message.send.EditMessage;
import com.github.nkinsp.telegram.bot.message.send.IMessage;
import lombok.CustomLog;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class BotApi {


    private Bot bot;

    @Setter
    private Boolean dev = false;

    private final static String API_URL = "https://api.telegram.org/bot%s/%s";




    public BotApi(Bot bot,Boolean dev){
        this.bot = bot;
        this.dev = dev;
    }




    private URI getUrl(String type){

        return URI.create(String.format(API_URL,bot.getApiKey(),type));

    }

    private HttpRequest.Builder getRequest(String method){

        return HttpRequest.newBuilder()
                .uri(getUrl(method))
                .timeout(Duration.ofSeconds(30))
                ;

    }


    private <T> T execute(HttpRequest request,HttpResponse.BodyHandler<T> responseBodyHandler) {

        try {

            HttpClient.Builder builder = HttpClient.newBuilder();

            builder.connectTimeout(Duration.ofSeconds(30));

            if(dev){
                builder.proxy(ProxySelector.of(new InetSocketAddress("127.0.0.1",1087)));
            }

            HttpClient httpClient = builder.build();

            HttpResponse<T> send = httpClient.send(request, responseBodyHandler);

            return send.body();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private JSONObject executeToJson(HttpRequest request){

        String string = execute(request, HttpResponse.BodyHandlers.ofString());

        return JSON.parseObject(string);

    }

    private byte[] executeToBytes(HttpRequest request){

         return execute(request, HttpResponse.BodyHandlers.ofByteArray());

    }


    public JSONObject post(String method, String body){



        HttpRequest request = getRequest(method)
                .header("Content-Type", "application/json;charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

       return executeToJson(request);

    }


    public String sendMessage(IMessage message){


        String serialize = message.serialize();



        JSONObject ret = post(message.messageType(),serialize);



        if(!ret.getBoolean("ok")){

            throw new RuntimeException(ret.getString("description"));
        }

        return ret.getJSONObject("result").getString("message_id");

    }

    public <T> String editMessage(EditMessage<T> message){


        String serialize = message.serialize();
        System.out.println("editMessage message=>"+serialize);

        JSONObject ret = post(message.messageType(),serialize);

        System.out.println("editMessage result=>"+ret);


        if(!ret.getBoolean("ok")){

            throw new RuntimeException(ret.getString("description"));
        }

        return ret.getJSONObject("result").getString("message_id");

    }


    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://trx.ufmoney.net/setting/platform"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

    }



}
