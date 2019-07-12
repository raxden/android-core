package com.core.commons.extension

import androidx.viewpager.widget.ViewPager

fun ViewPager.isFirstPage() = currentItem == 0

fun ViewPager.isLastPage() = currentItem == childCount - 1

fun ViewPager.previousPage(): Boolean = if (isFirstPage()) false else {
    currentItem -= 1
    true
}

fun ViewPager.nextPage(): Boolean = if (isLastPage()) false else {
    currentItem += 1
    true
}
