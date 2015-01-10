package com.DataShare.NetPool;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	WifiApManager wifiApManager;
	Button setHotSpot; 
	WifiConfiguration wifiConfig;
	WifiManager wifi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setHotSpot = (Button) findViewById(R.id.set_hotspot);
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		wifiApManager = new WifiApManager(this);
		
		wifiConfig=new WifiConfiguration();
		wifiConfig.SSID = "\"SSIDName\"";
		wifiConfig.preSharedKey  = "\"password\"";
		wifiConfig.hiddenSSID = true;
		wifiConfig.status = WifiConfiguration.Status.ENABLED;        
		wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
		wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
		wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
		
		
		setHotSpot.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				int res = wifi.addNetwork(wifiConfig);
			    Log.d("WifiPreference", "add Network returned " + res );
			    boolean b = wifi.enableNetwork(res, true);        
			    Log.d("WifiPreference", "enableNetwork returned " + b );

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
