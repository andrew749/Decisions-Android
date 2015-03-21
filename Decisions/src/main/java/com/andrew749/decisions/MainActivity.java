package com.andrew749.decisions;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

public class MainActivity extends ActionBarActivity {
	EditText numberfield;
	EditText optionfield;
	Button run;
	FloatingActionButton optionbutton;
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
		optionbutton = (FloatingActionButton) findViewById(R.id.add);
		resultlist = (ListView) findViewById(R.id.listView1);
		run = (Button) findViewById(R.id.button3);


		adapter = new SpecialAdapter(getApplicationContext(),testingoptions);
		resultlist.setAdapter(adapter);
		gennumber = new Random(testingoptions.size());

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
                if(numberfield.getText().length()>0&&testingoptions.size()>0) {
                    testingnumber = Integer.parseInt(numberfield.getText()
                            .toString());
                    for (int i = 0; i < testingoptions.size(); i++) {
                        testingoptions.get(i).clearValue();
                    }
                    testoptions();
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getApplicationContext(),"Please Fill out the Fields",Toast.LENGTH_SHORT);
                }
			}
		});
        resultlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteEntry(position);
                return true;
            }
        });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
    private void deleteEntry(int i){
        testingoptions.remove(i);
        adapter.notifyDataSetChanged();
    }
//options menu after input
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.exit:
			finish();
			return true;
		case R.id.clear:
			testingoptions.clear();
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