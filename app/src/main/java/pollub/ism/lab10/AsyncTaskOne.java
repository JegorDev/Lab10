package pollub.ism.lab10;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class AsyncTaskOne extends AsyncTask<Void, Void, String> {

    private WeakReference<TextView> textViewReference;

    public AsyncTaskOne(TextView textView) {
        this.textViewReference = new WeakReference<>(textView);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int n= random.nextInt(11);
        int sleepTime = n* 200;

        try{
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String resultMessage = "Finished task in " + sleepTime +" ms";
        return resultMessage;
    }

    protected void onPostExecute(String result){
        textViewReference.get().setText(result);
    }
}
