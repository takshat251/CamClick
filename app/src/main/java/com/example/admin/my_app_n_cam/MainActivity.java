package com.example.admin.my_app_n_cam;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static  final int REQUEST_IMAGE_CAPTURE = 1;

    ImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = (Button) findViewById(R.id.myButton);
        myImageView = (ImageView) findViewById(R.id.myImageView);
        if(!hasCamera())//checking if user device has camera
            myButton.setEnabled(false);//disables the button if no camera
    }

        public boolean hasCamera()
        {
            return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
        }
        public void launchCamera(View view)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//onclick launch camera
            startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");//converting data image into bitmap format
            myImageView.setImageBitmap(photo);
        }


    }
}
