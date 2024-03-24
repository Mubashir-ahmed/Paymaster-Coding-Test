package com.smallworld;

import java.io.IOException;

public class Main {
    public static void main (String[] args)
    {
        try
        {
            TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

            // List of unit test cases
            System.out.println("Sum of all unique transactions is : "+transactionDataFetcher.getTotalTransactionAmount());
            System.out.println("Maximum transaction amount is : "+transactionDataFetcher.getMaxTransactionAmount());
            System.out.println("Total transaction amount sent by : "+transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby"));
            System.out.println("Number of unique clients [including sender and beneficiary] : "+transactionDataFetcher.countUniqueClients());
            System.out.println("Is this client has any open compliance issue : "+transactionDataFetcher.hasOpenComplianceIssues("Aberama Gold"));
            System.out.println("List of all unresolved Issue IDs : "+transactionDataFetcher.getUnsolvedIssueIds());
            System.out.println("List of all solved issue messages : "+transactionDataFetcher.getAllSolvedIssueMessages());
            System.out.println("List of names of top senders : "+transactionDataFetcher.getTopSender());
        }

        catch (IOException e)   
        {
            e.printStackTrace();
        }
    }
    
}
