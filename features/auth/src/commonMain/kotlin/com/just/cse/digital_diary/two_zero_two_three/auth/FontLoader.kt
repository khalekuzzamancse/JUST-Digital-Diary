package com.just.cse.digital_diary.two_zero_two_three.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

//put the actual file in same package as directory name
//in the target folder such as androidMain and desktopMain
/*
//to use expect and actual keywords
        kotlin {
            compilerOptions {
                // Common compiler options applied to all Kotlin source sets
                freeCompilerArgs.add("-Xmulti-platform")
            }
        }
 */


@Composable
expect fun authModuleFont(
    name: String,
    res:String,
    weight:FontWeight,
    style: FontStyle

):Font