package com.example.alex.test_italki;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    EditText etUserName;
    EditText etPassword;
    String userName;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        Button btLogin1 = findViewById(R.id.btn_login1);
        Button btLogin2 = findViewById(R.id.btn_login2);
        Button btLogin3 = findViewById(R.id.btn_login3);
        Button btLogin4 = findViewById(R.id.btn_login4);


        etUserName.setText("123215@italki.com");
        etPassword.setText("123123");

        btLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ItalkiApi().loginRxJava(etUserName.getText().toString(), etPassword.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(User user) throws Exception {
                                Log.d(TAG, "accept1: -----------" + user.toString());
                            }
                        }, throwable -> {

                        });

            }
        });

        btLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ItalkiApi().loginRxJava2(etUserName.getText().toString(), etPassword.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(User user) throws Exception {
                                Log.d(TAG, "accept2: -----------" + user.toString());
                            }
                        }, throwable -> {

                        });

            }
        });


        btLogin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ItalkiApi().loginRxJava2GetHeaders(etUserName.getText().toString(), etPassword.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(User user) throws Exception {

                            }
                        }, throwable -> {

                        });

            }
        });

        btLogin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ItalkiApi().loginRetrofit(etUserName.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
