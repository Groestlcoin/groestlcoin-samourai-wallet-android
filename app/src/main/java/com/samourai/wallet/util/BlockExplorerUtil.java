package com.samourai.wallet.util;

import com.samourai.wallet.SamouraiWallet;

public class BlockExplorerUtil {

    private static CharSequence[] blockExplorers = { "Chainz", "Groestlsight"};
    private static CharSequence[] blockExplorerTxUrls = { "https://chainz.cryptoid.info/grs/tx.dws?", "https://groestlsight.groestlcoin.org/tx/"};
    private static CharSequence[] blockExplorerAddressUrls = { "https://chainz.cryptoid.info/grs/block.dws?", "https://groestlsight.groestlcoin.org/block/" };

    private static CharSequence[] tBlockExplorers = { "Chainz", "Groestlsight"};
    private static CharSequence[] tBlockExplorerTxUrls = { "https://chainz.cryptoid.info/grs-test/tx.dws?", "https://groestlsight-test.groestlcoin.org/tx/" };
    private static CharSequence[] tBlockExplorerAddressUrls = { "https://chainz.cryptoid.info/grs-test/block.dws?", "https://groestlsight-test.groestlcoin.org/block/" };

    private static BlockExplorerUtil instance = null;

    private BlockExplorerUtil() { ; }

    public static BlockExplorerUtil getInstance() {

        if(instance == null) {
            instance = new BlockExplorerUtil();
        }

        return instance;
    }

    public CharSequence[] getBlockExplorers() {

        if(SamouraiWallet.getInstance().isTestNet())    {
            return tBlockExplorers;
        }
        else    {
            return blockExplorers;
        }

    }

    public CharSequence[] getBlockExplorerTxUrls() {

        if(SamouraiWallet.getInstance().isTestNet())    {
            return tBlockExplorerTxUrls;
        }
        else    {
            return blockExplorerTxUrls;
        }

    }

    public CharSequence[] getBlockExplorerAddressUrls() {

        if(SamouraiWallet.getInstance().isTestNet())    {
            return tBlockExplorerAddressUrls;
        }
        else    {
            return blockExplorerAddressUrls;
        }

    }

}
