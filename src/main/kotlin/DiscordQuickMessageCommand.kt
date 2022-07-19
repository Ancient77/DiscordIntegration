import DiscordIntegrationModule.sendToDiscord
import com.lambda.client.command.ClientCommand

object DiscordQuickMessageCommand :
    ClientCommand(
        name = "d",
        description = "quicker way to send messages to discord",
    ) {
    init {
        greedy("message") { arg ->
            executeSafe("Send message to Discord") {
                sendToDiscord(
                    arg.value
                )
            }
        }
    }
}

