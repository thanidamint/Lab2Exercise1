package th.ac.tu.siit.its333.lab2exercise1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    // expr = the current string to be calculated
    StringBuffer expr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expr = new StringBuffer();
        updateExprDisplay();
    }

    public void updateExprDisplay() {
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(expr.toString());
    }

    double result;
    public void recalculate() {
        //Calculate the expression and display the output

        //Split expr into numbers and operators
        //e.g. 123+45/3 --> ["123", "+", "45", "/", "3"]
        //reference: http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
        String e = expr.toString();
        String[] tokens = e.split("((?<=\\+)|(?=\\+))|((?<=\\-)|(?=\\-))|((?<=\\*)|(?=\\*))|((?<=/)|(?=/))");

         result = Integer.parseInt(tokens[0]);

        //int i = Integer.parseInt(s);

        for (int x = 0; x < tokens.length && tokens.length % 2 == 1  ; x++)
        {
           String s = tokens[x];

            if (x%2==1) // 1,3,5
           {
               switch (s) {
                   case "+": result =  result + Integer.parseInt(tokens[x+1]); break;
                   case "-": result =  result - Integer.parseInt(tokens[x+1]); break;
                   case "*": result =  result * Integer.parseInt(tokens[x+1]); break;
                   case "/": result =  result / Integer.parseInt(tokens[x+1]); break;
               }

           }
        }

        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(Double.toString(result));

        //char c = expr.charAt(expr.length()-1);
        //if(c != '+' || )
    }

    public void digitClicked(View v) {
        //d = the label of the digit button
        String d = ((TextView)v).getText().toString();
        //append the clicked digit to expr
        expr.append(d);
        //update tvExpr
        updateExprDisplay();
        //calculate the result if possible and update tvAns
        recalculate();
    }

    public void operatorClicked(View v) {
        String o = ((TextView)v).getText().toString();
        if(!(expr.charAt(expr.length() - 1) == '+' || expr.charAt(expr.length() - 1) == '-' || expr.charAt(expr.length() - 1) == '*' || expr.charAt(expr.length() - 1) == '/')) {
            expr.append(o);
            updateExprDisplay();
        }
        //IF the last character in expr is not an operator and expr is not "",
        //THEN append the clicked operator and updateExprDisplay,
        //ELSE do nothing
    }

    public void ACClicked(View v) {
        //Clear expr and updateExprDisplay
        expr = new StringBuffer();
        updateExprDisplay();
        //Display a toast that the value is cleared
        Toast t = Toast.makeText(this.getApplicationContext(),
                "All cleared", Toast.LENGTH_SHORT);
        t.show();
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(" ");
    }

    public void BSClicked(View v) {
        //Remove the last character from expr, and updateExprDisplay
        if (expr.length() > 0) {
            expr.deleteCharAt(expr.length()-1);
            updateExprDisplay();
            recalculate();
        }

    }

    public void equalClicked(View v) {
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(Double.toString(result));
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(" ");
    }

    double memory;

    public void MbuttonClicked(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.madd: // Increase botton's id
                memory = memory + result;
                break;
            case R.id.msub: // Decrease button's id
                memory = memory - result;
                break;
            case R.id.mr: // Increase botton's id
                TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
                tvExpr.setText(Double.toString(memory));
                break;
            case R.id.mc: // Decrease button's id
                memory = 0;
                break;
        }

        //TextView tv = (TextView) findViewById(R.id.tvOutput);
        //tv.setText(Integer.toString(counter));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
