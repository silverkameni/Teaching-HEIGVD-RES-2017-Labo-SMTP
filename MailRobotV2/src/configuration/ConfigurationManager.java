/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import mail.Mail;
import mail.Person;

/**
 *
 * @author SILVERCORP
 */
public class ConfigurationManager implements IConfigurationManager {

    private String smtpServerAddress;
    private int serverport;
    private List<Person> victims;
    private List<Mail> messages;
    private int numberOfGroups;
    private String subject = null;

    public ConfigurationManager() throws IOException {
        victims = loadAddressFromFile("victims.utf8");
        messages = loadMailFromFile("messages.utf8");
        loadPropertie("configuration.properties");

    }

    /*
     *permet de recuperer le nom et le prenom pour chaque adresse email 
     *a partir de la liste des adresses emails contenu dans le fichier 
     * "victims" passé en parametre
     */
    @Override
    public List<Person> loadAddressFromFile(String filename) throws IOException  {

        List<Person> listOfPerson;

         
        
           try (FileInputStream f = new FileInputStream(filename)) {
            InputStreamReader fls = new InputStreamReader(f, "UTF-8");
            
            
            try (BufferedReader reader = new BufferedReader(fls)) {
            listOfPerson = new LinkedList<>();
            String address = reader.readLine();

            while (address != null) {

                String str[] = address.split("@"); // on enleve la partie heig-vd.ch
                str = str[0].split("\\.");         // on recupere le prenom et le nom  
                Person person = new Person(str[1], str[0], address);
                listOfPerson.add(person); //construction d'une nouvelle personne
                address = reader.readLine(); // lire la prochaine adresse

            }
            }
           }
        
        return listOfPerson;

    }

   
    @Override
    public void loadPropertie(String filename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        Properties properpties = new Properties();
        properpties.load(fileInputStream);

        this.smtpServerAddress = properpties.getProperty("smtpserverAddress");
        this.serverport = Integer.parseInt(properpties.getProperty("smtpServerport")); //conversion en INT
        this.numberOfGroups = Integer.parseInt(properpties.getProperty("numberOfGroups")); //conversion en INT
        this.subject = properpties.getProperty("Subject");

    }

    @Override
    public List<Mail> loadMailFromFile(String filename) throws UnsupportedEncodingException, IOException {

        List<Mail> listOfMail;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
            listOfMail = new LinkedList<>();
            String line = reader.readLine();
            while (line != null) {
                Mail mail = new Mail();
                StringBuilder body = new StringBuilder(); // usage de cette structure pour gagner en memoire lors des "append"
                while ((line != null) && (!line.equals("@@@"))) { //parcours jusqu'au separateur de mail "@@@"

                    if (line.indexOf("subject") != -1) { //on regarde s'il y'a du texte après "subject" 
                        String str[] = line.split(":");
                        mail.setSubject(str[1]); //on recupere le sujet (objet du mail)
                    } else {
                        body.append(line); // concatenation de chaque ligne du body
                        body.append("\r\n");
                    }
                    mail.setBody(body.toString()); //on ajoute le corps du message a notre variable Body
                    line = reader.readLine(); //on passe a la ligne suivante
                }

                listOfMail.add(mail);

                line = reader.readLine();
            }
            return listOfMail;
        }
    }

    @Override
    public String getServerAddress() {
        return smtpServerAddress;
    }

    @Override
    public int getServerport() {
        return serverport;
    }

    @Override
    public List<Person> getVictims() {
        return victims;
    }

    @Override
    public List<Mail> getMessages() {
        return messages;
    }

    @Override
    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    @Override
    public String getSubject() {
        return subject;
    }
    
}
