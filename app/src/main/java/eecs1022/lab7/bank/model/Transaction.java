package eecs1022.lab7.bank.model;

public class Transaction {
    public String deposit;
    public double value;

    public Transaction(String deposit, double value) {
        this.deposit = deposit;
        this.value = value;
    }

    public String getStatus () {

        if (deposit.equals("DEPOSIT")) {
            String a = String.format("%.2f", this.value);

            return "Transaction DEPOSIT: $" + a;
        } else if (deposit.equals("WITHDRAW")) {
            String b = String.format("%.2f", this.value);
            return "Transaction WITHDRAW: $" + b;
        } else return null;
    }

}
