package com.core.commons.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("setAdapter")
fun RecyclerView.setAdapter(adapter: RecyclerView.Adapter<*>) {
    setAdapter(adapter)
}

fun RecyclerView.setItems() {

}