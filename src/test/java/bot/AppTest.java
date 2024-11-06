package bot;

import com.alibaba.fastjson2.JSONObject;
import com.github.nkinsp.telegram.bot.api.BotApi;
import com.github.nkinsp.telegram.bot.bo.Bot;
import com.github.nkinsp.telegram.bot.message.send.IMessage;
import com.github.nkinsp.telegram.bot.message.send.TextMessage;
import com.github.nkinsp.telegram.bot.message.send.reply.KeyboardButton;
import com.github.nkinsp.telegram.bot.message.send.reply.ReplyKeyboardMarkup;

public class AppTest {

    public static void main(String[] args) {

        BotApi api = new BotApi(Bot.builder()
//                .apiKey("6105972764:AAH_JFy76bfoGQK-RP-DS68tmkE-3mQRSiI")
                .apiKey("6059572077:AAG6taMqW2XDx1z-5OZ5X7lNiU2FQAtTUes")
//                .apiKey("6059572077:AAG6taMqW2XDx1z-5OZ5X7lNiU2FQAtTUes")
                .build(), true);
//
//        String message = """
//                 {"photo": "https://kabank.oss-ap-southeast-1.aliyuncs.com/upload/image/2024/07/03/f2317ca1bc00409389f6fc5f7cf2e8ec.png", "caption": "<b>Payment Receipt for Receiving Orders</b>\\nNumber：<code>2407030205181001813</code>\\nAmount：9000.00 THB\\nTime：2024-07-03 01:05:19 (UTC+07:00)\\nBank：PromptPay\\nAccount：0657370940\\n", "chat_id": "-4246054359", "parse_mode": "HTML", "reply_markup": {"inline_keyboard": [[{"text": "Confirm Receipt", "callback_data": "confirm-receive-order:2407030205181001813"}]]}, "protect_content": true}
//                """;

        TextMessage message = IMessage.ofTextMessage()
                .to("-4049485187")
                .text("test")
                ;
//                .replyMarkup(new ReplyKeyboardMarkup()
//                        .addButton(x -> {
//                            x.add(new KeyboardButton().setText("按钮1"));
//                        })
//                        .addButton(x->{
//                            x.add(new KeyboardButton().setText("按钮2"));
//                        })
//                        .setInputFieldPlaceholder("菜单")
//                        .setIsPersistent(false)
//                        .setSelective(true)
//                        .setResizeKeyboard(true)
//
//                );

//        String string = api.sendMessage(message);

        api.deleteMessage("-4049485187","2088851");
//        System.out.println("s=>"+string);

//        JSONObject string = api.post("sendPhoto",message);


//        System.out.println("messageId=>"+string.toJSONString());



    }
}
