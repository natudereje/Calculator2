package com.example.calculator2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator2.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = findViewById(R.id.tvOutput);

        // Set click listeners for buttons
        findViewById(R.id.btnClear).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnDivide).setOnClickListener(this);
        findViewById(R.id.btnMultiply).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btnMinus).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btnPlus).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btnEqual).setOnClickListener(this);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btnDecimal).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String currentText = tvOutput.getText().toString();
        switch (v.getId()) {
            case R.id.btnClear:
                tvOutput.setText("");
                break;
            case R.id.btnDelete:
                if (!currentText.isEmpty()) {
                    tvOutput.setText(currentText.substring(0, currentText.length() - 1));
                }
                break;
            case R.id.btnEqual:
                try {
                    double result = evaluateExpression(currentText);
                    tvOutput.setText(String.valueOf(result));
                } catch (ArithmeticException e) {
                    tvOutput.setText("Error");
                }
                break;
            default:
                Button buttonClicked = (Button) v;
                tvOutput.append(buttonClicked.getText());
                break;
        }
    }

    private double evaluateExpression(String expression) {
        // Split the expression into operands and operator
        String[] tokens = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");

        double result = Double.parseDouble(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double operand = Double.parseDouble(tokens[i + 1]);

            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    if (operand != 0) {
                        result /= operand;
                    } else {
                        throw new ArithmeticException("Division by zero");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }

        return result;
    }
}
