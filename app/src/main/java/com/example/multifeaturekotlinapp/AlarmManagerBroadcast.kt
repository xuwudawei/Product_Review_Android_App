package com.example.multifeaturekotlinapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast

class AlarmManagerBroadcast:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        var mp= MediaPlayer.create(context,R.raw.holyfather)
        Log.d("Hello","Background Music")
        mp.start()
        Toast.makeText(context,"Background Music Started", Toast.LENGTH_LONG).show()
    }

}