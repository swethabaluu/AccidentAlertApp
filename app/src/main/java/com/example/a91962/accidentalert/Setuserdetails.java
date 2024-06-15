package com.example.a91962.accidentalert;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class Setuserdetails extends AppCompatActivity implements LocationListener {
    SmsManager sms,sms1;
    ImageView i1,i2,i3;
    String result=null;
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    String user,number_info,name,bgroup,ph,emg,blood_group,numberinfo,nameinfo;
    Cursor c,c1;
    String empty="";
    String number,mail,phone,address,dest;
    int age;
    ArrayList<String>message;
    String messgae_info;
    double lat=0.0,lon=0.0,latitude=0.0,longitude=0.0,la=0.0,lo=0,fg=0,fg1=0,radius=0;
    protected LocationManager lm;
    Button b1,b2,b3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);

        e2=(EditText)findViewById(R.id.editText6);

        e5=(EditText)findViewById(R.id.editText);
        e6=(EditText)findViewById(R.id.editText5);
        e7=(EditText)findViewById(R.id.editText1);
        e8=(EditText)findViewById(R.id.editText4);



        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button6);

        b3=(Button)findViewById(R.id.button5);


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                number=e2.getText().toString();

                SQLiteDatabase db = openOrCreateDatabase("Alert", Context.MODE_PRIVATE, null);

                try {

                    if (db != null) {


                        c = db.rawQuery("SELECT * from vehicleinfo where(vehid='"+number+"')", null);
                        if (c.moveToFirst()) {
                            do {


                                name = c.getString(c.getColumnIndexOrThrow("name"));
                               bgroup= c.getString(c.getColumnIndexOrThrow("gender"));
                                address = c.getString(c.getColumnIndexOrThrow("address"));
                                phone = c.getString(c.getColumnIndexOrThrow("classs"));
                                emg = c.getString(c.getColumnIndexOrThrow("password"));
                                e5.setText(name);
                                e6.setText(bgroup);
                                e7.setText(address);
                                e8.setText(phone);
                               // Toast.makeText(getApplicationContext(),"number:"+number_info,Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(),"name:"+name,Toast.LENGTH_SHORT).show();



                            } while (c.moveToNext());

                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Exception in extracting " + e, Toast.LENGTH_SHORT).show();
                }








            }
        });




        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 blood_group=e6.getText().toString();


                sms= SmsManager.getDefault();
                sms1= SmsManager.getDefault();

                try{
                   // Toast.makeText(getApplicationContext(),"check phone "+phone,Toast.LENGTH_SHORT).show();


                    messgae_info="Hi Family Members  This is Emergency Accident Alert  Intimation From Location:  "+b1.getText().toString()+"";


                    ArrayList<String> parts = sms.divideMessage(messgae_info);

                    sms.sendMultipartTextMessage(emg, null, parts, null, null);

                   // sms.sendMultipartTextMessage("9894774989", null, parts, null, null);

                    //sms.sendTextMessage("9003670809", null,"Hi Control Room This is Emergency Ambulance Alert From  "+b1.getText().toString()+" To:"+dest+"", null, null);

                    //sms.sendTextMessage("9003670809", null,"welcome", null, null);


                    Toast.makeText(getApplicationContext(),"Intimation Alet Send Succesfully ",Toast.LENGTH_SHORT).show();



                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Excepion in sending sms"+e,Toast.LENGTH_SHORT).show();
                }



                //for donor intimation



                SQLiteDatabase db1 = openOrCreateDatabase("Alert", Context.MODE_PRIVATE, null);

                try {

                    if (db1 != null) {


                        c1 = db1.rawQuery("SELECT * from donorinfo where(s3='"+blood_group+"')", null);
                        if (c1.moveToFirst()) {
                            do {


                                nameinfo = c1.getString(c1.getColumnIndexOrThrow("s1"));
                                numberinfo = c1.getString(c1.getColumnIndexOrThrow("s4"));

                               // Toast.makeText(getApplicationContext(),"dbcall  "+ numberinfo,Toast.LENGTH_SHORT).show();

                                Toast.makeText(getApplicationContext(),"Intimation Alet Send Succesfully "+ numberinfo,Toast.LENGTH_SHORT).show();


                                try{

                                    messgae_info="Hi Blood Donor  This is Emergency Accident Alert  Intimation From Location:  "+b1.getText().toString()+" Contact Family Member Number :"+emg+"";


                                    ArrayList<String> parts1 = sms1.divideMessage(messgae_info);

                                    sms1.sendMultipartTextMessage(numberinfo, null, parts1, null, null);


                                    Toast.makeText(getApplicationContext(),"Intimation Alet Send Succesfully ",Toast.LENGTH_SHORT).show();



                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getApplicationContext(),"Excepion in sending sms"+e,Toast.LENGTH_SHORT).show();
                                }



                            } while (c1.moveToNext());

                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Exception in extracting " + e, Toast.LENGTH_SHORT).show();
                }



            }
        });



        try {
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            lm.requestLocationUpdates(lm.GPS_PROVIDER, 0, 0, (LocationListener) this);
        }
        catch (SecurityException e)
        {

        }




    }




    @Override
    public void onLocationChanged(Location location) {

        Geocoder gc = new Geocoder(this);
        //Toast.makeText(this,"Location Getting",Toast.LENGTH_SHORT).show();
        lat=location.getLatitude();
        lon=location.getLongitude();

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addressList = geocoder.getFromLocation(
                    lat, lon, 1);
            // Toast.makeText(this," Fetchingg.....",Toast.LENGTH_LONG).show();

            //Toast.makeText(this," List size "+addressList+"  list "+addressList.size(),Toast.LENGTH_LONG).show();
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                StringBuilder sb = new StringBuilder();
                //  Toast.makeText(this," Fetchingaxaxxeedfefefaxg....."+address.getMaxAddressLineIndex(),Toast.LENGTH_LONG).show();
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append("\n");
                    //          Toast.makeText(this," Fetchingaxaxxaxg.....",Toast.LENGTH_LONG).show();
                }

                sb.append( address.getAddressLine(0)).append("\n");
                sb.append( address.getFeatureName()).append("\n");
                result = sb.toString();

                b1.setText(result);


            }
        } catch (IOException e) {
            Log.e(TAG, "Unable connect to Geocoder", e);



        }



        // Toast.makeText(this," Physical address "+result,Toast.LENGTH_LONG).show();



    }





    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }















}
