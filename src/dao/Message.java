package dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Message {
	@Id
	@GeneratedValue
	long id;
	@Transient
	User sender;
	@Transient
	User reciever;
	@Temporal(TemporalType.TIMESTAMP)
	Date sendingTime;
	String message, fromTo;
	Class receiverType, senderType;
	int receiverId, senderId;
public Message() {
	super();
	this.sendingTime = new Date();
}



public Message(User sender, User receiver, String message) {
	super();
	this.sender = sender;
	this.reciever = receiver;
	this.message = message;
	this.sendingTime = new Date();
	this.fromTo = sender.getLastName()/*.substring(0,3)*/+receiver.getLastName()/*.substring(0, 3)*/;
	this.receiverId = receiver.id;
	this.senderId = sender.id;
	this.receiverType = receiver.getClass();
	this.senderType = sender.getClass();
}



public Class getReceiverType() {
	return receiverType;
}



public Class getSenderType() {
	return senderType;
}



public int getReceiverId() {
	return receiverId;
}



public int getSenderId() {
	return senderId;
}



public User getSender() {
	return sender;
}

public void setSender(Administrator sender) {
	this.sender = sender;
}

public User getReciever() {
	return reciever;
}

public void setReciever(Educator reciever) {
	this.reciever = reciever;
}

public Date getSendingTime() {
	return sendingTime;
}

public void setSendingTime(Date sendingTime) {
	this.sendingTime = sendingTime;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public long getId() {
	return id;
}


}
