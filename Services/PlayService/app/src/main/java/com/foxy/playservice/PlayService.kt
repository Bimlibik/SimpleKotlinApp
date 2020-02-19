package com.foxy.playservice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class PlayService : Service() {

    lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(applicationContext, "Service is creating", Toast.LENGTH_SHORT).show()
        mediaPlayer = MediaPlayer.create(this, R.raw.example)
        mediaPlayer.isLooping = false
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(applicationContext, "Service is starting", Toast.LENGTH_SHORT).show()
        mediaPlayer.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "Service is stopping", Toast.LENGTH_SHORT).show()
        mediaPlayer.stop()
    }
}
