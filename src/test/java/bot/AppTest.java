package bot;

import com.github.nkinsp.telegram.bot.api.BotApi;
import com.github.nkinsp.telegram.bot.bo.Bot;
import com.github.nkinsp.telegram.bot.message.send.IMessage;
import com.github.nkinsp.telegram.bot.message.send.TextMessage;
import com.github.nkinsp.telegram.bot.message.send.reply.KeyboardButton;
import com.github.nkinsp.telegram.bot.message.send.reply.ReplyKeyboardMarkup;

public class AppTest {

    public static void main(String[] args) {

        BotApi api = new BotApi(Bot.builder()
                .apiKey("6889059292:AAGTUcoKyOJJ5ch134FOKIWj_j7iP0SPoKg")
                .build(), true);


        TextMessage message = IMessage.ofTextMessage()
                .to("988393799")
                .text("helloworld")
                .replyMarkup(new ReplyKeyboardMarkup()
                        .addButton(x -> {
                            x.add(new KeyboardButton().setText("按钮1"));
                        })
                        .addButton(x->{
                            x.add(new KeyboardButton().setText("按钮2"));
                        })
                        .setInputFieldPlaceholder("菜单")
                        .setIsPersistent(false)
                        .setSelective(true)
                        .setResizeKeyboard(true)

                );

        String string = api.sendMessage(message);


        System.out.println("messageId=>"+string);



    }
}
