package com.AI;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Main {

    public static void main(String[] args) {

        jade.core.Runtime r = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl("localhost", 5000,"aa");
        ContainerController container = r.createMainContainer(profile);

        AgentController Agent1;
        AgentController Agent2;

        Order[] orders =  {
                new Order("jacket",12.5, 100),
                new Order("phone",850, 12)
        };


        try {
            Agent2 = container.createNewAgent("ClientAgent", "com.AI.ClientAgent",orders );

            Agent2.start();

        }catch (StaleProxyException e){
            System.out.println(e);
        }

        try {
            Agent1 = container.createNewAgent("FactoryAgent", "com.AI.FactoryAgent", null);

            Agent1.start();

        }catch (StaleProxyException e){
            System.out.println(e);
        }


    }
}
