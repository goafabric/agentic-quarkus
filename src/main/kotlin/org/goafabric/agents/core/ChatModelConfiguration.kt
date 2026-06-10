package org.goafabric.agents.core

import dev.langchain4j.model.anthropic.AnthropicChatModel
import dev.langchain4j.model.chat.ChatModel
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import java.time.Duration

@ApplicationScoped
class ChatModelConfiguration {
    /*
   @Produces
   fun chatModelOpenAi(): ChatModel {
       return OpenAiChatModel.builder().apiKey("")
           .baseUrl("http://localhost:11434/v1")
           .modelName("gpt-oss:20b")
           .timeout(Duration.ofSeconds(60)).temperature(0.0)
           .build()
   }

    */


    @Produces
    fun chatModelAnthropic(): ChatModel {
        return AnthropicChatModel.builder().apiKey(System.getenv("ANTHROPIC_FOUNDRY_API_KEY"))
            .baseUrl("https://%s.services.ai.azure.com/anthropic/v1".format(System.getenv("ANTHROPIC_FOUNDRY_RESOURCE")))
            .modelName(System.getenv("ANTHROPIC_DEFAULT_SONNET_MODEL"))
            .timeout(Duration.ofSeconds(60)).temperature(0.0)
            .build()
    }
}