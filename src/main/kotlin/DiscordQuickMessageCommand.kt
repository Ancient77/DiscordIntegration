package com.lambda.commands

import com.lambda.client.command.ClientCommand
import com.lambda.modules.DiscordIntegrationModule.sendToDiscord

object DiscordQuickMessageCommand :
        ClientCommand(
                name = "d",
                description = "quicker way to send messages to discord",
        ) {
    init {
        string("message") { arg1 ->
            string(" ") { arg2 ->
                string("  ") { arg3 ->
                    string("   ") { arg4 ->
                        string("    ") { arg5 ->
                            string("     ") { arg6 ->
                                string("      ") { arg7 ->
                                    string("       ") { arg8 ->
                                        executeSafe("Send message to Discord") {
                                            sendToDiscord(
                                                    arg1.value +
                                                            " " +
                                                            arg2.value +
                                                            " " +
                                                            arg3.value +
                                                            " " +
                                                            arg4.value +
                                                            " " +
                                                            arg5.value +
                                                            " " +
                                                            arg6.value +
                                                            " " +
                                                            arg7.value +
                                                            " " +
                                                            arg8.value
                                            )
                                        }
                                    }
                                    executeSafe("Send message to Discord") {
                                        sendToDiscord(
                                                arg1.value +
                                                        " " +
                                                        arg2.value +
                                                        " " +
                                                        arg3.value +
                                                        " " +
                                                        arg4.value +
                                                        " " +
                                                        arg5.value +
                                                        " " +
                                                        arg6.value +
                                                        " " +
                                                        arg7.value
                                        )
                                    }
                                }
                                executeSafe("Send message to Discord") {
                                    sendToDiscord(
                                            arg1.value +
                                                    " " +
                                                    arg2.value +
                                                    " " +
                                                    arg3.value +
                                                    " " +
                                                    arg4.value +
                                                    " " +
                                                    arg5.value +
                                                    " " +
                                                    arg6.value
                                    )
                                }
                            }
                            executeSafe("Send message to Discord") {
                                sendToDiscord(
                                        arg1.value +
                                                " " +
                                                arg2.value +
                                                " " +
                                                arg3.value +
                                                " " +
                                                arg4.value +
                                                " " +
                                                arg5.value
                                )
                            }
                        }
                        executeSafe("Send message to Discord") {
                            sendToDiscord(
                                    arg1.value +
                                            " " +
                                            arg2.value +
                                            " " +
                                            arg3.value +
                                            " " +
                                            arg4.value
                            )
                        }
                    }
                    executeSafe("Send message to Discord") {
                        sendToDiscord(arg1.value + " " + arg2.value + " " + arg3.value)
                    }
                }
                executeSafe("Send message to Discord") { sendToDiscord(arg1.value + arg2.value) }
            }
            executeSafe("Send message to Discord") { sendToDiscord(arg1.value) }
        }
    }
}
