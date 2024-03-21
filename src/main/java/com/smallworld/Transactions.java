package com.smallworld;

public class Transactions 
{
    private Integer mtn;
    private Double amount;
    private String senderFullName;
    private int senderAge;
    private String beneficiaryFullName;
    private int beneficiaryAge;
    private Integer issueId;
    private boolean issueSolved;
    private String issueMessage;

    public void setMtn(Integer mtn) 
    {
        this.mtn = mtn;
    }

    public Integer getMtn() 
    {
        return mtn;
    }

    public void setAmount(Double amount) 
    {
        this.amount = amount;
    }

    public Double getAmount() 
    {
        return amount;
    }

    public void setSenderFullName(String senderFullName) 
    {
        this.senderFullName = senderFullName;
    }

    public String getSenderFullName() 
    {
        return senderFullName;
    }

    public void setSenderAge(int senderAge) 
    {
        this.senderAge = senderAge;
    }

    public int getSenderAge() 
    {
        return senderAge;
    }

    public void setBeneficiaryFullName(String beneficiaryFullName) 
    {
        this.beneficiaryFullName = beneficiaryFullName;
    }

    public String getBeneficiaryFullName() 
    {
        return beneficiaryFullName;
    }

    public void setBeneficiaryAge(int beneficiaryAge) 
    {
        this.beneficiaryAge = beneficiaryAge;
    }

    public int getBeneficiaryAge() 
    {
        return beneficiaryAge;
    }

    public void setIssueId(Integer issueId) 
    {
        this.issueId = issueId;
    }

    public Integer getIssueId() 
    {
        return issueId;
    }

    public void setIssueSolved(boolean issueSolved) 
    {
        this.issueSolved = issueSolved;
    }

    public boolean isIssueSolved() 
    {
        return issueSolved;
    }

    public void setIssueMessage(String issueMessage) 
    {
        this.issueMessage = issueMessage;
    }

    public String getIssueMessage() 
    {
        return issueMessage;
    }
}
