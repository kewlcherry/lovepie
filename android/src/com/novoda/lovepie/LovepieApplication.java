package com.novoda.lovepie;

import roboguice.application.RoboApplication;

import com.novoda.imageloader.core.BaseImageLoader;
import com.novoda.imageloader.core.ImageManager;
import com.novoda.imageloader.core.Settings;
import com.novoda.imageloader.core.SettingsBuilder;
import com.paypal.android.MEP.PayPal;

public class LovepieApplication extends RoboApplication {
	
	private static final String APP_ID = "APP-80W284485P519543T";
	public PayPal payPal;
	
	private static ImageManager imageLoader;
	
	@Override
	public void onCreate() {
		super.onCreate();
		new LoveLoader().start();
		
		SettingsBuilder builder = new SettingsBuilder();
	    builder.defaultImageId(R.drawable.logo_pie);
	    Settings settings = builder.build(this);
	    imageLoader = new BaseImageLoader(this, settings);
	}
	
	private class LoveLoader extends Thread {
		
		@Override
		public void run() {
			payPal = PayPal.initWithAppID(getApplicationContext(), APP_ID, PayPal.ENV_NONE);
			payPal.setShippingEnabled(false);
		}
	}
	
	public static ImageManager getImageLoader() {
		return imageLoader;
	}

}
