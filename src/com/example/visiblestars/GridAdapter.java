package com.example.visiblestars;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

	private List<StarData>stars;
	private Context context;
	
	private float textSize = 28.0f;
	
	private int numColumn = 2;
	
	private int maxWidth = 0;
	
	private List<String> starsString;
	
	public int getMaxWidth(){
		return maxWidth;
	}
	
	public GridAdapter(Context c,List<StarData> stars) {
		this.stars = stars;
		context = c;
		starsString = new ArrayList<String>();
		
		for(StarData sd : stars){
			starsString.add(sd.name);
			starsString.add(sd.type);
		}
		
		
		Paint p = new Paint();
		p.setTextSize(textSize);
		
		Rect rect = new Rect(); 
		for(String str:starsString){
			p.getTextBounds(str, 0, str.length(), rect);
			
			if(rect.right > maxWidth) 
				maxWidth = rect.right;
		}
				
		
	}

	@Override
	public int getCount() {
		return starsString.size();
	}

	@Override
	public String getItem(int position) {
		return starsString.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		TextView tv;
		
		if(convertView == null){
			
			Log.i("KJW","convertView is null");
			
			tv = new TextView(context){

				@Override
				protected void onDraw(Canvas canvas) {
					// TODO Auto-generated method stub
					super.onDraw(canvas);
					
					Rect bounds = new Rect();
					TextPaint p1 = this.getPaint();
					
					Rect rect = canvas.getClipBounds();
					
					rect.right = maxWidth + (int)textSize/2; // (int) (bounds.right+ p1.getTextSize()/2);
					
					Log.i("KJW","(" + rect.left + "," + rect.top + "," + rect.right + "," + rect.bottom + ")");
					
					Paint p = new Paint();
					p.setColor(Color.YELLOW);
					p.setStyle(Style.STROKE); 
					p.setStrokeWidth(1.5f);
					p.setAntiAlias(true);
					canvas.drawRect(rect, p);
					
				}
				
			};
			
		}else{
			tv = (TextView)convertView;
		}
		
		tv.setPadding(2,2,2,2);
		tv.setText(starsString.get(position));
		
		return tv;
	}

}
