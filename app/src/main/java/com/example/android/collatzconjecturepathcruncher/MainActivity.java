package com.example.android.collatzconjecturepathcruncher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView longestPathDisplay;
    TextView longestPathSeedDisplay;
    TextView currentSeedDisplay;
    EditText startingNumberDisplay;

    BigInteger longestPathSeed= BigInteger.ONE;
    int longestPath=0;
    BigInteger currentSeed=BigInteger.ZERO;
    int currentPath=0;
    BigInteger workingSeed=BigInteger.ONE;
    boolean run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        longestPathDisplay = (TextView) findViewById(R.id.longest_path);
        longestPathSeedDisplay = findViewById(R.id.longest_path_seed);
        currentSeedDisplay = findViewById(R.id.current_seed_display);
        startingNumberDisplay = findViewById(R.id.starting_number_display);

        longestPathDisplay.setText(getString(R.string.longest_path_display,longestPath));
        longestPathSeedDisplay.setText(getString(R.string.longest_path_seed_display,longestPathSeed));
        currentSeedDisplay.setText(getString(R.string.current_seed_display,currentSeed));
    }

    public void startCrunching(View view){

        String value = startingNumberDisplay.getText().toString();
        currentSeed = new BigInteger(value);

        workingSeed=currentSeed;

        run=true;

        while(run){

            if(workingSeed.compareTo(BigInteger.ONE)==0){

                if(currentPath>longestPath){
                    longestPath=currentPath;
                    longestPathSeed=currentSeed;
                    updateScreen();
                }
                currentSeed= currentSeed.add(BigInteger.ONE);
                workingSeed=currentSeed;
                Log.d("end", "startCrunching: Finished "+(currentSeed.subtract(BigInteger.ONE))+
                        " at "+currentPath+". Starting "+currentSeed);
                currentPath=0;
            }

            if (workingSeed.mod(new BigInteger("2")).compareTo(BigInteger.ZERO)==0){
                workingSeed=workingSeed.divide(new BigInteger("2"));
            }else{
                workingSeed=(workingSeed.multiply(new BigInteger("3"))).add(BigInteger.ONE);
            }
            currentPath++;
        }

    }

    public void updateScreen(){

        longestPathDisplay.setText(getString(R.string.longest_path_display,longestPath));
        longestPathSeedDisplay.setText(getString(R.string.longest_path_seed_display,longestPathSeed));
        currentSeedDisplay.setText(getString(R.string.current_seed_display,currentSeed));

    }

}
