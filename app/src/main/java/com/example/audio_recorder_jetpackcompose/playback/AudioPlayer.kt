package com.example.audio_recorder_jetpackcompose.playback

import java.io.File

interface AudioPlayer {
    fun playFile(file:File)
    fun stop()
}