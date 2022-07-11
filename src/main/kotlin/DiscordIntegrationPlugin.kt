package com.lambda

import com.lambda.client.plugin.api.Plugin
import com.lambda.commands.DiscordIntegrationCommands
import com.lambda.commands.DiscordQuickMessageCommand
import com.lambda.huds.DiscordIntegrationHud
import com.lambda.managers.DiscordIntegrationManager
import com.lambda.modules.DiscordIntegrationModule

object DiscordIntegrationPlugin : Plugin() {
    override fun onLoad() {
        hudElements.add(DiscordIntegrationHud)
        modules.add(DiscordIntegrationModule)
        commands.add(DiscordIntegrationCommands)
        managers.add(DiscordIntegrationManager)
        commands.add(DiscordQuickMessageCommand)
    }

    override fun onUnload() {}
}
