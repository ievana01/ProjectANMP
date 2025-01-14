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
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@BindingAdapter("android:imageUrl")
fun loadPhotoURL(imageView: ImageView, url: String?) {
    val picasso = Picasso.Builder(imageView.context).build()
    if (!url.isNullOrEmpty()) {
        picasso.load(url).into(imageView, object : Callback {
            override fun onSuccess() {
                imageView.visibility = View.VISIBLE
            }

            override fun onError(e: Exception?) {
                Log.e("picasso: ", e?.message.toString())
            }
        })
    } else {
        // Load a default image resource if URL is null or empty
        imageView.setImageResource(R.drawable.ic_media_play)
    }
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

@BindingAdapter("fdate")
fun setFormattedDate(view: TextView, date: String?) {
    if (date != null) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val parsedDate = dateFormat.parse(date)
        val dayFormat = SimpleDateFormat("dd", Locale.getDefault())
        view.text = dayFormat.format(parsedDate ?: Date())
    }
}

@BindingAdapter("fmonth")
fun setFormattedMonth(view: TextView, date: String?) {
    if (date != null) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val parsedDate = dateFormat.parse(date)
        val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())
        view.text = monthFormat.format(parsedDate ?: Date()).uppercase()
    }
}
