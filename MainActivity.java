package com.puzzle4kids.imagedemo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ImageView imgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        imgview = findViewById(R.id.imgview);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();         aa gallary mate
//                intent.setType("image/*");
//                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);       //aa camera mate
                try {
                    startActivityForResult(takePictureIntent, 1);
                } catch (ActivityNotFoundException e) {
                    // display error state to the user
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
//                Uri uri = data.getData();
//                imgview.setImageURI(uri);     aa gallary valu image picker

                Bundle bundle = data.getExtras();   //aa camera mate
                Bitmap bitmap = (Bitmap) bundle.get("data");
                imgview.setImageBitmap(bitmap);

//                Drawable drawable = new BitmapDrawable(getResources(),bitmap);
//                imgview.setBackground(drawable);
//  aa bitmap ne drawable ma convert kari ne img.setbackground ma set karyo
// jethi pachhi aapde uper sticker gothavu hoy to gothava thay.sticker ne imageview ma setImageBitmap set kari deva nu

                Intent intent = new Intent(MainActivity.this, Page2.class);
                intent.putExtra("image", bundle);
                startActivity(intent);

            }
        }
    }
}