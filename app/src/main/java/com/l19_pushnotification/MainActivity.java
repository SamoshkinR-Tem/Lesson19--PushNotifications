package com.l19_pushnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";

    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 1234;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList mNotificationList;
    public int messagesCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(MainActivity.TAG, "Google services: " + GcmPushHelper.checkPlayServices(this));
    }

    private void initView() {
        findViewById(R.id.btnAddNotification_AM).setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_AM);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RvAdapter(mNotificationList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddNotification_AM:
                onClickAddNotification();
                getToken();
                break;
        }
    }

    private void getToken() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InstanceID instanceID = InstanceID.getInstance(MainActivity.this);
                    String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                            GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                    Log.d(TAG, "Token: " + token);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void onClickAddNotification() {
        mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.sym_def_app_icon))
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setAutoCancel(true);

        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        mBuilder.setContentIntent(pendingIntent);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

//        Notification notification = mBuilder.build();
//        notification.ledARGB = etLedColor.getText().toString().isEmpty() ? 0xffff00ff : Color.parseColor(etLedColor.getText().toString());
//        notification.ledOnMS = 100;
//        notification.ledOffMS = 100;
//        notification.flags = Notification.FLAG_SHOW_LIGHTS;
//
//        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

}
