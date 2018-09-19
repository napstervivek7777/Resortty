package info.echoexitwebcorner.resort;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import info.echoexitwebcorner.resort.Utils.Constance;

public class MainActivity extends AppCompatActivity {
    Context context;
    ImageView ivBack;
    EditText et_man, et_woman, et_child;
    LinearLayout ll_submit;
    int man = 0, women = 0, child = 0;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        ivBack = findViewById(R.id.ivBack);
        ivBack.setVisibility(View.GONE);

        et_man = findViewById(R.id.et_man);
        et_woman = findViewById(R.id.et_woman);
        et_child = findViewById(R.id.et_child);
        ll_submit = findViewById(R.id.ll_submit);

        ll_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                man = Integer.parseInt(et_man.getText().toString());
                women = Integer.parseInt(et_woman.getText().toString());
                child = Integer.parseInt(et_child.getText().toString());

                Intent intent = new Intent(context, ActivityforPrinChitDisplay.class);
                intent.putExtra(Constance.Man, man);
                intent.putExtra(Constance.Women, women);
                intent.putExtra(Constance.Child, child);
                startActivity(intent);

                et_man.setText("");
                et_woman.setText("");
                et_child.setText("");
            }
        });

    }

   /* public boolean validation() {
        if (et_man.getText().toString().equals("")) {
            Toast.makeText(context, "Enter Total Man", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_woman.getText().toString().equals("")) {
            Toast.makeText(context, "Enter Total Women", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_child.getText().toString().equals("")) {
            Toast.makeText(context, "Enter Total Children", Toast.LENGTH_SHORT).show();
            return false;
        } else {

            return true;
        }
    }*/

    @Override
    public void onBackPressed() {

//            super.onBackPressed();

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click Back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }
}
