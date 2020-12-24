package com.AI;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class FactoryAgent extends Agent {

    Product[] products = {
            new Product("book", 14.5, 120),
            new Product("phone", 850, 50),
            new Product("pen", 2.5, 600)
    };


    @Override
    protected void setup() {
        System.out.println("Factory is Running ...");

        requestProductList();
        sendProductList();
        responseToMoneyState();
    }

    void sendProductList() {

        String msg = "";
        for (Product product : this.products) {
            msg += product.productName + ":";
        }
        ACLMessage acceptance = new ACLMessage(ACLMessage.REQUEST);

        acceptance.addReceiver(new AID("ClientAgent", AID.ISLOCALNAME));

        acceptance.setContent(msg);

        send(acceptance);
    }

    void requestProductList() {
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                ACLMessage clientRequest = receive();

                if (clientRequest != null) {

                    AID clientID = clientRequest.getSender();
                    String localID = clientID.getLocalName();
                    System.out.println("new order from: " + localID);
                    System.out.println(clientRequest.getContent());

                }
            }
        });


    }

    public void responseToMoneyState() {

        ACLMessage stateMsg = receive();


        if (stateMsg != null) {

            AID clientID = stateMsg.getSender();
            String localID = clientID.getLocalName();
            System.out.println("state of: " + localID);
            System.out.println(stateMsg.getContent());

        }
    }


}
