package com.example.app42sample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app42sample.Util;
import com.example.app42sample.RestClient;
import com.example.app42sample.RestClient.RequestMethod;
import com.iiitm.app42sample.R;
import com.shephertz.app42.paas.sdk.android.App42API;

public class MainActivity extends Activity {
	private static String url="http://www.iciis2014.org/conference/show.php";
	private ProgressBar spinner; ProgressDialog progress;
TextView t1,t2,t3,t4;String paper,result;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedule);
	//	spinner = (ProgressBar)findViewById(R.id.progressBar1);
	  
		
	//	if (android.os.Build.VERSION.SDK_INT > 9) {
	  //          StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    //        StrictMode.setThreadPolicy(policy);
	      //    }
		 paper= getIntent().getExtras().getString("paperid");
	        
		  progress = new ProgressDialog(this);
		 progress.setTitle("Loading");
		 progress.setMessage("Please Wait while loading...");
		 progress.show();
		 
			FillDataTask f=new FillDataTask();
			     f.execute(); 
			
					
				
	
		
	}
	private class FillDataTask extends AsyncTask<Context, Integer, String>
	 {
	   
			@Override
			protected String doInBackground(Context... params) {
				/*App42API.initialize(
			               getApplicationContext(),
			                "843d02f836f34de72e195cd550223c41cc3e3b823d3813012103109765814caf",
			                "a1e0c480942473bc56aa95e778b997949f641df747d36cf5ac32ef155f07a5ef");
			        App42API.setLoggedInUser(paper) ;
			        Util.registerWithApp42("638278271530");
				*/
				 RestClient client = new RestClient(url);
					client.AddHeader("Connection", "keep-alive");
					client.AddHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
					client.AddHeader("X-Requested-With", "XMLHttpRequest");
					client.AddParam("paperid",paper);
					try {
						client.Execute(RequestMethod.POST );
						 result=client.getResponse();
						//  v.setText(result);
			//			Toast.makeText(getApplicationContext(),result,1000).show();
						Log.d("Reply: ", "**********"+ result);
					} 
					catch (Exception e) 
						{
						e.printStackTrace();
						}
				return result;
			
			
			
			}
			@Override
			 protected void onPostExecute(String value) {
				 super.onPostExecute(value);   
				 // dismiss the dialog once done
				 progress.dismiss();
			     
			    
			    		String b[]=result.split(",");
			    	
					t1=(TextView)findViewById(R.id.TextView02);
					t2=(TextView)findViewById(R.id.TextView08);
					t3=(TextView)findViewById(R.id.TextView05);
					t4=(TextView)findViewById(R.id.TextView52);
					
				if(result.equalsIgnoreCase(b[0]))
					{
					//Log.d("Reply: ", "**********"+ result);
						//Intent j=new Intent(getApplicationContext(),MainActivity1.class);
				      //	startActivity(j);
					t1.setError("paper id not found please press back button and enter correct one");
					Toast.makeText(getApplicationContext(), "paper id not found please press back button and enter correct one", 1000).show();
					}
					else
					{    
						b[0]=b[0].substring(8,b[0].length()-4);
						b[1]=b[1].substring(5);
						b[2]=b[2].substring(5,b[2].length());
						b[3]=b[3].substring(5,b[3].length()-5);
					t1.setText(b[0]);
					t2.setText(b[1]);
					t3.setText(b[2]);
					t4.setText(b[3]);
					}
				 

			    }

			
			}
	
	
	
	
}
			


