package com.maizuo.thrift.test;

import com.maizuo.thrift.client.CinemaClient;

/**
 * Created by Administrator on 2016/7/18.
 */
public class CinemaClientTest {
    public static void main(String[] args) {
        CinemaClient client = new CinemaClient();
        System.out.println(client.getCinemas(1,1));
        

    }
}
