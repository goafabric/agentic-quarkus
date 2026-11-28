package org.goafabric.agentic.mapple.web

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.NotFoundException
import org.goafabric.agentic.mapple.logic.MappleRunService
import org.goafabric.agentic.mapple.logic.RunStart
import org.goafabric.agentic.mapple.logic.RunStatus

@ApplicationScoped
class MappleLogic(
    private val runService: MappleRunService,
) {

    fun cases(): List<CaseSummary> =
        IDS.map { caseId ->
            CaseSummary(caseId, title(caseId, loadCase(caseId)))
        }

    fun getCase(caseId: String): CaseDetail {
        val content = loadCase(caseId)
        return CaseDetail(caseId, title(caseId, content), content)
    }

    fun run(caseId: String): RunStart =
        runService.start(caseId, loadCase(caseId))

    fun runStatus(runId: String): RunStatus =
        runService.status(runId) ?: throw NotFoundException("Unknown Mapple run: $runId")

    private fun loadCase(caseId: String): String {
        if (caseId !in IDS) throw NotFoundException("Unknown Mapple test case: $caseId")

        val resource = "mapple-returns/testdata/cases/$caseId.md"
        return Thread.currentThread().contextClassLoader.getResourceAsStream(resource)
            ?.bufferedReader()
            ?.use { it.readText() }
            ?: throw IllegalArgumentException("Mapple test case '$caseId' not found at classpath:$resource")
    }

    private fun title(caseId: String, content: String): String =
        content.lineSequence()
            .firstOrNull { it.startsWith("# ") }
            ?.removePrefix("# ")
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
            ?: caseId

    companion object {
        private val IDS = listOf(
            "case-01-homer",
            "case-02-bart",
            "case-03-marge",
            "case-04-lisa",
            "case-05-burns",
        )
    }
}
