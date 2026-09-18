package com.github.nkinsp.telegram.bot.api;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.github.nkinsp.telegram.bot.bo.Bot;
import com.github.nkinsp.telegram.bot.message.send.EditMessage;
import com.github.nkinsp.telegram.bot.message.send.IMessage;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class BotApi {


    private static final Logger log = LoggerFactory.getLogger(BotApi.class);

    private Bot bot;

    @Setter
    private Boolean dev = false;

    private final static String API_URL = "https://api.telegram.org/bot%s/%s";

    private final static String FILE_URL = "https://api.telegram.org/file/bot%s/%s";

    public BotApi(Bot bot, Boolean dev) {
        this.bot = bot;
        this.dev = dev;
    }


    private URI getUrl(String type) {

        return URI.create(String.format(API_URL, bot.getApiKey(), type));

    }

    private URI getFileUrl(String type) {

        return URI.create(String.format(FILE_URL, bot.getApiKey(), type));

    }



    private HttpRequest.Builder getRequest(String method) {

        return HttpRequest.newBuilder()
                .uri(getUrl(method))
                .timeout(Duration.ofSeconds(30))
                ;

    }



    private <T> T execute(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) {

        try {

            HttpClient.Builder builder = HttpClient.newBuilder();

            builder.connectTimeout(Duration.ofSeconds(30));

            if (dev) {
                builder.proxy(ProxySelector.of(new InetSocketAddress("127.0.0.1", 7897)));
            }

            HttpClient httpClient = builder.build();


            HttpResponse<T> send = httpClient.send(request, responseBodyHandler);




            return send.body();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private JSONObject executeToJson(HttpRequest request) {

        String string = execute(request, HttpResponse.BodyHandlers.ofString());

        return JSON.parseObject(string);

    }

    private byte[] executeToBytes(HttpRequest request) {

        return execute(request, HttpResponse.BodyHandlers.ofByteArray());

    }


    public JSONObject post(String method, String body) {


        HttpRequest request = getRequest(method)
                .header("Content-Type", "application/json;charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        return executeToJson(request);

    }

    public <T> T getFileData(String method, HttpResponse.BodyHandler<T> bodyHandler) {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(getFileUrl(method))
                .timeout(Duration.ofSeconds(30))
                .GET()
                .build();
        return execute(request, bodyHandler);

    }


    /**
     * 发送消息
     *
     * @param message
     * @return
     */
    public String sendMessage(IMessage message) {
        return sendMessage(message.messageType(), message.serialize());
    }

    public String sendMessage(String messageType, String message) {


        JSONObject ret = post(messageType, message);

        if (!ret.getBoolean("ok")) {
            throw new RuntimeException(ret.getString("description"));
        }
        return ret.getJSONObject("result").getString("message_id");
    }

    public <T> String editMessage(EditMessage<T> message) {


        String serialize = message.serialize();


        JSONObject ret = post(message.messageType(), serialize);

        if (!ret.getBoolean("ok")) {

            throw new RuntimeException(ret.getString("description"));
        }

        return ret.getJSONObject("result").getString("message_id");

    }


    public void deleteMessage(String chatId, String messageId) {

        JSONObject ret = post("deleteMessage", new JSONObject().fluentPut("chat_id", chatId).fluentPut("message_id", messageId).toJSONString());

    }


    public byte[] getFileBytes(String fileId) {

        JSONObject ret = post("getFile", new JSONObject().fluentPut("file_id", fileId).toJSONString());

        if (ret == null) {
            return null;
        }

        log.info("getFileBytes=>{}",ret);

        JSONObject data = ret.getJSONObject("result");

        if (data == null || !data.containsKey("file_path")) {
            return null;
        }
        String filePath = data.getString("file_path");

        return getFileData(filePath, HttpResponse.BodyHandlers.ofByteArray());

    }

    public InputStream getFileInputStream(String fileId) {

        JSONObject ret = post("getFile", new JSONObject().fluentPut("file_id", fileId).toJSONString());

        if (ret == null) {
            return null;
        }

        log.info("getFileInputStream=>{}",ret);

        JSONObject data = ret.getJSONObject("result");

        if (data == null || !data.containsKey("file_path")) {
            return null;
        }
        String filePath = data.getString("file_path");

        return getFileData(filePath, HttpResponse.BodyHandlers.ofInputStream());

    }


    /**
     *
     * @param url
     * @param secretToken
     */
    public void setWebhook(String url,String secretToken){

        JSONObject object = new JSONObject();

        object.put("url",url);
        object.put("secret_token",secretToken);

        log.info("setWebhook request=>{}", object);

        JSONObject result = post("setWebhook", object.toJSONString());

        log.info("setWebhook result=>{}", result);

        if (result == null){
            throw new RuntimeException("setWebhook error");
        }

        if (!result.getBooleanValue("ok")) {
            throw new RuntimeException(result.getString("description"));
        }

    }
}
