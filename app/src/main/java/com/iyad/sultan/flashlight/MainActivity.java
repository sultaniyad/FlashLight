package com.iyad.sultan.flashlight;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static MediaPlayer OnSound;
    private static MediaPlayer OffSound;

    private ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //    setIsFlashOnWhenUserPause();
        FlashManager.startCamera();
        img = (ImageButton) findViewById(R.id.imgFalsh);
        if (!checkForPermissionCamera())
            askCemeraPermasion();

        OnSound = MediaPlayer.create(MainActivity.this, R.raw.on);
        OffSound = MediaPlayer.create(MainActivity.this, R.raw.off);
    }


    //    @Override
    protected void onStop() {
        super.onStop();
        FlashManager.stopCamera();
        imgOff();

    }

    @Override
    protected void onResume() {
        super.onResume();
        FlashManager.startCamera();

        if(FlashManager.isFlashOn)
        {
            FlashManager.turnFlashLightOnNormalMode();
            imgOn();
            OnSound.start();
        }
            else imgOff();

    }

    public boolean isHasFlash() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public void GoAHead(View view) {
        if (isHasFlash()) {
            if (!FlashManager.isFlashOn) {
                FlashManager.turnFlashLightOnNormalMode();
                imgOn();
                OnSound.start();
            } else {
                FlashManager.turnFlashLightOff();
                imgOff();
                OffSound.start();
            }

        } else {
            alert();
        }
    }


    void imgOn() {
        img.setImageResource(R.drawable.on);
}

    void imgOff() {
        img.setImageResource(R.drawable.off);
    }


    public boolean checkForPermissionCamera() {

        return (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA));
    }

    public void askCemeraPermasion() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.CAMERA"}, 123);

    }

    void alert() {
        new AlertDialog.Builder(MainActivity.this).setTitle("عفوا !").setMessage("لم يتم العثور على فلاش في جهازك").setPositiveButton("افلق التطبيق", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        }).create().show();
    }
}

