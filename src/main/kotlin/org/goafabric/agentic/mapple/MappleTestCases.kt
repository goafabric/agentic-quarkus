package org.goafabric.agentic.mapple

object MappleTestCases {

    val IDS = listOf(
        "case-01-homer",
        "case-02-bart",
        "case-03-marge",
        "case-04-lisa",
        "case-05-burns",
    )

    fun load(caseId: String): String {
        require(caseId in IDS) { "Unknown Mapple test case: $caseId" }

        val resource = "mapple-returns/testdata/cases/$caseId.md"
        return Thread.currentThread().contextClassLoader.getResourceAsStream(resource)
            ?.bufferedReader()
            ?.use { it.readText() }
            ?: throw IllegalArgumentException("Mapple test case '$caseId' not found at classpath:$resource")
    }

    fun title(caseId: String, content: String): String =
        content.lineSequence()
            .firstOrNull { it.startsWith("# ") }
            ?.removePrefix("# ")
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
            ?: caseId
}
