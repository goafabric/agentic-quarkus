package org.goafabric.agentic.controller

import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.util.*

@ApplicationScoped
class ChatCLI(private val chatBot: ChatBot, @ConfigProperty(name = "agentic.mode") private val mode: String) {
    interface ChatBot {
        @SystemMessage("You are a helpful chatbot that can helo with everything")
        fun chat(@UserMessage message: String): String
    }

    fun onStart(@Observes ev: StartupEvent) {
        if (mode != "chatbot") return
        val scanner = Scanner(System.`in`)
        while (true) {
            print("[User]: ")
            println("[Assistant]: " + chatBot.chat(scanner.nextLine()))
        }
    }
}