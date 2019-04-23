/* 05.11.07 Contains various supporing utilities for Business Logic Implementation */


package com.sms;

import java.io.*;
import java.text.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class sms_utilities {

public String replace_single (String target, String from, String to) {
int start = target.indexOf(from);
if (start == -1) return target;
int lf = from.length();
char [] targetChars = target.toCharArray();
StringBuffer buffer = new StringBuffer();
int copyFrom = 0;
while (start != -1) {
buffer.append (targetChars, copyFrom, start-copyFrom);
buffer.append (to);
copyFrom = start + lf;
start = target.indexOf (from, copyFrom);
}
buffer.append (targetChars, copyFrom, targetChars.length - copyFrom);
return buffer.toString();
};


/* Unicode Symbols Converter */
public String replace_complex (String target) {

char unicode[] = {'\u0259','\u018F','\u00FC','\u00DC','\u00F6','\u00D6','\u011F','\u011E','\u00E7','\u00C7','\u015F','\u015E','\u0131','\u0130'};

char ascii[] = {'e','E','u','U','o','O','g','G','c','C','s','S','i','I'};

for (int i =0; i < unicode.length; i++) { //for (int i =0; i > unicode.length; i++) }
target = target.replace(unicode[i],ascii[i]);
}
return target;
};


public boolean compare (String src, String destination) {

if (src.equals(destination)) 
return true;

return false;
};


public String trim(String msisdn) {

return msisdn.trim();
};

}

