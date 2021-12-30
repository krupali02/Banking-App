package eecs1022.lab7.bank.model;

public class Bank {

    // initialization of variables
    private String name;
    private String e;
    private int count;
    private boolean error = false;
    private boolean gc = false;
    private Client a;
    private int x;

    private  Client[] c;

    // CONSTRUCTOR METHOD
    public Bank () {
        count = 0;
        //this.t = new String[10];
        this.c = new Client[6];
        //this.stat = new Client[6];

    }

    // Accessor method
    public String getStatus() {
//
//	    	if (count == 0) {
//	    	return "Accounts: {}";
        //}

        if (x!= 0) {
            return e;
        }
        return update();

    }

    // check if the names being entered as a parameter are already present in the 'c' array
    public boolean check (String name) {
        boolean is = false;
        for (int i = 0; i < count && (is == false); i++) {
            is = name.equals(c[i].getName());
        }
        return is;
    }


    public String[] getStatement(String name) {

        //String [] n = null;

//	    	if (count == 0) {
//	    		//n = null;
//	    	}

        if (check(name) == false) {
            x++;
            e = "Error: From-Account " + name + " does not exist";
        }

        else {
            for (int i = 0; i < count; i++) {
                if (name.equals(c[i].getName())) {
                    String [] n = get(name).getStatement();
                    return n;
                }}
        } return null;
    }


    //type Client
    public Client get (String name) {
        for (int i = 0; i < count; i++) {
            if (name.equals(c[i].getName())) {
                a = c[i];
            }

        } return a;
    }



    // Deposit mutator method
    public void deposit(String toName, double amount) {

        if (check(toName) == false) {
            x++;
            e = "Error: To-Account " + toName + " does not exist";
        }

        else if (amount <= 0) {
            x++;
            e = "Error: Non-Positive Amount";
        }

        else {
            for (int i = 0; i < count; i++) {
                if (c[i].getName().equals(toName))
                    c[i].deposit(amount);
                e = update();
            }}

    }


    // withdraw mutator method
    public void withdraw(String fromName, double amount) {

        if (check(fromName)== false) {
            x++;
            e = "Error: From-Account " + fromName + " does not exist";
        }

        else if (amount <= 0) {
            x++;

            e = "Error: Non-Positive Amount";
        }

        else if (!(get(fromName).getAmount() >= amount)) {
            x++;
            e = "Error: Amount too large to withdraw";
        }

        else {
            //get(fromName).
            for (int i = 0; i < count; i++) {
                if (c[i].getName().equals(fromName))
                    c[i].withdraw(amount);
                e = update();
            }}
    }



    // transfer mutator method
    public void transfer(String fromName, String toName, double amount) {

        if (check(fromName)== false) {
            x++;
            e = "Error: From-Account " + fromName + " does not exist";
        }

        else if (check(toName)== false) {
            error = true;
            e = "Error: To-Account " + toName + " does not exist";
        }

        else if (amount <= 0) {
            x++;
            e = "Error: Non-Positive Amount";
        }

        else if (!(amount <= get(fromName).getAmount())) {
            x++;
            e = "Error: Amount too large to transfer";
        }

        else {
            deposit (toName, amount);
            withdraw (fromName, amount);
            e = update();
        }


    }


    // ADD CLIENT
    public void addClient(String name, double amount) {
        if (count == 6) {
            x++;
            e = "Error: Maximum Number of Accounts Reached";
        }

        else if (check(name) == true) {
            x++;
            e = "Error: Client " + name + " already exists";
        }

        else if (amount <= 0) {
            x++;
            e = "Error: Non-Positive Initial Balance";
        }

        else {
            c[count] = new Client(name,amount);
            count ++;
            e = update();
        }

    }

    // output the final result in the {--}
    public String update () {

        String fin = "Accounts: {";

        for (int i = 0; i < count; i++) {
            if (i == 0) {
//					String amo = String.format("%.2f", amount);
//					fin += name +": $" + amo;
                fin += c[i].getStatus();
            }

            else {
//					String h = c[i].getName();
//					double am = c[i].getAmount();
//					String amou = String.format("%.2f", am);
//					fin += ", " + h +": $" + amou;
                fin += ", " + c[i].getStatus();
            }

            //fin = fin + "}";

        }
        //}
        return fin += "}";
    }


}
