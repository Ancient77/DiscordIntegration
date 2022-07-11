package com.lambda.huds

import com.lambda.DiscordIntegrationPlugin
import com.lambda.client.event.SafeClientEvent
import com.lambda.client.plugin.api.PluginLabelHud

internal object DiscordIntegrationHud : PluginLabelHud(
    name = "DiscordIntegrationHud",
    category = Category.COMBAT,
    description = "Simple hud example",
    pluginMain = DiscordIntegrationPlugin
) {
    private val prefix by setting("Prefix", "Hello")
    private val suffix by setting("Suffix", "World")

    override fun SafeClientEvent.updateText() {
        displayText.add(prefix, primaryColor)
        displayText.add(suffix, secondaryColor)
    }
}