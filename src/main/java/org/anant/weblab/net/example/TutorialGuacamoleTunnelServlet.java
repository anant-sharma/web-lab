package org.anant.weblab.net.example;

import javax.servlet.http.HttpServletRequest;
import org.glyptodon.guacamole.GuacamoleException;
import org.glyptodon.guacamole.net.GuacamoleSocket;
import org.glyptodon.guacamole.net.GuacamoleTunnel;
import org.glyptodon.guacamole.net.InetGuacamoleSocket;
import org.glyptodon.guacamole.net.SimpleGuacamoleTunnel;
import org.glyptodon.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.glyptodon.guacamole.protocol.GuacamoleConfiguration;
import org.glyptodon.guacamole.servlet.GuacamoleHTTPTunnelServlet;

public class TutorialGuacamoleTunnelServlet extends GuacamoleHTTPTunnelServlet {
	
	@Override
    protected GuacamoleTunnel doConnect(HttpServletRequest request)
        throws GuacamoleException {

        // Create our configuration
        GuacamoleConfiguration config = new GuacamoleConfiguration();
        config.setProtocol("rdp");
        config.setParameter("hostname", "192.168.0.5");
        config.setParameter("port", "3389");
        config.setParameter("username", "anant");
        config.setParameter("password", "pass@word10");
        config.setParameter("security", "any");
        config.setParameter("ignore-cert", "true");
        //config.setParameter("width","250");
        //config.setParameter("height","150");
        //config.setParameter("dpi","73");

        // Connect to guacd - everything is hard-coded here.
        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(new InetGuacamoleSocket("localhost", 4822),config);

        // Return a new tunnel which uses the connected socket
        return new SimpleGuacamoleTunnel(socket);
    }
}