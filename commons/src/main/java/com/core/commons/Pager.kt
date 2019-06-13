package com.core.commons

class Pager<T> constructor(
        var page: Int = 0,
        var pageSize: Int = 50
) {

    private var initPage: Int = page
    var pageData: MutableMap<Int, MutableList<T>> = mutableMapOf()

    fun addPage(page: Int, data: MutableList<T>): Pager<T> {
        pageData[page] = data
        return this
    }

    fun getPage(page: Int): MutableList<T> = pageData[page] ?: mutableListOf()

    fun getData(): MutableList<T> {
        val data = mutableListOf<T>()
        pageData.values.forEach { data.addAll(it) }
        return data
    }

    fun remove(item: T) {
        pageData.values.forEach { it.remove(item) }
    }

    fun next(): Pager<T> {
        page = page.inc()
        return this
    }

    fun restart(): Pager<T> {
        page = initPage
        pageData.clear()
        return this
    }
}
