package com.iyad.sultan.flashlight;

import android.hardware.Camera;


/**
 * Created by Administrator on 8/11/2016.
 */
public class FlashManager  {
    public static boolean  isFlashOn ;
    public static boolean isNormalModeOn;


    private static Camera camera;
    private static Camera.Parameters params;



    public static void startCamera(){

        if(camera == null){
            try {
                camera =Camera.open();
                params = camera.getParameters();
            }
            catch (RuntimeException e) {

            }
        }
    }

    public static void stopCamera(){

        if(camera != null) {
            camera.release();
            camera = null;
        }

    }


    public static void turnFlashLightOnNormalMode(){
//turn light on code

            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;
    }
    public static void turnFlashLightOnBlinkMode(){
        isFlashOn = true;
    }


    public static void turnFlashLightOff(){
        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(params);
        camera.stopPreview();
        isFlashOn = false;
    }




}
