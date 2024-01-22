package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent

class AppEventHandler(
    context: Context,
) {

    val appEvent = AppEvent(
        onMessageRequest = {
            val smsIntent = Intent(Intent.ACTION_VIEW)
            smsIntent.setData(Uri.parse("smsto:"))
            smsIntent.setType("vnd.android-dir/mms-sms")
            smsIntent.putExtra("address", it)
            try {
                context.startActivity(smsIntent)
             //   context.finish()
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    context,
                    "SMS failed, please try again later.", Toast.LENGTH_SHORT
                ).show()
            }

        },
        onEmailRequest = {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(it))
            emailIntent.setType("message/rfc822")
            context.startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"))

        },
        onCallRequest = {
            val u = Uri.parse("tel:$it")
            val i = Intent(Intent.ACTION_DIAL, u)
            try {
                context.startActivity(i)
            } catch (s: SecurityException) {
                Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG)
                    .show()
            }

        },
        onWebsiteViewRequest = {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            browserIntent.setPackage("com.android.chrome")
            try {
                context.startActivity(browserIntent)
            } catch (e: ActivityNotFoundException) {
                // Chrome is not installed, fallback to default browser
                browserIntent.setPackage(null)
                context.startActivity(browserIntent)
            }


        }
    )
}