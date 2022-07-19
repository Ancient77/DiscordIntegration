import com.google.gson.JsonObject
import com.lambda.client.commons.utils.ConnectionUtils
import com.lambda.client.event.events.PacketEvent
import com.lambda.client.event.listener.listener
import com.lambda.client.module.Category
import com.lambda.client.plugin.api.PluginModule
import com.lambda.client.util.text.MessageSendHelper
import com.lambda.client.util.threads.defaultScope
import com.lambda.client.util.threads.safeListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.minecraft.client.Minecraft
import net.minecraft.network.play.client.CPacketChatMessage
import net.minecraftforge.fml.common.gameevent.TickEvent
import org.apache.commons.io.IOUtils

var messages = mutableListOf<String>()
var delNextMessage = false

object DiscordIntegrationModule :
    PluginModule(
        name = "DiscordIntegration",
        description = "Discord Integration in Minecraftchat",
        category = Category.CHAT,
        pluginMain = DiscordIntegrationPlugin
    ) {
    var DiscordWebhook by setting("DiscordWebhook", " ")
    var Webserveraddress by setting("Webserveraddress", " ")
    private var DiscordChat by setting("DiscordChat", false)


    fun sendToDiscord(message: String) {
        if (DiscordWebhook != " ") {
            defaultScope.launch(Dispatchers.IO) {
                ConnectionUtils.runConnection(
                    DiscordWebhook,
                    { connection ->
                        val bytes =
                            JsonObject().run {
                                addProperty(
                                    "username",
                                    Minecraft.getMinecraft().player.name
                                )
                                addProperty("content", message)
                                // addProperty("avatar_url", "avatar.value")
                                toString().toByteArray(Charsets.UTF_8)
                            }

                        connection.setRequestProperty(
                            "Content-Type",
                            "application/json; charset=UTF-8"
                        )
                        connection.setRequestProperty("Accept", "application/json")
                        connection.setRequestProperty("User-Agent", "")

                        connection.requestMethod = "POST"
                        connection.outputStream.use { it.write(bytes) }

                        val response =
                            connection.inputStream.use {
                                IOUtils.toString(it, Charsets.UTF_8)
                            }

                        if (response.isNotEmpty()) {
                            MessageSendHelper.sendErrorMessage(
                                "Unexpected response from DiscordNotifs http request: $response"
                            )
                        }
                    },
                    { MessageSendHelper.sendErrorMessage("Error while sending webhook") },
                )
            }
        }
    }

    init {
        listener<PacketEvent.Send> {
            val packet = it.packet
            if (DiscordChat && packet is CPacketChatMessage) {
                if (delNextMessage) {
                    delNextMessage = false
                    it.cancel()
                } else {
                    sendToDiscord(packet.message)
                    delNextMessage = true
                    it.cancel()
                }
            }
        }
        safeListener<TickEvent.ClientTickEvent> {
        }
    }
}

