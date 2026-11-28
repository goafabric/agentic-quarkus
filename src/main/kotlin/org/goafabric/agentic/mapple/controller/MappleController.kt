package org.goafabric.agentic.mapple.web

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.goafabric.agentic.mapple.TestCaseLoader

@Path("/api/mapple")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
class MappleController(
    private val runService: MappleRunService,
) {

    @GET
    @Path("/cases")
    fun cases(): List<CaseSummary> =
        TestCaseLoader.IDS.map { caseId ->
            val content = TestCaseLoader.load(caseId)
            CaseSummary(caseId, TestCaseLoader.title(caseId, content))
        }

    @GET
    @Path("/cases/{caseId}")
    fun getCase(@PathParam("caseId") caseId: String): CaseDetail {
        if (caseId !in TestCaseLoader.IDS) throw NotFoundException("Unknown Mapple test case: $caseId")
        val content = TestCaseLoader.load(caseId)
        return CaseDetail(caseId, TestCaseLoader.title(caseId, content), content)
    }

    @POST
    @Path("/cases/{caseId}/run")
    fun run(@PathParam("caseId") caseId: String): Response {
        return Response.accepted(runService.start(caseId)).build()
    }

    @GET
    @Path("/runs/{runId}")
    fun runStatus(@PathParam("runId") runId: String): RunStatus =
        runService.status(runId) ?: throw NotFoundException("Unknown Mapple run: $runId")
}

data class CaseSummary(val id: String, val title: String)

data class CaseDetail(val id: String, val title: String, val content: String)
