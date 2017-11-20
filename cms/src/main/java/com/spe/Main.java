package com.spe;

import com.spe.domain.Project;
import com.spe.repository.ProjectDBRepo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {


    public static void main(String[] args) {

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
        ProjectDBRepo repo = new ProjectDBRepo(serverProps);

        //tests
        repo.save(new Project(3,"New York","15/03/2017","17:00",50, "", ""));
        repo.save(new Project(4,"San Francisco","18/03/2017","11:00",73, "", ""));
        if (repo.findOne(3).getTags().equals("New York"))
            System.out.println("Id-ul 1 a fost gasit, destinatia New York!");
//        repo.delete(1);
        if (repo.findOne(4).getTags().equals("San Francisco"))
            System.out.println("Id-ul 2 a fost gasit, destinatia San Francisco!");
//        repo.update(2,new Flight(2,"Los Angeles","18/03/2017","11:00","Cluj-Napoca International",73));
//        if (repo.findOne(2).getDestination().equals("Los Angeles"))
//            System.out.println("Id-ul 2 a fost gasit, destinatia e Los Angeles acum!");
    }
}
