package services;

import java.util.ArrayList;
import java.util.List;

import domain.Actor;


public class ActorService {
    private static List<Actor> db =  new ArrayList<Actor>();

    private static int currentId = 0;

    public List<Actor> getAll() {

        return db;
    }

    public Actor get (int id){

        for (Actor a: db){
            if(a.getId()==id)
                return a;
        }
        return null;
    }

    public void add (Actor a){

        a.setId(++currentId);
        db.add(a);
    }

    public void update (Actor actor){

        for (Actor a: db){

            if (a.getId() == actor.getId()){

                a.setSurname(actor.getSurname());
                a.setName(actor.getName());
            }
        }
    }

    public void delete (Actor a){

        db.remove(a);
    }
}
