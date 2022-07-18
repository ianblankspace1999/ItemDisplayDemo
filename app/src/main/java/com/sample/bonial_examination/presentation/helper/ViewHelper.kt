package com.sample.bonial_examination.presentation.helper

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.util.DisplayMetrics
import android.widget.ImageView


open class ViewHelper {

     fun getScreenWidthResolution(context: Context): Float {
         val displayMetrics: DisplayMetrics = context.resources.displayMetrics
//         val dpHeight = displayMetrics.heightPixels / displayMetrics.density
         val dpWidth = displayMetrics.widthPixels / displayMetrics.density
         return dpWidth
    }


    fun bindLoadImage(view: ImageView, url: String?) {
        val imageUri: Uri = Uri.parse(url)
        view.setImageURI(imageUri)
    }

    fun showError(context: Context, message: String) {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Error")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


}