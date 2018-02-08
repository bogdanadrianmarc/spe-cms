package com.spe.cms.repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DBInit {

    public Properties getProps()
    {
        //repo
        Properties serverProps = new Properties();
        try {
            serverProps.load(new FileReader("bd.config"));
            //System.setProperties(serverProps);

            System.out.println("Properties set. ");
            //System.getProperties().list(System.out);
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find bd.config " + e);
        }
        return serverProps;
    }

}
