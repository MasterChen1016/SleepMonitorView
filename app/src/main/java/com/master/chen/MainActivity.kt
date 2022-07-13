package com.master.chen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.master.chen.databinding.ActivityMainBinding
import com.master.sleepmonitor.SleepEntry
import com.master.sleepmonitor.SleepMonitView
import com.master.sleepmonitor.SleepType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        dataBinding.sleepView.mData = getSleepData()
    }

    private fun getSleepData(): MutableList<SleepEntry> {
        val s = mutableListOf<SleepEntry>()
        s.add(SleepEntry(SleepType.SOBER, 18 * 60))//清醒18分钟
        s.add(SleepEntry(SleepType.DEEP, 259 * 60))//深睡259分钟
        s.add(SleepEntry(SleepType.LIGHT, 5 * 60))//浅睡醒5分钟
        s.add(SleepEntry(SleepType.DEEP, 68 * 60))//深睡68分钟
        s.add(SleepEntry(SleepType.LIGHT, 10 * 60))//浅睡10分钟
        s.add(SleepEntry(SleepType.SOBER, 18 * 60))//清醒18分钟
        return s
    }
}