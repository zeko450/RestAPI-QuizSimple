package com.example.service;

import com.example.dao.ISystemDAO;
import com.example.dao.SystemDAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/system")
public class SystemResource {
    ISystemDAO dao = null;

    public SystemResource() {
        dao = new SystemDAO();
    }


    @GET
    @Path("questions/{id}/options")
    @Produces(MediaType.APPLICATION_JSON)
    public Response optionsForQuestion(@PathParam("id")int questionId)
    {
        Response.ResponseBuilder rb = Response.ok(dao.optionsForQuestion(questionId));
        return rb.build();

    }
    @GET
    @Path("quiz/{id}/questions")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getQuestionsForQuiz(@PathParam("id")int quizId)
    {
        Response.ResponseBuilder rb = Response.ok(dao.getQuestionsForQuiz(quizId));
        return rb.build();

    }

    @GET
    @Path("questions/{id}/rightanswer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rightOptionsForQuestion(@PathParam("id")int questionId)
    {
        Response.ResponseBuilder rb = Response.ok(dao.rightOptionsForQuestion(questionId));
        return rb.build();

    }

    @GET
    @Path("notusedquizzes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotUsedQuizzes(){
        Response.ResponseBuilder rb = Response.ok(dao.getNotUsedQuizzes());
        return rb.build();
    }

    @GET
    @Path("usedquizes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsedQuizzes(){
        Response.ResponseBuilder rb = Response.ok(dao.getUsedQuizzes());
        return rb.build();
    }

    @GET
    @Path("quiz/{quizId}/question/{questionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuizQuestion(@PathParam("quizId")int quizId, @PathParam("questionId")int questionId)
    {
        Response.ResponseBuilder rb = Response.ok(dao.getQuizQuestion(quizId, questionId));
        return rb.build();
    }

    @POST
    @Path("quiz/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewQuiz(@PathParam("title")String title)
    {
        Response.ResponseBuilder rb = Response.ok(dao.addNewQuiz(title));
        return rb.build();
    }

    @POST
    @Path("quiz/{id}/questions/{N}/{difficulty}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRandomQuestionsForQuiz(@PathParam("id")int quizId,@PathParam("N")int N, @PathParam("difficulty") String difficulty)
    {
        Response.ResponseBuilder rb = Response.ok(dao.addRandomQuestionsForQuiz(quizId, N, difficulty));
        return rb.build();
    }

    @PUT
    @Path("quiz/{quizId}/question/{questionId}/answer/{selectedOptionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateQuizQuestion(@PathParam("quizId")int quizId, @PathParam("questionId")int questionId, @PathParam("selectedOptionId") int selectedOptionId)
    {
        Response.ResponseBuilder rb = Response.ok(dao.updateQuizQuestion(quizId, questionId, selectedOptionId));
        return rb.build();
    }
}