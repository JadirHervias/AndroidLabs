package pe.edu.tecsup.sugarormapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.edu.tecsup.sugarormapp.adapter.ImageAdapter;
import pe.edu.tecsup.sugarormapp.R;
import pe.edu.tecsup.sugarormapp.repository.UserRepository;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullnameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private static final String PHOTOS_KEY = "easy_image_photos_list";

    protected RecyclerView recyclerView;

    protected View galleryButton;

    private ImageAdapter imageAdapter;

    private ArrayList<File> photos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Nammu.init(this);

        recyclerView = findViewById(R.id.recycler_view);


        fullnameInput = (EditText)findViewById(R.id.fullname_input);
        emailInput = (EditText)findViewById(R.id.email_input);
        passwordInput = (EditText)findViewById(R.id.password_input);

        if (savedInstanceState != null) {
            photos = (ArrayList<File>) savedInstanceState.getSerializable(PHOTOS_KEY);
        }

        imageAdapter = new ImageAdapter(this, photos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(imageAdapter);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Nammu.askForPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionCallback() {
                @Override
                public void permissionGranted() {
                    //Nothing, this sample saves to Public gallery so it needs permission
                }

                @Override
                public void permissionRefused() {
                    finish();
                }
            });
        }

        EasyImage.configuration(this)
                .setImagesFolderName("EasyImage sample")
                .setCopyTakenPhotosToPublicGalleryAppFolder(true)
                .setCopyPickedImagesToPublicGalleryAppFolder(true)
                .setAllowMultiplePickInGallery(true);

        checkGalleryAppAvailability();

        findViewById(R.id.camera_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyImage.openCameraForImage(RegisterActivity.this, 0);
            }
        });

        findViewById(R.id.chooser_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyImage.openChooserWithGallery(RegisterActivity.this, "Pick source", 0);
            }
        });

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PHOTOS_KEY, photos);
    }
    private void checkGalleryAppAvailability() {
        if (!EasyImage.canDeviceHandleGallery(this)) {
            //Device has no app that handles gallery intent
            galleryButton.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling

                e.printStackTrace();
            }

            @Override
            public void onImagesPicked(List<File> imageFiles, EasyImage.ImageSource source, int type) {
                onPhotosReturned(imageFiles);
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA_IMAGE) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(RegisterActivity.this);
                    if (photoFile != null) photoFile.delete();
                }
            }
        });
    }

    private void onPhotosReturned(List<File> returnedPhotos) {

        photos.addAll(returnedPhotos);
        imageAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(photos.size() - 1);

    }

    @Override
    protected void onDestroy() {
        // Clear any configuration that was done!
        EasyImage.clearConfiguration(this);
        super.onDestroy();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Nammu.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void callRegister(View view){
        String fullname = fullnameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        ImageView picture = (ImageView) findViewById(R.id.image_view);
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        Drawable d = picture.getDrawable();
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, stream);
        byte[] image_byte=stream.toByteArray();


        if(fullname.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRepository.create(fullname, email, password,image_byte);

        finish();

    }

}
