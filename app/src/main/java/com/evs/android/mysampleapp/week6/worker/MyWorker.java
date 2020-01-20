package com.evs.android.mysampleapp.week6.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class MyWorker extends Worker {

    public final String TAG = MyWorker.class.getSimpleName();

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork() called");
        return Worker.Result.success();
    }

    public static void enqueueSelf(Context context) {
        WorkManager.getInstance(context).enqueueUniquePeriodicWork("myWork",
                ExistingPeriodicWorkPolicy.KEEP,
                new PeriodicWorkRequest.Builder(MyWorker.class,
                        PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
                        .build());
    }

    public static void dequeueSelf(Context context) {
        WorkManager.getInstance(context).cancelUniqueWork("myWork");
    }

    /*private boolean isWorkScheduled(String tag) {
        WorkManager instance = WorkManager.getInstance(WorkerActivity.this);
        ListenableFuture<List<WorkInfo>> statuses = instance.getWorkInfosByTag(tag);
        try {
            boolean running = false;
            List<WorkInfo> workInfoList = statuses.get();
            for (WorkInfo workInfo : workInfoList) {
                WorkInfo.State state = workInfo.getState();
                running = state == WorkInfo.State.RUNNING | state == WorkInfo.State.ENQUEUED;
            }
            return running;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }*/
}
