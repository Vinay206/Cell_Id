package com.example.vinaykumar.cellid;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellInfoGsm;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toast toast = Toast.makeText(getApplicationContext(), "Hello toast!", Toast.LENGTH_SHORT);


        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                TextView textSS = (TextView) findViewById(R.id.ss);

                try {
                    TextView textGsmCellLocation = (TextView) findViewById(R.id.gsmcelllocation);
                    TextView textCID = (TextView) findViewById(R.id.cid);
                    TextView textLAC = (TextView) findViewById(R.id.lac);


                    //retrieve a reference to an instance of TelephonyManager
                    TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();



                    // Get GSM Signal Strength (Dbm)
                    CellInfoGsm GSM = (CellInfoGsm)telephonyManager.getAllCellInfo().get(0);
                    CellSignalStrengthGsm cellSignalStrengthGsm = GSM.getCellSignalStrength();



                    int cid = cellLocation.getCid();
                    int lac = cellLocation.getLac();
                    int css = cellSignalStrengthGsm.getDbm();
                    textGsmCellLocation.setText(cellLocation.toString());
                    textSS.setText("GSM Signal : " + String.valueOf(css) + " dBm");//+String.valueOf(css));
                    textCID.setText("GSM Cell id : " + String.valueOf(cid));
                    textLAC.setText("GSM Location Area Code: " + String.valueOf(lac));
                    toast.setText("Updated");
                    toast.show();

                } catch (Exception e) {
                    // Do noting
                    textSS.setText("GSM Signal : No Signal");
                    toast.setText("No signal");
                    toast.show();

                }


            }
        });


    }
}
