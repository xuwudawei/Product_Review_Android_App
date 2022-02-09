package com.example.multifeaturekotlinapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BackgroundMusicJobService :JobService() {
    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.d("TAG","onStartJob")
        val intent= Intent(this@BackgroundMusicJobService,AlarmManagerBroadcast::class.java)
        val pendingIntent= PendingIntent.getBroadcast(this,234,intent, PendingIntent.FLAG_IMMUTABLE)
        val alarmManager=getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),pendingIntent)
        Toast.makeText(this@BackgroundMusicJobService,"Background Music Set", Toast.LENGTH_LONG).show()
        return true

    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d("TAG","onStopJob")
        return true
    }

}