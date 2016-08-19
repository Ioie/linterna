package com.example.user.faruleti;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button boton_encender;
    private boolean linternaEncendida = false;
    private boolean linternaflash = false;
    private Camera camera;

    Camera.Parameters parametersCamera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       linternaflash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH); //revisa si el equipo tiene fla
        boton_encender = (Button) findViewById(R.id.boton_encender);
        if (!linternaflash){
            boton_encender.setEnabled(false);// si el equipo no tiene fla se deshabilita el button
            return;// = a hagammos de cuenta que no paso nada
            }
        boton_encender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linternaEncendida){
                    apagaflash();
                }
                else {
                    prenderflash();
                }

            }
        });

        }

    private void prenderflash() {
        if(camera == null){
            try{
                camera = camera.open();

            }
            catch (RuntimeException e){
                Log.e("Error al abrir la CAM",e.getMessage());
                boton_encender.setEnabled(false);

            }

        }
        parametersCamera = camera.getParameters();
        parametersCamera.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parametersCamera);
        camera.startPreview();
        linternaEncendida = true;



    }

    private void apagaflash() {
        if(linternaEncendida){
            parametersCamera = camera.getParameters();
            parametersCamera.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parametersCamera);
            camera.stopPreview();
            linternaEncendida = false;

        }

    }




    }

