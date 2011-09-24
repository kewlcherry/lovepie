package com.novoda.lovepie;

import roboguice.application.RoboApplication;

import com.paypal.android.MEP.PayPal;

public class LovepieApplication extends RoboApplication {
	
	private static final String APP_ID = "APP-80W284485P519543T";
	public PayPal payPal;
	
	@Override
	public void onCreate() {
		super.onCreate();
		new LoveLoader().start();
	}
	
	private class LoveLoader extends Thread {
		
		@Override
		public void run() {
			payPal = PayPal.initWithAppID(getApplicationContext(), APP_ID, PayPal.ENV_NONE);
			payPal.setShippingEnabled(false);
		}
	}

}
