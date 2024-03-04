package jpa.services.impl;

import jpa.models.Message;

public class MessageDaoImpl extends GeneriqueDaoImpl<Message, String>{
    public MessageDaoImpl (){
        super();
        this.className = Message.class ;
    }
}
