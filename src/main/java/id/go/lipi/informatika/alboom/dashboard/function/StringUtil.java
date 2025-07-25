/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.go.lipi.informatika.alboom.dashboard.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public final class StringUtil {

    public static String ALIGN_RIGHT = "right";
    public static String ALIGN_LEFT = "left";

    public static String padding(String s, int length, String alignment) {
        if (alignment.equalsIgnoreCase("right")) {
            return String.format("%1$#" + length + "s", s);
        } else {
            return String.format("%1$-" + length + "s", s);
        }
    }

    public static String toQuery(String sql, Object[] params) {
        try {
            for (int i = 0; i < params.length; i++) {
                while (sql.contains("?")) {
                    if (params[i] instanceof String) {
                        sql = sql.replaceFirst(Pattern.quote("?"), "'" + params[i] + "'");
                    } else {
                        sql = sql.replaceFirst(Pattern.quote("?"), params[i] + "");
                    }
                    break;
                }
            }
            return sql;
        }catch(Exception ex){}
        return "";
    }

    public static boolean isIPExist(String argIPAddress, String argIP) {
        StringTokenizer st = new StringTokenizer(argIPAddress, ",");
        while (st.hasMoreTokens()) {
            String ip = st.nextToken();
            if (ip.trim().equals(argIP.trim())) {
                return true;
            }
        }
        return false;
    }

    public static String decodeHexString(String hexText) {
        String decodedText = null;
        String chunk = null;
        if (hexText != null && hexText.length() > 0) {
            int numBytes = hexText.length() / 2;

            byte[] rawToByte = new byte[numBytes];
            int offset = 0;
            for (int i = 0; i < numBytes; i++) {
                chunk = hexText.substring(offset, offset + 2);
                offset += 2;
                rawToByte[i] = (byte) (Integer.parseInt(chunk, 16) & 0x000000FF);
            }
            decodedText = new String(rawToByte);
        }
        return decodedText;
    }

    public static String encodeHexString(String sourceText) {
        byte[] rawData = sourceText.getBytes();
        StringBuilder hexText = new StringBuilder();
        String initialHex = null;
        int initHexLength = 0;

        for (int i = 0; i < rawData.length; i++) {
            int positiveValue = rawData[i] & 0x000000FF;
            initialHex = Integer.toHexString(positiveValue);
            initHexLength = initialHex.length();
            while (initHexLength++ < 2) {
                hexText.append("0");
            }
            hexText.append(initialHex);
        }
        return hexText.toString();
    }

    public static String toMoney(double arg) {
        int x = (int) arg;
        String y = String.valueOf(x);
        int z = y.length() / 3;
        int a = y.length() - 3 * z;
        String result = "";
        if (a != 0) {
            result += y.substring(0, a) + ",";
        }
        String b = y.substring(a, y.length());
        for (int i = 0; i < z; i++) {
            result += b.substring(i * 3, i * 3 + 3) + ",";
        }
        return result.substring(0, result.length() - 1);
    }
    
    public static String toMoney(String y) {
        if (y!=null && !y.equalsIgnoreCase("")){
            int z = y.length() / 3;
            int a = y.length() - 3 * z;
            String result = "";
            if (a != 0) {
                result += y.substring(0, a) + ",";
            }
            String b = y.substring(a, y.length());
            for (int i = 0; i < z; i++) {
                result += b.substring(i * 3, i * 3 + 3) + ",";
            }
            return result.substring(0, result.length() - 1);
        }        
        return y;
    }

    public static String toDateTime(String arg) {
        StringBuilder sb = new StringBuilder(1024);
        sb.append(arg.substring(4, 6)).append("-");
        sb.append(arg.substring(2, 4)).append(" ");
        //sb.append(arg.substring(0, 2)).append(" ");
        sb.append(arg.substring(6, 8)).append(":");
        sb.append(arg.substring(8, 10));//.append(":");
        //sb.append(arg.substring(10, 12));
        return sb.toString();
    }

    public static String addLeadingZeroes(int i, int length) {
        return String.format("%0" + length + "d", i);
    }

    public static String addLeadingZeroes(String i, int length) {
        String nol = "";
        for (int j = 0; j < length - i.length(); j++) {
            nol = nol + "0";
        }
        return nol + i;
    }

    public static String forPln(String i) {
        String nol = "";
        for (int j = 0; j < i.length(); j++) {
            nol += i.substring(j, j + 1);
            if (((j + 1) % 4 == 0 && (j + 1) < i.length())) {
                nol += " ";
            }
        }
        return nol;
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    
     public static String numberFormat(String i) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator('.');
        dfs.setDecimalSeparator(',');
        formatter.setDecimalFormatSymbols(dfs);
        return formatter.format(i);
    }

    public static String numberFormat(Object i) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator('.');
        dfs.setDecimalSeparator(',');
        formatter.setDecimalFormatSymbols(dfs);
        return formatter.format(i);
    }

    
    public static String numberFormat2Decimal(double i, int decimal) {
    	String x = "";
        if (decimal > 0) {
            for (int j = 0; j < decimal; j++) {
                x += "0";
            }
            x = "." + x;
        }

        DecimalFormat formatter = new DecimalFormat("###,###,##0" + x);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator('.');
        dfs.setDecimalSeparator(',');
        formatter.setDecimalFormatSymbols(dfs);
        String result = formatter.format(i);
//        while ((result.endsWith("0")&& result.contains(",")) ||result.endsWith(",")){
//            result = result.substring(0,result.length()-1);
//        }
        return result;
    }

    public static String trim(String input) {
        input = input.replaceAll(">\\s+<", "><");
        BufferedReader reader = new BufferedReader(new StringReader(input));
        StringBuilder result = new StringBuilder();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line.trim()).append(" ");            
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
        //System.out.println(Pattern.matches("^-?\\d+$", "2"));
        System.out.println("pl20".substring(2, "pl20".length())     );
    }
}
