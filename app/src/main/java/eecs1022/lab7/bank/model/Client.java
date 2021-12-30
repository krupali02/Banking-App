package eecs1022.lab7.bank.model;

public class Client {

    private String name;
    private double amount;
    private int count = 1;
    //int n=1;
    private double deposit;
    private double withdraw;
    private String result;

    private String[] reg;
    private final int r = 10;



    public Client (String name, double amount) {
        this.name = name;
        this.amount = amount;
        this.reg = new String [r];
    }

    public String getStatus () {
        String newAmount = String.format("%.2f", this.amount);
        result = name+": $"+newAmount;


        this.reg[0] = name+": $"+newAmount;


        return result;
    }

    public String[] getStatement() {

        String [] regi = new String[this.count];
        if (this.count != 0) {
            for (int i = 0; i < this.count; i++) {
                regi [i] = reg [i];
            }
        }
//
        return regi;
//	    	String [] arr = new String[n];
//	    	n++;
//
//	    	for (int i = 0; i < arr.length; i++) {
//	    		arr[i] = result;
//	    	}
//
//	        return arr;
    }


    public void deposit(double deposit) {

        if (count < r) {
            this.amount += deposit;
            String dep = String.format("%.2f", deposit);

            this.reg[this.count] = "Transaction DEPOSIT: $" + dep;
            this.count++;
        }
        //result = "Transaction DEPOSIT: " + deposit;

    }

    public void withdraw(double withdraw) {

        if (count < r) {
            this.amount -= withdraw;
            String wit = String.format("%.2f", withdraw);

            this.reg[this.count] = "Transaction WITHDRAW: $" + wit;
            this.count++;
        }

    }

    public String getName () {
        return name;
    }

    public double getAmount () {
        return amount;
    }


}
