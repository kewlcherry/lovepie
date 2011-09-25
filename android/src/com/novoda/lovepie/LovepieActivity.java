package com.novoda.lovepie;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import roboguice.inject.InjectResource;
import roboguice.util.RoboAsyncTask;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.paypal.android.MEP.CheckoutButton;
import com.paypal.android.MEP.PayPal;
import com.paypal.android.MEP.PayPalAdvancedPayment;
import com.paypal.android.MEP.PayPalReceiverDetails;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LovepieActivity extends RoboActivity implements OnClickListener, OnItemClickListener, OnItemLongClickListener {
	@InjectView(R.id.amount_field) EditText amount;
	@InjectView(R.id.paypal_layout) RelativeLayout payPalLayout;
	@InjectView(R.id.charity_list) ListView list;
	@InjectView(R.id.progress) ProgressBar progress;
	@InjectView(R.id.loading_text) TextView loadingText;
	@InjectResource(R.string.loading_fail) String loadingFail;
	@InjectResource(R.color.selected) Integer selectedId;
	@InjectResource(R.color.white) Integer unselectedId;
	@Inject SensorManager sensorManager;
	@Inject Vibrator vibrator;
	
	private static final int REQUEST_CODE = 1;
	private static final int MAX_TRIES = 30;
	
	private PayPal payPal;
	private CheckoutButton payButton;
	private Handler handler = new Handler();
	private int tries = 0;
	
	private float accel; 
	private float accelCurrent; 
	private float accelLast;
	 
	private final SensorEventListener mSensorListener = new SensorEventListener() {

		public void onSensorChanged(SensorEvent se) {
			float x = se.values[0];
			float y = se.values[1];
			float z = se.values[2];
			accelLast = accelCurrent;
			accelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
			float delta = accelCurrent - accelLast;
			accel = accel * 0.9f + delta;
			
			if (accel > 5) {
				randomList();
			}
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
	
	private List<Charity> charityList;
	private ArrayList<Boolean> selectedPositions = new ArrayList<Boolean>();
	
	private void randomList() {
		
		vibrator.vibrate(1000);
		randomise();
		while (getNumSelected() > 6 || getNumSelected() == 0) {
			randomise();
		}
	}

	private void randomise() {
		for (int i = 0; i < selectedPositions.size(); i++) {
			boolean rand = new Random().nextBoolean();
			selectedPositions.set(i, rand);
			list.setAdapter(new CharityAdapter(LovepieActivity.this, charityList, selectedPositions));
		}
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lovepie);
        handler.post(loadingChecker);
        new GetCharitiesTask().execute();
        
       
        sensorManager.registerListener(mSensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        accel = 0.00f;
        accelCurrent = SensorManager.GRAVITY_EARTH;
        accelLast = SensorManager.GRAVITY_EARTH;
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	sensorManager.registerListener(mSensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    @Override
    public void onPause() {
    	sensorManager.unregisterListener(mSensorListener);
        super.onStop();
    }
    
	private Runnable loadingChecker = new Runnable() {
		
		@Override
		public void run() {
			LovepieApplication app = (LovepieApplication) getApplication();
			payPal = app.payPal;
			if (payPal != null) {
				onPayPalLoaded();
			} else if (tries < MAX_TRIES) {
				handler.postDelayed(this, 500);
				tries++;
			} else {
				progress.setVisibility(View.GONE);
				loadingText.setText(loadingFail);
			}
		}
	};
	
	private void onPayPalLoaded() {
		progress.setVisibility(View.GONE);
		loadingText.setVisibility(View.GONE);
		payButton = payPal.getCheckoutButton(this, PayPal.BUTTON_278x43, CheckoutButton.TEXT_DONATE);
		payButton.setLayoutParams(buttonParams());
		payButton.setOnClickListener(this);
		payPalLayout.addView(payButton);
	}
	
	private LayoutParams buttonParams() {
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		return params;
	}
    
	@Override
    public void onClick(View view) {
		int selected = getNumSelected();
		if (amount.getText().toString().equals("")) {
			Toast.makeText(this, R.string.empty_amount, Toast.LENGTH_SHORT).show();
		} else if (selected > 6) {
			Toast.makeText(this, R.string.max_charities, Toast.LENGTH_SHORT).show();
		} else if (selected == 0) {
			Toast.makeText(this, R.string.min_charities, Toast.LENGTH_SHORT).show();
		} else {
			String entered = (String) amount.getText().toString();
	    	pay(entered);
		}
		onPayPalLoaded();
    }
	
	private int getNumSelected() {
		int count = 0;
		for (boolean isSelected : selectedPositions) {
			if(isSelected) {
				count++;
			}
		}
		return count;
	}

	private void pay(String entered) {
    	Double amountEach = Double.parseDouble("" + entered) / Double.parseDouble("" + getNumSelected());
    	BigDecimal bigNum = BigDecimal.valueOf(amountEach);
    	
    	ArrayList<PayPalReceiverDetails> recievers = new ArrayList<PayPalReceiverDetails>();
    	for (int i = 0; i < charityList.size(); i++) {
    		if (selectedPositions.get(i)) {
    			String invoiceId = generateInvoiceString(i);    			
    			PayPalReceiverDetails receiver = new PayPalReceiverDetails();
    			//receiver.setRecipient("seller_1316894385_biz@novoda.com");
    			receiver.setRecipient(charityList.get(i).getReceiver_email());
    			receiver.setSubtotal(bigNum);
    			receiver.setPaymentType(PayPal.PAY_TYPE_PARALLEL);
    			receiver.setPaymentSubtype(PayPal.PAYMENT_SUBTYPE_DONATIONS);
    			receiver.setDescription(invoiceId);
    			recievers.add(receiver);
    		}
    	}
    	
    	PayPalAdvancedPayment donation = new PayPalAdvancedPayment();
    	donation.setCurrencyType("GBP");
    	donation.setReceivers(recievers);
    	Intent checkoutIntent = PayPal.getInstance().checkout(donation, this);
    	startActivityForResult(checkoutIntent, REQUEST_CODE);
	}

	private String generateInvoiceString(int pos) {
		Charity charity = charityList.get(pos);
		String id = charity.getToken_for_invoice_id();
		String name = charity.getNonprofit_name();
		String invoiceId = id + " Donation via Missionfish for " + name;
		return invoiceId;
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == REQUEST_CODE) {
			switch (resultCode) {
			case Activity.RESULT_OK:
				Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
				break;
			case Activity.RESULT_CANCELED:
				Toast.makeText(this, R.string.failure, Toast.LENGTH_LONG).show();
			}
		}
	}
	
	public class GetCharitiesTask extends RoboAsyncTask<Void> {
		
		private static final String SERVER_URL = "http://love-pie.appspot.com/charity";
		private List<Charity> charities;
	    
	    public Void call() throws Exception {
	    	AndroidHttpClient client = AndroidHttpClient.newInstance("LovepieAndroid/1.0");
	    	HttpResponse response = client.execute(new HttpGet(SERVER_URL));
	    	InputStream stream = response.getEntity().getContent();
	    	parse(stream);
	    	client.close();
			return null;
	    } 
	    
	    private void parse(InputStream stream) {
	    	try{
	            Gson gson = new Gson();
	            Reader reader = new InputStreamReader(stream);
	            charities = gson.fromJson(reader, Charities.class);
	        } catch(Exception e) {
	        	e.printStackTrace();
	        }
		}

		@Override 
	    protected void onPreExecute() { 
			// Spinner
	    } 
	    
	    @Override 
	    protected void onException(Exception e) { 
	        Toast.makeText(LovepieActivity.this, R.string.connection_fail, Toast.LENGTH_LONG).show();
	    } 
	    
	    @Override
	    protected void onSuccess(Void v) {
	    	charityList = charities;
	    	for (int i = 0; i < charityList.size(); i++) {
	    		selectedPositions.add(false);
	    	}
	    	list.setAdapter(new CharityAdapter(LovepieActivity.this, charityList, selectedPositions));
	    	list.setOnItemClickListener(LovepieActivity.this);
	    	list.setOnItemLongClickListener(LovepieActivity.this);
	    }
	    
	    @Override 
	    protected void onFinally() { 
	    	// Remove spinner
	    } 
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (selectedPositions.get(position)) {
			selectedPositions.set(position, false);
			view.setBackgroundColor(unselectedId);
		} else {
			selectedPositions.set(position, true);
			view.setBackgroundColor(selectedId);
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position,long id) {
		Charity charity = charityList.get(position);
		Intent intent = new Intent(this, CharityActivity.class);
		intent.putExtra(CharityActivity.NAME, charity.getNonprofit_name());
		intent.putExtra(CharityActivity.STATEMENT, charity.getStatement());
		intent.putExtra(CharityActivity.IMAGE_URL, charity.getLogo_path());
		intent.putExtra(CharityActivity.URL, charity.getWeb_url());
		startActivity(intent);
		return false;
	}
	
}