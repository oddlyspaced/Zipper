package com.oddlyspaced.zippytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.oddlyspaced.zippytest.kellinwood.zipsigner.ZipSigner
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testSign()
    }

    private fun testSign() {

        try {
            // Sign with the built-in default test key/certificate.
            val zipSigner = ZipSigner()
            zipSigner.keymode = "testkey"

            val inpFile = File(Environment.getExternalStorageDirectory().path + "/apk/sample.zip")
            val outFile = File(Environment.getExternalStorageDirectory().path + "/apk/sample-signed.zip")
            zipSigner.signZip(inpFile.path, outFile.path)
            Toast.makeText(applicationContext, "SUCCESS", Toast.LENGTH_LONG).show()
        }
        catch (e: Exception) {
            Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_LONG).show()
            Log.e("ERROR", e.toString())
            e.printStackTrace()
        }

    }
}