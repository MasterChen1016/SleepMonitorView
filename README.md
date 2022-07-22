# SleepMonitorView
## 一个超简单的睡眠统计控件，适用于手环统计到的睡眠数据

自己在项目中遇到这样的需求，然后经过一番搜索找不到同款，自己就动手做了一个。里面的功能比较简单，只针对我自己项目里的需求做的简单功能。如果本人项目不忙（不懒）的话后面再对其进行扩展
### 预览效果
![](https://github.com/441907002/SleepMonitorView/blob/master/demo%E6%95%88%E6%9E%9C%E9%A2%84%E8%A7%88.png)
### 项目中实际运用
![](https://github.com/441907002/SleepMonitorView/blob/master/%E5%AE%9E%E9%99%85%E9%A1%B9%E7%9B%AE%E8%BF%90%E7%94%A8.jpg)
#### 属性介绍
```
<com.master.sleepmonitor.SleepMonitView    
        android:id="@+id/sleep_view"    
        android:layout_width="match_parent"    
        android:layout_height="300dp"    
        android:paddingLeft="10dp"    
        android:paddingRight="10dp"    
        app:layout_constraintBottom_toBottomOf="parent"    
        app:backgroundColor="@color/white"    
        app:deepColumnColor="#01C8E4"    
        app:layout_constraintTop_toTopOf="parent"    
        app:lightColumnColor="#4FF5FC"    
        app:soberColumnColor="#CCCDD0" />
```

| 属性 | 类型 | 说明 |
| --- | --- | --- |
| septalLineHigh | dimension | 间隔线高度 |
| septalLineColor | color | 间隔线颜色 |
| soberColumnColor | color | 清醒矩形色 |
| lightColumnColor | color | 浅睡矩形色 |
| deepColumnColor | color | 深睡矩形色 |
| backgroundColor | color | 控件整体的背景色，默认白色 |

#### 使用
```
添加远程仓库
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
```
添加依赖
implementation 'com.github.MasterChen1016:SleepMonitorView:1.0.0'
```
```
sleepView.mData = getSleepData()

//模拟数据
private fun getSleepData(): MutableList<SleepEntry> {
        val s = mutableListOf<SleepEntry>()   
        s.add(SleepEntry(SleepType.SOBER, 18 * 60))//清醒18分钟    
        s.add(SleepEntry(SleepType.DEEP, 259 * 60))//深睡259分钟   
        s.add(SleepEntry(SleepType.LIGHT, 5 * 60))//浅睡5分钟   
        s.add(SleepEntry(SleepType.DEEP, 68 * 60))//深睡68分钟   
        s.add(SleepEntry(SleepType.LIGHT, 10 * 60))//浅睡10分钟    
        s.add(SleepEntry(SleepType.SOBER, 18 * 60))//清醒18分钟    
        return s
}

```

#### 公司项目
![](https://github.com/441907002/SleepMonitorView/blob/master/app.png)
#### Demo
[Apk下载](https://github.com/MasterChen1016/SleepMonitorView/releases/tag/v1.0.0)
