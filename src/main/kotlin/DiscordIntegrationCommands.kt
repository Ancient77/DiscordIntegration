import DiscordIntegrationModule.DiscordWebhook
import DiscordIntegrationModule.Webserveraddress
import com.lambda.client.command.ClientCommand
import com.lambda.client.util.text.MessageSendHelper


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
