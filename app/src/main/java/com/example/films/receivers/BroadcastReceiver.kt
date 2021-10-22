package com.example.films.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MainBroadcastReceiver : BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        StringBuilder().apply {
            append("Изменение в состоянии соединение с интернетом.\n")
            toString().also {
                Toast.makeText(p0, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}