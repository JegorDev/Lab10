package pollub.ism.lab10;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class AsyncTaskTwo extends AsyncTask<Integer, Integer, Void> {

    WeakReference<Button> startButtonReference;
    WeakReference<Button> cancelButtonReference;
    WeakReference<ProgressBar> progressBarReference;
    int repetitionsAmount;
    int stopTime;
    int progress;

    public AsyncTaskTwo(ProgressBar progressBar, Button startButton, Button cancelButton) {
        progressBarReference = new WeakReference<>(progressBar);
        startButtonReference = new WeakReference<>(startButton);
        cancelButtonReference = new WeakReference<>(cancelButton);
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        repetitionsAmount = integers[0];
        stopTime = integers[1];

        for(int i=0;i<repetitionsAmount;i++){
            if(isCancelled()){
                break;
            }

            try{
                Thread.sleep(stopTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            progress = 100 *(i+1) / repetitionsAmount;

            publishProgress(progress);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        startButtonReference.get().setEnabled(true);
        cancelButtonReference.get().setEnabled(false);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBarReference.get().setProgress(values[0]);
    }

    @Override
    protected void onCancelled(Void aVoid) {
        startButtonReference.get().setEnabled(true);
        cancelButtonReference.get().setEnabled(false);
    }
}
