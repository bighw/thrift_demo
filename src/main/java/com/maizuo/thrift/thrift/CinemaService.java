/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.maizuo.thrift.thrift;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CinemaService {

  public interface Iface {

    public List<Cinema> getCinemas(int cinemaId, int channelId) throws org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void getCinemas(int cinemaId, int channelId, org.apache.thrift.async.AsyncMethodCallback<AsyncClient.getCinemas_call> resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client implements org.apache.thrift.TServiceClient, Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      this(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot)
    {
      iprot_ = iprot;
      oprot_ = oprot;
    }

    protected org.apache.thrift.protocol.TProtocol iprot_;
    protected org.apache.thrift.protocol.TProtocol oprot_;

    protected int seqid_;

    public org.apache.thrift.protocol.TProtocol getInputProtocol()
    {
      return this.iprot_;
    }

    public org.apache.thrift.protocol.TProtocol getOutputProtocol()
    {
      return this.oprot_;
    }

    public List<Cinema> getCinemas(int cinemaId, int channelId) throws org.apache.thrift.TException
    {
      send_getCinemas(cinemaId, channelId);
      return recv_getCinemas();
    }

    public void send_getCinemas(int cinemaId, int channelId) throws org.apache.thrift.TException
    {
      oprot_.writeMessageBegin(new org.apache.thrift.protocol.TMessage("getCinemas", org.apache.thrift.protocol.TMessageType.CALL, ++seqid_));
      getCinemas_args args = new getCinemas_args();
      args.setCinemaId(cinemaId);
      args.setChannelId(channelId);
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public List<Cinema> recv_getCinemas() throws org.apache.thrift.TException
    {
      org.apache.thrift.protocol.TMessage msg = iprot_.readMessageBegin();
      if (msg.type == org.apache.thrift.protocol.TMessageType.EXCEPTION) {
        org.apache.thrift.TApplicationException x = org.apache.thrift.TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      if (msg.seqid != seqid_) {
        throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.BAD_SEQUENCE_ID, "getCinemas failed: out of sequence response");
      }
      getCinemas_result result = new getCinemas_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "getCinemas failed: unknown result");
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void getCinemas(int cinemaId, int channelId, org.apache.thrift.async.AsyncMethodCallback<getCinemas_call> resultHandler) throws org.apache.thrift.TException {
      checkReady();
      getCinemas_call method_call = new getCinemas_call(cinemaId, channelId, resultHandler, this, protocolFactory, transport);
      this.currentMethod = method_call;
      manager.call(method_call);
    }

    public static class getCinemas_call extends org.apache.thrift.async.TAsyncMethodCall {
      private int cinemaId;
      private int channelId;
      public getCinemas_call(int cinemaId, int channelId, org.apache.thrift.async.AsyncMethodCallback<getCinemas_call> resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.cinemaId = cinemaId;
        this.channelId = channelId;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("getCinemas", org.apache.thrift.protocol.TMessageType.CALL, 0));
        getCinemas_args args = new getCinemas_args();
        args.setCinemaId(cinemaId);
        args.setChannelId(channelId);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public List<Cinema> getResult() throws org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_getCinemas();
      }
    }

  }

  public static class Processor implements org.apache.thrift.TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(Iface iface)
    {
      iface_ = iface;
      processMap_.put("getCinemas", new getCinemas());
    }

    protected static interface ProcessFunction {
      public void process(int seqid, org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException;
    }

    private Iface iface_;
    protected final HashMap<String,ProcessFunction> processMap_ = new HashMap<String,ProcessFunction>();

    public boolean process(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException
    {
      org.apache.thrift.protocol.TMessage msg = iprot.readMessageBegin();
      ProcessFunction fn = processMap_.get(msg.name);
      if (fn == null) {
        org.apache.thrift.protocol.TProtocolUtil.skip(iprot, org.apache.thrift.protocol.TType.STRUCT);
        iprot.readMessageEnd();
        org.apache.thrift.TApplicationException x = new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.UNKNOWN_METHOD, "Invalid method name: '"+msg.name+"'");
        oprot.writeMessageBegin(new org.apache.thrift.protocol.TMessage(msg.name, org.apache.thrift.protocol.TMessageType.EXCEPTION, msg.seqid));
        x.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
        return true;
      }
      fn.process(msg.seqid, iprot, oprot);
      return true;
    }

    private class getCinemas implements ProcessFunction {
      public void process(int seqid, org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException
      {
        getCinemas_args args = new getCinemas_args();
        try {
          args.read(iprot);
        } catch (org.apache.thrift.protocol.TProtocolException e) {
          iprot.readMessageEnd();
          org.apache.thrift.TApplicationException x = new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.PROTOCOL_ERROR, e.getMessage());
          oprot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("getCinemas", org.apache.thrift.protocol.TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        iprot.readMessageEnd();
        getCinemas_result result = new getCinemas_result();
        result.success = iface_.getCinemas(args.cinemaId, args.channelId);
        oprot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("getCinemas", org.apache.thrift.protocol.TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

  }

  public static class getCinemas_args implements org.apache.thrift.TBase<getCinemas_args, getCinemas_args._Fields>, java.io.Serializable, Cloneable   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("getCinemas_args");

    private static final org.apache.thrift.protocol.TField CINEMA_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("cinemaId", org.apache.thrift.protocol.TType.I32, (short)1);
    private static final org.apache.thrift.protocol.TField CHANNEL_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("channelId", org.apache.thrift.protocol.TType.I32, (short)2);

    public int cinemaId;
    public int channelId;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      CINEMA_ID((short)1, "cinemaId"),
      CHANNEL_ID((short)2, "channelId");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // CINEMA_ID
            return CINEMA_ID;
          case 2: // CHANNEL_ID
            return CHANNEL_ID;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    private static final int __CINEMAID_ISSET_ID = 0;
    private static final int __CHANNELID_ISSET_ID = 1;
    private BitSet __isset_bit_vector = new BitSet(2);

    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.CINEMA_ID, new org.apache.thrift.meta_data.FieldMetaData("cinemaId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
      tmpMap.put(_Fields.CHANNEL_ID, new org.apache.thrift.meta_data.FieldMetaData("channelId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(getCinemas_args.class, metaDataMap);
    }

    public getCinemas_args() {
    }

    public getCinemas_args(
      int cinemaId,
      int channelId)
    {
      this();
      this.cinemaId = cinemaId;
      setCinemaIdIsSet(true);
      this.channelId = channelId;
      setChannelIdIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getCinemas_args(getCinemas_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      this.cinemaId = other.cinemaId;
      this.channelId = other.channelId;
    }

    public getCinemas_args deepCopy() {
      return new getCinemas_args(this);
    }

    @Override
    public void clear() {
      setCinemaIdIsSet(false);
      this.cinemaId = 0;
      setChannelIdIsSet(false);
      this.channelId = 0;
    }

    public int getCinemaId() {
      return this.cinemaId;
    }

    public getCinemas_args setCinemaId(int cinemaId) {
      this.cinemaId = cinemaId;
      setCinemaIdIsSet(true);
      return this;
    }

    public void unsetCinemaId() {
      __isset_bit_vector.clear(__CINEMAID_ISSET_ID);
    }

    /** Returns true if field cinemaId is set (has been assigned a value) and false otherwise */
    public boolean isSetCinemaId() {
      return __isset_bit_vector.get(__CINEMAID_ISSET_ID);
    }

    public void setCinemaIdIsSet(boolean value) {
      __isset_bit_vector.set(__CINEMAID_ISSET_ID, value);
    }

    public int getChannelId() {
      return this.channelId;
    }

    public getCinemas_args setChannelId(int channelId) {
      this.channelId = channelId;
      setChannelIdIsSet(true);
      return this;
    }

    public void unsetChannelId() {
      __isset_bit_vector.clear(__CHANNELID_ISSET_ID);
    }

    /** Returns true if field channelId is set (has been assigned a value) and false otherwise */
    public boolean isSetChannelId() {
      return __isset_bit_vector.get(__CHANNELID_ISSET_ID);
    }

    public void setChannelIdIsSet(boolean value) {
      __isset_bit_vector.set(__CHANNELID_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case CINEMA_ID:
        if (value == null) {
          unsetCinemaId();
        } else {
          setCinemaId((Integer)value);
        }
        break;

      case CHANNEL_ID:
        if (value == null) {
          unsetChannelId();
        } else {
          setChannelId((Integer)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case CINEMA_ID:
        return new Integer(getCinemaId());

      case CHANNEL_ID:
        return new Integer(getChannelId());

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case CINEMA_ID:
        return isSetCinemaId();
      case CHANNEL_ID:
        return isSetChannelId();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getCinemas_args)
        return this.equals((getCinemas_args)that);
      return false;
    }

    public boolean equals(getCinemas_args that) {
      if (that == null)
        return false;

      boolean this_present_cinemaId = true;
      boolean that_present_cinemaId = true;
      if (this_present_cinemaId || that_present_cinemaId) {
        if (!(this_present_cinemaId && that_present_cinemaId))
          return false;
        if (this.cinemaId != that.cinemaId)
          return false;
      }

      boolean this_present_channelId = true;
      boolean that_present_channelId = true;
      if (this_present_channelId || that_present_channelId) {
        if (!(this_present_channelId && that_present_channelId))
          return false;
        if (this.channelId != that.channelId)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return 0;
    }

    public int compareTo(getCinemas_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getCinemas_args typedOther = (getCinemas_args)other;

      lastComparison = Boolean.valueOf(isSetCinemaId()).compareTo(typedOther.isSetCinemaId());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetCinemaId()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.cinemaId, typedOther.cinemaId);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetChannelId()).compareTo(typedOther.isSetChannelId());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetChannelId()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.channelId, typedOther.channelId);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (field.id) {
          case 1: // CINEMA_ID
            if (field.type == org.apache.thrift.protocol.TType.I32) {
              this.cinemaId = iprot.readI32();
              setCinemaIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            }
            break;
          case 2: // CHANNEL_ID
            if (field.type == org.apache.thrift.protocol.TType.I32) {
              this.channelId = iprot.readI32();
              setChannelIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(CINEMA_ID_FIELD_DESC);
      oprot.writeI32(this.cinemaId);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(CHANNEL_ID_FIELD_DESC);
      oprot.writeI32(this.channelId);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getCinemas_args(");
      boolean first = true;

      sb.append("cinemaId:");
      sb.append(this.cinemaId);
      first = false;
      if (!first) sb.append(", ");
      sb.append("channelId:");
      sb.append(this.channelId);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
        __isset_bit_vector = new BitSet(1);
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

  }

  public static class getCinemas_result implements org.apache.thrift.TBase<getCinemas_result, getCinemas_result._Fields>, java.io.Serializable, Cloneable   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("getCinemas_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.LIST, (short)0);

    public List<Cinema> success;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 0: // SUCCESS
            return SUCCESS;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments

    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
              new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Cinema.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(getCinemas_result.class, metaDataMap);
    }

    public getCinemas_result() {
    }

    public getCinemas_result(
      List<Cinema> success)
    {
      this();
      this.success = success;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getCinemas_result(getCinemas_result other) {
      if (other.isSetSuccess()) {
        List<Cinema> __this__success = new ArrayList<Cinema>();
        for (Cinema other_element : other.success) {
          __this__success.add(new Cinema(other_element));
        }
        this.success = __this__success;
      }
    }

    public getCinemas_result deepCopy() {
      return new getCinemas_result(this);
    }

    @Override
    public void clear() {
      this.success = null;
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<Cinema> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(Cinema elem) {
      if (this.success == null) {
        this.success = new ArrayList<Cinema>();
      }
      this.success.add(elem);
    }

    public List<Cinema> getSuccess() {
      return this.success;
    }

    public getCinemas_result setSuccess(List<Cinema> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<Cinema>)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getCinemas_result)
        return this.equals((getCinemas_result)that);
      return false;
    }

    public boolean equals(getCinemas_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return 0;
    }

    public int compareTo(getCinemas_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      getCinemas_result typedOther = (getCinemas_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(typedOther.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, typedOther.success);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (field.id) {
          case 0: // SUCCESS
            if (field.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                this.success = new ArrayList<Cinema>(_list0.size);
                for (int _i1 = 0; _i1 < _list0.size; ++_i1)
                {
                  Cinema _elem2;
                  _elem2 = new Cinema();
                  _elem2.read(iprot);
                  this.success.add(_elem2);
                }
                iprot.readListEnd();
              }
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, this.success.size()));
          for (Cinema _iter3 : this.success)
          {
            _iter3.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getCinemas_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

  }

}