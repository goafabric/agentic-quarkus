package org.goafabric.agentic.mapple.web

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.goafabric.agentic.mapple.logic.RunStatus

@Path("/api/mapple")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
class MappleController(
    private val mappleLogic: MappleLogic,
) {

    @GET
    @Path("/cases")
    fun cases(): List<CaseSummary> = mappleLogic.cases()

    @GET
    @Path("/cases/{caseId}")
    fun getCase(@PathParam("caseId") caseId: String): CaseDetail = mappleLogic.getCase(caseId)

    @POST
    @Path("/cases/{caseId}/run")
    fun run(@PathParam("caseId") caseId: String): Response =
        Response.accepted(mappleLogic.run(caseId)).build()

    @GET
    @Path("/runs/{runId}")
    fun runStatus(@PathParam("runId") runId: String): RunStatus = mappleLogic.runStatus(runId)
}

data class CaseSummary(val id: String, val title: String)

data class CaseDetail(val id: String, val title: String, val content: String)
