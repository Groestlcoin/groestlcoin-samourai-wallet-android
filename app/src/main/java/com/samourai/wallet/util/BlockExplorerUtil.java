package com.samourai.wallet.util;

import com.samourai.wallet.SamouraiWallet;

public class BlockExplorerUtil {

    private static CharSequence[] blockExplorers = { "Chainz", "Groestlsight"};
    private static CharSequence[] blockExplorerTxUrls = { "https://chainz.cryptoid.info/grs/tx.dws?", "https://groestlsight.groestlcoin.org/tx/"};
    private static CharSequence[] blockExplorerAddressUrls = { "https://www.smartbit.com.au/address/", "http://srv1.yogh.io/#addr:id:", "https://live.blockcypher.com/btc/address/", "https://live.blockcypher.com/btc/address/" };

    private static CharSequence[] tBlockExplorers = { "Chainz", "Groestlsight"};
    private static CharSequence[] tBlockExplorerTxUrls = { "https://testnet.smartbit.com.au/tx/", "https://live.blockcypher.com/btc-testnet/tx/" };
    private static CharSequence[] tBlockExplorerAddressUrls = { "https://testnet.smartbit.com.au/address/", "https://live.blockcypher.com/btc-testnet/address/" };

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
