package by.prakovec.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Admin on 15.02.2015.
 */
public class CalculatorActivity extends Activity {

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

                Toast.makeText(
                        CalculatorActivity.this, R.string.msg_incorrect_data, Toast.LENGTH_SHORT
                ).show();
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
        mResult.setText(savedInstanceState.getCharSequence("result"));
    }
}
