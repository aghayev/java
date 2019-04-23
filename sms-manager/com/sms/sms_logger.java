/* 05.11.07 logging of business logic implementations to files */

package com.sms;

import java.io.*;
import java.text.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class sms_logger {

private  BufferedWriter out = null;
private  final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
private  Calendar cal = null;
private  SimpleDateFormat sdf = null;
private  String time = null;
private	 String path = "/home/kannel/log/";
private  String ext = "log";


public sms_logger(String name) {

        try {
	out = new BufferedWriter(new FileWriter(path+name+"."+ext, true));
	}
        catch (IOException e) {
        	}
	}

public void close() {
        try {
        out.close(); 
        }
        catch (IOException e) {}
}

public void write( String sentence, int newLine ) throws IOException {


        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	time = sdf.format(cal.getTime());


out.write(time+": "+sentence);
out.newLine();

out.flush();
}

}

