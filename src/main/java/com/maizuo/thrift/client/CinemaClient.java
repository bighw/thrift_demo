package com.maizuo.thrift.client;

import com.maizuo.thirft.pool.ThriftPoolProxy;
import com.maizuo.thrift.thrift.Cinema;
import com.maizuo.thrift.thrift.CinemaService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;

import java.util.List;

/**
 * Created by Administrator on 2016/7/16.
 */
public class CinemaClient {
    public CinemaService.Client getClient (TTransport transport){
        try {
            TProtocol protocol = new TBinaryProtocol(transport);
            CinemaService.Client client = new CinemaService.Client(protocol);
            if(!transport.isOpen()){
                transport.open();
            }
            return client;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    public void closeTransport(TTransport transport){
        if(transport != null){
            ThriftPoolProxy.returnThriftCon("CinemaSocketPool", transport);
        }
    }

    public List<Cinema> getCinemas(int cinemaId , int channelId) {
        TTransport socket = ThriftPoolProxy.getThriftCon("CinemaSocketPool");
        if (socket == null) {
            return null;
        }
        CinemaService.Client client = this.getClient(socket);
        try {
            return client.getCinemas(cinemaId, channelId);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                this.closeTransport(socket);
            }
        }
        return null;
    }
}
