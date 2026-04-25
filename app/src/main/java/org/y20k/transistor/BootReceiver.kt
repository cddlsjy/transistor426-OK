/*
 * BootReceiver.kt
 * Handles device boot to auto-start playback if enabled
 *
 * This file is part of
 * TRANSISTOR - Radio App for Android
 *
 * Copyright (c) 2015-25 - Y20K.org
 * Licensed under the MIT-License
 * http://opensource.org/licenses/MIT
 */


package org.y20k.transistor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.y20k.transistor.helpers.PreferencesHelper


/*
 * BootReceiver class
 */
class BootReceiver: BroadcastReceiver() {

    /* Overrides onReceive from BroadcastReceiver */
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED || intent.action == "android.intent.action.QUICKBOOT_POWERON") {
            // check if auto-start on boot is enabled
            if (PreferencesHelper.loadAutoStartOnBoot()) {
                // start the main activity with the start player intent
                val startIntent = Intent(context, MainActivity::class.java).apply {
                    action = Keys.ACTION_START
                    putExtra(Keys.EXTRA_START_LAST_PLAYED_STATION, true)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(startIntent)
            }
        }
    }
}
