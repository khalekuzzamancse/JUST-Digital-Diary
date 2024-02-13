package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent


class AppEventHandler(
    private val context: Context,
) {

    fun handleEvent(event: AppEvent) {
        when(event){
           is AppEvent.CallRequest->makeCall(event.number)
            is AppEvent.MessageRequest->sendSMS(event.number)
            is AppEvent.EmailRequest->sentEmail(event.email)
            is AppEvent.WebVisitRequest->visitWeb(event.url)
        }

    }

    private fun sendSMS(number: String) {
        val smsIntent = Intent(Intent.ACTION_VIEW)
        smsIntent.setData(Uri.parse("smsto:"))
        smsIntent.setType("vnd.android-dir/mms-sms")
        smsIntent.putExtra("address", number)
        try {
            context.startActivity(smsIntent)
            //   context.finish()
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                context,
                "SMS failed, please try again later.", Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun sentEmail(email: String) {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        emailIntent.setType("message/rfc822")
        context.startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"))
    }

    private fun makeCall(number: String) {
        val u = Uri.parse("tel:$number")
        val i = Intent(Intent.ACTION_DIAL, u)
        try {
            context.startActivity(i)
        } catch (s: SecurityException) {
            Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun visitWeb(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        browserIntent.setPackage("com.android.chrome")
        try {
            context.startActivity(browserIntent)
        } catch (e: ActivityNotFoundException) {
            // Chrome is not installed, fallback to default browser
            browserIntent.setPackage(null)
            context.startActivity(browserIntent)
        }
    }

}