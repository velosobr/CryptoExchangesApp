package com.velosobr.cryptoexchangesapp

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import org.koin.core.context.GlobalContext.get

object FlipperInitializer {
    fun init(context: Context) {
        SoLoader.init(context, false)
        val client = AndroidFlipperClient.getInstance(context)
        val networkPlugin = get().get<NetworkFlipperPlugin>()
        client.addPlugin(InspectorFlipperPlugin(context, DescriptorMapping.withDefaults()))
        client.addPlugin(networkPlugin)
        client.start()
    }
}