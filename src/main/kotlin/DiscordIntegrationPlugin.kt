import DiscordIntegrationModule.Webserveraddress
import com.lambda.client.plugin.api.Plugin
import com.lambda.client.util.text.MessageSendHelper
import com.lambda.client.util.threads.defaultScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

object DiscordIntegrationPlugin : Plugin() {
    override fun onLoad() {
        modules.add(DiscordIntegrationModule)
        commands.add(DiscordIntegrationCommands)
        commands.add(DiscordQuickMessageCommand)
        val httpclient: CloseableHttpClient = HttpClients.createDefault()
        defaultScope.launch(Dispatchers.Default) {
            while (true) {
                kotlin.runCatching {
                    delay(5000L)
                    // Creating a HttpGet object
                    val httpget = HttpGet(Webserveraddress)
                    // Executing the Get request
                    val newmessages =
                        EntityUtils.toString(httpclient.execute(httpget).entity)
                            .split("\r\n")
                            .map { it.trim() }
                            .toMutableList()
                    if (newmessages.size > messages.size) {
                        val sublist: MutableList<String> = if (messages.size == 0) {

                            if (newmessages.size - messages.size >= 5) {
                                newmessages.subList(newmessages.size - 5, newmessages.size)
                            } else {
                                newmessages.subList(1, newmessages.size)
                            }
                        } else {
                            newmessages.subList(messages.size, newmessages.size)
                        }

                        sublist.forEach { MessageSendHelper.sendRawChatMessage("&5DISCORD:  $it") }
                        messages = newmessages
                    }
                }
            }

        }
    }

    override fun onUnload() {}
}
