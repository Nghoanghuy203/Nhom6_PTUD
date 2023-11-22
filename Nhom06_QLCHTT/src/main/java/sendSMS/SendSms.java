package sendSMS; 

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


public class SendSMS extends Thread{
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC0d1d237f5af9c0d9799d9627632a111e";
    public static final String AUTH_TOKEN = "43b7f782b0afe3fe9cd5785ed03d0001";
    private String mess;
    
    
    
    public SendSMS(String mess) {
		super();
		this.mess = mess;
	}
	public void sendSMS(String mess) {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+84362026128"),
                new com.twilio.type.PhoneNumber("+16206589794"),
                mess)
            .create();

        System.out.println(message.getSid());
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		sendSMS(mess);
	}
}