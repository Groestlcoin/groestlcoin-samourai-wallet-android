package com.samourai.wallet.util;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

//import android.util.Log;

public class ExchangeRateFactory	{

    private static Context context = null;

    private static String strDataLBC = null;
    private static String strDataBTCe = null;
    private static String strDataBFX = null;
    private static String strDataBTCAvg = null;
    private static String strPoloniex = null;
    private static String strBittrex = null;

    private static HashMap<String,Double> fxRatesLBC = null;
    private static HashMap<String,Double> fxRatesBTCe = null;
    private static HashMap<String,Double> fxRatesBFX = null;
    private static HashMap<String,Double> fxRatesBTCAvg = null;
    private static HashMap<String,Double> fxPoloniex = null;
    private static HashMap<String,Double> fxBittrex = null;
//    private static HashMap<String,String> fxSymbols = null;

    private static ExchangeRateFactory instance = null;

    private static String[] currencies = {
            "CNY", "EUR", "GBP", "RUB", "USD"
    };

    private static String[] currencyLabels = {
            "United States Dollar - USD",
            "Euro - EUR",
            "British Pound Sterling - GBP",
            "Chinese Yuan - CNY",
            "Russian Rouble - RUB"
    };

    private static String[] currencyLabelsBTCe = {
            "United States Dollar - USD",
            "Euro - EUR",
            "Russian Rouble - RUR"
    };

    /*private static String[] exchangeLabels = {
            "LocalBitcoins.com",
            "BTC-e",
            "Bitfinex",
            "Bitcoin Average"
    };*/
    private static String[] exchangeLabels = {
            //"Poloniex",
            "Bittrex",
    };

    private ExchangeRateFactory()	 { ; }

    public static ExchangeRateFactory getInstance(Context ctx)	 {

        context = ctx;

        if(instance == null)	 {
            fxRatesLBC = new HashMap<String,Double>();
            fxRatesBTCe = new HashMap<String,Double>();
            fxRatesBFX = new HashMap<String,Double>();
            fxRatesBTCAvg = new HashMap<String,Double>();
            fxPoloniex = new HashMap<String,Double>();
            fxBittrex = new HashMap<String,Double>();
//            fxSymbols = new HashMap<String,String>();

            instance = new ExchangeRateFactory();
        }

        return instance;
    }

    public double getAvgPrice(String currency)	 {
        // int fxSel = PrefsUtil.getInstance(context).getValue(PrefsUtil.CURRENT_EXCHANGE_SEL, 0);
        HashMap<String,Double> fxRates = null;
        if(!fxRatesBTCe.isEmpty() && fxRatesBTCe.containsKey(currency) && fxRatesBTCe.get(currency) > 0.0)	 {
            fxRates = fxRatesBTCe;
        }
        else if(!fxRatesBFX.isEmpty() && fxRatesBFX.containsKey(currency) && fxRatesBFX.get(currency) > 0.0)	 {
            fxRates = fxRatesBFX;
        }
        else if(!fxRatesLBC.isEmpty() && fxRatesLBC.containsKey(currency) && fxRatesLBC.get(currency) > 0.0)	 {
            fxRates = fxRatesLBC;
        }

        double GRS_price = getAvgGRSPrice("BTC");

        if(GRS_price > 0.0 && fxRates.get(currency) != null && fxRates.get(currency) > 0.0)	 {
            PrefsUtil.getInstance(context).setValue("CANNED_" + currency, Double.toString(fxRates.get(currency)*GRS_price));
            return fxRates.get(currency)*GRS_price;
        }
        else	 {
            return Double.parseDouble(PrefsUtil.getInstance(context).getValue("CANNED_" + currency, "0.0"));
        }
    }

    public double getAvgGRSPrice(String currency)	 {
        int fxSel = PrefsUtil.getInstance(context).getValue(PrefsUtil.CURRENT_EXCHANGE_SEL, 0);
        HashMap<String,Double> fxRates = null;
        // if(fxSel == 0)	 {
        //     fxRates = fxPoloniex;
        // }
        //else	 {
        fxRates = fxBittrex;
        //}

        if(fxRates.get(currency) != null && fxRates.get(currency) > 0.0)	 {
            PrefsUtil.getInstance(context).setValue("CANNED_" + currency, Double.toString(fxRates.get(currency)));
            return fxRates.get(currency);
        }
        else	 {
            return Double.parseDouble(PrefsUtil.getInstance(context).getValue("CANNED_" + currency, "0.0"));
        }
    }

    public String[] getCurrencies()	 {
        return currencies;
    }

    public String[] getCurrencyLabels()	 {
        return currencyLabels;
    }

    public String[] getCurrencyLabelsBTCe()	 {
        return currencyLabelsBTCe;
    }

    public String[] getExchangeLabels()	 {
        return exchangeLabels;
    }

    public void setDataLBC(String data)	 {
        strDataLBC = data;
    }

    public void setDataBTCe(String data)	 {
        strDataBTCe = data;
    }

    public void setDataBFX(String data)	 {
        strDataBFX = data;
    }

    public void setDataAVG(String data)	 {
        strDataBTCAvg = data;
    }

    public void parseLBC()	 {
        for(int i = 0; i < currencies.length; i++)	 {
            getLBC(currencies[i]);
        }
    }

    public void parseBTCe()	 {
        for(int i = 0; i < currencies.length; i++)	 {
            if(currencies[i].equals("GBP") || currencies[i].equals("CNY"))	 {
                continue;
            }
            if(currencies[i].equals("RUB"))	 {
                getBTCe("RUR");
            }
            else	 {
                getBTCe(currencies[i]);
            }
        }
    }

