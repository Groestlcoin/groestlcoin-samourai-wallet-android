package com.samourai.wallet.util;

import android.content.Context;

public class PushTx {

    private static PushTx instance = null;
    private static Context context = null;

    private PushTx() { ; }

    public static PushTx getInstance(Context ctx) {

        context = ctx;

        if(instance == null) {
            instance = new PushTx();
        }

        return instance;
    }

    public String chainSo(String hexString) {

        try {
            String response = WebUtil.getInstance(null).postURL(WebUtil.CHAINSO_PUSHTX_URL, "tx_hex=" + hexString);
//        Log.i("Send response", response);
            return response;
        }
        catch(Exception e) {
            return null;
        }

    }

    public String blockchain(String hexString) {

        try {
            String response = WebUtil.getInstance(null).postURL(WebUtil.BLOCKCHAIN_DOMAIN + "pushtx", "tx=" + hexString);
//        Log.i("Send response", response);
            return response;
        }
        catch(Exception e) {
            return null;
        }

    }

    public String samourai(String hexString) {

        try {
            String response = WebUtil.getInstance(null).postURL(WebUtil.SAMOURAI_API + "pushtx", "tx=" + hexString);
//        Log.i("Send response", response);
            return response;
        }
        catch(Exception e) {
            return null;
        }

    }

    public String chainz(String hexString) {

        try {
            String response = WebUtil.getInstance(null).postURL("text/plain", WebUtil.BLOCKCHAIN_DOMAIN_API + "pushtx", hexString);
//        Log.i("Send response", response);
            return response;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean chainz_valid(String result)
    {
        if(result.length() > 67 && result.charAt(66) == '\n')
            return true;
        else return false;
    }
    public String groestlsight(String hexString) {

        try {
            String response = WebUtil.getInstance(null).postURL(WebUtil.GROESTLSIGHT_SEND_URL, "rawtx="+hexString);
//        Log.i("Send response", response);
            return response;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
