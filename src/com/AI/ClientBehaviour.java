package com.AI;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ClientBehaviour extends CyclicBehaviour {

    double money = 150000;

    @Override
    public void action() {


        Agent agent = getAgent();

        ACLMessage msg = agent.receive();

        if (msg != null) {
            if (msg.getContent().equals("you are good") || msg.getContent().equals("send only two products")) {
                System.out.println(msg.getContent());

            } else if (msg.getContent().substring(0, 22).equals("Available Products => ")) {
                AID clientID = msg.getSender();
                String localID = clientID.getLocalName();
                System.out.println(localID);
                System.out.println(msg.getContent());
                moneyState();

            }
        }
    }

    public void moneyState() {


        String state = "";

        double total = 25000;

        if (total > money)
            state = "bad";
        else
            state = "good";

        ACLMessage moneyState = new ACLMessage(ACLMessage.REQUEST);

        moneyState.addReceiver(new AID("FactoryAgent", AID.ISLOCALNAME));

        moneyState.setContent(state);

        getAgent().send(moneyState);
    }

}
