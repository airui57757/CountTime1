package com.airui.counttime;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity  implements OnClickListener{
	
	private EditText inputEdit;
	private Button getTime , startTime , stopTime;
	private TextView time;
	private int i=0;
	private Timer timer = null;
	private TimerTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
       
    }
    
    private void initView(){
    	inputEdit = (EditText)findViewById(R.id.input_time);
        getTime = (Button)findViewById(R.id.get_time);
        startTime = (Button)findViewById(R.id.start_time);
        stopTime =(Button)findViewById(R.id.stop_time);
        time =  (TextView) findViewById(R.id.time);
        
        getTime.setOnClickListener(this);
        startTime.setOnClickListener(this);
        stopTime.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.get_time:
			time.setText(inputEdit.getText().toString());
			i = Integer.parseInt(inputEdit.getText().toString());
			break;
		case R.id.start_time:
			startTime();
			break;
		case R.id.stop_time:
			stopTime();
			break;
			default:
				break;
		}
		
	}
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			time.setText(msg.arg1 +"");
			startTime();
		};
	};
	
	public void startTime(){
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				i--;
				Message message =  mHandler.obtainMessage();
				message.arg1 = i;
				mHandler.sendMessage(message);
				
			}
		};
		timer.schedule(task, 1000);
	}
	
	public void stopTime(){
		task.cancel();		
	}
}
