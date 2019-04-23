/* an abstract class contraining definitions of the methods which will be used by customized sms applications */

package com.sms;

import java.io.*;
import java.text.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class sms_processing {

private String shortcode;

public void business_logic (String sender, String messagecenter, String message) throws IOException {};

public String get_url (String host, String port, String cgi, ArrayList key,ArrayList value)  throws IOException {

String API;
String line = null;

API = "http://"+host+":"+port+cgi+"?";

int index;
int size = key.size();
for ( index=0; index < size; index++) {
if (index==0) 
API += key.get(index) + "=" + value.get(index);
else
API += "&" + key.get(index) + "=" + value.get(index);
}

try {
URL url  = new URL(API);

//reading from open HTTP pipe
BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

line = in.readLine();
}
catch (MalformedURLException e) {};

return line;
};


}



