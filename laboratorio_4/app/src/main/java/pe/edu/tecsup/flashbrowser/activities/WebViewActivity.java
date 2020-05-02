package pe.edu.tecsup.flashbrowser.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import pe.edu.tecsup.flashbrowser.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView myWebView = new WebView(WebViewActivity.this);
        setContentView(myWebView);
        myWebView.loadUrl("https://www.tecsup.edu.pe");
    }
}
