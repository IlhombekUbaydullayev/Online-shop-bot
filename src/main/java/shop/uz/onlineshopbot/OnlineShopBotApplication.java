package shop.uz.onlineshopbot;

import java.net.URI;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.URI;

@SpringBootApplication
@ComponentScan(basePackages = {
        "shop.uz.onlineshopbot",
        "org.telegram.telegrambots"
})
public class OnlineShopBotApplication {

    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(OnlineShopBotApplication.class, args);
//        System.getProperties().put("proxySet", "true");
//
//        System.getProperties().put("socksProxyHost", "127.0.0.1");
//
//        System.getProperties().put("socksProxyPort", "9150");
//        ClientConfig config = new ClientConfig();
//        Client client = ClientBuilder.newClient(config);
//        WebTarget target = client.target(getBaseURI());
//
//        String jsonaddresss =  target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
//
//        String address = adres(jsonaddresss);
//
//
//        System.out.println("Perfect Address is : >>"+address);
    }


//    private static URI getBaseURI()
//    {
//        return UriBuilder.fromUri("https://maps.googleapis.com/maps/api/geocode/json?latlng=32.263200,50.778187&key=<ENTER YOUR PLACE API KEY HEAR >").build();
//    }
//    private static String adres(String jsonaddresss)
//    {
//        try
//        {
//            JSONParser parser = new JSONParser();
//
//            Object obj = parser.parse(jsonaddresss);
//            JSONObject jsonObject = (JSONObject) obj;
//
//            JSONArray msg = (JSONArray) jsonObject.get("results");
//
//            //System.out.println("Message"+msg.get(1)); //Get Second Line Of Result
//
//            JSONObject jobj = (JSONObject) parser.parse(msg.get(1).toString()); // Parsse it
//
//            String perfect_address = jobj.get("formatted_address").toString();
//
//            jsonaddresss = perfect_address;
//        }
//        catch(Exception e)
//        {
//            jsonaddresss = "Error In Address"+e;
//        }
//        return jsonaddresss;
//    }

}
