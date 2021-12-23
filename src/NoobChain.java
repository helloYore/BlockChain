import java.util.ArrayList;

import com.google.gson.GsonBuilder;


/**
 * @author helloyore
 * @createTime 2021年12月23日 13:50:00
 * @Description
 */
public class NoobChain {

    /**
     * 存储区块到数组中
     */
    public static ArrayList<Block> blockChain = new ArrayList<Block>();

    /**
     * 定义挖矿难度
     */
    private static int difficulty = 6;

    /**
     * 检查区块链的完整性
     */
    public static Boolean isChainValue() {
        Block prevBlock;
        Block currBlock;
        //目标hash值，判断是否被挖的依据
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //循环检查哈希值
        for (int i = 1; i < blockChain.size(); i++) {
            currBlock = blockChain.get(i);
            prevBlock = blockChain.get(i - 1);
            //比较注册的哈希值和计算出的哈希值
            if (!currBlock.hash.equals(currBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            //比较前一个区块的计算出的哈希值和注册的哈希值
            if (!prevBlock.hash.equals(currBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //检查是否被挖了
            if (!currBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block has not been mined.");
                return false;
            }
        }
        return true;
    }


    //测试
    public static void main(String[] args) {
//        Block firstBlock = new Block("I am  1", "0");
//        System.out.println("Hash for block1: " + firstBlock.hash);
//        Block secondBlock = new Block("I am 2", firstBlock.hash);
//        System.out.println("Hash for block2: " + secondBlock.hash);
//        Block thirdBlock = new Block("I am  3", secondBlock.hash);
//        System.out.println("Hash for block3: " + thirdBlock.hash);
        blockChain.add(new Block("I am hello 1", "0"));
        System.out.println("Try to Mine block 1");
        blockChain.get(0).mineBlock(difficulty);   //创建新区块时候需要触发mineBlock()方法

        blockChain.add(new Block("I am yore 2", blockChain.get(blockChain.size() - 1).hash));
        System.out.println("Try to Mine block 2");
        blockChain.get(1).mineBlock(difficulty);

        blockChain.add(new Block("I am helloYore  3", blockChain.get(blockChain.size() - 1).hash));
        System.out.println("Try to Mine block 3");
        blockChain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is valid: " + isChainValue());

        //引入gson包，目的是可以用json方式查看整个一条区块链结构。
        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println("\nThe Block Chain: ");
        System.out.println(blockChainJson);
    }
}
