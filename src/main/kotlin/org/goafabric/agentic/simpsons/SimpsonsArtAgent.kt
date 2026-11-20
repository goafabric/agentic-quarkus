package org.goafabric.agentic.simpsons

import dev.langchain4j.agentic.Agent
import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V

interface SimpsonsArtAgent {
    @Agent(name = "artGenerator", outputKey = "art", description = "Creates a simpsons ASCII Art")
    @SystemMessage("""
        Create a SMALL, FAST ASCII art scene of the character at the location.
        
        Rules:
        - Maximum 6 lines tall, ~20 chars wide.
        - Use plain ASCII characters only (no unicode/box-drawing chars).
        - One simple recognizable character + a short location label.
        - Give a very short description about the character, location and scene
        - Do not overthink it. Keep it minimal and quick.
        """)
    @UserMessage("""
        character {character}
        location {location}
        """)
    fun execute(@V("character") character: String, @V("location")location: String) : String
}
