package com.just.cse.digital_diary.two_zero_two_three.auth

import androidx.compose.runtime.Composable

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font//the package are different
import androidx.compose.ui.text.font.Font////the package are different

//put the actual file in same package as directory name
//in the target folder such as androidMain and desktopMain
@Composable
actual fun authModuleFont(
    name: String,
    res:String,
    weight:FontWeight,
    style: FontStyle

): Font {
    return  Font("font/$res.ttf",weight,style)
//desktop and IOS have to use:androidx.compose.ui.text.platform.Font
}
/*
for IOS use:
return cache.getOrPut(res){
val byteArray=runBlocking{
resources("font/$res.ttf").readBytes()
Font(res,weight,style)

}
}
 */