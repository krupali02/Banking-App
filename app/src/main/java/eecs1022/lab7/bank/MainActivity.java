package eecs1022.lab7.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import eecs1022.lab7.bank.model.Bank;
import eecs1022.lab7.bank.model.Client;

public class MainActivity extends AppCompatActivity {

    /* Hint: How do you share the same bank object between button clicks (attached with controller methods) of the app? */

    public Bank bankNew ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Hint: How do you initialize an empty bank and displays its status to the output textview
         * when the app is first launched?
         */

        bankNew = new Bank();
        String status = bankNew.getStatus();
        setContentsOfTextView(R.id.display, status);

    }

    /* this mutator sets the output label */
    private void setContentsOfTextView(int id, String newContents) {
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }

    /* this accessor retrieves input entered on the text view  */
    private String getInputOfTextField(int id) {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    /* this accessor retrieves input chosen from some spinner (drop-down menu) */
    private String getItemSelected(int id) {
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;
    }

    /* Hints on controller methods:
     *
     * Declare two controller methods, each of which declared with a parameter with type View (see Week 9 Tutorials).
     *  - One method (say cm1) should be attached to the "ADD A NEW ACCOUNT" button,
     *      whereas the other method (say cm2) should be attached to the "CONFIRM" button.
     *
     *  - Controller method cm1 should:
     *    + Retrieve the name of client and the initial balance in the corresponding textfields.
     *      You may need to convert the retrieved text, e.g., "23.4" to a double value using Double.parseDouble.
     *    + Then, invoke the relevant method on the shared bank object to add a new client/account accordingly.
     *    + Finally, display the status of the bank to the output textview.
     *
     * - Controller method cm2 should:
     *    + Retrieve the chosen service type listed in the spinner (Deposit, Withdraw, Transfer, Print Statement)
     *    + Depending on the service type chosen, retrieve the from-account, to-account, and/or amount accordingly.
     *      (See the "Assumed Usage Pattern of the App" section in your Lab7 PDF instructions)
     *    + Then, invoke the relevant method(s), depending on the chosen service type, on the shared bank object.
     *    + Finally, display the status of the bank (in the case of a deposit, withdraw, or transfer)
     *          or the statement of an account (in the case of print statement) to the ouptut textview.
     */

    public void addNewAccountClicked (View view) {
        String name = getInputOfTextField(R.id.nocA);
        String amount = getInputOfTextField(R.id.ibA);

        bankNew.addClient(name, Double.parseDouble(amount));
        String stat = bankNew.getStatus();
        setContentsOfTextView(R.id.display, stat);
    }

    public void confirmationClicked (View view) {
         String tA = getInputOfTextField(R.id.taA);
         String amount = getInputOfTextField(R.id.amA);
         String fA = getInputOfTextField(R.id.faA);
         String so = getItemSelected(R.id.stA);

    if (so.equals("Print Statement")) {
        String [] name = bankNew.getStatement(fA);
        if (name == null) {
            String stat = bankNew.getStatus();
            setContentsOfTextView(R.id.st, stat);
        }
        else {
            String fin = "";
            for (int i = 0; i < name.length; i++) {
                if (i != 0) {
                    fin += "\n" + name[i];
                }
                else {
                    fin = name[i];
                }
            }
        }
   }

    else if (so.equals("Deposit")) {
        bankNew.deposit(tA, Double.parseDouble(amount));
        String stat = bankNew.getStatus();
        setContentsOfTextView(R.id.display, stat);
    }

    else if (so.equals("Withdraw")) {
        bankNew.withdraw(fA, Double.parseDouble(amount));
        String stat = bankNew.getStatus();
        setContentsOfTextView(R.id.display, stat);
    }

    else if (so.equals("Transfer")) {
        bankNew.transfer(fA, tA, Double.parseDouble(amount));
        String stat = bankNew.getStatus();
        setContentsOfTextView(R.id.display, stat);
    }

    }

}