package com.smallworld;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class TransactionDataFetcherTest {
    private TransactionDataFetcher transactionDataFetcher;

    @BeforeEach
    public void setUp() throws IOException {
        transactionDataFetcher = new TransactionDataFetcher();
    }

    @Test
    public void testGetTotalTransactionAmount() {
        assertEquals(2889.17, transactionDataFetcher.getTotalTransactionAmount());
    }

    @Test
    public void testGetTotalTransactionAmountSentBy() {
        assertEquals(678.06, transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby"));
    }

    @Test
    public void testGetMaxTransactionAmount() {
        assertEquals(985, transactionDataFetcher.getMaxTransactionAmount());
    }

    @Test
    public void testCountUniqueClients() {
        long uniqueClientsCount = transactionDataFetcher.countUniqueClients();
        assertEquals(14, uniqueClientsCount);
    }

    @Test
    public void testHasOpenComplianceIssues() {
        assertTrue(transactionDataFetcher.hasOpenComplianceIssues("Arthur Shelby"));
        assertFalse(transactionDataFetcher.hasOpenComplianceIssues("Aberama Gold"));
    }

    @Test
    public void testGetTransactionsByBeneficiaryName() {
        Map<String, List<Transactions>> transactionsByBeneficiary = transactionDataFetcher.getTransactionsByBeneficiaryName();
        assertEquals(3, transactionsByBeneficiary.get("Michael Gray").size());
    }

    @Test
    public void testGetUnsolvedIssueIds() {
        Set<Integer> unsolvedIssueIds = transactionDataFetcher.getUnsolvedIssueIds();
        assertTrue(unsolvedIssueIds.contains(1));
        assertFalse(unsolvedIssueIds.contains(2));
    }

    @Test
    public void testGetAllSolvedIssueMessages() {
        List<String> solvedIssueMessages = transactionDataFetcher.getAllSolvedIssueMessages();
        assertTrue(solvedIssueMessages.contains("Never gonna give you up"));
        assertFalse(solvedIssueMessages.contains("Looks like money laundering"));
    }

    @Test
    public void testGetTop3TransactionsByAmount() {
        List<Transactions> top3Transactions = transactionDataFetcher.getTop3TransactionsByAmount();
        assertEquals(3, top3Transactions.size());
        assertEquals(985.0, top3Transactions.get(0).getAmount());
        assertEquals(666.0, top3Transactions.get(1).getAmount());
        assertEquals(430.2, top3Transactions.get(2).getAmount());
    }

    @Test
    public void testGetTopSender() {
        List<String> topSender = transactionDataFetcher.getTopSender();
        assertTrue(!topSender.isEmpty());
        assertEquals("Arthur Shelby", topSender.get(0));
    }
}
