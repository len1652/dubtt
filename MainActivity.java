package com.ndphuc.dp.dbtt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText edtSearch;
    Button btnSeach,btnChange;
    TextView txtname,txtCoutry,txtTemp,txtStatus,txtHumidity,txtCloud,txtWind,txtDay;
    ImageView imgIcon;
    String City="";

    String tenThongTinThanhPho = "thanhpho";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();

    }
    @Override
    protected void onPause() { // tat phan mem thi luu trang thai
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences(tenThongTinThanhPho,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ThanhPho",City);
        editor.commit();
    }

    @Override
    protected void onResume() { //bat dau phan mem thi phuc hoi trang thai
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences(tenThongTinThanhPho,MODE_PRIVATE);
        String thanhPho = sharedPreferences.getString("ThanhPho","");
        GetCurrentWeatherData(thanhPho);
        Toast.makeText(MainActivity.this,thanhPho,Toast.LENGTH_SHORT).show();

    }
    private void addEvents() {
    nhanvaonuttim();
    nhanvaonutcacngaytieptheo();
    }

    private void nhanvaonutcacngaytieptheo() {
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = edtSearch.getText().toString();
                // nhảy màn hình
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                // truyen du lieu qua Main2Activity
                intent.putExtra("name",City);
                startActivity(intent);
            }
        });
    }

    private void nhanvaonuttim() {
        btnSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = edtSearch.getText().toString();
                if(city.equals("")){

                    GetCurrentWeatherData(City);
                }else{
                    City = city;
                    GetCurrentWeatherData(City);
                }


            }
        });
    }

    public void GetCurrentWeatherData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url ="https://api.openweathermap.org/data/2.5/weather?q="+data+"&appid=99f3124a9490a0a6b932de5c574a6db1&units=metric";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String day = jsonObject.getString("dt");
                            String name = jsonObject.getString("name");
                            txtname.setText("Tên thành phố: "+ name);

                            // luu tru de lan sau hien thi
                            City=name;

                            long l = Long.valueOf(day);
                            Date date = new Date(l*1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd HH:mm a");
                            String Day = simpleDateFormat.format(date);

                            txtDay.setText(Day);

                            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                            String status = jsonObjectWeather.getString("main");
                            String icon = jsonObjectWeather.getString("icon");

                            //doc hinh anh
                            txtStatus.setText(status);

                            setanh(icon);



                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String nhietdo = jsonObjectMain.getString("temp");
                            String doam = jsonObjectMain.getString("humidity");

                            //lay nhiet do kieu int sau do tra ve kieu chuoi
                            Double a = Double.valueOf(nhietdo);
                            int b = a.intValue();
                            String Nhietdo = String.valueOf(b);

                            txtTemp.setText(nhietdo+"°C");
                            txtHumidity.setText(doam+"%");

                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                            String gio = jsonObjectWind.getString("speed");
                            txtWind.setText(gio+"m/s");

                            JSONObject jsonObjectclouds = jsonObject.getJSONObject("clouds");
                            String may = jsonObjectclouds.getString("all");
                            txtCloud.setText(may+"%");

                            JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                            String country = jsonObjectSys.getString("country");
                            txtCoutry.setText("Tên quốc gia: "+country);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }

    private void setanh(String icon) {
        switch (icon) {
            case  "01d":
                imgIcon.setImageResource(R.drawable.a01d);
                break;
            case  "01n":
                imgIcon.setImageResource(R.drawable.a01n);
                break;
            case  "02d":
                imgIcon.setImageResource(R.drawable.a02d);
                break;
            case  "02n":
                imgIcon.setImageResource(R.drawable.a02n);
                break;
            case  "03d":
                imgIcon.setImageResource(R.drawable.a03d);
                break;
            case  "03n":
                imgIcon.setImageResource(R.drawable.a03n);
                break;
            case  "04d":
                imgIcon.setImageResource(R.drawable.a04d);
                break;
            case  "04n":
                imgIcon.setImageResource(R.drawable.a04n);
                break;
            case  "09d":
                imgIcon.setImageResource(R.drawable.a09d);
                break;
            case  "09n":
                imgIcon.setImageResource(R.drawable.a09n);
                break;
            case  "10d":
                imgIcon.setImageResource(R.drawable.a10d);
                break;
            case  "10n":
                imgIcon.setImageResource(R.drawable.a10n);
                break;
            case  "11d":
                imgIcon.setImageResource(R.drawable.a11d);
                break;
            case  "13d":
                imgIcon.setImageResource(R.drawable.a13d);
                break;
            case  "13n":
                imgIcon.setImageResource(R.drawable.a13n);
                break;
            case  "50d":
                imgIcon.setImageResource(R.drawable.a50d);
                break;
            case  "50n":
                imgIcon.setImageResource(R.drawable.a50d);
                break;

            default:
                imgIcon.setImageResource(R.drawable.may);
        }
    }

    private void addControls() {
        edtSearch = findViewById(R.id.txtSearch);
        btnChange = findViewById(R.id.btnChange);
        btnSeach = findViewById(R.id.btnSearch);
        txtname= findViewById(R.id.txtThanhPho);
        txtCoutry = findViewById(R.id.txtQuocGia);
        txtTemp = findViewById(R.id.txtTemp);
        txtStatus = findViewById(R.id.txtTrangThai);
        txtHumidity = findViewById(R.id.txtDoAm);
        txtCloud = findViewById(R.id.txtMay);
        txtWind = findViewById(R.id.txtGio);
        txtDay = findViewById(R.id.txtXemNgay);
        imgIcon = findViewById(R.id.imgIcon);
    }
}