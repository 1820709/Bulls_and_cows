
package com.example.bullsandcows;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btn_minus_a, btn_minus_b, btn_minus_c, btn_minus_d;
    Button btn_plus_a, btn_plus_b, btn_plus_c, btn_plus_d;
    Button btn_match, btn_reset;
    TextView txt_number_a, txt_number_b, txt_number_c, txt_number_d;
    TextView txt_info, txt_output;
    Random rnd;
    int guessA = 1, guessB = 2, guessC = 3, guessD = 4;
    int randomA, randomB, randomC, randomD;
    int bulls = 0, cows = 0, tries = 0;
    String outputTxt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        generateNumbers();
        createButtonsListeners();
    }

    private void initView(){
        btn_minus_a = (Button) findViewById(R.id.btn_minus_a);
        btn_minus_b = (Button) findViewById(R.id.btn_minus_b);
        btn_minus_c = (Button) findViewById(R.id.btn_minus_c);
        btn_minus_d = (Button) findViewById(R.id.btn_minus_d);

        btn_plus_a = (Button) findViewById(R.id.btn_plus_a);
        btn_plus_b = (Button) findViewById(R.id.btn_plus_b);
        btn_plus_c = (Button) findViewById(R.id.btn_plus_c);
        btn_plus_d = (Button) findViewById(R.id.btn_plus_d);

        btn_match = (Button) findViewById(R.id.btn_match);
        btn_reset = (Button) findViewById(R.id.btn_reset);

        txt_number_a = (TextView) findViewById(R.id.txt_number_a);
        txt_number_b = (TextView) findViewById(R.id.txt_number_b);
        txt_number_c = (TextView) findViewById(R.id.txt_number_c);
        txt_number_d = (TextView) findViewById(R.id.txt_number_d);

        txt_info = (TextView) findViewById(R.id.txt_info);
        txt_output = (TextView) findViewById(R.id.txt_output);
    }

    private void generateNumbers() {
        rnd = new Random();

        randomA = rnd.nextInt(9) + 1;

        do {
            randomB = rnd.nextInt(9) + 1;
        } while (randomB == randomA);

        do {
            randomC = rnd.nextInt(9) + 1;
        } while (randomC == randomA || randomC == randomB);

        do {
            randomD = rnd.nextInt(9) + 1;
        } while (randomD == randomA || randomD == randomB || randomD == randomC);
    }

    private void createButtonsListeners(){
        // Minus buttons listeners
        btn_minus_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(guessA > 0){
                    guessA--;
                } else {
                    guessA = 9;
                }
                txt_number_a.setText(""+guessA);
            }
        });

        btn_minus_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(guessB > 0){
                    guessB--;
                } else {
                    guessB = 9;
                }
                txt_number_b.setText(""+guessB);
            }
        });

        btn_minus_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(guessC > 0){
                    guessC--;
                } else {
                    guessC = 9;
                }
                txt_number_c.setText(""+guessC);
            }
        });

        btn_minus_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(guessD > 0){
                    guessD--;
                } else {
                    guessD = 9;
                }
                txt_number_d.setText(""+guessD);
            }
        });

        // Plus buttons listeners
        btn_plus_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(guessA < 9){
                    guessA++;
                } else {
                    guessA = 0;
                }
                txt_number_a.setText(""+guessA);
            }
        });

        btn_plus_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(guessB < 9){
                    guessB++;
                } else {
                    guessB = 0;
                }
                txt_number_b.setText(""+guessB);
            }
        });

        btn_plus_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(guessC < 9){
                    guessC++;
                } else {
                    guessC = 0;
                }
                txt_number_c.setText(""+guessC);
            }
        });

        btn_plus_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(guessD < 9){
                    guessD++;
                } else {
                    guessD = 0;
                }
                txt_number_d.setText(""+guessD);
            }
        });

        // RESET btn listener
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                guessA = 1;
                guessB = 2;
                guessC = 3;
                guessD = 4;
                txt_info.setText("Try again! The number was: " + randomA+randomB+randomC+randomD);
                txt_number_a.setText(""+guessA);
                txt_number_b.setText(""+guessB);
                txt_number_c.setText(""+guessC);
                txt_number_d.setText(""+guessD);
            }
        });

        //MATCH button listener
        btn_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(guessA==guessB || guessA==guessC || guessA==guessD || guessB==guessC
                        || guessB==guessD || guessC==guessD){
                    txt_info.setText("All numbers must be different!");
                } else {
                    tries++;
                    matchBulls();
                    matchCows();
                    outputTxt = outputTxt + tries + ". " + guessA + guessB + guessC + guessD +
                            " - Bulls: " + bulls + ", Cows: " + cows + "\n";
                    txt_output.setText(outputTxt);
                    bulls = 0;
                    cows = 0;
                }
            }
        });
    }

    private void matchBulls() {
        //bulls
        if(guessA == randomA) {
            bulls++;
        }
        if(guessB == randomB) {
            bulls++;
        }
        if(guessC == randomC) {
            bulls++;
        }
        if(guessD == randomD) {
            bulls++;
        }
        if(bulls == 4){
            //btn_reset.setEnabled(false);
            //btn_match.setEnabled(false);
            txt_info.setText("Congratulations!");
        }
    }

    private void matchCows() {
        //bulls
        if(guessA == randomB || guessA == randomC || guessA == randomD) {
            cows++;
        }
        if(guessB == randomA || guessB == randomC || guessB == randomD) {
            cows++;
        }
        if(guessC == randomA || guessC == randomB || guessC == randomD) {
            cows++;
        }
        if(guessD == randomA || guessD == randomB || guessD == randomC) {
            cows++;
        }
    }
}
