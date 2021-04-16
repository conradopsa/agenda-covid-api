package io.unicarioca.agenda_covid_api.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.conversions.Bson;

import io.unicarioca.agenda_covid_api.models.Patient;
import io.unicarioca.agenda_covid_api.repositories.PatientRepository;

import static com.mongodb.client.model.Filters.*;
import static io.unicarioca.agenda_covid_api.helpers.ResponseException.*;

@Path("/patient")
public class PatientController {

    private PatientRepository patientRepository = new PatientRepository();

    @GET
    @Path("/{idPatient}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatient(@PathParam("idPatient") Integer id) {

        try {
            return Response.ok(patientRepository.getPatients(eq("idPatient", id)).get(0)).build();
        } catch (Exception ex) {
            return ResponseException500(ex);
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatients() {

        try {
            return Response.ok(patientRepository.getPatients(null)).build();
        } catch (Exception ex) {
            return ResponseException500(ex);
        }

    }

}
