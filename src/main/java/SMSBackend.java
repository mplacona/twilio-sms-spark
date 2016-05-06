import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.instance.Sms;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class SMSBackend {
    public static void main(String[] args) {
        get("/", (req, res) -> "Hello, World");

        TwilioRestClient client = new TwilioRestClient("YOUR_TWILIO_ACCOUNT_SID", "YOUR_TWILIO_AUTH_TOKEN");

        post("/sms", (req, res) -> {
            String body = req.queryParams("Body");
            String to = req.queryParams("To");
            String from = "YOUR_TWILIO_PHONE_NUMBER";

            Map<String, String> callParams = new HashMap<>();
            callParams.put("To", to);
            callParams.put("From", from);
            callParams.put("Body", body);
            Sms message = client.getAccount().getSmsFactory().create(callParams);

            return message.getSid();
        });
    }
}
