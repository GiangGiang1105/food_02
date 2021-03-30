package com.example.gfood.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gfood.R
import com.example.gfood.screen.base.PageFragment
import com.example.gfood.utils.addFragment
import kotlinx.android.synthetic.main.activity_main.containerLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(PageFragment.newInstance(), containerLayout.id)
    }
}
