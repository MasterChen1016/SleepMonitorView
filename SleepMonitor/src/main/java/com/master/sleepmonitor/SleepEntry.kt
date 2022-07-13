package com.master.sleepmonitor

data class SleepEntry(
    var type: SleepType,//睡眠状态
    var sleepDuration: Int //睡眠时长，单位：秒
)