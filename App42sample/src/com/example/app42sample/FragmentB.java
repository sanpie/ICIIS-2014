package com.example.app42sample;




import com.iiitm.app42sample.R;
import com.shephertz.app42.paas.sdk.android.App42API;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Geek on 02-11-2014.
 */
public class FragmentB extends Fragment {
	EditText e;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
       
    	  View view = inflater.inflate(R.layout.fragment_b, container, false);
    	    e = (EditText) view.findViewById(R.id.editText);
    	   
    	  
    	    return view;
    	
    
     
    }
    public void start(View v)
    { App42API.initialize(
    		getActivity(),
            "843d02f836f34de72e195cd550223c41cc3e3b823d3813012103109765814caf",
            "a1e0c480942473bc56aa95e778b997949f641df747d36cf5ac32ef155f07a5ef");
    App42API.setLoggedInUser("krishna") ;
    Util.registerWithApp42("638278271530");
   /* 	String s=e.getText().toString();
    	Intent i=new Intent(getActivity(),MainActivity.class);
    	i.putExtra("paperid", s);
    	getActivity().startService(i);
    */}
	
}
