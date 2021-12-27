package pleasefivebank.EntryPage;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.User;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Registration{

    private boolean checkbox;
    private String university;


    public boolean getCheckbox(){
        return checkbox;
    }

    public String getUniversity() {return university;}

    public void setUniversity(String university){this.university = university;}
    
    //juan
    public void changeCheckBox(){
        if(checkbox){
            checkbox = false;
        } else{ checkbox = true; }
    }

    //andreea
    /*public void setAccount(){
        //generate random IBAN
        String iban = generateIBAN();
        //remove the empty spaces
        iban = iban.replace(" ","");
        String accountNr = iban.substring(7,19);
        //create basic or student account
        if(this.university.isEmpty()){
            this.account = new BasicAccount(accountNr, iban, 0, false);
        } else {
            this.account = new StudentAccount(accountNr, iban, 0, false);
        }
    }*/


    //andreea
    public static void register(User user, String username, String password){
        //String birthdate = extractBirthdate(this.personalID);//set the birthdate
        //We write the user as document
        Document userdoc = user.toDocument();
        //we create a document with encrypted credentials and add it to the database
        Document login = new Document("_id", new ObjectId()).append("user name", username).
                append("password", Mongo.encrypt(password));
        Mongo.coll.insertOne(login);
        //get the automatically generated id of the document just inserted
        String key = login.get("_id").toString();
        //store the id in the key field of the user document and add the user to the database
        Mongo.coll.insertOne(userdoc.append("key", key));
        //print at the end of file UserDB2
        toFile(userdoc, login, key);
        //restart the database to get the new information there
        try {
            Mongo.mongo();//Mongo is a utility class and cannot be instantiated
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //andreea && ossian
    public static String extractBirthdate(String personnummer){
        String yearString  = personnummer.substring(0,2);
        int year = Integer.parseInt(yearString);
        if(year > 22){ year += 1900; }
        else { year += 2000; }
        String month = personnummer.substring(2,4);
        String day = personnummer.substring(4,6);
        return year +"."+ month + "."+ day;
    }

    //juan
    public static String generateIBAN(){
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.SE)
                .bankCode("555")
                .buildRandom();
        String ibanString = iban.toString();
        return ibanString;
    }

    //andreea
    public static void toFile(Document user, Document login, Object key) {
        try {
            FileWriter writer = new FileWriter("UserDB2.json", true);
            writer.write(login.append("_id", key).toJson() + System.lineSeparator());
            writer.write(user.toJson() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Carlotta
    public String generateCardNr() {
        Random random = new Random();
        int randomNumberLength = 16;
        int counter = 0;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < randomNumberLength; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
            counter++;
            if(counter == 4){
                builder.append(" ");
                counter = 0;
            }
        }
        return builder.toString();
    }
    //carlotta
    public String calculateExpirationDate(){
        String expirationDate = "";
        String pattern = "MM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        pattern = "yyyy";
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern);
        String year = simpleDateFormat2.format(new Date());

        int yearInt = Integer.parseInt(year);
        yearInt = yearInt + 5;
        expirationDate=expirationDate.concat(Integer.toString(yearInt)+"-");
        expirationDate=expirationDate.concat(date);

        return expirationDate.substring(2);
    }
}