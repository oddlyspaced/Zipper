package com.oddlyspaced.zippytest

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oddlyspaced.zippytest.kellinwood.zipsigner.ZipSigner
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // testSign()
        // zipFiles()
        copyTemplate()
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
        }
        catch (e: Exception) {
            Log.e("MAIN", e.toString())
            e.printStackTrace()
        }
    }

    private fun zipFiles() {
        FileZipper.zipFolder(Environment.getExternalStorageDirectory().path + "/apk/hello", Environment.getExternalStorageDirectory().path + "/apk/hello.zip")
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

    /*
      static public void zipFolder(String srcFolder, String destZipFile)
            throws Exception {
        ZipOutputStream zip = null;
        FileOutputStream fileWriter = null;
        fileWriter = new FileOutputStream(destZipFile);
        zip = new ZipOutputStream(fileWriter);
        addFolderToZip("", srcFolder, zip);
        zip.flush();
        zip.close();
    }

    static private void addFileToZip(String path, String srcFile,
                                     ZipOutputStream zip) throws Exception {
        File folder = new File(srcFile);
        if (folder.isDirectory()) {
            addFolderToZip(path, srcFile, zip);
        } else {
            byte[] buf = new byte[1024];
            int len;
            FileInputStream in = new FileInputStream(srcFile);
            zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
            while ((len = in.read(buf)) > 0) {
                zip.write(buf, 0, len);
            }
        }
    }

    static private void addFolderToZip(String path, String srcFolder,
                                       ZipOutputStream zip) throws Exception {
        File folder = new File(srcFolder);
        for (String fileName : folder.list()) {
            if (path.equals("")) {
                addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
            } else {
                addFileToZip(path + "/" + folder.getName(), srcFolder + "/"
                        + fileName, zip);
            }
        }
    }
     */

}