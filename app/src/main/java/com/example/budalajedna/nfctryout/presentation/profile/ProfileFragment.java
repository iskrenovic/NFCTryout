package com.example.budalajedna.nfctryout.presentation.profile;

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
import com.example.budalajedna.nfctryout.databinding.FragmentProfileBinding;
import com.example.budalajedna.nfctryout.presentation.main.MainCallback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment implements  ProfileViewModel.Callback{

    private FragmentProfileBinding binding;
    private ProfileViewModel viewModel;

    private Callback callback;
    private MainCallback mainCallback;

    private static final int PICK_IMAGE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_profile,container,false);

        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        viewModel.init(getResources(),mainCallback.getUser(),mainCallback.getPictureTransform());

        viewModel.setCallback(this);

        binding.setVm(this.viewModel);

        return this.binding.getRoot();
    }

    public void setCallback(Callback callback, MainCallback mainCallback) {
        this.callback = callback;
        this.mainCallback = mainCallback;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            try {
                InputStream is = mainCallback.getActivity().getContentResolver().openInputStream(imageUri);
                Drawable image = Drawable.createFromStream(is, imageUri.toString());
                viewModel.setPicture(image);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Bitmap picture = MediaStore.Images.Media.getBitmap(mainCallback.getActivity().getContentResolver(), imageUri);
                mainCallback.getUser().setProfilePicture(mainCallback.getPictureTransform().getBitmapString(picture));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void changePicture() {
        Intent intent = new Intent();
        intent.setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Izaberite profilu fotografiju"), PICK_IMAGE);
    }

    @Override
    public void emailClick() {
        callback.emailClick();
    }

    @Override
    public void numberClick() {
        callback.numberClick();
    }

    @Override
    public void skypeClick() {
        callback.skypeClick();
    }

    @Override
    public void instagramClick() {
        callback.instagramClick();
    }

    public interface Callback {
        void emailClick();
        void numberClick();
        void skypeClick();
        void instagramClick();
    }
}
