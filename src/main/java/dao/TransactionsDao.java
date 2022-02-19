package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.entities.Transactions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class TransactionsDao {

    private static SessionFactory sf;
    private Session session;
    private Query query;
    private final Logger logger = Logger.getLogger("TransactionDao.class");
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Transactions findTransactionByTransactionReference(String reference) throws Exception {
        Transactions savedTransaction = new Transactions();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            query =
                    session.createQuery(
                            "from Transactions trans where trans.transactionReference=:reference");
            query.setParameter("reference", reference);
            savedTransaction = (Transactions) query.uniqueResult();
            session.getTransaction().commit();
            logger.info("Payment fetched " + objectMapper.writeValueAsString(savedTransaction));
        } catch (Exception e) {
            throw new Exception("Unable to fetch transaction " + e.getMessage());
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return savedTransaction;
    }

    public Transactions saveTransactions(Transactions transactionToSave) throws Exception {
        try {
            logger.info("Transaction TO save  ");
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            query =
                    session.createQuery(
                            "from Transactions trans where trans.transactionReference=:reference");
            query.setParameter("reference", transactionToSave.getTransactionReference());
            Transactions savedTransaction = (Transactions) query.uniqueResult();
            if (savedTransaction == null) {
                session.save(transactionToSave);
                logger.info("Transaction saved {} " + transactionToSave);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Unable to save transaction " + e.getMessage());
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return transactionToSave;
    }


    public Transactions updateTransactions(Transactions transactionToSave) throws Exception {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            query =
                    session.createQuery(
                            "from Transactions trans where trans.transactionReference=:reference");
            query.setParameter("reference", transactionToSave.getTransactionReference());
            Transactions savedTransaction = (Transactions) query.uniqueResult();
            if (savedTransaction != null) {
                savedTransaction.setStatus(transactionToSave.isStatus());
//                savedTransaction.setStatus(transactionToSave.getStatus());
//                if(transactionToSave.getIdentifier() != null){
//                    savedTransaction.setIdentifier(transactionToSave.getIdentifier());
//                }
//                if(transactionToSave.getQuoteId() != null){
//                    savedTransaction.setQuoteId(transactionToSave.getQuoteId());
//                }
                logger.info("Transaction updated {} " + transactionToSave);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Unable to update transaction " + e.getMessage());
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return transactionToSave;
    }


    public List<Transactions> fetchAllTransactions() throws Exception {
        List<Transactions> savedTransaction = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            query = session.createQuery("from Transactions trans");
            savedTransaction = (List<Transactions>) query.list();
            session.getTransaction().commit();
            logger.info("Accounts fetched " +
                    objectMapper.writeValueAsString(savedTransaction));
        } catch (Exception e) {
            throw new Exception("Unable to fetch transaction " + e.getMessage());
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return savedTransaction;
    }


    public Transactions updateErrorTransactions(Transactions transactionToSave) throws Exception {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            query =
                    session.createQuery(
                            "from Transactions trans where trans.transactionReference=:reference");
            query.setParameter("reference", transactionToSave.getTransactionReference());
            Transactions savedTransaction = (Transactions) query.uniqueResult();
            if (savedTransaction != null) {
                savedTransaction.setStatus(transactionToSave.isStatus());
                logger.info("Transaction updated {} " + transactionToSave);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Unable to update error transaction " + e.getMessage());
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return transactionToSave;
    }

    public Transactions updateCompletedTransactions(Transactions transactionToSave) throws Exception {
        Transactions savedTransaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            query =
                    session.createQuery(
                            "from Transactions trans where trans.transactionReference=:reference");
            query.setParameter("reference", transactionToSave.getTransactionReference());
            savedTransaction = (Transactions) query.uniqueResult();
            if (savedTransaction != null) {
                savedTransaction.setStatus(transactionToSave.isStatus());
                logger.info("Transaction updated {} " + transactionToSave);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Unable to update transaction " + e.getMessage());
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return savedTransaction;
    }

}
