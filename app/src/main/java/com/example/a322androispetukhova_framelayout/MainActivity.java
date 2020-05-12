package com.example.a322androispetukhova_framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnChangeToUsual;
    private Button btnDelete;
    private Button btnPositiveNegative;
    private Button btnPer;
    private Button btnDivide;
    private Button btnMultiplication;
    private Button btnMinus;
    private Button btnPlus;
    private Button btnEquals;
    private TextView result;
    private TextView numberField;
    private TextView operationField;
    String lastOperation = "=";
    Double operand = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {

        result = findViewById(R.id.result);
        btnDivide = findViewById(R.id.btnDivide);
        btnMultiplication = findViewById(R.id.btnMultiplication);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnEquals = findViewById(R.id.btnEquals);
        numberField = findViewById(R.id.numberField);
        operationField = findViewById(R.id.operationField);
        btnPositiveNegative = findViewById(R.id.btnPositiveNegative);
        btnPer = findViewById(R.id.btnPer);
        btnChangeToUsual = findViewById(R.id.btnChangeToUsual);

        btnDelete = findViewById(R.id.btnDelete);

        setOnClickButton();
        setOnClickChange();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) { // сохранение состояния
        outState.putString("OPERATION", lastOperation);
        if (operand != null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        result.setText(operand.toString());
        operationField.setText(lastOperation);
    }


    private View.OnClickListener numberButtonListener = new View.OnClickListener() { //создаем переменную слушателя для кнопок с цифрами и точки

        @Override
        public void onClick(View v) {
            Button buttonNumber = (Button) v;
            numberField.append(buttonNumber.getText());
            if (lastOperation.equals("=") && operand != null) {
                operand = null;
            }
        }
    };

    private void setOnClickButton() {
        findViewById(R.id.btnPoint).setOnClickListener(numberButtonListener); //устанавливаем слушателя для кнопок всех
        findViewById(R.id.btn0).setOnClickListener(numberButtonListener);
        findViewById(R.id.btn1).setOnClickListener(numberButtonListener);
        findViewById(R.id.btn2).setOnClickListener(numberButtonListener);
        findViewById(R.id.btn3).setOnClickListener(numberButtonListener);
        findViewById(R.id.btn4).setOnClickListener(numberButtonListener);
        findViewById(R.id.btn5).setOnClickListener(numberButtonListener);
        findViewById(R.id.btn6).setOnClickListener(numberButtonListener);
        findViewById(R.id.btn7).setOnClickListener(numberButtonListener);
        findViewById(R.id.btn8).setOnClickListener(numberButtonListener);
        findViewById(R.id.btn9).setOnClickListener(numberButtonListener);
        btnDelete.setOnClickListener(numberOperationListener);
        btnPositiveNegative.setOnClickListener(numberOperationListener);
        btnPer.setOnClickListener(numberOperationListener);
        btnDivide.setOnClickListener(numberOperationListener);
        btnMultiplication.setOnClickListener(numberOperationListener);
        btnMinus.setOnClickListener(numberOperationListener);
        btnPlus.setOnClickListener(numberOperationListener);
        btnEquals.setOnClickListener(numberOperationListener);
    }

    private View.OnClickListener numberOperationListener = new View.OnClickListener() { ///создаем переменную слушателя для операндов ( операций)
        @Override
        public void onClick(View v) {
            Button buttonOperation = (Button) v;
            String op = buttonOperation.getText().toString();
            String number = numberField.getText().toString();
            if (op.equals("C")) {
                operationField.setText(null);
                numberField.setText(null);
                result.setText(null);
                operand = null;
            } else {
                if (op.equals("+/-")) {
                    double d = Double.parseDouble(number) * -1;
                    numberField.setText(Double.toString(d));
                } else {
                    if (number.length() > 0) {
                        try {
                            performOperation(Double.valueOf(number), op);
                        } catch (NumberFormatException ex) {
                            numberField.setText("");
                        }
                    }

                    lastOperation = op;
                    operationField.setText(lastOperation);

                }
            }

        }

    };

    private void performOperation(Double number, String operation) {
        // если операнд ранее не был установлен (при вводе самой первой операции)
        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch (lastOperation) {
                case "=":
                    operand = number;
                    break;
                case "/":
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand /= number;
                    }
                    break;
                case "x":
                    operand *= number;
                    break;
                case "+":
                    operand += number;
                    break;
                case "-":
                    operand -= number;
                    break;
                case "%":
                    operand *= number / 100;
                    break;

            }

        }

        result.setText(operand.toString());
        numberField.setText("");

    }

    private void setOnClickChange() {

        final LinearLayout row1 = findViewById(R.id.row1);
        btnChangeToUsual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (row1.getVisibility() == row1.VISIBLE){
                    row1.setVisibility(View.GONE);

                }else{
                    row1.setVisibility(View.VISIBLE);
                }
            }
        });


    }
}