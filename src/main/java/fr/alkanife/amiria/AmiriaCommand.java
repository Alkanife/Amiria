package fr.alkanife.amiria;

import java.lang.reflect.Method;

public class AmiriaCommand {

    private final String name, desription;
    private final boolean admin;
    private final Object object;
    private final Method method;

    public AmiriaCommand(String name, String desription, boolean admin, Object object, Method method) {
        super();
        this.name = name;
        this.desription = desription;
        this.admin = admin;
        this.object = object;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public String getDesription() {
        return desription;
    }

    public boolean isAdmin() {
        return admin;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }
}
