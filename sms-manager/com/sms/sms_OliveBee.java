/* 05.11.07 Implementation of interface for MotionAvenue Olive Interface */
/* 05.11.07 Contains MT-SMS Reply Business Logic */
/* 05.11.07 inherits business_logic method from its parent SMS_PROCESSING class */

package com.sms;

import java.io.*;
import java.text.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class sms_OliveBee extends sms_processing {

sms_logger logger = null;
sms_utilities instance = null;

public sms_OliveBee(String name) {
logger = new sms_logger(name);
instance = new sms_utilities();
}


public void business_logic (String sender, String messagecenter, String message) throws IOException {

String host, port, cgi;
ArrayList<String> key = new ArrayList<String>();
ArrayList<String> value = new ArrayList<String>();
String shortcode = "9460";

/* Because there are no url encoding in app program sender comes here with space instead of plus, so it is easy to trim sender */
sender = instance.trim(sender);
//message = instance.replace_single(message,"+"," ");

/* Integration with OliveBee Interface */
host = "127.0.0.1";
port = "8080";
cgi = "/OliveBee-war/MOInterface/AzercellAdapter";

key.add("sender");
key.add("messagecenter");
key.add("operator");
key.add("username");
key.add("password");
key.add("text");

value.add(sender);
value.add(messagecenter);
value.add("azercel");
value.add("azercel");
value.add("azercel");
value.add(URLEncoder.encode(instance.replace_complex(message),"UTF-8"));

                logger.write("checking destination shortcode  ",1);

                if (instance.compare(shortcode,messagecenter)) {
                logger.write("accepted "+sender+" "+messagecenter+" "+message,1);

		String response = super.get_url(host,port,cgi,key,value);
                logger.write("response is "+sender+" "+messagecenter+" "+message+"* "+response,1);

                }
                else { logger.write("rejected "+sender+" "+messagecenter,1);  }
};

public void close() {
logger.close();
}


}


