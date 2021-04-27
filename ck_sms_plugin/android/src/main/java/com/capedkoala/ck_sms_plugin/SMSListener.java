package com.capedkoala.ck_sms_plugin;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

// import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.InputStreamReader;

/**
 * SMS Listener class extends BroadcastReceiver and uses onReceive to read any incoming SMS messages
 * that are received while the application is running (in foreground or background).
 */
public class SMSListener extends BroadcastReceiver
{
    String message = null;          // Holds the message received from the person using the app.
    // String filename = "smslog.txt"; // Filename of the text file which hold the messages.

    // FileOutputStream outputStream = null;       // Used to write to the text file.
    // FileInputStream in;                         // Used to read from the text file.
    // InputStreamReader inputStreamReader = null;
    // BufferedReader bufferedReader = null;

    // StringBuilder sb = null;  // Holds the final string containing all the contents of the text file.
    // String line = null;       // Holds each line of the text file (used only when reading from the file).

    SmsManager sms = SmsManager.getDefault(); // Manages SMS operations such as sending data, text, and pdu SMS messages.

    // File file = null;           // Holds the contents of the text message in the file, along with the sender's phone number.
    String messageBody = null;  // Holds the body of the SMS message.
    String sender = null;       // Holds the sender's phone number.

    /**
     * This method is called when the BroadcastReceiver is receiving an Intent broadcast. In this case,
     * the action we are listening for is when an SMS is received.
     * @param context  The Context in which the receiver is running.
     * @param intent   The Intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent)
    {
		Log.i("CKSMS_RX", "RECEIVED TRIGGERED");
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
			for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
				String messageBody = smsMessage.getMessageBody();
				String originatingAddress = smsMessage.getDisplayOriginatingAddress();

				Log.i("CKSMS_RX", messageBody);
				String convertedToString = String.valueOf(smsMessage);
				Log.i("CKSMS_RX", "CONVERTEDTOSTRING");
				Log.i("CKSMS_RX", convertedToString);
			}
		}
    }

    // HTTP CALL
    OkHttpClient client = new OkHttpClient();

    // TextView txtString;
    static public String url= "https://1b12a5b551b2.ngrok.io";

	static public void runQueue(){
		Log.i("CKSMS_RX", "RECEIVED TRIGGERED");

		try {
            smsListenerCKSMS();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

	static void smsListenerCKSMS() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                Log.i("CKSMS-HTTP", myResponse);

                // CKSMSPlugin.this.runOnUiThread(new Runnable() {
                //     @Override
                //     public void run() {
                //         // txtString.setText(myResponse);
                //     }
                // });

            }
        });
    }
}