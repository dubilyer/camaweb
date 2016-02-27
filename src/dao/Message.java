package dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Message {
	@JoinColumn(name = "id")
	@OneToOne(fetch = FetchType.LAZY)
	Child child;
	@Id
	@GeneratedValue
	long id;
	String message, fromTo;
	int receiverId, senderId;
	Class receiverType, senderType;
	@Transient
	User reciever;

	@Transient
	User sender;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date sendingTime;

	public Message() {
		super();
		this.sendingTime = new Date();
	}
	public Message(User sender, User receiver, String message, Child child) {
		super();
		this.sender = sender;
		this.reciever = receiver;
		this.message = message;
		this.sendingTime = new Date();
		this.fromTo = sender.getLastName()/* .substring(0,3) */ + receiver
				.getLastName()/* .substring(0, 3) */;
		this.receiverId = receiver.id;
		this.senderId = sender.id;
		this.receiverType = receiver.getClass();
		this.senderType = sender.getClass();
	}

	public Child getChild() {
		return child;
	}

	public String getFromTo() {
		return fromTo;
	}

	public long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public Class getReceiverType() {
		return receiverType;
	}

	public User getReciever() {
		return reciever;
	}

	public User getSender() {
		return sender;
	}

	public int getSenderId() {
		return senderId;
	}

	public Class getSenderType() {
		return senderType;
	}

	public Date getSendingTime() {
		return sendingTime;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setReciever(Educator reciever) {
		this.reciever = reciever;
	}

	public void setSender(Administrator sender) {
		this.sender = sender;
	}

	public void setSendingTime(Date sendingTime) {
		this.sendingTime = sendingTime;
	}

}
