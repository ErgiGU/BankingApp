package pleasefivebank.Objects;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;

//ive yet to do enough research to give a satisfactory explanation of the functions. further description pending
    public class MailBot {
        Session newSession = null;
        MimeMessage mimeMessage = null;

        public void sendEmail() throws MessagingException {
            String fromUser = "xxxx@xxx.xx";  //Enter sender email id (MUST BE GMAIL)
            String fromUserPassword = "xxxxxx";  //Enter sender gmail password , this will be authenticated by gmail smtp server
            // (if it doesn't work go to myaccount.google.com/security and enable third party access)
            String emailHost = "smtp.gmail.com";
            Transport transport = newSession.getTransport("smtp");
            transport.connect(emailHost, fromUser, fromUserPassword);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        }

        public MimeMessage draftEmail(String emailRecipient,String emailSubject ,String emailBody) throws AddressException, MessagingException, IOException {
            mimeMessage = new MimeMessage(newSession);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));
            mimeMessage.setSubject(emailSubject);

            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(emailBody,"html/text");
            MimeMultipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(bodyPart);
            mimeMessage.setContent(multiPart);
            return mimeMessage;
        }

        public void setupServerProperties() {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            newSession = Session.getDefaultInstance(properties,null);
        }
    }

