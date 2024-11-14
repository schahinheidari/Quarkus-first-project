package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.enums.AcademicLevel;
import org.example.enums.AcademicRank;
import org.example.model.Professor;
import org.example.model.Student;
import org.example.service.ProfessorService;

import java.util.List;
import java.util.Optional;

@Path("professor/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfessorController {

    @Inject
    ProfessorService professorService;

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Professor professor) {
        try {
            professorService.save(professor);
            return Response.status(Response.Status.CREATED).entity(professor).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error Creating Professor: " + e.getMessage()).build();
        }
    }

    @PUT
    public Response update(Professor professor) {
        try {
            Professor updateProfessor = professorService.update(professor);
            return Response.status(Response.Status.OK).entity(updateProfessor).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error Update Professor: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(
            @PathParam("id") long id) {
        Optional<Professor> professor = professorService.findProfessorById(id);
        if (professor.isPresent()) {
            professorService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Professor Not Found").build();
        }
    }
    @GET
    @Path("/{id}")
    public Response findById(
            @PathParam("id") long id) {
        Optional<Professor> professor = professorService.findProfessorById(id);
        return professor.map(value -> Response.status(Response.Status.OK)
                        .entity(value)
                        .build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("Professor Not Found")
                        .build());
    }

    @GET
    public List<Professor> getAll(){
        return professorService.findAllProfessors();
    }

    @GET
    @Path("/academicRank/{academicRank}")
    public List<Professor> getStudentsByAcademicLevel(
            @PathParam("academicRank") AcademicRank academicRank) {
        return professorService.findStudentsByAcademicRank(academicRank);
    }

}
