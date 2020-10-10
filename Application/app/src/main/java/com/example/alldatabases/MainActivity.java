package com.example.alldatabases;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class MainActivity extends AppCompatActivity {

    String myJSON;
    double[] addressArr;
    TMapPolyLine tMapPolyLine;
    Boolean findloadState = false;
    double cLat=0.0001, cLong=0.0001;
    double mLat=0.00002, mLong=0.00002;
    double distanceFlag = 600;
    float location_interval = 0.1f; // 통지사이의 최소 변경거리 (m)
    int location_time = 100; // 통지사이의 최소 시간간격 (miliSecond)
    double latitude;
    double longitude;
    private BluetoothSPP bt;
    TMapPoint endAdd;
    ArrayList<TMapMarkerItem> databaseMarker = new ArrayList<TMapMarkerItem>();
    double bluetoothFlag = 1000;
    double bluetoothMin = 100;
    double bluetoothMax = 2800;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_LATI = "lati";
    private static final String TAG_LONGI = "longi";

    private final String urladdress= "192.168.81.72";
    TMapView tmapview;
    float s_lati, l_lati, s_longi, l_longi;
    float ds_lati, dl_lati,ds_longi, dl_longi;
    float rs_lati, rl_lati, rs_longi, rl_longi;


    ArrayList<HashMap<String, String>> personList;
    public JSONArray peoples = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkUtil.setNetworkPolicy();



        // 티맵 서비스 구현
        RelativeLayout mapContainer = (RelativeLayout) findViewById(R.id.mapContainer);
        tmapview = new TMapView(this);
        tmapview.setSKTMapApiKey("l7xx67f4c8be2c2d4059a542bd9d6a3aa3ad");
        mapContainer.addView(tmapview);
        tmapview.setIconVisibility(true);

        // 온클릭 리스너
        FloatingActionButton doGetaddress = findViewById(R.id.findLoad);
        doGetaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),AddressGet.class);
                addressArr = new double[2];
                intent.putExtra("requestAddress",addressArr);
                startActivityForResult(intent, 1);
            }
        });


        //
        setGps();

        //
        personList = new ArrayList<HashMap<String, String>>();



        // 블루투스 설정
        bt = new BluetoothSPP(this); //Initializing
        ActionBar ab = getSupportActionBar() ;

        // 블루투스 상태 검사
        if (!bt.isBluetoothAvailable()) { //블루투스 사용 불가
            Toast.makeText(getApplicationContext()
                    , "Bluetooth is not available"
                    , Toast.LENGTH_SHORT).show();
            finish();
        }

        // 블루투스 연결 리스너
        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() { //연결됐을 때
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getApplicationContext(), "Connected to " + name + "\n" + address, Toast.LENGTH_SHORT).show();
            }
            public void onDeviceDisconnected() { //연결해제
                Toast.makeText(getApplicationContext(), "Connection lost", Toast.LENGTH_SHORT).show();
            }
            public void onDeviceConnectionFailed() { //연결실패
                Toast.makeText(getApplicationContext(), "Unable to connect", Toast.LENGTH_SHORT).show();
            }
        }); // 블루투스 연결 처리


        final ArrayList<Double> dataBuffer = new ArrayList<Double>();
        final ArrayList<Double> dataBuffer2 = new ArrayList<Double>();

        // 블루투스 데이터 리스너
        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() { //데이터 수신
            public void onDataReceived(byte[] data, String message) {
                dataBuffer.add(Double.parseDouble(message));
                if (dataBuffer.size() == 20 ){
                    double tempbf =0 ;
                    for(int i=0 ; i<dataBuffer.size(); i++){
                        if(dataBuffer.get(i) == 0) {
                            dataBuffer.set(i,82.0);
                        }
                        tempbf = tempbf + dataBuffer.get(i);
                    }
//                    Log.d("d33","average :"+(tempbf/dataBuffer.size()));

                    dataBuffer2.add(tempbf/dataBuffer.size());
                    if (dataBuffer2.size() == 2){
                        double tempbf2 = 0;
                        for (int i =0; i<dataBuffer2.size(); i++){
                            tempbf2 =  tempbf2 + dataBuffer2.get(i);
                        }
                        Log.d("d33","average2 :"+(tempbf2/dataBuffer2.size()));
                        dataBuffer2.clear();
                    }
                    dataBuffer.clear();

                }


                if(!findloadState)  return;
                Log.d("d33","f"+message);

                double temp = Double.parseDouble(message);
                if (!(temp > bluetoothMin && temp < bluetoothMax)) return;

                if (temp > bluetoothFlag) {
                    insertData("http://" + urladdress + "/Data_insert.php");
                } else {
                    ds_lati = (float) (latitude - mLat);
                    dl_lati = (float) (latitude + mLat);
                    ds_longi = (float) (longitude - mLong);
                    dl_longi = (float) (longitude + mLong);
                    rs_lati = (float) (latitude - cLat);
                    rl_lati = (float) (latitude + cLat);
                    rs_longi = (float) (longitude - cLong);
                    rl_longi = (float) (longitude + cLong);

                    deleteData("http://" + urladdress + "/Delete_Ice.php");
                }

            }
        });
    }

    public void setGps() {
        final LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자(실내에선 NETWORK_PROVIDER 권장)
                location_time,
                location_interval,
                mLocationListener);
    }


    // 위치 추적위한 리스너
    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                tmapview.setLocationPoint(longitude, latitude);
                tmapview.setCenterPoint(longitude, latitude);


                // 경로안내 중일 때, 현재 위치가 갱신되면서 새로운 위치를 기준으로 다시 경로 검색 후 다시 안내
                if(findloadState) {
                    try {
                        tMapPolyLine = new TMapData().findPathData(new TMapPoint(latitude,longitude), endAdd);
                        tmapview.removeAllTMapPolyLine();
                        tmapview.addTMapPolyLine("Line1",tMapPolyLine);
                    } catch (Exception e){
                        e.printStackTrace();
                    }


                    // 경로테스트

                    // 경로 테스트
                    TMapPolyLine tempPolyLine = new TMapPolyLine();
                    ArrayList<TMapPoint> arr;   // 경로 테스트
                    double shortestD = distanceFlag+1;

                    for (int i = 0; i < databaseMarker.size(); i++) {
                        tmapview.addMarkerItem(""+i,databaseMarker.get(i));
                        try {
                            double temp = 0;
                            tempPolyLine = new TMapData().findPathData(new TMapPoint(latitude, longitude), databaseMarker.get(i).getTMapPoint());
                            temp = tempPolyLine.getDistance();
                            Log.d("d7", "t" + temp);
                            if (temp!= 0 && temp < shortestD){
                                shortestD = temp;
                                Log.d("d7", "" + shortestD);
                            } else { }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (shortestD < distanceFlag) {
                        Toast.makeText(MainActivity.this, "근방에 블랙아이스가 있습니다. 속도를 줄여주세요!", Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(MainActivity.this, "근방에 블랙아이스가 없습니다.", Toast.LENGTH_SHORT).show();
                    }


                    // Database 검색 시작
                    s_lati = (float) (location.getLatitude()-cLat);
                    l_lati = (float) (location.getLatitude()+cLat);
                    s_longi = (float) (location.getLongitude()-cLong);
                    l_longi = (float) (location.getLongitude()+cLong);
                    getData("http://"+urladdress+"/Research_Ice.php");

                }

            }

        }

        public void onProviderDisabled(String provider) {
        }
        public void onProviderEnabled(String provider) {
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };






    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    findloadState = true;

                    if(findloadState){
                        // 경로 테스트
                        // 경로 테스트
                        TMapPolyLine tempPolyLine;
                        double shortestD = distanceFlag+1;

                        for (int i = 0; i < databaseMarker.size(); i++) {
                            tmapview.addMarkerItem(""+i,databaseMarker.get(i));
                            try {
                                double temp = 0;
                                tempPolyLine = new TMapData().findPathData(new TMapPoint(latitude, longitude), databaseMarker.get(i).getTMapPoint());
                                temp = tempPolyLine.getDistance();
                                Log.d("d7", "t" + temp);
                                if (temp!= 0.0 && temp < shortestD){
                                    shortestD = temp;
                                    Log.d("d7", "" + shortestD);
                                } else { }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    addressArr = data.getDoubleArrayExtra("reponseAddress");
                    endAdd = new TMapPoint(addressArr[0], addressArr[1]);
                    tMapPolyLine = new TMapData().findPathData(new TMapPoint(latitude, longitude), endAdd);
                    tMapPolyLine.setLineColor(Color.RED);
                    tMapPolyLine.setLineWidth(5);
                    tmapview.addTMapPolyLine("Line1", tMapPolyLine);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {   // RESULT_CANCEL
                Toast.makeText(MainActivity.this, "길안내를 취소하셨습니다.", Toast.LENGTH_SHORT).show();
            }
//        } else if (requestCode == REQUEST_ANOTHER) {
//            ...
        }

        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                //setup();
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth was not enabled."
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    private void insertData(String url) {
        class InserDataDB extends AsyncTask<String,Void,String>{

            String result;
            protected void onPreExecute(){
            }
            @Override
            protected String doInBackground(String... params) {

                String postData = "ms_lati=" + (latitude-mLat)+ "&" + "ml_lati=" + (latitude+mLat) + "&" + "ms_longi=" + (longitude-mLong)+ "&" + "ml_longi=" +  (longitude+mLong)+ "&" + "Lati=" +  latitude + "&" + "Longi=" +longitude  ;
                Log.i("###############","aaaaaaaaaaaa"+postData);

                try {
                    String uri = params[0];
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    if (con != null) {
                        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        con.setReadTimeout(5000);
                        con.setConnectTimeout(5000);
                        con.setRequestMethod("POST");
                        con.setDoOutput(true);
                        con.setDoInput(true);
                        con.connect();

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(postData.getBytes("UTF-8"));
                        outputStream.flush();
                        outputStream.close();
                        result = readStream(con.getInputStream());
                        Log.i("#######", "|wwwwwwwwwww"+result);
                        con.disconnect();
                    }

                } catch (Exception e) {
                    Log.i("PHPRequest", "request was failed.");
                    return null;
                }
                Log.i("PHPRequest", "request was susses.");
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(result.equals("1")) {
                    Log.i("###", "|AAAAA 삽입되었습니다.." );
                }
            }
        }
        InserDataDB db = new InserDataDB();
        db.execute(url);
    }

    private String readStream(InputStream in) throws IOException {
        StringBuilder jsonHtml = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line = null;

        while((line = reader.readLine()) != null)
            jsonHtml.append(line);

        reader.close();
        return jsonHtml.toString();
    }

    private void deleteData(String url) {
        class deleteDB extends AsyncTask<String, Void, String> {

            String result;

            protected void onPreExecute() {
            }

            @Override
            protected String doInBackground(String... params) {

                String postData = "ds_lati=" + ds_lati + "&" + "dl_lati=" + dl_lati + "&" + "ds_longi=" + ds_longi+ "&" + "dl_longi=" +dl_longi ;
                try {
                    String uri = params[0];
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    if (con != null) {
                        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        con.setReadTimeout(5000);
                        con.setConnectTimeout(5000);
                        con.setRequestMethod("POST");
                        con.setDoInput(true);
                        con.connect();


                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(postData.getBytes("UTF-8"));
                        outputStream.flush();
                        outputStream.close();
                        result = readStream(con.getInputStream());
                        Log.i("#######", "|aaaaaaaaaaaaa"+result);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }

                return result;

            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(result.equals(1)) {
                    getData("http://"+urladdress+"/Research_Ice.php");
                    Log.i("###", "|AAAAAaa 삭제되었습니다." );
                }
            }
        }
        deleteDB d = new deleteDB();
        d.execute(url);
    }



    private void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            protected void onPreExecute() {
            }

            @Override
            protected String doInBackground(String... params) {

                StringBuilder sb = new StringBuilder();
                String postData = "s_lati=" + s_lati + "&" + "l_lati=" + l_lati + "&" + "s_longi=" + s_longi+ "&" + "l_longi=" + l_longi;
                Log.i("###", "|eeeee"+postData);
                try {
                    String uri = params[0];
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    if (con != null) {
                        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        con.setReadTimeout(5000);
                        con.setConnectTimeout(5000);
                        con.setRequestMethod("POST");
                        con.setDoInput(true);
                        con.connect();


                        OutputStream outputStream =con.getOutputStream();
                        outputStream.write(postData.getBytes("UTF-8"));
                        outputStream.flush();
                        outputStream.close();

                        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            Log.i("###", "|bggggggggggggggggggggggggg");
                            String json;
                            while ((json = bufferedReader.readLine()) != null) {
                                sb.append(json + "\n");
                            }
                            bufferedReader.close();
                        }
                        con.disconnect();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }

                return sb.toString().trim(); //trim()은 공백제거 문자, 신경안써도됨

            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.i("postResult", result);
                myJSON = result;
                showResult();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }


    protected void showResult(){
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            if(databaseMarker != null)    databaseMarker.clear();

            for(int i =0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String lati = c.getString(TAG_LATI);
                String longi = c.getString(TAG_LONGI);

                TMapMarkerItem temp = new TMapMarkerItem();
                temp.setTMapPoint(new TMapPoint(Double.parseDouble(lati), Double.parseDouble(longi)));
                databaseMarker.add(temp);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // 블루투스 메소드들
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu) ;
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings1 :
                if (bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bt.disconnect();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
                return true ;
            default :
                return super.onOptionsItemSelected(item) ;
        }
    }

    public void onStart() {
        super.onStart();
        if (!bt.isBluetoothEnabled()) { //
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else {
            if (!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER); //DEVICE_ANDROID는 안드로이드 기기 끼리
                //setup();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        bt.stopService(); //블루투스 중지
    }

}
