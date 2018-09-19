package com.github.songjiubin.pageindexer.demo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.github.songjiubin.pageindexer.widget.PageNumberIndexer
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ViewPagerActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        val simpleAdapter = SimpleAdapter(supportFragmentManager)
        simpleAdapter.submitList(listOf("a", "b", "c", "d", "e", "f", "g"))
        viewPager.adapter = simpleAdapter
        pageNumberIndexer.mNumPages = simpleAdapter.count
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                pageNumberIndexer.setActiveMarker(position)
            }
        })
        pageNumberIndexer.addOnPageIndexChangeListener(object : PageNumberIndexer.OnPageIndexChangeListener {
            override fun onPageIndexSelected(index: Int) {
                viewPager.setCurrentItem(index, false)
            }
        })
    }
}
