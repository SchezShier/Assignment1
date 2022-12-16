package com.schie.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText weight,goldValue;
    RadioButton btnWear, btnKeep;
    TextView Output1, Output2, Output3;
    Button btnCalculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateButtonClick(View view) {
        try{
            weight = (EditText)findViewById(R.id.weight);
            goldValue = (EditText) findViewById(R.id.goldValue);
            btnWear = (RadioButton) findViewById(R.id.btnWear);
            btnKeep = (RadioButton) findViewById(R.id.btnKeep);
            Output1 = (TextView) findViewById(R.id.Output1);
            Output2 = (TextView) findViewById(R.id.Output2);
            Output3 = (TextView) findViewById(R.id.Output3);
            btnCalculate = (Button) findViewById(R.id.btnCalculate);

            double goldWeight = Double.parseDouble(weight.getText().toString());
            double Value = Double.parseDouble(goldValue.getText().toString());
            double TotalValue = goldWeight * Value;
            double goldMinus=0;
            double keepValue =85 , wearValue = 200;
            double payable = 0, zakat =0 ;

            if(btnKeep.isChecked()){
                goldMinus  = (goldWeight - keepValue) ;
                if(goldMinus > 0 ) {
                    payable = goldMinus * Value;
                    zakat = payable * 0.025;
                    Toast errorToast = Toast.makeText(MainActivity.this, "The amount need to be paid : RM"+zakat, Toast.LENGTH_LONG);
                    errorToast.show();
                }else if (goldMinus < 0){
                    payable = 0;
                    Toast errorToast = Toast.makeText(MainActivity.this, "You do not require to pay zakat", Toast.LENGTH_LONG);
                    errorToast.show();
                }
            }else if (btnWear.isChecked()){
                goldMinus  = (goldWeight - wearValue) ;
                if(goldMinus > 0 ) {
                    payable = goldMinus * Value;
                    zakat = payable * 0.025;
                    Toast errorToast = Toast.makeText(MainActivity.this, "The amount need to be paid : RM"+zakat, Toast.LENGTH_LONG);
                    errorToast.show();
                }else if (goldMinus < 0) {
                    payable = 0;
                    Toast errorToast = Toast.makeText(MainActivity.this, "You do not require to pay zakat", Toast.LENGTH_LONG);
                    errorToast.show();
                }
            }
            Output1.setText("Total Value of the gold: RM " + TotalValue);
            Output2.setText("Payable Zakat RM " + payable);
            Output3.setText("Total Zakat: RM " + zakat);
        }

        catch (java.lang.NumberFormatException nfe ){
          Toast.makeText(this, "please enter a valid number", Toast.LENGTH_LONG).show();

        }catch (Exception exp){
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();

            Log.d("Exception",exp.getMessage());
        }
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }
    //menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.calculator:
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.page:
                intent = new Intent(MainActivity.this,About.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
        }


}