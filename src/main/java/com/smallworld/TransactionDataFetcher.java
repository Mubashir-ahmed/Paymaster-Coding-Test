package com.smallworld;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TransactionDataFetcher 
{
    private Transactions[] transactions;

    public TransactionDataFetcher() throws IOException 
    {
        //String path =  System.getProperty("user.dir"); -- To verify the file path of Json File.
        ObjectMapper objectMapper = new ObjectMapper();
        transactions = objectMapper.readValue(new File("Transactions.json"), Transactions[].class);
    }

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
        double sum = 0.0;
        Set<Integer> uniqueTransactions = new HashSet<>();
        for (Transactions transaction : transactions)
        {
            if (!uniqueTransactions.contains(transaction.getMtn()) )
            {
                sum = sum + transaction.getAmount();
            }
            uniqueTransactions.add(transaction.getMtn());
        }
        return sum;
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        double sum = 0.0;
        Set<Integer> uniqueTransactions = new HashSet<>();
        for (Transactions transaction : transactions)
        {
            if (!uniqueTransactions.contains(transaction.getMtn()) && transaction.getSenderFullName().equals(senderFullName))
            {
                sum = sum + transaction.getAmount();
            }
            uniqueTransactions.add(transaction.getMtn());
        }
        return sum;
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        double maximum = 0.0;
        for (Transactions transaction : transactions)
        {
            maximum = Math.max(maximum , transaction.getAmount());
        }
        return maximum;
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        long count = 0;
        Set<String> nameOfClients = new HashSet<>();
        for (Transactions transaction : transactions)
        {
            nameOfClients.add(transaction.getBeneficiaryFullName());
            nameOfClients.add(transaction.getSenderFullName());
        }
        count = nameOfClients.size();
        return count;
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        for (Transactions transaction : transactions)
        {
            if ((transaction.getBeneficiaryFullName().equals(clientFullName) || transaction.getSenderFullName().equals(clientFullName)) && transaction.isIssueSolved() == false)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Transactions> getTransactionsByBeneficiaryName() {
        Map<String,Transactions> beneficiaryTransactionMap = new HashMap<>();
        for (Transactions transaction : transactions)
        {
            beneficiaryTransactionMap.put(transaction.getBeneficiaryFullName(), transaction);
        }
        return beneficiaryTransactionMap;
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
        Set<Integer> openIssues = new HashSet<>();
        for (Transactions transaction : transactions)
        {
            if (!transaction.isIssueSolved())
            {
                openIssues.add(transaction.getMtn());
            }
        }    
        return openIssues;
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        List<String> solvedIssuesMessaeges = new ArrayList<>();
        for (Transactions transaction : transactions)
        {
            if(transaction.isIssueSolved() && transaction.getIssueId() != null)
            {
                solvedIssuesMessaeges.add(transaction.getIssueMessage());
            }
        }
        return solvedIssuesMessaeges;
    }

    /**
     * Returns the 3 transactions with highest amount sorted by amount descending
     */
    public List<Transactions> getTop3TransactionsByAmount() {
        List<Transactions> top3Transactions = new ArrayList<>();

        List<Transactions> transactionList = Arrays.asList(transactions);
        transactionList.sort(Comparator.comparing(Transactions::getAmount).reversed());
        if (transactionList.size() > 3) // To avoid Index Out Of Bound Exception
        {
            top3Transactions = transactionList.subList(0, 3);
        }
    
        return top3Transactions;
    }

    /**
     * Returns the sender with the most total sent amount
     */
    public List<String> getTopSender() {
        List<String> topSender = new ArrayList<>(); // Using list considering there can be two senders with maximum amount
        Map<String,Double> senderTotalAmountMap = new HashMap<>();
        double maximum = 0;

        // At first stage, I retrived a map of sender and total amount of transactions they made counting only unique transactions
        Set<Integer> uniqueTransactions = new HashSet<>();
        for (Transactions transaction : transactions)
        {
            if (!uniqueTransactions.contains(transaction.getMtn()))
            {    
                senderTotalAmountMap.put(transaction.getSenderFullName(), senderTotalAmountMap.getOrDefault(transaction.getSenderFullName(), 0.0)+transaction.getAmount());
            }
            uniqueTransactions.add(transaction.getMtn());
        }

        // At Second stage we iterate over map to find the maximum value present
        for (String senderName : senderTotalAmountMap.keySet())
        {
            maximum = Math.max(maximum, senderTotalAmountMap.get(senderName));
        }

        // At third stage, we again iterate over map to find senders with maximum amount
        for (String senderName : senderTotalAmountMap.keySet())
        {
            if (senderTotalAmountMap.get(senderName) == maximum)
            {
                topSender.add(senderName);
            }
        }

        return topSender;
    }

    public static void main (String[] args)
    {
        try
        {
            TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

            // List of unit test cases
            System.out.println("Sum of all unique transactions is : "+transactionDataFetcher.getTotalTransactionAmount());
            System.out.println("Maximum transaction amount is : "+transactionDataFetcher.getMaxTransactionAmount());
            System.out.println("Total transaction amount sent by : "+transactionDataFetcher.getTotalTransactionAmountSentBy("Grace Burgess"));
            System.out.println("Number of Unique Clients [including sender and beneficiary] : "+transactionDataFetcher.countUniqueClients());
            System.out.println("Is this client has any open compliance issue : "+transactionDataFetcher.hasOpenComplianceIssues("Arthur Shelby"));
            System.out.println("List of Unresolved Issue IDs are : "+transactionDataFetcher.getUnsolvedIssueIds());
            System.out.println("List of all solved issue messages are : "+transactionDataFetcher.getAllSolvedIssueMessages());
            System.out.println("List of Top senders are : "+transactionDataFetcher.getTopSender());
            System.out.println("List of Top 3 senders are : "+transactionDataFetcher.getTop3TransactionsByAmount());
        }

        catch (IOException e)   
        {
            e.printStackTrace();
        }
    }

}
