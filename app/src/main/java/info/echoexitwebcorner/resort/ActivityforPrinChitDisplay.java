package info.echoexitwebcorner.resort;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import info.echoexitwebcorner.resort.Utils.Constance;

public class ActivityforPrinChitDisplay extends AppCompatActivity {
    Context context;
    ImageView ivBack;
    EditText tv_totMan, tv_tot_Woman, tv_totChild;
    TextView tv_Prsn, tv_currDate;
    LinearLayout ll_finish;
    int man = 0, women = 0, child = 0;
    Date date;
    String currDate;
    SimpleDateFormat format;
    LinearLayout ll_main;

    private static final int PERMISSION_CALLBACK_CONSTANT = 200;
    String[] permissionsRequired = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE};
    static Socket Printer1;
    static DataOutputStream Printer1out;
    PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityfor_prin_chit_display);
        context = ActivityforPrinChitDisplay.this;
        checkPermission();

        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_currDate = findViewById(R.id.tv_currDate);
        tv_totMan = findViewById(R.id.tv_totMan);
        tv_tot_Woman = findViewById(R.id.tv_tot_Woman);
        tv_totChild = findViewById(R.id.tv_totChild);
        tv_Prsn = findViewById(R.id.tv_Prsn);
        ll_finish = findViewById(R.id.ll_finish);
        ll_main = findViewById(R.id.ll_main);

        format = new SimpleDateFormat("dd/MM/yyyy");
        date = Calendar.getInstance().getTime();
        currDate = format.format(date);

        if (getIntent().hasExtra(Constance.Man)) {
            man = getIntent().getIntExtra(Constance.Man, 0);
        }
        if (getIntent().hasExtra(Constance.Women)) {
            women = getIntent().getIntExtra(Constance.Women, 0);
        }
        if (getIntent().hasExtra(Constance.Child)) {
            child = getIntent().getIntExtra(Constance.Child, 0);
        }

        tv_currDate.setText(currDate);
        tv_totMan.setText(man + "");
        tv_tot_Woman.setText(women + "");
        tv_totChild.setText(child + "");


        ll_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tot=(Integer.valueOf(tv_totMan.getText().toString())) +  (Integer.valueOf(tv_tot_Woman.getText().toString()))
                        + (Integer.valueOf(tv_totChild.getText().toString()));
                tv_Prsn.setText(tot+"");

                Toast.makeText(context, "Total Person: " + tv_Prsn.getText().toString(), Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        opnpopup();
                    }
                }, 1000);

            }
        });

    }

    public void opnpopup() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.popup_for_printchit, null);
        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                customView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        // Set an elevation value for popup window
        // Call requires API level 21
        if (Build.VERSION.SDK_INT >= 21) {
            mPopupWindow.setElevation(5.0f);
        }
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);

        Button btnSubmit = customView.findViewById(R.id.btnSubmit);
        Button btnCancel = customView.findViewById(R.id.btnCancel);
        TextView tv_currDate = customView.findViewById(R.id.tv_currDate);

        TextView tv_RtotMan = customView.findViewById(R.id.tv_totMan);
        TextView tv_Rtot_MP = customView.findViewById(R.id.tv_tot_MP);
        TextView tv_RtotManP = customView.findViewById(R.id.tv_totManP);

        TextView tv_Rtot_Woman = customView.findViewById(R.id.tv_tot_Woman);
        TextView tv_Rtot_WP = customView.findViewById(R.id.tv_tot_WP);
        TextView tv_Rtot_WomanP = customView.findViewById(R.id.tv_tot_WomanP);

        TextView tv_RtotChild = customView.findViewById(R.id.tv_totChild);
        TextView tv_RtotCP = customView.findViewById(R.id.tv_totCP);
        TextView tv_RtotChildP = customView.findViewById(R.id.tv_totChildP);

        final TextView tv_Prsn = customView.findViewById(R.id.tv_Prsn);
//        connectPrinter();
        int mp = 0, wp = 0, cp = 0, tmp = 0, twp = 0, tcp = 0, totp = 0;

        tmp = Integer.parseInt(tv_totMan.getText().toString());
        twp = Integer.parseInt(tv_tot_Woman.getText().toString());
        tcp = Integer.parseInt(tv_totChild.getText().toString());


        mp = 100;
        wp = 100;
        cp = 60;

        totp = (tmp * mp) + (twp * wp) + (tcp * cp);

        tv_currDate.setText(currDate);
        tv_RtotMan.setText(tv_totMan.getText().toString());
        tv_Rtot_Woman.setText(tv_tot_Woman.getText().toString());
        tv_RtotChild.setText(child + "");

        tv_Rtot_MP.setText(mp + "");
        tv_Rtot_WP.setText(wp + "");
        tv_RtotCP.setText(cp + "");

        tv_RtotManP.setText(tmp * mp + "");
        tv_Rtot_WomanP.setText(twp * wp + "");
        tv_RtotChildP.setText(tcp * cp + "");

        tv_Prsn.setText(totp + "");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Total Amount : " + tv_Prsn.getText().toString(), Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
                Intent intent=new Intent(context,MainActivity.class);
                startActivity(intent);
//                disconnectPrinter();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.showAtLocation(ll_main, Gravity.CENTER, 0, 0);
        mPopupWindow.update();
    }

    public void checkPermission() {
        if (ActivityCompat.checkSelfPermission(context, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, permissionsRequired[1]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, permissionsRequired[2]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, permissionsRequired[3]) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissionsRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissionsRequired[1])
                    || ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissionsRequired[2])
                    || ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissionsRequired[3])) {
                //Show Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions((Activity) context, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        onBackPressed();
                    }
                });
                builder.show();
            } else {
                //just request the permission
                ActivityCompat.requestPermissions((Activity) context, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 200: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED && grantResults[3] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    onBackPressed();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public void disconnectPrinter() {
        try {
            if (Printer1out != null)
                Printer1out.close();
            if (Printer1 != null)
                Printer1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void connectPrinter() {
        Thread printert1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Printer1 = new Socket(Constance.Printer1, 9100);
                    Printer1out = new DataOutputStream(Printer1.getOutputStream());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        printert1.start();

    }
}
