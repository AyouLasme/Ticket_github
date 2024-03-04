package jpa;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import jpa.controlleurs.*;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class TestApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {

        final Set<Class<?>> clazzes = new HashSet<Class<?>>();

        //clazzes.add(OpenApiResource.class);
        clazzes.add(TicketControlleur.class);
        clazzes.add(TagControlleur.class);
        clazzes.add(MessageControlleur.class);
        clazzes.add(UtilisateurControlleur.class);
        clazzes.add(AffectationControlleur.class);
//      clazzes.add(AcceptHeaderOpenApiResource.class);

        return clazzes;
    }
}
