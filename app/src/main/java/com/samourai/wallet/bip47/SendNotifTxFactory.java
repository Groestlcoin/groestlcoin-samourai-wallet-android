package com.samourai.wallet.bip47;

import com.samourai.wallet.SamouraiWallet;

import java.math.BigInteger;

public class SendNotifTxFactory	{

    public static final BigInteger _bNotifTxValue = SamouraiWallet.bDust;
    public static final BigInteger _bSWFee = SamouraiWallet.bFee;
//    public static final BigInteger _bSWCeilingFee = BigInteger.valueOf(50000L);

    public static final String SAMOURAI_NOTIF_TX_FEE_ADDRESS = "3K8eqP6j14JzPnmRMG6exTsNo8iUZHEH5e";  //GRS
    public static final String TESTNET_SAMOURAI_NOTIF_TX_FEE_ADDRESS = "tgrs1qsetg4dslqsytuj7xj7k6h7lnkp52qmpy0rgyra";

//    public static final double _dSWFeeUSD = 0.5;

    private SendNotifTxFactory () { ; }

}
