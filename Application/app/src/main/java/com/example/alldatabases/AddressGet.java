package com.example.alldatabases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;

import java.util.ArrayList;

public class AddressGet extends AppCompatActivity {
    double[] temp;
    ArrayList<String> itemsS = new ArrayList<String>();
    ArrayList<TMapPOIItem> items = new ArrayList<TMapPOIItem>();
    TMapPoint[] positions = new TMapPoint[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_address);

        Intent addressIntent = getIntent();
        temp = addressIntent.getDoubleArrayExtra("requestAddress");

        Button endBtn = (Button)findViewById(R.id.arrivalBtn);
        Button returnAddress = (Button)findViewById(R.id.doFindLoad);
        final EditText endEdit = (EditText)findViewById(R.id.arrivalEdt);
        final ListView list = (ListView)findViewById(R.id.searchResult_list_item);


        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TMapData tmapdata = new TMapData();
                String tmp = endEdit.getText().toString();
                final ArrayAdapter adapter;
                if(items !=  null)  items.clear();
                if(itemsS != null)  itemsS.clear();

                if (tmp == null)    return;

                try {
                    items = tmapdata.findAllPOI(tmp);
                    for (int i=0; i < items.size(); i++){
                        itemsS.add(items.get(i).name);
                    }
                    adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, itemsS);
                    list.setAdapter(adapter);
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        returnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(positions[0] != null) {
                    temp[0] = positions[0].getLatitude();
                    temp[1] = positions[0].getLongitude();

                    Intent intent = new Intent();
                    intent.putExtra("reponseAddress", temp);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(endEdit.isFocused()){
                    endEdit.setText(itemsS.get(position));
                    positions[0] = items.get(position).getPOIPoint();
                    Log.d("d7",  items.get(position).toString());
                    Log.d("d7", positions[0].toString());
                }
                else{

                }
            }
        });
//        setResult(RESULT_OK, addressIntent);
//        finish();

    }
}