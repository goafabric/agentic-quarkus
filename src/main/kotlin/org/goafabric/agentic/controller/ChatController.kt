package org.goafabric.agentic.controller

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.goafabric.agentic.agents.SuperVisorAgent
import org.goafabric.agentic.assistant.Assistant

data class ChatRequest(val message: String = "")
data class ChatResponse(val reply: String)

@Path("/chat")
@ApplicationScoped
class ChatController(
    //private val superAgent: SuperAgent,
    private val superAgent: SuperVisorAgent,
    private val assistant: Assistant,
    @param:ConfigProperty(name = "agentic.mode") private val mode: String
) {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun chat(request: ChatRequest): ChatResponse {
        val reply = when (mode) {
            "agentic"   -> superAgent.execute(request.message)
            "assistant" -> assistant.chat(request.message)
            else        -> error("Unknown agentic.mode: $mode")
        }
        return ChatResponse(reply)
    }
}