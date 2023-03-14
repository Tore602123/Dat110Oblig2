package no.hvl.dat110.broker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	protected ConcurrentHashMap<String, ClientSession> clients;
	protected ConcurrentHashMap<String, Boolean> connected;
	protected ConcurrentHashMap<String, ArrayList<Message>> messageBuffer;

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
		
		connected = new ConcurrentHashMap<String, Boolean>();
		messageBuffer = new ConcurrentHashMap<String, ArrayList<Message>>();
		
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	public Set<String> getTopics() {

		return subscriptions.keySet();

	}

	public ClientSession getSession(String user) {

		ClientSession session = clients.get(user);
		return session;
	}

	public Set<String> getSubscribers(String topic) {

		return (subscriptions.get(topic));

	}

	public void addClientSession(String user, Connection connection) {
		
		ClientSession session = new ClientSession(user, connection);
		clients.put(user, session);
		connected.put(user, true);
		messageBuffer.put(user, new ArrayList<Message>());

	}

	public void removeClientSession(String user) {
		
		clients.remove(user);

	}

	public void createTopic(String topic) {
		
		subscriptions.put(topic, new HashSet<>());
	}

	public void deleteTopic(String topic) {
		
		subscriptions.remove(topic);

	}

	public void addSubscriber(String user, String topic) {
		
		if (subscriptions.containsKey(topic)) {
			subscriptions.get(topic).add(user);
		}

	}

	public void removeSubscriber(String user, String topic) {
		
		if (subscriptions.containsKey(topic)) {
			subscriptions.get(topic).remove(user);
		}
	}

	public void disconnectUser(String user) {
		connected.put(user, false);
		clients.get(user).disconnect();
	}

	public boolean isConnected(String user) {
		return connected.get(user);
	}

	public void reconnectUser(String user, Connection connection) {
		connected.put(user, true);
		clients.put(user, new ClientSession(user, connection));
	}

	public void addToMessageBuffer(String user, Message msg) {
		messageBuffer.get(user).add(msg);
	}

	public ArrayList<Message> getMessageBuffer(String user) {
		return messageBuffer.get(user);
	}

	public void emptyMessageBuffer(String user) {
		messageBuffer.get(user).clear();
	}
	

}