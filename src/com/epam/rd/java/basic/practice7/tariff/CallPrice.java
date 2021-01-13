package com.epam.rd.java.basic.practice7.tariff;

public class CallPrice implements Comparable<CallPrice> {
    private double withinNetworkCallPrice;
    private double outOfNetworkCallPrice;
    private double landLineNumCallPrice;

    public CallPrice(double networkCall, double outNetworkCall, double landLineCall) {
        withinNetworkCallPrice = networkCall;
        outOfNetworkCallPrice = outNetworkCall;
        this.landLineNumCallPrice = landLineCall;
    }

    public CallPrice() {
    }

    public void setLandLineNumCallPrice(double landLineNumCallPrice) {
        this.landLineNumCallPrice = landLineNumCallPrice;
    }

    public void setOutOfNetworkCallPrice(double outOfNetworkCallPrice) {
        this.outOfNetworkCallPrice = outOfNetworkCallPrice;
    }

    public void setWithinNetworkCallPrice(double withinNetworkCallPrice) {
        this.withinNetworkCallPrice = withinNetworkCallPrice;
    }

    public double getWithinNetworkCallPrice() {
        return withinNetworkCallPrice;
    }

    public double getOutOfNetworkCallPrice() {
        return outOfNetworkCallPrice;
    }

    public double getLandLineNumCallPrice() {
        return landLineNumCallPrice;
    }

    @Override
    public String toString() {
        return "Call prices:\n\t" + "Within network calls: " + withinNetworkCallPrice + "\n\tOut of network calls: "
                + outOfNetworkCallPrice + "\n\tLandline calls: " + landLineNumCallPrice;
    }

    @Override
    public int compareTo(CallPrice o) {
        int result = Double.compare(withinNetworkCallPrice, o.withinNetworkCallPrice);
        if (result != 0) {
            return result;
        }
        result = Double.compare(outOfNetworkCallPrice, o.outOfNetworkCallPrice);
        if (result != 0) {
            return result;
        }
        result = Double.compare(landLineNumCallPrice, o.landLineNumCallPrice);
        if (result != 0) {
            return result;
        }
        return 0;
    }
}
