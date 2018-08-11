package com.example.divyansh.letsmakeitwork;


        import android.app.Activity;
        import android.content.Context;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends Activity
{
    private LocationManager lm;
    private LocationListener locationListener;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chalao();
    }

    public void chalao()
    {
        final Handler h = new Handler();
        final int delay = 1000; //milliseconds
        h.postDelayed(new Runnable(){
            public void run(){
                letsTry();
                h.postDelayed(this, delay);
            }
        }, delay);
    }
    public void displayValues(String Value) {
        TextView ValueView = (TextView) findViewById(R.id.values);
        ValueView.setText(String.valueOf(Value));
    }
    int counter=0;
    private void letsTry() {
        counter++;
        lm = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        Location loc = null;
        try {
            lm.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0,
                    locationListener);
            loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } catch (SecurityException e) {
            Toast.makeText(getBaseContext(),
                    "Something's wrong! ",
                    Toast.LENGTH_SHORT).show();
        }
        if (loc != null) {

            displayValues("Location "+counter+ ": Lat: " + loc.getLatitude() +
                    " Lng: " + loc.getLongitude() +" Alt: "+loc.getAltitude()+" Speed: " + loc.getSpeed());
        }
        else
        {
            Toast.makeText(getBaseContext(),
                    "Bohot kuch galat hai! ",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void onClick(View view)
    {
        System.runFinalizersOnExit(true);
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    private class MyLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location loc) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onStatusChanged(String provider, int status,
                                    Bundle extras) {
            // TODO Auto-generated method stub
        }
    }
}