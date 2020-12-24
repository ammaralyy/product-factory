package com.AI;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ClientAgent extends Agent {

    double money = 150000;
    int clientID = 0;

    @Override
    protected void setup() {

        System.out.println("Client one Sending ...");

        firstRequest();
        receiveProductList();
        moneyState();

    }


    void firstRequest(){
        ACLMessage clientOrder = new ACLMessage(ACLMessage.REQUEST);

        clientOrder.addReceiver(new AID("FactoryAgent", AID.ISLOCALNAME));

        clientOrder.setContent("Request Products");

        send(clientOrder);
    }

    public void moneyState() {

        Order[] orders = (Order[]) getArguments();

        String state = "";
        int productCount = orders[clientID].singleProductCount;
        double productPrice = orders[clientID].singleProductPrice;

        double total = productCount * productPrice;

        if (total > money)
            state = "bad";

        ACLMessage moneyState = new ACLMessage(ACLMessage.REQUEST);

        moneyState.addReceiver(new AID("FactoryAgent", AID.ISLOCALNAME));

        moneyState.setContent(state);

        send(moneyState);
    }

    void receiveProductList() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    AID clientID = msg.getSender();
                    String localID = clientID.getLocalName();
                    System.out.println("new msg from: " + localID);
                    System.out.println("Available Products => " + msg.getContent());
                }
            }

        });
    }

}
