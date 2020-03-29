import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


class BabyThread extends Thread
{
    protected String hash;
    protected int num;
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {

        MessageDigest md = MessageDigest.getInstance("SHA-256");


        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash)
    {

        BigInteger number = new BigInteger(1, hash);


        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
    String[] alphabet = {"a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" , "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q", "r" , "s" , "t", "u" , "v" , "w" , "x","y","z"};
    String[] word = {" " , " " ," " , " " , " "};
    String output;
    public BabyThread(String hash , int num)
    {
        this.hash = hash;
        this.num = num;
    }
    public void run() {
        for(int i = 0 ; i < alphabet.length ; i++)
        {
            word[0] = alphabet[i];
            for(int j = 0 ; j < alphabet.length ; j++) {
                word[1] = alphabet[j];
                for (int k = 0; k < alphabet.length; k++) {
                    word[2] = alphabet[k];
                    for (int m = 0; m < alphabet.length; m++) {
                        word[3] = alphabet[m];
                        for (int l = 0; l < alphabet.length; l++) {
                            word[4] = alphabet[l];
                            output = word[0] + word[1] + word[2] + word[3] + word[4];
                            String tmp = output;
                            try {
                                output = toHexString(getSHA(output));
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            }
                            if (output.equals(hash)) {
                                System.out.println(tmp + " : " + hash + " Thread №" + num + " is interrupt");
                                this.interrupt();
                            }

                        }
                    }
                }
            }
    }



    }
}

public class BruteForce
{
    static BabyThread frstThread , secThread , thrdThread;

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String hash1 = "1115dd800feaacefdf481f1f9070374a2a81e27880f187396db67958b207cbad" ,
                hash2 = "3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b" ,
                hash3 = "74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f";
        frstThread = new BabyThread(hash1 , 1);
        secThread = new BabyThread(hash2 , 2);
        thrdThread = new BabyThread(hash3 , 3);

        frstThread.start();
        secThread.start();
        thrdThread.start();
        System.out.println( "Рязанов В.В БАСО-02-18" + "\n" +"Main thread is over");


    }
}