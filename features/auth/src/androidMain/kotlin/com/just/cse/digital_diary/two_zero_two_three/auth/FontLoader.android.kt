package com.just.cse.digital_diary.two_zero_two_three.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

//put the actual file in same package as directory name
//in the target folder such as androidMain and desktopMain
//use it the the module gradle build kotlin block to enable expect-actual keyword
/*
*/
@Composable
actual fun authModuleFont(
    name: String,
    res:String,
    weight:FontWeight,
    style: FontStyle

):Font{
val context= LocalContext.current
    val id=context.resources.getIdentifier(res,"font",context.packageName)
    return  Font(id, weight,style)

}