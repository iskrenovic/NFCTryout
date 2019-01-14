package com.example.budalajedna.nfctryout.presentation.input;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.datahandling.Instagram;

public class InstagramAuthDialog extends Dialog {
    private final String redirect_url;
    private final String request_url;

    private Instagram instagram=new Instagram();

    public InstagramAuthDialog(@NonNull Context context) {
        super(context);
        this.redirect_url = context.getResources().getString(R.string.redirect_url);
        this.request_url = context.getResources().getString(R.string.base_url) +
                "oauth/authorize/?client_id=" +
                context.getResources().getString(R.string.instagram_client) +
                "&redirect_uri=" + redirect_url +
                "&response_type=token&display=touch&scope=public_content";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.inst_dialog);

        final WebView webView =(WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(request_url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(redirect_url)) {
                    InstagramAuthDialog.this.dismiss();
                    return true;
                }
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url.contains("access_token=")) {
                    Uri uri = Uri.parse(url);
                    String access_token = uri.getEncodedFragment();
                    access_token = access_token.substring(access_token.lastIndexOf("=") + 1);
                    instagram.onTokenReceived(access_token);
                }
            }
                });
    }



    public interface AuthenticationListener {
        void onTokenReceived(String auth_token);
    }
}
