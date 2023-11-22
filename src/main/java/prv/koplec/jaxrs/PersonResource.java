package prv.koplec.jaxrs;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import prv.koplec.models.Person;

@Path("persons")
public class PersonResource {
    private static Map<Integer, Person> personDB = new ConcurrentHashMap<>();
    static {
        personDB.put(1, new Person(1, 25, "田中"));
        personDB.put(2, new Person(2, 30, "鈴木"));
        personDB.put(3, new Person(3, 45, "佐藤"));
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getById(@PathParam("id") int id){
        return personDB.get(id);
    }
}
