package com.example.alex.test_italki;

/**
 * Created by alex on 18-1-4.
 */


/**
 * Parameterized class to more nicely handle responses from the
 * backend. We don't need a container for each type of object
 *
 * @param <T>
 * @author joe@italki.com
 */
public class ItalkiResponse<T> {

    private T data;

    private String performance;


    private long serverTime;

    /**
     * Get request performance, in the form of "[float] ms"
     *
     * @return String
     */
    public String getPerformance() {
        return performance;
    }


    /**
     * Get data returned by the request
     *
     * @return T
     */
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    /**
     * Get server time (in milliseconds)
     * <p>
     *
     * @return long
     */
    public long getServerTime() {
        return serverTime;
    }


}
