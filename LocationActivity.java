package com.st.devicefeaturesdemo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class LocationActivity extends Activity {
	private TextView textLocation;
	private LocationManager locationManager;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
		textLocation = (TextView) this.findViewById(R.id.textLocation);
		locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
	}

	public String getProvider(LocationManager locman) {
		List<String> providers = locman.getProviders(true);
		for (String provider : providers) {
			if (provider.equalsIgnoreCase(LocationManager.GPS_PROVIDER)) {
				return LocationManager.GPS_PROVIDER;
			}
			if (provider.equalsIgnoreCase(LocationManager.NETWORK_PROVIDER)) {
				return LocationManager.NETWORK_PROVIDER;
			}
		}
		return null;
	}

	public void getLocation(View v) {
		String providername = getProvider(locationManager);
		textLocation.append("Location provider : " + providername + "\n");

		// get current location info
		try {
			Location location = locationManager.getLastKnownLocation(providername);
			if (location != null)
				textLocation
						.append(String.format(
								"Latitude   : %f , Longitude :%f\n%s\n",
								location.getLatitude(),
								location.getLongitude(),
								getAddress(location.getLatitude(),
										location.getLongitude())));
			else
				textLocation.append("Current location is not known\n");
		}
		catch(Exception ex) {
			textLocation.append("Location is not enabled!\n");
		}
	}

	LocationListener locationListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
			textLocation
					.append(String.format(
							"Latitude   : %f , Longitude :%f\n%s\n",
							location.getLatitude(),
							location.getLongitude(),
							getAddress(location.getLatitude(),
									location.getLongitude())));

		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			switch (status) {
			case LocationProvider.AVAILABLE:
				textLocation.append("Location provider is available\n");
				break;
			case LocationProvider.OUT_OF_SERVICE:
				textLocation.append("Location provider is out of service\n");
				break;
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				textLocation
						.append("Location provider is temporarily unavailable\n");
				break;
			}
		}

		public void onProviderEnabled(String provider) {
		}

		public void onProviderDisabled(String provider) {
		}
	};

	public void activateListener(View v) {
		// Register the listener with the Location Manager to receive location
		// updates
        try {
			locationManager.requestLocationUpdates(getProvider(locationManager), 0,	0, locationListener);
			Log.d("LocationActivity", "Activiate Listener");
		}
		catch(Exception ex) {
			Log.d("LocationActivity","Cannot obtain location updates");
		}
	}

	public String getAddress(double latitude, double longitude) {

		Geocoder geocoder = new Geocoder(this, Locale.getDefault());
		List<Address> addresses = null;
		try {
			// Call the synchronous getFromLocation() method by passing in the
			// lat/long values.
			addresses = geocoder.getFromLocation(latitude, longitude, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (addresses != null && addresses.size() > 0) {
			Address address = addresses.get(0);
			// Format the first line of address (if available), city, and
			// country name.
			String addressText = String.format("%s, %s, %s", address
					.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0)
					: "", address.getLocality(), address.getCountryName());
			return addressText;

		}
		return null;
	}

}
