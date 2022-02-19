package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dao.TransactionsDao;
import model.entities.Transactions;
import model.request.InitiateTransactionRequest;
import model.response.FetchTransactionResponse;
import model.response.GetTransactionListResponse;
import model.response.InitiateTransactionResponse;
import model.response.VerifyTransactionResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;

@Stateless
public class PskService {

    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost post;
    HttpGet get;
    HttpPut put;
    HttpPatch patch;
    HttpDelete delete;
    Gson gson = new Gson();
    CloseableHttpResponse response;
    private ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = Logger.getLogger("PskService.class");
    private final static String FLW_BASE_URL = "https://api.paystack.co";
    private final static String PUBLIC_KEY = "pk_test_08fa24875fc9203214f33f9c8c56435beaf4762b";
    private final static String SECRET_KEY = "sk_test_bc269d290396099eedb92226b2646ace9a017d98";
//    private String token = "flw-t1nf-f9b3bf384cd30d6fca42b6df9d27bd2f-m03k";

    @Inject
    private TransactionsDao transactionsDao;

    public InitiateTransactionResponse initiateTransactionService(
            InitiateTransactionRequest initiateTransactionRequest) throws Exception {
        logger.info("Transaction initialization in service class " +
                objectMapper.writeValueAsString(initiateTransactionRequest));
        Transactions savedTransaction = transactionsDao
                .findTransactionByTransactionReference(initiateTransactionRequest.getTransaction_reference());
        if(savedTransaction == null) {
            saveTransaction(initiateTransactionRequest);
            String url = FLW_BASE_URL + "/transaction/initialize";
            logger.info("url " + url);
            logger.info("request {} " + initiateTransactionRequest);
            post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", "Bearer " + SECRET_KEY);
            String jsonInString = gson.toJson(initiateTransactionRequest);
            StringEntity stringEntity = new StringEntity(jsonInString);
            post.setEntity(stringEntity);
            response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("status code " + statusCode);
            String message = EntityUtils.toString(response.getEntity());
            logger.info("Raw Payment response " + message);
            InitiateTransactionResponse cardPaymentResponse = objectMapper
                    .readValue(message, InitiateTransactionResponse.class);
            updateTransaction(cardPaymentResponse, initiateTransactionRequest.getTransaction_reference());
            logger.info("Payment response " + gson.toJson(cardPaymentResponse));
            return cardPaymentResponse;
        } else {
            throw new Exception("Transaction reference exists");
        }
    }


    private void saveTransaction(InitiateTransactionRequest initiateTransactionRequest) throws Exception {
        Transactions transactionToSave = new Transactions();
        transactionToSave.setAmount(initiateTransactionRequest.getAmount());
        transactionToSave.setEmail(initiateTransactionRequest.getEmail());
        transactionToSave.setCurrency(initiateTransactionRequest.getCurrency());
        transactionToSave.setTransactionReference(initiateTransactionRequest.getTransaction_reference());
        transactionToSave.setCallbackUrl(initiateTransactionRequest.getCallback_url());
        transactionToSave.setPlan(initiateTransactionRequest.getPlan());
        transactionToSave.setInvoiceLimit(initiateTransactionRequest.getInvoice_limit());


        Date createdAt = new Date();
        Timestamp timeCreated = new Timestamp(createdAt.getTime());
        transactionToSave.setCreatedAt(timeCreated);

        Date updatedAt = new Date();
        Timestamp timeUpdated = new Timestamp(updatedAt.getTime());
        transactionToSave.setUpdatedAt(timeUpdated);

        transactionsDao.saveTransactions(transactionToSave);
    }

    private void updateTransaction(
            InitiateTransactionResponse initiateTransactionResponse, String transactionReference
    ) throws Exception {
        Transactions savedTransaction = transactionsDao
                .findTransactionByTransactionReference(transactionReference);
        savedTransaction.setStatus(true);
        transactionsDao.updateTransactions(savedTransaction);
    }

    public VerifyTransactionResponse getTransactionStatusService(
            String reference) throws Exception {
        String url = FLW_BASE_URL + "/transaction/verify/" + reference;
        System.out.println("url " + url);
        get = new HttpGet(url);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("Authorization", "Bearer " + SECRET_KEY);
        response = client.execute(get);
        String message = EntityUtils.toString(response.getEntity());
        logger.info("Transaction status response  {} " + message);
        VerifyTransactionResponse verifyTransactionResponse = objectMapper.readValue(message, VerifyTransactionResponse.class);
        logger.info("Transaction status " + gson.toJson(verifyTransactionResponse));
        return verifyTransactionResponse;

    }

    public GetTransactionListResponse getTransactionListService(
            int perPage, int page) throws Exception {
        String url = FLW_BASE_URL + "/transaction?" + "perPage=" + perPage + "&page=" + page;
        System.out.println("url " + url);
        get = new HttpGet(url);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("Authorization", "Bearer " + SECRET_KEY);
        response = client.execute(get);
        String message = EntityUtils.toString(response.getEntity());
        logger.info("Raw Transaction List response  {} " + message);
        GetTransactionListResponse getTransactionListResponse = objectMapper.readValue(message, GetTransactionListResponse.class);
        logger.info("Transaction List Response " + gson.toJson(getTransactionListResponse));
        return getTransactionListResponse;

    }
    public FetchTransactionResponse fetchTransactionResponseService(int id) throws Exception {
        String url = FLW_BASE_URL + "/transaction/" + id;
        System.out.println("url " + url);
        get = new HttpGet(url);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("Authorization", "Bearer " + SECRET_KEY);
        response = client.execute(get);
        String message = EntityUtils.toString(response.getEntity());
        logger.info("Raw Transaction response  {} " + message);
        FetchTransactionResponse fetchTransactionResponse = objectMapper.readValue(message, FetchTransactionResponse.class);
        logger.info("Fetch Transaction Response " + gson.toJson(fetchTransactionResponse));
        return fetchTransactionResponse;

    }
}