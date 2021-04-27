package com.capedkoala.ck_sms_plugin;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

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

import com.capedkoala.ck_sms_plugin.models.SMSRun;
import com.capedkoala.ck_sms_plugin.models.SMSResult;

public class TestRunner 
{
	static public void runTest(SMSRun run, String device, String url){
		Log.i("CKSMS-TESTID", run.id.toString());
		Log.i("CKSMS-deviceID", device.toString());
		Log.i("CKSMS-url", url.toString());
		
		// START TEST HTTP CALL
		Log.i("CKSMS-RUNR", run.id + " START");
		startRun(run.id, url, device);

		for (int j = 0; j < run.sms_results.length; j++) {
			Log.i("CKSMS-RESID", run.sms_results[j].id + "");
			// RUNNING TEST
			SMSResult test = run.sms_results[j];
			Log.i("CKSMS-RUNNING", test.to + " " + test.message);

			// GET SMS MANAGER
			SmsManager smsManager = SmsManager.getDefault();

			// EDIT STRING
			String message = "_id_" + run.sms_results[j].id + "_\n" + test.message;

			smsManager.sendTextMessage(test.to, null, message, null, null);

			// SMS LISTENER???

			// UPDATE STATUS TO SENT
			sentTest(run.sms_results[j].id + "", url, device + "");
			
		} 

		// END TEST HTTP CALL
		completeRun(run.id, url, device);
		Log.i("CKSMS-RUNR", run.id + " END");
	}

	static public void sentTest(String resultID, String url, String deviceID) {
		OkHttpClient client = new OkHttpClient();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String time = dtf.format(now);

        String json = "{\"id\":"+ resultID + ", \"device_id\": "+deviceID+", \"date\": \""+time+"\" }";
		// "id": id, "device_id": deviceID, "date": date.toString()

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url + "/sms-test-results/sent")
                .post(body)
                .build();

		client.newCall(request).enqueue(new Callback() {
		
			@Override
			public void onFailure(Call call, IOException e) {
				call.cancel();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
					final String myResponse = response.body().string();
					Log.i("CKSMS-RUNR", "RESULT SUCCESSFULLY UPDATED");
					Log.i("CKSMS-SENT", myResponse);
				}
        });
	}

	static public void startRun(String testID, String url, String deviceID){
		
		OkHttpClient client = new OkHttpClient();

        String json = "{\"id\":"+ testID + "}";

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url + "/sms-test-results/start")
                .post(body)
                .build();

		client.newCall(request).enqueue(new Callback() {
		
			@Override
			public void onFailure(Call call, IOException e) {
				call.cancel();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
					final String myResponse = response.body().string();
					Log.i("CKSMS-RUNR", "STARTED SUCCESSFULLY CALLED");
					Log.i("CKSMS-RUNR", myResponse);
				}
        });
	}
	
	static public void completeRun(String testID, String url, String deviceID){
		
		OkHttpClient client = new OkHttpClient();

        String json = "{\"id\":"+ testID + "}";

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url + "/sms-test-results/complete")
                .post(body)
                .build();
		
		client.newCall(request).enqueue(new Callback() {
		
			@Override
			public void onFailure(Call call, IOException e) {
				call.cancel();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
					final String myResponse = response.body().string();
					Log.i("CKSMS-RUNR", "END SUCCESSFULLY CALLED");
					Log.i("CKSMS-RUNR", myResponse);
				}
        });
	}
}