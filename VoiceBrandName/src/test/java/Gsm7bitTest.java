import com.ecorau.vbn.utils.GSM7BitConverter;

public class Gsm7bitTest {
    public static void main(String[] args){
        String str = GSM7BitConverter.stringToGsm7BitPackHex("SAMSUNG(");
        String str2 = GSM7BitConverter.stringToGsm7BitPackHexUssd("SAMSUNG(");
        String str1 = GSM7BitConverter.gsm7BitHexToString("d6a4f3287d56a131d90000");
        System.out.println(str);
    }
}
