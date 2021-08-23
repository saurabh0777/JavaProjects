import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;
import org.apache.hadoop.hive.ql.exec.UDF;

public class Decrypt extends UDF {

    public String evaluate(String input) {
        byte[] password = new String("coe45#max56$kit98@mm").getBytes();
        String initVector = "rtYjU3546$jOperhk#263Dfghj";
        MessageDigest digest = null;
        MessageDigest digest2 = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest2 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] passwordhash = digest.digest(password);
        byte[] vectorhash = digest2.digest(initVector.getBytes());
        Cipher cipher = null;
        String output = null;
        try {
            IvParameterSpec iv = new IvParameterSpec(vectorhash);
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(passwordhash, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            //output = new String(cipher.doFinal(Hex.decodeHex(input))).trim();
            output = new String(cipher.doFinal(Hex.decodeHex(input.toCharArray()))).trim();  
            System.out.print("decoded value:"+output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
    
    public static void main(String[] args) {
    
       Decrypt ob = new Decrypt();
  	   ob.evaluate("c4fa35cec165caef279c4c078b62e8eb");
    	
    	
    }
    }


