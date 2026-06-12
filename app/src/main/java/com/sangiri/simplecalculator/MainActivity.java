package com.sangiri.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private double operand1 = 0;
    private double operand2 = 0;
    private boolean isOperand1Set = false;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String buttonText = button.getText().toString();
                handleButtonClick(buttonText);
            }
        };

        int[] buttonIds = new int[]{
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonAdd, R.id.buttonSub,
                R.id.buttonMul, R.id.buttonDiv, R.id.buttonDot, R.id.buttonEq, R.id.buttonClear
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "C":
                clear();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                setOperator(buttonText);
                break;
            case "=":
                calculate();
                break;
            default:
                appendToEditText(buttonText);
                break;
        }
    }

    private void clear() {
        editText.setText("");
        operand1 = 0;
        operand2 = 0;
        isOperand1Set = false;
        operator = "";
    }

    private void setOperator(String op) {
        if (!editText.getText().toString().isEmpty()) {
            operand1 = Double.parseDouble(editText.getText().toString());
            isOperand1Set = true;
            operator = op;
            editText.setText("");
        }
    }

    private void calculate() {
        if (isOperand1Set && !editText.getText().toString().isEmpty()) {
            operand2 = Double.parseDouble(editText.getText().toString());
            double result = 0;
            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        editText.setText("Error");
                        return;
                    }
                    break;
            }
            editText.setText(String.valueOf(result));
            operand1 = result;
            operand2 = 0;
            isOperand1Set = false;
            operator = "";
        }
    }

    private void appendToEditText(String str) {
        editText.append(str);
    }
}