    public void parseBFX()	 {
        for(int i = 0; i < currencies.length; i++)	 {
            getBFX("USD");
        }
    }

    public void parseAVG()	 {
        for(int i = 0; i < currencies.length; i++)	 {
            getAVG(currencies[i]);
        }
    }

    private void getLBC(String currency)	 {
        try {
            JSONObject jsonObject = new JSONObject(strDataLBC);
            if(jsonObject != null)	{
                JSONObject jsonCurr = jsonObject.getJSONObject(currency);
                if(jsonCurr != null)	{
                    double avg_price = 0.0;
                    if(jsonCurr.has("avg_12h"))	{
                        avg_price = jsonCurr.getDouble("avg_12h");
                    }
                    else if(jsonCurr.has("avg_24h"))	{
                        avg_price = jsonCurr.getDouble("avg_24h");
                    }
                    fxRatesLBC.put(currency, Double.valueOf(avg_price));
//                    Log.i("ExchangeRateFactory", "LBC:" + currency + " " + Double.valueOf(avg_price));
                }
            }
        } catch (JSONException je) {
            fxRatesLBC.put(currency, Double.valueOf(-1.0));
//            fxSymbols.put(currency, null);
        }
    }

    private void getBTCe(String currency)	 {
        try {
            JSONObject jsonObject = new JSONObject(strDataBTCe);
            if(jsonObject != null)	{
                JSONObject jsonCurr = jsonObject.getJSONObject("btc_" + currency.toLowerCase());
                if(jsonCurr != null)	{
                    double avg_price = 0.0;
                    if(jsonCurr.has("avg"))	{
                        avg_price = jsonCurr.getDouble("avg");
                    }
                    if(currency.equals("RUR"))	{
                        fxRatesBTCe.put("RUB", Double.valueOf(avg_price));
                    }
                    fxRatesBTCe.put(currency, Double.valueOf(avg_price));
//                    Log.i("ExchangeRateFactory", "BTCe:" + currency + " " + Double.valueOf(avg_price));
                }
            }
        } catch (JSONException je) {
            fxRatesBTCe.put(currency, Double.valueOf(-1.0));
//            fxSymbols.put(currency, null);
        }
    }

    private void getBFX(String currency)	 {
        try {
            JSONObject jsonObject = new JSONObject(strDataBFX);
            if(jsonObject != null)	{
                double avg_price = 0.0;
                if(jsonObject.has("last_price"))	{
                    avg_price = jsonObject.getDouble("last_price");
                }
                fxRatesBFX.put(currency, Double.valueOf(avg_price));
//                Log.i("ExchangeRateFactory", "BFX:" + currency + " " + Double.valueOf(avg_price));
            }
        } catch (JSONException je) {
            fxRatesBFX.put(currency, Double.valueOf(-1.0));
//            fxSymbols.put(currency, null);
        }
    }

    private void getAVG(String currency)	 {
        try {
            JSONObject jsonObject = new JSONObject(strDataBTCAvg);
            if(jsonObject != null)	{
                JSONObject jsonCurr = jsonObject.getJSONObject(currency);
                if(jsonCurr != null)	{
                    double last_price = 0.0;
                    if(jsonCurr.has("last"))	{
                        last_price = jsonCurr.getDouble("last");
                    }
                    fxRatesBTCAvg.put(currency, Double.valueOf(last_price));
//                    Log.i("ExchangeRateFactory", "LBC:" + currency + " " + Double.valueOf(avg_price));
                }
            }
        } catch (JSONException je) {
            fxRatesBTCAvg.put(currency, Double.valueOf(-1.0));
//            fxSymbols.put(currency, null);
        }
    }
    public void setDataPoloniex(String str)
    {  strPoloniex = str; }

    public void setDataBittrex(String str)
    {  strBittrex = str; }

    public void parsePoloniex()	 {
        getPoloniex();

    }

    public void parseBittrex()	 {
        getBittrex();

    }

    private void getPoloniex()	 {
        try {
            JSONArray recenttrades = new JSONArray(strPoloniex);

            double btcTraded = 0.0;
            double coinTraded = 0.0;

            for(int i = 0; i < recenttrades.length(); ++i)
            {
                JSONObject trade = (JSONObject)recenttrades.get(i);

                btcTraded += trade.getDouble("total");
                coinTraded += trade.getDouble("amount");

            }

            Double averageTrade = btcTraded / coinTraded;

            fxPoloniex.put("BTC", Double.valueOf(averageTrade));
//                Log.i("ExchangeRateFactory", "BFX:" + currency + " " + Double.valueOf(avg_price));


        } catch (JSONException je) {
            fxPoloniex.put("BTC", Double.valueOf(-1.0));
//            fxSymbols.put(currency, null);
        }
    }

    private void getBittrex()	 {
        try {
            JSONObject jsonObject = new JSONObject(strBittrex);
            JSONArray recenttrades = jsonObject.getJSONArray("result");

            double btcTraded = 0.0;
            double coinTraded = 0.0;

            for(int i = 0; i < recenttrades.length(); ++i)
            {
                JSONObject trade = (JSONObject)recenttrades.get(i);

                btcTraded += trade.getDouble("Total");
                coinTraded += trade.getDouble("Quantity");

            }

            Double averageTrade = btcTraded / coinTraded;

            fxBittrex.put("BTC", Double.valueOf(averageTrade));
//                Log.i("ExchangeRateFactory", "BFX:" + currency + " " + Double.valueOf(avg_price));


        } catch (JSONException je) {
            fxRatesBFX.put("BTC", Double.valueOf(-1.0));
//            fxSymbols.put(currency, null);
        }
    }

}
