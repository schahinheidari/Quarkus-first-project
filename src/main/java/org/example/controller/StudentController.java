package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.enums.AcademicLevel;
import org.example.enums.Branch;
import org.example.model.Student;
import org.example.service.StudentService;

import java.util.List;
import java.util.Optional;

@Path("student/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentController {

    @Inject
    StudentService studentService;


    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Student student) {
        try {
            studentService.save(student);
            return Response.status(Response.Status.CREATED).entity(student).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error Creating Student: " + e.getMessage()).build();
        }
    }

    @PUT
    public Response update(Student student) {
        try {
            Student updateStudent = studentService.update(student);
            return Response.status(Response.Status.OK).entity(updateStudent).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error Update Student: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{stdNumber}")
    public Response delete(
            @PathParam("stdNumber") long stdNumber) {
        Optional<Student> student = studentService.findStudentByStdNumber(stdNumber);
        if (student.isPresent()) {
            studentService.delete(stdNumber);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Student Not Found").build();
        }
    }
    @GET
    @Path("/{id}")
    public Response findById(
            @PathParam("id") long id) {
        Optional<Student> student = studentService.findStudentById(id);
        return student.map(value -> Response.status(Response.Status.OK)
                .entity(value)
                        .build())
                        .orElse(Response.status(Response.Status.NOT_FOUND)
                                .entity("Student Not Found")
                                .build());
    }

    @GET
    public List<Student> getAll(){
        return studentService.findAllStudents();
    }

    @GET
    @Path("/stdNumber/{stdNumber}")
    public Response findStudentByStdNumber(
            @PathParam("stdNumber") long stdNumber) {
        Optional<Student> student = studentService.findStudentByStdNumber(stdNumber);
        return student.map(value -> Response.ok(value).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                        .entity("Student Not Found")
                        .build());
    }

    @GET
    @Path("/academicLevel/{academicLevel}")
    public List<Student> getStudentsByAcademicLevel(
            @PathParam("academicLevel") AcademicLevel academicLevel) {
        return studentService.findStudentsByAcademicLevel(academicLevel);
    }

    @GET
    @Path("/branch/{branch}")
    public List<Student> getStudentsByBranch(
            @PathParam("branch") Branch branch) {
        return studentService.findStudentsByBranch(branch);
    }
}
