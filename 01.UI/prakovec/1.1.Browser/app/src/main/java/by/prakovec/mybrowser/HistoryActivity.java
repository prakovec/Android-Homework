package by.prakovec.mybrowser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Admin on 08.02.2015.
 */
public class HistoryActivity extends Activity {

    public static final String EXTRA_HISTORY =
            "by.prakovec.mybrowser.history";
    private String historyURL;
    private Button ok;
    private TextView historyView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hisory);

        historyURL = getIntent().getStringExtra(EXTRA_HISTORY);

        ok = (Button) findViewById(R.id.bOK);
        historyView = (TextView) findViewById(R.id.tvHistory);

        historyView.setText(historyURL);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.bOK)
                    finish();
            }
        });
    }
}
