package com.example.audio_recorder_jetpackcompose.record

import java.io.File

interface AudioRecorder {
    fun start(outputFile:File)
    fun stop()

}