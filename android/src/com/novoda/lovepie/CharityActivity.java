package com.novoda.lovepie;

import com.novoda.imageloader.core.ImageManager;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CharityActivity extends RoboActivity {
	
	@InjectView(R.id.charity_logo) ImageView logo;
	@InjectView(R.id.charity_name) TextView name;
	@InjectView(R.id.charity_statement) TextView statement;
	
	public static final String NAME = "name";
	public static final String STATEMENT = "statement";
	public static final String IMAGE_URL = "image_url";
	public static final String URL = "url";
	
	private ImageManager imageLoader;
	
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
    }

}
