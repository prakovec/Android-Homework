package by.prakovec.calculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Admin on 15.02.2015.
 */
public class CalculatorActivity extends Activity {

    private static final String CONDITION = "result";

    EditText mNumber1;
    EditText mNumber2;
    RadioGroup mOperator;
    TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);

        mNumber1 = (EditText) findViewById(R.id.number1);
        mNumber2 = (EditText) findViewById(R.id.number2);
        mOperator = (RadioGroup) findViewById(R.id.rgroup);
        mResult = (TextView) findViewById(R.id.result);

        findViewById(R.id.calculate).setOnClickListener(new OnComputeClickListener());
    }

    private class OnComputeClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {

                int checkedRadiobuttonId = mOperator.getCheckedRadioButtonId();
                double calcResult;
                switch (checkedRadiobuttonId) {
                    case R.id.plus:
                        calcResult = Double.parseDouble(mNumber1.getText().toString()) +
                                Double.parseDouble(mNumber2.getText().toString());
                        printResult(calcResult);
                        break;
                    case R.id.minus:
                        calcResult = Double.parseDouble(mNumber1.getText().toString()) -
                                Double.parseDouble(mNumber2.getText().toString());
                        printResult(calcResult);
                        break;
                    case R.id.multiply:
                        calcResult = Double.parseDouble(mNumber1.getText().toString()) *
                                Double.parseDouble(mNumber2.getText().toString());
                        printResult(calcResult);
                        break;
                    case R.id.divided:
                        if (Double.parseDouble(mNumber2.getText().toString()) == 0) {
                            throw new IllegalArgumentException();
                        } else {
                            calcResult = Double.parseDouble(mNumber1.getText().toString()) /
                                    Double.parseDouble(mNumber2.getText().toString());
                            printResult(calcResult);
                        }

                        break;
                    default:
                        Toast.makeText(CalculatorActivity.this, R.string.msg_operator_empty,
                                Toast.LENGTH_SHORT).show();
                }
            } catch (IllegalArgumentException e) {
                if (!TextUtils.isEmpty(mResult.getText())) {

                }

                RadioButton activeRadioButton = (RadioButton) findViewById(mOperator.getCheckedRadioButtonId());
                String textRadioButton = activeRadioButton.getText().toString();
                AlertDialog dialog = getDialog(CalculatorActivity.this, textRadioButton);
                dialog.show();
            }
        }

    }

    void printResult(double resultDouble) {

        String result = getString(R.string.result, resultDouble);
        mResult.setText(result);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("result", mResult.getText());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mResult.setText(savedInstanceState.getCharSequence(CONDITION));
    }

    public AlertDialog getDialog(Activity activity, String text) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        String dialogTitle = getString(R.string.title_dialog_error, text);
        String dialogMessage = getString(R.string.msg_dialog_error, text, Double.parseDouble(mNumber1.getText().toString()), Double.parseDouble(mNumber2.getText().toString()));
        builder.setTitle(dialogTitle)
                .setMessage(dialogMessage)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }
}
