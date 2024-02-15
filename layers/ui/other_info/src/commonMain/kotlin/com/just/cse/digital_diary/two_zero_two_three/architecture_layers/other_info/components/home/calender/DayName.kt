package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.calender

/**
 * * For type safety it is used,instead of hardcoded string,string can causes more bug
 * * because string are able to maintain single source of truth
 */
enum class DayName (order:Int){
    Saturday(0),
    Sunday(1),
    Monday(2),
    Tuesday(3),
    Wednesday(4),
    Thursday(5),
    Friday(6);
}

