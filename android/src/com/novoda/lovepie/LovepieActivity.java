package com.novoda.lovepie;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import roboguice.inject.InjectResource;

import com.paypal.android.MEP.CheckoutButton;
import com.paypal.android.MEP.PayPal;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    	
    }
    
}