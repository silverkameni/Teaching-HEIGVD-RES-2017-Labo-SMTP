
package prank;

import java.util.ArrayList;
import java.util.List;
import mail.Mail;
import mail.Person;

/*
 *@authors silver kameni && zacharie nguefack
 *
 *Cette classe definira le structure logique exacte de notre Prank qui se composera
 *donc d'un emetteur,d'un message et de plusieurs destinataire en copie 
 */
public class Prank {

    private Person victimSender;
    private final List<Person> victimRecipients = new ArrayList<>();
    private final List<Person> witnessRecepients = new ArrayList<>();
    private Mail message;

    public Person getVictimSender() {
        return victimSender;
    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public Mail getMessage() {
        return message;
    }

    public void setMessage(Mail message) {
        this.message = message;
    }
    
    public List<Person> getVictimRecipient() {
        return victimRecipients;
    }
    
    public void addVictimRecipients(List<Person> victimRecipients) {
        this.victimRecipients.addAll(victimRecipients);
    }
    
    public List<Person> getwitnessrecipient() {
        return witnessRecepients;
    }
     public void addWitnessvictim(List<Person> witnessrecipient) {
        this.witnessRecepients.addAll(witnessrecipient);
    }
    
    

}
