/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecorau.vbn;

import io.netty.channel.sctp.SctpMessage;
import lombok.Getter;
import lombok.Setter;
import org.restcomm.protocols.ss7.cap.api.CAPMessage;
import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.map.api.MAPMessage;
import org.restcomm.protocols.ss7.sccp.message.SccpMessage;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;

@Getter
@Setter
public class RequestContex implements Cloneable{
    
    private SctpMessage sctpMessage;
    private M3UAMessage m3uaMessage;
    private SccpMessage sccpMessage;
    private Object tcapMessage;
    private CAPMessage capMessage;
    private MAPMessage mapMessage;
    private Component[] components;
    private Invoke invoke;

    public RequestContex(){
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
