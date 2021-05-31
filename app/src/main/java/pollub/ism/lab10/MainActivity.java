package pollub.ism.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import pollub.ism.lab10.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding window = null;

    private AsyncTaskTwo asyncTaskTwo = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        window = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(window.getRoot());

        window.progressBar.setProgress(0);
        window.progressBar.setMax(100);
        window.buttonThree.setEnabled(true);
        window.buttonFour.setEnabled(false);
    }

    public void buttonOneAction(View view){
        new AsyncTaskOne(window.textFieldOne).execute();
        window.textFieldOne.setText(R.string.workInProgress);
    }

    public void buttonTwoAction(View view){
        new AsyncTaskOne(window.textFieldTwo).execute();
        window.textFieldTwo.setText(R.string.workInProgress);

    }

    public void buttonThreeAction(View view){
        window.buttonThree.setEnabled(false);
        int repetitions = Integer.valueOf(window.editFieldOne.getText().toString());
        int pauseTime = Integer.valueOf(window.editFieldTwo.getText().toString());

        asyncTaskTwo = new AsyncTaskTwo(window.progressBar, window.buttonThree, window.buttonFour);
        asyncTaskTwo.execute(repetitions,pauseTime);
        window.buttonFour.setEnabled(true);

    }

    public void buttonFourAction(View view){
        asyncTaskTwo.cancel(true);
    }
}
