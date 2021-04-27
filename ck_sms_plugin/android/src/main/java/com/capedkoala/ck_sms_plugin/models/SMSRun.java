package com.capedkoala.ck_sms_plugin.models;
import com.capedkoala.ck_sms_plugin.models.SMSResult;

public class SMSRun {
	public String id;
	public String test_id;
	public String name;
	public int device_specified;
	public String status;
	public int delay_override;
	public int deleted;
	public int archived;
	public String scheduled;
	public String created;
	public String updated;
	public String alias;
	public SMSResult[] sms_results;
}


    // {
    //   "id": "5",
    //   "test_id": 1,
    //   "name": "Test 5",
    //   "device_specified": 0,
    //   "status": "Pending",
    //   "delay_override": 5,
    //   "deleted": 0,
    //   "archived": 0,
    //   "owner": null,
    //   "scheduled": "2021-02-25T16:31:41.998Z",
    //   "created": "2021-02-17T16:31:48.981Z",
    //   "updated": "2021-02-17T16:31:48.981Z",
    //   "alias": "Any Device",
    //   "sms_results": [
    //     {
    //       "id": 3,
    //       "sms_id": 1,
    //       "sms_test_run_id": 5,
    //       "device_used": null,
    //       "status": "Pending",
    //       "deleted": 0,
    //       "archived": 0,
    //       "ad_hoc": 0,
    //       "sent": null,
    //       "received": null,
    //       "created": "2021-02-17T16:31:49.027266+00:00",
    //       "updated": "2021-02-17T16:31:49.027266+00:00",
    //       "to": "+447983357260",
    //       "message": "Trial Message",
    //       "alias": "initial_message"
    //     }
    //   ]
    // },