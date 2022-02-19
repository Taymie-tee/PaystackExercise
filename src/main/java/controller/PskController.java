package controller;

import model.request.InitiateTransactionRequest;
import model.response.FetchTransactionResponse;
import model.response.GetTransactionListResponse;
import model.response.InitiateTransactionResponse;
import model.response.VerifyTransactionResponse;
import service.PskService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path(value = "/v1")
public class PskController {

    @Inject
    private PskService pskService;

    @POST
    @Path(value = "/transaction/initialize")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public InitiateTransactionResponse initializeTransaction(
            InitiateTransactionRequest initiateTransactionRequest) throws Exception {
        System.out.println("Payment Request: {} " + initiateTransactionRequest);
        InitiateTransactionResponse response = pskService.initiateTransactionService(initiateTransactionRequest);
        System.out.println("Payment Response: {} " + response);
        return response;
    }

    @GET
    @Path(value = "/transaction/verify/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public VerifyTransactionResponse getTransactionStatus(@PathParam("reference") String reference) throws Exception {
        VerifyTransactionResponse response = pskService.getTransactionStatusService(reference);
        System.out.println("Transaction Status Response: {} " + response);
        return response;
    }

    @GET
    @Path(value = "/transaction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GetTransactionListResponse getTransactionList(@QueryParam("perPage") int perPage,
                                                         @QueryParam("page") int page) throws Exception {
        GetTransactionListResponse response = pskService.getTransactionListService(perPage, page);
        System.out.println("List of transaction Response: {} " + response);
        return response;
    }

    @GET
    @Path(value = "/transaction/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FetchTransactionResponse fetchTransaction(@PathParam("id") int id) throws Exception {
        FetchTransactionResponse response = pskService.fetchTransactionResponseService(id);
        System.out.println("Fetch transaction Response: {} " + response);
        return response;
    }
}
