package com.afrosin.android_part_1;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private final String OPERATION_PERCENT = "%";
    private final String OPERATION_DIVISION = "/";
    private final String OPERATION_MULTIPLICATION = "*";
    private final String OPERATION_MINUS = "-";
    private final String OPERATION_ADD = "+";
    private final String OPERATION_DIGIT_SEPARATOR = ".";
    private final String OPERATION_DIG_0 = "0";
    private final String OPERATION_DIG_1 = "1";
    private final String OPERATION_DIG_2 = "2";
    private final String OPERATION_DIG_3 = "3";
    private final String OPERATION_DIG_4 = "4";
    private final String OPERATION_DIG_5 = "5";
    private final String OPERATION_DIG_6 = "6";
    private final String OPERATION_DIG_7 = "7";
    private final String OPERATION_DIG_8 = "8";
    private final String OPERATION_DIG_9 = "9";

    private final String DIGIT_PATTERN = "(\\d+(?:\\.\\d+)?)";
    //([-+*\/%])
    private final String OPERATION_PATTERN = String.format("([%s%s%s\\%s%s])", OPERATION_MINUS, OPERATION_ADD, OPERATION_MULTIPLICATION, OPERATION_DIVISION, OPERATION_PERCENT);

    private double calcResult;
    private StringBuilder displayString;
    private StringBuilder stringForCalc;

    public Calculator() {
        this.calcResult = 0;
        this.displayString = new StringBuilder();
        this.stringForCalc = new StringBuilder();
    }

    public double getCalcResult() {
        return calcResult;
    }

    public String getCurrentOperationAsString() {
        return displayString.toString();
    }

    public void setCurrentOperation(String str, CalcOperation operation) {

        switch (operation) {
            case AC:
                clearData();
                break;
            case DEL:
                deleteLastChar();
                break;
            case ADD:
                appendStr(str, OPERATION_ADD);
                break;
            case MINUS:
                appendStr(str, OPERATION_MINUS);
                break;
            case DIVISION:
                appendStr(str, OPERATION_DIVISION);
                break;
            case MULTIPLICATION:
                appendStr(str, OPERATION_MULTIPLICATION);
                break;
            case PERCENT:
                appendStr(str, OPERATION_PERCENT);
                break;
            case DIGIT_SEPARATOR:
                appendStr(str, OPERATION_DIGIT_SEPARATOR);
                break;
            case DIG_0:
                appendStr(str, OPERATION_DIG_0);
                break;
            case DIG_1:
                appendStr(str, OPERATION_DIG_1);
                break;
            case DIG_2:
                appendStr(str, OPERATION_DIG_2);
                break;
            case DIG_3:
                appendStr(str, OPERATION_DIG_3);
                break;
            case DIG_4:
                appendStr(str, OPERATION_DIG_4);
                break;
            case DIG_5:
                appendStr(str, OPERATION_DIG_5);
                break;
            case DIG_6:
                appendStr(str, OPERATION_DIG_6);
                break;
            case DIG_7:
                appendStr(str, OPERATION_DIG_7);
                break;
            case DIG_8:
                appendStr(str, OPERATION_DIG_8);
                break;
            case DIG_9:
                appendStr(str, OPERATION_DIG_9);
                break;
            case EQUALLY:
                calcResult();
                break;
            default:
                appendStr(str, str);
        }
    }

    private void appendStr(String displayStr, String forCalcStr) {
        this.displayString.append(displayStr);
        this.stringForCalc.append(forCalcStr);
    }

    private void clearData() {
        this.displayString.setLength(0);
        this.stringForCalc.setLength(0);
    }

    private void calcResult() {

        String str = stringForCalc.toString();
        calcResult = 0;

        if (str.replaceAll(String.format("\\%s", OPERATION_DIGIT_SEPARATOR), "").length() > 0) {

            double tmpDigit;

            Pattern pattern = Pattern.compile(OPERATION_PATTERN);
            Matcher operation_matcher = pattern.matcher(str);

            pattern = Pattern.compile(DIGIT_PATTERN);
            Matcher digit_matcher = pattern.matcher(str);

            digit_matcher.find();
            calcResult = strToDouble(digit_matcher.group(0));

            while (digit_matcher.find()) {
                tmpDigit = strToDouble(digit_matcher.group(0));
                operation_matcher.find();

                switch (operation_matcher.group(0)) {
                    case OPERATION_ADD:
                        calcResult += tmpDigit;
                        break;
                    case OPERATION_MINUS:
                        calcResult -= tmpDigit;
                        break;
                    case OPERATION_DIVISION:
                        calcResult /= tmpDigit;
                        break;
                    case OPERATION_MULTIPLICATION:
                        calcResult *= tmpDigit;
                        break;
//                case OPERATION_PERCENT:
//                    calcResult *= tmpDigit / 100;
//                    break;
                }
            }
        }
    }

    public double strToDouble(String str) {
        return Double.parseDouble(str);
    }

    public void deleteLastChar() {

        if (displayString.length() > 0) {
            displayString.setLength(displayString.length() - 1);
            stringForCalc.setLength(stringForCalc.length() - 1);
        }
    }
}