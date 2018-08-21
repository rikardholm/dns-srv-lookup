package x;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import java.net.InetSocketAddress;
import java.util.Hashtable;

public class ServiceLookerUpper {
    public InetSocketAddress lookThisUp(String serviceName) {
        Hashtable<String, String> env = new Hashtable<>();
        env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
        env.put("java.naming.provider.url", "dns://127.0.0.1:8600");
        InitialDirContext ctx;
        String firstSrvRevord;
        try {
            ctx = new InitialDirContext(env);
            Attributes attributes = ctx.getAttributes(serviceName + ".service.consul", new String[]{"SRV"});
            firstSrvRevord = attributes.get("srv").get(0).toString();
        } catch (NamingException e) {
            throw new ServiceLookupException(e);
        }
        String[] fields = firstSrvRevord.split(" ");

        int port = Integer.parseInt(fields[2]);
        String hostname = fields[3];

        return new InetSocketAddress(hostname, port);
    }
}
