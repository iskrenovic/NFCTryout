package com.example.budalajedna.nfctryout.presentation.hello;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.databinding.FragmentHelloBinding;
import com.example.budalajedna.nfctryout.datahandling.PictureTransform;
import com.example.budalajedna.nfctryout.presentation.main.MainCallback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;


public class HelloFragment extends Fragment implements HelloViewModel.Callback{

    private FragmentHelloBinding binding;
    private HelloViewModel viewModel;

    private Callback callback;
    private MainCallback mainCallback;

    private static final int PICK_IMAGE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_hello,container,false);

        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(HelloViewModel.class);

        viewModel.setCallback(this);
        viewModel.setPicture(getResources().getDrawable(R.drawable.ic_profile));

        binding.setVm(this.viewModel);

        return this.binding.getRoot();
    }

    public void setCallbacks(Callback callback, MainCallback mainCallback){
        this.callback = callback;
        this.mainCallback = mainCallback;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            try {
                InputStream is = mainCallback.getActivity().getContentResolver().openInputStream(imageUri);
                Drawable image = Drawable.createFromStream(is,imageUri.toString());
                viewModel.setPicture(image);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                PictureTransform pictureTransform = new PictureTransform();
                Bitmap picture = MediaStore.Images.Media.getBitmap(mainCallback.getActivity().getContentResolver(), imageUri);
                mainCallback.getUser().setProfilePicture(pictureTransform.getBitmapString(picture));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void nextStep(String contactName) {
        mainCallback.getUser().setContactName(contactName);
        callback.nextHello();
    }

    @Override
    public void emptyName() {
        callback.emptyField("Upišite ime i prezime...");
    }

    @Override
    public void getProfilePicture() {
        Intent intent = new Intent();
        intent.setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Izaberite profilu fotografiju"), PICK_IMAGE);
    }



    public interface Callback{
        void nextHello();
        void emptyField(String text);
    }
}
