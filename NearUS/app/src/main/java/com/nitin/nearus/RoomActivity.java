package com.nitin.nearus;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class RoomActivity extends AppCompatActivity {

    private static final String TARGET_WEB_PLATFORM_URL = "https://near-us-web.vercel.app/videoroom.html";
    private static final int HARDWARE_REQUEST_CODE = 102;

    private WebView activityZoneWebView;
    private TextView roomIdText;
    private String roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        roomId = getIntent().getStringExtra("ROOM_ID");
        if (roomId == null || roomId.isEmpty()) {
            roomId = "NUS8921";
        }

        roomIdText = findViewById(R.id.roomIdText);
        activityZoneWebView = findViewById(R.id.activityZoneWebView);

        if (roomIdText != null) {
            roomIdText.setText("Active Room: " + roomId);
        }

        if (checkHardwarePermissions()) {
            initializeUnifiedWorkspace();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO},
                    HARDWARE_REQUEST_CODE);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initializeUnifiedWorkspace() {
        if (activityZoneWebView == null) return;

        activityZoneWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // INJECT CUSTOM RESPONSIVE CSS OVERRIDES: Scales your web elements perfectly to fit tiny mobile sizes
                view.loadUrl("javascript:(function() { " +
                        "var meta = document.createElement('meta'); " +
                        "meta.name = 'viewport'; " +
                        "meta.content = 'width=device-width, initial-scale=0.8, maximum-scale=1.0, user-scalable=no'; " +
                        "document.getElementsByTagName('head')[0].appendChild(meta); " +
                        "})()");
            }
        });

        activityZoneWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                runOnUiThread(() -> request.grant(request.getResources()));
            }
        });

        WebSettings webSettings = activityZoneWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);

        // Responsive Scaling Elements
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        String urlPathWithParameters = TARGET_WEB_PLATFORM_URL + "?room=" + roomId;
        activityZoneWebView.loadUrl(urlPathWithParameters);
    }

    private boolean checkHardwarePermissions() {
        int cameraCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int audioCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return cameraCheck == PackageManager.PERMISSION_GRANTED && audioCheck == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == HARDWARE_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeUnifiedWorkspace();
            } else {
                Toast.makeText(this, "Hardware tokens rejected.", Toast.LENGTH_SHORT).show();
                initializeUnifiedWorkspace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (activityZoneWebView != null && activityZoneWebView.canGoBack()) {
            activityZoneWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}