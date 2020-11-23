package com.oddlyspaced.zippytest

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oddlyspaced.zippytest.kellinwood.zipsigner.ZipSigner
import org.zeroturnaround.zip.ZipUtil
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // testSign()
        // zipFiles()
        // copyTemplate()
        // ZipUtil.explode(File(applicationContext.externalCacheDir!!.path, "template.zip"))
        // ZipUtil.pack(File(applicationContext.externalCacheDir!!.path, "ttep"), File(applicationContext.externalCacheDir!!.path, "wow.zip"));
    }

    /*
    Steps :
    1. Unpack assets to a safe location
    2. <Apply mods to icon>
    3. Zip the apk asset
    4. Sign the zip
    5. Rename extension to .apk
     */

    private fun copyTemplate() {
        try {
            val templateFile = assets.open("template.zip")
            val outFile = File(applicationContext.externalCacheDir!!.path + "/template.zip")
            templateFile.copyTo(outFile.outputStream())
            templateFile.close()
        } catch (e: Exception) {
            Log.e("MAIN", e.toString())
            e.printStackTrace()
        }
    }

    private fun testSign() {

        try {
            // Sign with the built-in default test key/certificate.
            val zipSigner = ZipSigner()
            zipSigner.keymode = "testkey"

            val inpFile = File(Environment.getExternalStorageDirectory().path + "/apk/sample.zip")
            val outFile =
                File(Environment.getExternalStorageDirectory().path + "/apk/sample-signed.zip")
            zipSigner.signZip(inpFile.path, outFile.path)
            Toast.makeText(applicationContext, "SUCCESS", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_LONG).show()
            Log.e("ERROR", e.toString())
            e.printStackTrace()
        }

    }
}