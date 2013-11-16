package com.example.visiblestars;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GridView grid;
	private GridAdapter adapter;
	
	private List<StarData>sdata;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		RelativeLayout rl = (RelativeLayout)findViewById(R.id.relative);
		rl.setBackgroundColor(Color.BLACK);
		
		grid = (GridView)findViewById(R.id.gridView);
		grid.setBackgroundColor(Color.BLACK);

		grid.setHorizontalSpacing(5);
		
		grid.setVerticalSpacing(10);
		
		grid.setNumColumns(2);
		
		grid.setColumnWidth(40);
		
		sdata = new ArrayList<StarData>();
		
		add(sdata,"M0","A");
		add(sdata,"M10","B");
		add(sdata,"M201","C");
		add(sdata,"M21","B");
		add(sdata,"M2","A");
		add(sdata,"M30","B");
		
		
		adapter  = new GridAdapter(getApplicationContext(),sdata);
		grid.setAdapter(adapter);
		
		int maxwidth = adapter.getMaxWidth();
		
		grid.setColumnWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
		
		grid.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				Toast.makeText(MainActivity.this, ""+pos, Toast.LENGTH_SHORT).show();	
				
			}
			
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void add(List<StarData>lsd,String n,String t){
		StarData sd = new StarData();
		sd.name = n;
		sd.type = t;
		lsd.add(sd);
	}

}
