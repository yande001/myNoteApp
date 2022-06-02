package com.example.darren.mynoteapp.util

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(time: Long): String{
    val date = Date(time)
    val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm aaa", Locale.getDefault())
    return sdf.format(date)
}