package com.samourai.wallet.util;

import com.samourai.wallet.SamouraiWallet;

public class BlockExplorerUtil {

    private static CharSequence[] blockExplorers = { "Chainz", "Groestlsight", "Blockbook"};
    private static CharSequence[] blockExplorerTxUrls = { "https://chainz.cryptoid.info/grs/tx.dws?", "https://groestlsight.groestlcoin.org/tx/", "https://blockbook.groestlcoin.org/tx/"};
    private static CharSequence[] blockExplorerAddressUrls = { "https://chainz.cryptoid.info/grs/address.dws?", "https://groestlsight.groestlcoin.org/address/", "https://blockbook.groestlcoin.org/address/" };

    private static CharSequence[] tBlockExplorers = { "Chainz", "Groestlsight", "Blockbook"};
    private static CharSequence[] tBlockExplorerTxUrls = { "https://chainz.cryptoid.info/grs-test/tx.dws?", "https://groestlsight-test.groestlcoin.org/tx/", "https://blockbook-test.groestlcoin.org/tx/" };
    private static CharSequence[] tBlockExplorerAddressUrls = { "https://chainz.cryptoid.info/grs-test/address.dws?", "https://groestlsight-test.groestlcoin.org/address/", "https://blockbook-test.groestlcoin.org/address/" };

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
