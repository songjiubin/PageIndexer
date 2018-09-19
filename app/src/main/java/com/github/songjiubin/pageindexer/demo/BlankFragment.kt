package com.github.songjiubin.pageindexer.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


private const val ARG_PAGE_NUM = "pageNum"
private const val ARG_CONTENT = "content"

class BlankFragment : Fragment() {
    private var pageNum: String? = null
    private var content: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pageNum = it.getString(ARG_PAGE_NUM)
            content = it.getString(ARG_CONTENT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        val num = view.findViewById<TextView>(R.id.num)
        num.text = "Page$pageNum hello $content"
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(pageNum: String, content: String) =
                BlankFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PAGE_NUM, pageNum)
                        putString(ARG_CONTENT, content)
                    }
                }
    }
}
