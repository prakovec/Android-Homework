package by.prakovec.mybrowser;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

/**
 * Created by Admin on 07.02.2015.
 */
public class ViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView v, String url) {
        v.loadUrl(url);
        BrowserActivity.historyUrl += url + "\n";
        return true;
    }


}
