package com.github.songjiubin.pageindexer.demo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SimpleAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val list: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return BlankFragment.newInstance(position.toString(), list[position])
    }

    override fun getCount(): Int {
        return list.size
    }

    fun submitList(list: List<String>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}