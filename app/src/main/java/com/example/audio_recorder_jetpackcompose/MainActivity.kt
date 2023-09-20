package com.example.audio_recorder_jetpackcompose

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.audio_recorder_jetpackcompose.playback.AndroidAudioPlayer
import com.example.audio_recorder_jetpackcompose.record.AndroidAudioRecorder
import com.example.audio_recorder_jetpackcompose.ui.theme.AudiorecorderjetpackcomposeTheme
import java.io.File
import android.Manifest
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.widget.ImageView
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.IconButton
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    private val recorder by lazy {
        AndroidAudioRecorder(applicationContext)
    }

    private val player by lazy {
        AndroidAudioPlayer(applicationContext)
    }

    private var audioFile:File?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )
        setContent {
            AudiorecorderjetpackcomposeTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            File(cacheDir,"audio.mp3").also {
                                recorder.start(it)
                                audioFile = it
                            }
                        }
                    ) {
                        Text(text = "start recording")
                    }

                    Button(
                        onClick = {
                            recorder.stop()
                        }
                    ) {
                        Text(text = "stop recording")
                    }

                    Button(
                        onClick = {
                            player.playFile(audioFile?: return@Button)
                        }
                    ) {
                        Text(text = "play")
                    }
                    Button(
                        onClick = {
                            player.stop()
                        }
                    ) {
                        Text(text = " stop playing")
                    }
                }
            }
        }
    }
}


