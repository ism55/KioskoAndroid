package com.example.kioskoandroid;

import androidx.appcompat.app.AppCompatActivity;
import com.example.kioskoandroid.Service.DeviceAdminService;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DevicePolicyManager devicePolicyManager = null; // devicePolicyManager used to activate device admin
    ComponentName adminCompName = null;             // adminCompName

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDeviceAdmin();

    }


    /**
     * Initialize device admin privileges
     */
    private void initDeviceAdmin(){
        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        adminCompName = new ComponentName(this, DeviceAdminService.class);              // Initializing the component;

        Switch swUser = (Switch) findViewById(R.id.swUsers);
        Switch swStatusBar = (Switch) findViewById(R.id.swStatusBar);

         swUser.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (swUser.isChecked()){
                     /*
                      * Deshabilitar agregar usuarios
                      * */
                     devicePolicyManager.addUserRestriction(adminCompName, UserManager.DISALLOW_ADD_USER);
                     Toast.makeText(getApplicationContext(),R.string.habilitado,Toast.LENGTH_SHORT).show();
                 }else{
                     devicePolicyManager.clearUserRestriction(adminCompName, UserManager.DISALLOW_ADD_USER);
                     Toast.makeText(getApplicationContext(),R.string.deshabilitado,Toast.LENGTH_SHORT).show();
                 }
             }
         });

         swStatusBar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (swStatusBar.isChecked()) {
                     /*
                      * Deshabilitar barra de notificaciones
                      * */
                     devicePolicyManager.setStatusBarDisabled(adminCompName,true);
                     Toast.makeText(getApplicationContext(), R.string.habilitado, Toast.LENGTH_SHORT).show();
                 } else {
                     /*
                      * Deshabilitar barra de notificaciones
                      * */
                     devicePolicyManager.setStatusBarDisabled(adminCompName,false);
                     Toast.makeText(getApplicationContext(), R.string.deshabilitado, Toast.LENGTH_SHORT).show();

                 }
             }
         });



        if (devicePolicyManager.isDeviceOwnerApp(getPackageName())) {

            Toast.makeText(getApplicationContext(), R.string.Administrador,Toast.LENGTH_SHORT).show();
            /*
            * Pinned app
            * */
            devicePolicyManager.setLockTaskPackages(adminCompName, new String[]{getPackageName()});

            /*
             * Deshabilitar safe boot
             * */
            devicePolicyManager.addUserRestriction(adminCompName, UserManager.DISALLOW_SAFE_BOOT);
            /*
             * Deshabilitar reset de fabrica
             * */
            devicePolicyManager.addUserRestriction(adminCompName, UserManager.DISALLOW_FACTORY_RESET);

            /*
             * Deshabilitar modificacion de cuentas
             * */
            devicePolicyManager.addUserRestriction(adminCompName, UserManager.DISALLOW_MODIFY_ACCOUNTS);


            devicePolicyManager.addUserRestriction(adminCompName, UserManager.DISALLOW_SMS);



        }else{
            Toast.makeText(getApplicationContext(),R.string.NoAdministrador,Toast.LENGTH_SHORT).show();
        }
    }



    }


