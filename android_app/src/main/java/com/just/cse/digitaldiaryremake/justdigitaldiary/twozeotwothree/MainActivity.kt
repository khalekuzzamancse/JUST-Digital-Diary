package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree.ui.theme.theme.AppTheme
import com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree.navigation.ScreenWithDrawer


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appEvent = AppEvent(
            onMessageRequest = {
                val smsIntent = Intent(Intent.ACTION_VIEW)
                smsIntent.setData(Uri.parse("smsto:"))
                smsIntent.setType("vnd.android-dir/mms-sms")
                smsIntent.putExtra("address", it)
                try {
                    startActivity(smsIntent)
                    finish()
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(
                        this,
                        "SMS failed, please try again later.", Toast.LENGTH_SHORT
                    ).show()
                }

            },
            onEmailRequest = {
                val emailIntent = Intent(Intent.ACTION_SEND)
                emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(it))
                emailIntent.setType("message/rfc822")
                startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"))

            },
            onCallRequest = {
                val u = Uri.parse("tel:$it")
                val i = Intent(Intent.ACTION_DIAL, u)
                try {
                    startActivity(i)
                } catch (s: SecurityException) {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                        .show()
                }

            },
            onWebsiteViewRequest = {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                browserIntent.setPackage("com.android.chrome")
                try {
                    startActivity(browserIntent)
                } catch (e: ActivityNotFoundException) {
                    // Chrome is not installed, fallback to default browser
                    browserIntent.setPackage(null)
                    startActivity(browserIntent)
                }


            }
        )
        setContent {
            AppTheme {
                ScreenWithDrawer(
                    appEvent = appEvent
                )


            }
        }
    }
}



