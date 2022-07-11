package com.lambda.commands

import com.lambda.client.command.ClientCommand
import com.lambda.client.util.text.MessageSendHelper
import com.lambda.modules.DiscordIntegrationModule.DiscordWebhook
import com.lambda.modules.DiscordIntegrationModule.Webserveraddress
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils


object DiscordIntegrationCommands :
        ClientCommand(
                name = "discord",
                description = "DiscordIntegrationCommands",
        ) {
    init {
        literal("setwebhook") {
            string("webhook") { nameArg ->
                execute {
                    DiscordWebhook = nameArg.value
                    MessageSendHelper.sendChatMessage("Discord Webhook set as: " + nameArg.value)
                }
            }
        }
        literal("setip") {
            string("webserveradress") { nameArg ->
                execute {
                    Webserveraddress = nameArg.value
                    MessageSendHelper.sendChatMessage("Webserveraddress set as: " + nameArg.value)
                }
            }
        }

    }
}
