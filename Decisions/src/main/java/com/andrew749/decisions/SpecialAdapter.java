package com.andrew749.decisions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SpecialAdapter extends BaseAdapter {
	private LayoutInflater inflater = null;
	private Context mcontext;
	private ArrayList<Entry> roptions=new ArrayList<Entry>();

	public SpecialAdapter(Context context, ArrayList<Entry> options) {
		mcontext = context;
		roptions = options;
		inflater = (LayoutInflater) mcontext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
        String temp;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listitem, parent, false);
			holder = new ViewHolder();
			holder.optionTextView = (TextView) convertView
					.findViewById(R.id.option);
			holder.resultTextView = (TextView) convertView
					.findViewById(R.id.testingnumber);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();

		}
		holder.optionTextView.setText(roptions.get(position).name);
		temp=""+roptions.get(position).number;
		holder.resultTextView.setText(temp);

		return convertView;
	}

	public int getCount() {
		return roptions.size();
	}

	public Object getItem(int arg0) {
		return roptions.get(arg0);
	}

	public long getItemId(int arg0) {
		return arg0;
	}
//Viewholder
	static class ViewHolder {
		TextView optionTextView;
		TextView resultTextView;
	}
}