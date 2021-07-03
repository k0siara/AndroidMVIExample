package com.patrykkosieradzki.androidmviexample.utils

import android.graphics.Bitmap
import android.os.Build
import android.os.Environment.DIRECTORY_PICTURES
import android.os.Environment.getExternalStoragePublicDirectory
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.captureToImage
import com.patrykkosieradzki.androidmviexample.BuildConfig
import java.io.File
import java.io.FileOutputStream

@RequiresApi(Build.VERSION_CODES.O)
fun captureAndCompare(
    screenshotName: String,
    node: SemanticsNodeInteraction
) {
    if (Robot.screenshotsEnabled) {
        val bitmap = node.captureToImage().asAndroidBitmap()
        saveScreenshot(screenshotName, bitmap)
//    val golden = InstrumentationRegistry.getInstrumentation()
//        .context.resources.assets.open("$goldenName.png").use { BitmapFactory.decodeStream(it) }
//
//    golden.compare(bitmap)
    }
}

private fun saveScreenshot(filename: String, bmp: Bitmap) {
    val picturesPath = getExternalStoragePublicDirectory(DIRECTORY_PICTURES).canonicalPath
    val folderPath =
        "$picturesPath/${BuildConfig.APPLICATION_ID}/${BuildConfig.BUILD_TYPE}/screenshots"
    val folder = File(folderPath).apply { mkdirs() }
    val file = File(folder, "$filename.png").apply { createNewFile() }
    FileOutputStream(file).use { out ->
        bmp.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
    println("Saved screenshot to ${file.canonicalPath}")
}

private fun Bitmap.compare(other: Bitmap) {
    if (this.width != other.width || this.height != other.height) {
        throw AssertionError("Size of screenshot does not match golden file (check device density)")
    }
    // Compare row by row to save memory on device
    val row1 = IntArray(width)
    val row2 = IntArray(width)
    for (column in 0 until height) {
        // Read one row per bitmap and compare
        this.getRow(row1, column)
        other.getRow(row2, column)
        if (!row1.contentEquals(row2)) {
            throw AssertionError("Sizes match but bitmap content has differences")
        }
    }
}

private fun Bitmap.getRow(pixels: IntArray, column: Int) {
    this.getPixels(pixels, 0, width, 0, column, width, 1)
}