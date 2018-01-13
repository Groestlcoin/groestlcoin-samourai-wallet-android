package com.samourai.wallet.util;

public class BlockExplorerUtil {

    private static CharSequence[] blockExplorers = { "Chainz", "Groestlsight"};
    private static CharSequence[] blockExplorerUrls = { "https://chainz.cryptoid.info/grs/tx.dws?", "https://groestlsight.groestlcoin.org/tx//"};

    public static final int CHAINZ = 0;
    public static final int GROESTLSIGHT = 1;


    private static BlockExplorerUtil instance = null;

    private BlockExplorerUtil() { ; }

    public static BlockExplorerUtil getInstance() {

        if(instance == null) {
            instance = new BlockExplorerUtil();
        }

        return instance;
    }

    public CharSequence[] getBlockExplorers() {
        return blockExplorers;
    }

    public CharSequence[] getBlockExplorerUrls() {
        return blockExplorerUrls;
    }

}
