package com.andrew749.decisions;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText numberfield;
	EditText optionfield;
	Button numberbutton;
	Button run;
	Button optionbutton;
	ListView resultlist;
	private int testingnumber = 0;
	Random gennumber;
	ListAdapter la;
	private com.andrew749.decisions.SpecialAdapter adapter;
    ArrayList<Entry>testingoptions=new ArrayList<Entry>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		optionfield = (EditText) findViewById(R.id.editText1);
		numberfield = (EditText) findViewById(R.id.editText2);
		numberbutton = (Button) findViewById(R.id.button2);
		optionbutton = (Button) findViewById(R.id.button1);
		resultlist = (ListView) findViewById(R.id.listView1);
		run = (Button) findViewById(R.id.button3);


		adapter = new SpecialAdapter(getApplicationContext(),testingoptions);
		resultlist.setAdapter(adapter);
		gennumber = new Random(testingoptions.size());
		//determines how many times an option will be tested
		numberbutton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try {
					testingnumber = Integer.parseInt(numberfield.getText()
							.toString());
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"Please enter a valid number!", Toast.LENGTH_LONG)
							.show();
				} finally {
					
				}

				numberfield.setText("");
			}
		});
		//determines the options which will be tested
		optionbutton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try{
				testingoptions.add(new Entry(optionfield.getText().toString()));
				}catch(Exception e){
					Toast.makeText(getApplicationContext(),
							"You have selected too many options", Toast.LENGTH_LONG)
							.show();
				}
				optionfield.setText("");
				adapter.notifyDataSetChanged();
			}
		});
		//tests the options
		run.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
                for(int i=0;i<testingoptions.size();i++){
                    testingoptions.get(i).clearValue();
                }
				testoptions();
				adapter.notifyDataSetChanged();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
//options menu after input
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.exit:
			finish();
			return true;
		case R.id.clear:
			for (int i = 0; i < testingoptions.size(); ++i) {
				testingoptions.remove(i);
			}
			adapter.notifyDataSetChanged();
			return true;
		/**case R.id.switchmode:
			
			return true;**/
		}
		return super.onOptionsItemSelected(item);
	}
//tests every option the specified amount of times
	public void testoptions() {
		gennumber = new Random();
		for (int i = 0; i < testingnumber; ++i) {
			int numToIncrement=gennumber.nextInt(testingoptions.size());
            testingoptions.get(numToIncrement).incrementNumber();
		}
	}

}