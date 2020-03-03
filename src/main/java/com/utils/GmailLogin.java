package com.utils;

import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

public class GmailLogin {

	// This method return otp from a subject
	public static int getEmail(String emailID, String password, String subjectToBeSearched) throws Exception {
		Properties props = new Properties();
		// Setting imaps(reading a message) smtp(sending a message) to
		// mail.store.protocol to read a mails
		props.setProperty("mail.store.protocol", "imaps");
		// Creating a default instance
		Session session = Session.getDefaultInstance(props, null);
		// session.setDebug(true);
		Store store = session.getStore("imaps");
		// below method is used to login in mail
		store.connect("imap.gmail.com", emailID, password);

		//It will search for inbox folder
		Folder inbox = store.getFolder("INBOX");
		//It will Open a inbox folder
		inbox.open(Folder.READ_WRITE);

		// It will return list of inbox message irrespective of
		// subjectToBeSearched
		int messagecount = inbox.getMessageCount();
		System.out.println("Message count is " + messagecount);

		ArrayList<Integer> al = new ArrayList<Integer>();
		int otp = 0;
		// Appending all messages to Message[]
		Message[] messages = inbox.getMessages();
		for (int i = 0; i < messagecount; i++) {
			// Searching all messages which matches with the subject
			if (messages[i].getSubject().equals(subjectToBeSearched)) {
				// Appending message to mimemultipart as
				// messages[i].getContent() return mimemultipart
				MimeMultipart messageBody = (MimeMultipart) messages[i].getContent();
				// converting mimemultipart to string
				String msg = getTextFromMimeMultipart(messageBody);
        				// Used pattern regular expression to get otp.
				// Here d indicates digits and brackets acts as a group and 6
				// indicates it contains 6 digits.
				Pattern p = Pattern.compile("Authorization code \\*(\\d{6})\\*");
				// Matcher is used to search required regular expression in a
				// message
				Matcher m = p.matcher(msg);
				// find() return boolean value. if it is true. goes to next step
				if (m.find()) {
					// group method return entire regular expression value which
					// is given in pattern
					System.out.println(m.group());
					// group(1) return 6 digits
					String string_otp = m.group(1);
					otp= Integer.valueOf(string_otp);
					System.out.println("Otp is ---> " + otp);	
				}
				
				
			}
			

			/*
			 * else { System.out.println(subjectToBeSearched+ " Not found "); }
			 */
		}
          al.add(otp);
          int last= +al.get(al.size()-1);
          System.out.println("Last OTP is ---> " +al.get(al.size()-1));
		inbox.close(true);
		store.close();
		return last;
	}

	public static void main(String args[]) throws Exception {
		GmailLogin gmail = new GmailLogin();
		gmail.getEmail("testmfino005@gmail.com", "mFino@3600", "Your Good Money 3 MFA Code.");
	}

	// getEmail() returns messageBody as mimeMultipart. In order to convert
	// messageBody into mimeMultipart this method is used.
	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
		String result = "";
		int partCount = mimeMultipart.getCount();
		for (int i = 0; i < partCount; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				// result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
				result = html;
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}

}
