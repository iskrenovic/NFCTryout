package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.databinding.FragmentItwitterBinding;
import com.example.budalajedna.nfctryout.datahandling.TwitterHandler;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class InputTwitterFragment extends Fragment {

    private FragmentItwitterBinding binding;
    private InputTwiterViewModel viewModel;

    private TwitterLoginButton twitterLoginButton;

    private Callback callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        prepareTwitter();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_itwitter,container,false);

        View view = this.binding.getRoot();

        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(InputTwiterViewModel.class);

        binding.setVm(this.viewModel);

        prepareTwitterButton((TwitterLoginButton) view.findViewById(R.id.twitter_login));

        return view;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private void prepareTwitter(){

        TwitterConfig config = new TwitterConfig.Builder(getActivity())
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getResources().getString(R.string.consumer_key), getResources().getString(R.string.secret_key
                )))
                .debug(true)
                .build();

        Twitter.initialize(config);
    }

    private void prepareTwitterButton(TwitterLoginButton loginButton){

        twitterLoginButton = loginButton;

        twitterLoginButton.setCallback(new com.twitter.sdk.android.core.Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                callback.setTwitterUserID(result.data.getUserId() + "");
                TwitterSession session= TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken=session.getAuthToken();
                String token=authToken.token;
                String auth=authToken.toString();
                String secret=authToken.secret;


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://api.twitter.com/oauth/authenticate?oauth_token="+R.string.auth_token));
                callback.openIntent(intent);
                callback.nextFragment();
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }

    public interface Callback{
        void setTwitterUserID(String userName);
        void nextFragment();
        void openIntent(Intent intent);
    }
}
