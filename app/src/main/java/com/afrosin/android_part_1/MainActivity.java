package com.afrosin.android_part_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormatSymbols;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView twCurrentOperation;
    private TextView twCalcResult;
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        twCurrentOperation = findViewById(R.id.tw_current_operation);
        twCalcResult = findViewById(R.id.tw_calc_result);
        String[] operationButtons = {"bt_ac", "bt_del", "bt_percent", "bt_division", "bt_multiplication",
                "bt_minus", "bt_add", "bt_equally", "bt_digit_separator"
        };

        for (String operationButton : operationButtons) {
            Button btTmp = findViewById(getResources().getIdentifier(operationButton, "id", getPackageName()));
            btTmp.setOnClickListener(this);
        }

        for (int i = 0; i <= 9; i++) {
            Button btTmp = findViewById(getResources().getIdentifier(String.format("bt_dig_%d", i), "id", getPackageName()));
            btTmp.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        int btnId = view.getId();

        switch (btnId) {
            case R.id.bt_ac:
                calcAction(twCurrentOperation, R.string.bt_ac, CalcOperation.AC);
                break;
            case R.id.bt_del:
                calcAction(twCurrentOperation, R.string.bt_del, CalcOperation.DEL);
                break;
            case R.id.bt_percent:
                calcAction(twCurrentOperation, R.string.bt_percent, CalcOperation.PERCENT);
                break;
            case R.id.bt_division:
                calcAction(twCurrentOperation, R.string.bt_division, CalcOperation.DIVISION);
                break;
            case R.id.bt_multiplication:
                calcAction(twCurrentOperation, R.string.bt_multiplication, CalcOperation.MULTIPLICATION);
                break;
            case R.id.bt_minus:
                calcAction(twCurrentOperation, R.string.bt_minus, CalcOperation.MINUS);
                break;
            case R.id.bt_add:
                calcAction(twCurrentOperation, R.string.bt_add, CalcOperation.ADD);
                break;
            case R.id.bt_equally:
                calcAction(twCurrentOperation, R.string.bt_equally, CalcOperation.EQUALLY);
                showResult();
                break;
            case R.id.bt_digit_separator:
                calcAction(twCurrentOperation, R.string.bt_digit_separator, CalcOperation.DIGIT_SEPARATOR);
                break;
            case R.id.bt_dig_0:
                calcAction(twCurrentOperation, R.string.dig_0, CalcOperation.DIG_0);
                break;
            case R.id.bt_dig_1:
                calcAction(twCurrentOperation, R.string.dig_1, CalcOperation.DIG_1);
                break;
            case R.id.bt_dig_2:
                calcAction(twCurrentOperation, R.string.dig_2, CalcOperation.DIG_2);
                break;
            case R.id.bt_dig_3:
                calcAction(twCurrentOperation, R.string.dig_3, CalcOperation.DIG_3);
                break;
            case R.id.bt_dig_4:
                calcAction(twCurrentOperation, R.string.dig_4, CalcOperation.DIG_4);
                break;
            case R.id.bt_dig_5:
                calcAction(twCurrentOperation, R.string.dig_5, CalcOperation.DIG_5);
                break;
            case R.id.bt_dig_6:
                calcAction(twCurrentOperation, R.string.dig_6, CalcOperation.DIG_6);
                break;
            case R.id.bt_dig_7:
                calcAction(twCurrentOperation, R.string.dig_7, CalcOperation.DIG_7);
                break;
            case R.id.bt_dig_8:
                calcAction(twCurrentOperation, R.string.dig_8, CalcOperation.DIG_8);
                break;
            case R.id.bt_dig_9:
                calcAction(twCurrentOperation, R.string.dig_9, CalcOperation.DIG_9);
                break;
        }
    }

    private void showResult() {

        String strCurrentOperation = twCurrentOperation.getText().toString();

        if (strCurrentOperation.replaceAll(String.format("\\%s", getStringById(this, R.string.bt_digit_separator)), "").length() > 0) {
            double result = calculator.getCalcResult();
            twCalcResult.append(String.format(getDigitFormat(result, twCalcResult.getText().toString()), strCurrentOperation, getStringById(this, R.string.bt_equally), result));
        }

        // очищаем
        calcAction(twCurrentOperation, R.string.bt_ac, CalcOperation.AC);
    }

    // получаем формат выводимой строки
    private String getDigitFormat(double calcResult, String textViewText) {
        StringBuilder strFormat = new StringBuilder();

        String strResult = String.format("%f", calcResult);

        if (textViewText.length() > 0) {
            strFormat.append("\n");
        }
        strFormat.append("%s\n%s\n");
        // вычисляем количество нулей после запятой
        if (strResult.indexOf(DecimalFormatSymbols.getInstance().getDecimalSeparator()) > 0) {
            int decimalSeparatorIdx = strResult.indexOf(DecimalFormatSymbols.getInstance().getDecimalSeparator());
            String tmpStr = strResult.substring(decimalSeparatorIdx + 1);
            int countDigitAfterSeparator = tmpStr.replaceAll("(0{1,}$)", "").length();
            strFormat.append(String.format("%s%s%d%s", "%", ".", countDigitAfterSeparator, "f"));
        } else {
            strFormat.append("%.0f");
        }
        return strFormat.toString();
    }

    private void calcAction(TextView twCurrentOperation, int stringId, CalcOperation operation) {
        calculator.setCurrentOperation(getStringById(this, stringId), operation);
        twCurrentOperation.setText(calculator.getCurrentOperationAsString());
    }

    private static String getStringById(MainActivity activity, int resourceId) {
        return activity.getBaseContext().getString(resourceId);
    }

}