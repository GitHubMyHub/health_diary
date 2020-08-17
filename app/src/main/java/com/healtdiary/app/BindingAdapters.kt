package com.healtdiary.app

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.healtdiary.app.data.Data
import com.healtdiary.app.ui.data.DataAdapter
import com.healtdiary.app.ui.data.DataViewModel.DataApiStatus


private val TAG = "BindingAdapters"

@BindingAdapter("listData")
fun bindRecyclerViewData(recyclerView: RecyclerView, data: List<Data>?) {
    val adapter = recyclerView.adapter as DataAdapter
    adapter.submitList(data)
}

@BindingAdapter("dataApiStatus")
fun bindStatus(statusImageView: ImageView, status: DataApiStatus?) {
    when (status) {
        DataApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        DataApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        DataApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

