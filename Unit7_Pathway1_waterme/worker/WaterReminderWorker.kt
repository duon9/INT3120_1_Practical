 

package com.example.waterme.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.waterme.R

class WaterReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        val plantName = inputData.getString(nameKey)
        Log.d("WaterReminderWorker", "Running worker for plant: $plantName 🌿")

        makePlantReminderNotification(
            applicationContext.resources.getString(R.string.time_to_water, plantName),
            applicationContext
        )

        return Result.success()
    }

    companion object {
        const val nameKey = "NAME"
    }
}
