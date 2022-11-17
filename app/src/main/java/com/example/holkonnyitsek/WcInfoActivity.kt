package com.example.holkonnyitsek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.holkonnyitsek.adapter.CommentAdapter
import kotlinx.android.synthetic.main.activity_wc_info.*

class WcInfoActivity : AppCompatActivity() {

    lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wc_info)

        commentAdapter = CommentAdapter(this)
        recyclerComment.adapter = commentAdapter
    }
}