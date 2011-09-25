package com.novoda.lovepie;

import com.novoda.imageloader.core.ImageManager;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CharityActivity extends RoboActivity implements OnClickListener {
	
	@InjectView(R.id.charity_logo) ImageView logo;
	@InjectView(R.id.charity_name) TextView name;
	@InjectView(R.id.charity_statement) TextView statement;
	
	public static final String NAME = "name";
	public static final String STATEMENT = "statement";
	public static final String IMAGE_URL = "image_url";
	public static final String URL = "url";
	
	private ImageManager imageLoader;
	private String url;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.charity);
        imageLoader = LovepieApplication.getImageLoader();
        Intent intent = getIntent();
        name.setText(intent.getStringExtra(NAME));
        statement.setText(intent.getStringExtra(STATEMENT));
        String logoUrl = intent.getStringExtra(IMAGE_URL);
        logo.setTag(logoUrl);
        imageLoader.load(logoUrl, this, logo);
        logo.setOnClickListener(this);
        url = intent.getStringExtra(URL);
    }

	@Override
	public void onClick(View v) {
		startUri();  
	}

	private void startUri() {
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + url));
		startActivity(i);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.charity_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

	    switch (item.getItemId()) {
	    case R.id.menu_website:
	        startUri();
	        return true;
	    }
	    return false;
	}

}
