package com.xenya.homeworkagain

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gv_test.pointsArray = arrayOf(
            4,
            3,
            2,
            1,
            5,
            6,
            5,
            4,
            3,
            2,
            1,
            5,
            6,
            5,
            4,
            3,
            2,
            1,
            5,
            6,
            5,
            4,
            3,
            2,
            1,
            5,
            6,
            5,
            4,
            3,
            2,
            1,
            5,
            6,
            5,
            1
        )
    }

    override fun onResume() {
        super.onResume()
        Handler() {
            gv_test.pointsArray = arrayOf(4, 3, 6)
            return@Handler true
        }.sendEmptyMessageDelayed(0, 5000)
    }
}
