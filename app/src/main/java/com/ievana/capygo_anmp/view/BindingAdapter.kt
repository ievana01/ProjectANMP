package com.ievana.capygo_anmp.view

import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import android.R

@BindingAdapter("android:imageUrl")
fun loadPhotoURL(imageView: ImageView, url:String) {
    val picasso = Picasso.Builder(imageView.context)
    picasso.listener { picasso, uri, exception ->
        exception.printStackTrace()
    }
    picasso.build().load(url).into(imageView, object: Callback {
        override fun onSuccess() {
            var v = imageView.parent as View
            imageView.visibility = View.VISIBLE
        }

        override fun onError(e: Exception?) {
            Log.e("picasso: ", e?.message.toString())
        }
    })
    Log.d("bindingadapter: ", "cek " + url)
}

@BindingAdapter("spinnerEntries")
fun setSpinnerEntries(spinner: Spinner, entries: List<String>?) {
    entries?.let {
        val adapter = ArrayAdapter(spinner.context, R.layout.simple_spinner_item, it)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}
