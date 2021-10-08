package io.github.sergey_ivanenko.thecatapi.domain

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

object CatsLoader {

    private var outputStream: OutputStream? = null

    fun saveMediaToStorage(context: Context?, bitmap: Bitmap, filename: String?) {
        val dir = File(Environment.getExternalStorageDirectory(), "Pictures/Cats")
        if (!dir.exists()) {
            dir.mkdir()
        }

        val file = File(dir, filename ?: "${System.currentTimeMillis()}.jpg")

        try {
            outputStream = FileOutputStream(file)
            outputStream.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                Toast.makeText(context, "Successfully Saved", Toast.LENGTH_SHORT).show()
                it?.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
