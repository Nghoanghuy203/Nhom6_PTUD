package sendSMS;

import com.twilio.Twilio;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


public class SendSms {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC0d1d237f5af9c0d9799d9627632a111e";
    public static final String AUTH_TOKEN = "99551c8426e13f3bd5183fa738c3cae0";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+84362026128"),
                new com.twilio.type.PhoneNumber("+16206589794"),
                "Este es un mensaje de Prueba desde JAVA2....")
            .create();

        System.out.println(message.getSid());
    }
}
