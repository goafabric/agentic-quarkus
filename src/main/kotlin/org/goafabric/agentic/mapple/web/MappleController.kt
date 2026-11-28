package org.goafabric.agentic.mapple.web

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.goafabric.agentic.mapple.MappleTestCases

@Path("/api/mapple")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
class MappleController(
    private val runService: MappleRunService,
) {

    @GET
    @Path("/cases")
    fun cases(): List<CaseSummary> =
        MappleTestCases.IDS.map { caseId ->
            val content = MappleTestCases.load(caseId)
            CaseSummary(caseId, MappleTestCases.title(caseId, content))
        }

    @GET
    @Path("/cases/{caseId}")
    fun getCase(@PathParam("caseId") caseId: String): CaseDetail {
        if (caseId !in MappleTestCases.IDS) throw NotFoundException("Unknown Mapple test case: $caseId")
        val content = MappleTestCases.load(caseId)
        return CaseDetail(caseId, MappleTestCases.title(caseId, content), content)
    }

    @POST
    @Path("/cases/{caseId}/run")
    fun run(@PathParam("caseId") caseId: String): Response {
        if (caseId !in MappleTestCases.IDS) throw NotFoundException("Unknown Mapple test case: $caseId")
        return Response.accepted(runService.start(caseId)).build()
    }

    @GET
    @Path("/runs/{runId}")
    fun runStatus(@PathParam("runId") runId: String): RunStatus =
        runService.status(runId) ?: throw NotFoundException("Unknown Mapple run: $runId")
}

data class CaseSummary(val id: String, val title: String)

data class CaseDetail(val id: String, val title: String, val content: String)
