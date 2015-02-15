package by.prakovec.linkviewer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Admin on 14.02.2015.
 */
public class LinkViewerActivity extends Activity {

    static final String HTTP = "http://";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkviewer);

        final EditText mLink = (EditText) findViewById(R.id.link);

        mLink.setText(HTTP);
        findViewById(R.id.go).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openLink(mLink.getText().toString());
                    }
                });
    }

    void openLink(String link) {

        if (link.isEmpty()|| HTTP.equals(link)) {
            Toast.makeText(this, R.string.msg_empty_link, Toast.LENGTH_SHORT).show();

        } else {
            Uri data = Uri.parse(link);
            Intent intent = new Intent(Intent.ACTION_VIEW, data);
            startActivity(intent);
        }
    }
}
