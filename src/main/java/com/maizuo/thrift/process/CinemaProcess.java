package com.maizuo.thrift.process;

import com.maizuo.thrift.thrift.Cinema;
import com.maizuo.thrift.thrift.CinemaService;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/7/15.
 */
public class CinemaProcess implements CinemaService.Iface {
    @Override
    public List<Cinema> getCinemas(int cinemaId, int channelId) throws TException {
        List<Cinema> cinemas = new ArrayList<Cinema>();
        List<Boolean> booList = new ArrayList<Boolean>();
        booList.add(true);
        booList.add(false);
        for (int i = 0; i <= 10; i++) {
            Cinema cinema = new Cinema();
            cinema.setCinemaId(i + 10000);
            cinema.setCinemaName("中影夏日国际" + i);
            cinema.setIsUse(booList.get(new Random().nextInt(1)));
            cinema.setHallCount(i + 2);
            cinemas.add(cinema);
        }
        return cinemas;
    }
}
