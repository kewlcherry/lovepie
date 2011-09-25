package com.novoda.lovepie;

import java.util.List;

import com.novoda.imageloader.core.ImageManager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CharityAdapter extends ArrayAdapter<Charity> {
	
	private Activity context;
	private List<Charity> charities;
	private ImageManager imageLoader;
	
	public CharityAdapter(Activity context, List<Charity> charities) {
		super(context, R.layout.list_charity, charities);
		this.context = context;
		this.charities = charities;
		imageLoader = LovepieApplication.getImageLoader();
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View charity = convertView;
		CharityHolder holder = null;
		
		if (charity == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			charity = inflater.inflate(R.layout.list_charity, parent, false);
			holder = new CharityHolder(charity);
			charity.setTag(holder);
		} else {
			holder = (CharityHolder) charity.getTag();
		}
		
		Charity charityData = charities.get(position);
		holder.getName().setText(charityData.getNonprofit_name());
		holder.getStatement().setText(charityData.getStatement());
		ImageView imView = holder.getImage();
		imView.setTag(charityData.getLogo_path());
		imageLoader.load(charityData.getLogo_path(), context, imView);
		
		return charity;
	}
	
	private class CharityHolder {
		
		View base;
		TextView name = null;
		TextView statement = null;
		ImageView image = null;
		
		CharityHolder(View base) {
			this.base = base;
		}
		
		TextView getName() {
			if (name == null) {
				name = (TextView) base.findViewById(R.id.list_title);
			}
			return name;
		}
		
		TextView getStatement() {
			if (statement == null) {
				statement = (TextView) base.findViewById(R.id.list_body);
			}
			return statement;
		}
		
		ImageView getImage() {
			if (image == null) {
				image = (ImageView) base.findViewById(R.id.list_image);
			}
			return image;
		}
	}

}
