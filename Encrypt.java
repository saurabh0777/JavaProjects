import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.hadoop.hive.ql.exec.UDF;
import javax.crypto.spec.IvParameterSpec;

public class Encrypt extends UDF {

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
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            output = Hex.encodeHexString(cipher.doFinal(input.getBytes()));
            System.out.print("encoded value:"+output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
    
    public static void main(String[] args) {
    	
    	   Encrypt ob = new Encrypt();
    	   ob.evaluate("601123318979");
  
      }


 
    
}
