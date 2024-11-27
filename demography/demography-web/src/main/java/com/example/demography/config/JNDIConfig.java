package com.example.demography.config;


import com.example.demography.service.PersonService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class JNDIConfig {
    public static PersonService getPersonService() {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        env.put("jboss.naming.client.ejb.context", true);
        env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        try {
            InitialContext remoteContext = new InitialContext();
            return (PersonService) remoteContext.lookup("java:global/demography-0.0.1-SNAPSHOT/PersonServiceImpl!com.example.demography.service.PersonService");
        }
        catch (NamingException e){
            e.printStackTrace();
            return null;
        }
    }
}
