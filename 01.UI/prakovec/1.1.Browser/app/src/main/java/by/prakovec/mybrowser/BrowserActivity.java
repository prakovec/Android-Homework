package by.prakovec.mybrowser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Admin on 07.02.2015.
 */
public class BrowserActivity extends Activity implements View.OnClickListener {

    private EditText url;
    private WebView myBrowser;
    private Button go;
    private Button back;
    private Button refresh;
    private Button forward;
    private Button clearHistory;
    private String tempURL;
    static String historyUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        myBrowser = (WebView) findViewById(R.id.wvBrowser);
        myBrowser.getSettings().setJavaScriptEnabled(true);
        myBrowser.getSettings().setLoadWithOverviewMode(true);
        myBrowser.getSettings().setUseWideViewPort(true);
        myBrowser.setWebViewClient(new ViewClient());
//        try {
//            myBrowser.loadUrl(tempURL);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        back = (Button) findViewById(R.id.bBack);
        go = (Button) findViewById(R.id.bGo);
        refresh = (Button) findViewById(R.id.bRefresh);
        forward = (Button) findViewById(R.id.bForward);
        clearHistory = (Button) findViewById(R.id.bHistory);
        url = (EditText) findViewById(R.id.etURL);

        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        clearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BrowserActivity.this, HistoryActivity.class);
                i.putExtra(HistoryActivity.EXTRA_HISTORY, historyUrl);
                startActivity(i);
            }
        });


        back.setEnabled(false);
        forward.setEnabled(false);
        clearHistory.setEnabled(false);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myBrowser.canGoBack()) {
            myBrowser.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bGo:
                String theWebsite = url.getText().toString();
                myBrowser.loadUrl(theWebsite);
                //Hidden the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(url.getWindowToken(), 0);

                back.setEnabled(true);
                findViewById(R.id.bHistory).setEnabled(true);
                break;

            case R.id.bBack:
                if (myBrowser.canGoBack()) {
                    myBrowser.goBack();
                    if (!myBrowser.canGoBack()) {
                        back.setEnabled(false);
                    }
                }
                forward.setEnabled(true);

                break;
            case R.id.bForward:
                if (myBrowser.canGoForward()) {
                    myBrowser.goForward();
                    if (!myBrowser.canGoForward()) {
                        forward.setEnabled(false);
                    }
                }
                back.setEnabled(true);
                break;

            case R.id.bRefresh:
                myBrowser.reload();
                break;
        }

    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        tempURL = myBrowser.getUrl();
        outState.putString("url", tempURL);

    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tempURL = savedInstanceState.getString("url");
        myBrowser.loadUrl(tempURL);
        url.setText(tempURL);
    }

}
