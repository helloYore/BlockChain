import util.StringUtil;

import java.util.Date;

/**
 * @author helloyore
 * @createTime 2021年12月23日 13:28:00
 * @Description
 */
public class Block {

    public String hash;//该区块的哈希值 我们的数字签名
    public String previousHash;//该区块的前一个区块的哈希值
    private String data; //存储的数据
    private long timeStamp; //as number of milliseconds since 1/1/1970.
    private int nonce; //挖矿者的工作证明

    //构造方法
    public Block(String data, String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    //在block类中应用sha256方法 计算hash值，包括该区块中所有我们不希望被恶意篡改的数据
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    //挖矿 引入了diffculty难度
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined! : " + hash);
    }


}
