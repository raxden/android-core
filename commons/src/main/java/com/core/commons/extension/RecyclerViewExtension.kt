package com.core.commons.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface OnPageEndlessListener {
    fun onPageEndless()
}

fun RecyclerView.setOnPageEndlessListener(listener: OnPageEndlessListener) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (pageEndlessDetect(recyclerView))
                listener.onPageEndless()
        }
    })
}

private fun pageEndlessDetect(recyclerView: RecyclerView): Boolean {
    return (recyclerView.layoutManager as? LinearLayoutManager)?.let {
        val visibleItemCount = it.childCount
        val totalItemCount = it.itemCount
        val pastVisibleItems = it.findFirstVisibleItemPosition()
        pastVisibleItems + visibleItemCount >= totalItemCount - visibleItemCount
    } ?: false
}
