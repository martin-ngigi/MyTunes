package com.example.mytunes

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mytunes.databinding.ActivityAudioBinding


class AudioActivity : AppCompatActivity() {

    lateinit var audioBinding: ActivityAudioBinding

    val IMAGE_INTENT =1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        audioBinding = ActivityAudioBinding.inflate(layoutInflater)
        setContentView(audioBinding.root)

        audioBinding.selectAudioBtn.setOnClickListener {
            getAudioSelected()
        }
    }

    private fun getAudioSelected() {
        Intent(Intent.ACTION_GET_CONTENT).also {
            it.type = "audio/*" //* means will look for every type of image
            startActivityForResult(it, IMAGE_INTENT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode== Activity.RESULT_OK) {

                //the selected audio.
                val uri: Uri? = data?.data
                audioBinding.audioTv.text="$uri"

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}