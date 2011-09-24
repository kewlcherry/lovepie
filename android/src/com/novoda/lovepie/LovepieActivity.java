package com.novoda.lovepie;

import java.math.BigDecimal;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import roboguice.inject.InjectResource;

import com.paypal.android.MEP.CheckoutButton;
import com.paypal.android.MEP.PayPal;
import com.paypal.android.MEP.PayPalPayment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LovepieActivity extends RoboActivity implements OnClickListener {
	@InjectView(R.id.amount_field) EditText amount;
	@InjectView(R.id.paypal_layout) RelativeLayout payPalLayout;
	@InjectView(R.id.progress) ProgressBar progress;
	@InjectView(R.id.loading_text) TextView loadingText;
	@InjectResource(R.string.loading_fail) String loadingFail;
	
	private static final int REQUEST_CODE = 1;
	private static final int MAX_TRIES = 30;
	
	private PayPal payPal;
	private CheckoutButton payButton;
	private Handler handler = new Handler();
	private int tries = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lovepie);
        handler.post(loadingChecker);
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
		if (amount.getText().toString().equals("")) {
			Toast.makeText(this, R.string.empty_amount, Toast.LENGTH_SHORT).show();
		} else {
			String entered = (String) amount.getText().toString();
	    	Double amount = Double.parseDouble(entered);
	    	BigDecimal finalAmount = BigDecimal.valueOf(amount);
	    	pay(finalAmount);
		}
    }
	
	private void pay(BigDecimal amount) {
		PayPalPayment donation = new PayPalPayment();
    	donation.setCurrencyType("GBP");
    	donation.setPaymentType(PayPal.PAY_TYPE_PARALLEL);
    	donation.setPaymentSubtype(PayPal.PAYMENT_SUBTYPE_DONATIONS);
    	donation.setSubtotal(amount);
    	donation.setRecipient("carl@novoda.com");
    	Intent checkoutIntent = PayPal.getInstance().checkout(donation, this);
    	startActivityForResult(checkoutIntent, REQUEST_CODE);
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
    
}