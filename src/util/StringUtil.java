package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author helloyore
 * @createTime 2021年12月23日 13:32:00
 * @Description 所有的输入调用此方法后均会生成一个独一无二的hash值（数字签名）
 */
public class StringUtil {
    /**
     * 使用Sha256进行加密
     * SHA（Secure Hash Algorithm）安全散列算法，这种算法的特点是数据的少量更改会在Hash值中产生不可预知的大量更改，hash值用作表示大量数据的固定大小的唯一值，而SHA256算法的hash值大小为256位。
     * 之所以选用SHA256是因为它的大小正合适，一方面产生重复hash值的可能性很小，另一方面在区块链实际应用过程中，有可能会产生大量的区块，而使得信息量很大，那么256位的大小就比较恰当了。
     */
    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //应用Sha256
            byte[] hash = digest.digest(input.getBytes("UTF-8"));//将包含十六进制的哈希值
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException();
        }
    }
}
