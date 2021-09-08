package com.springinaction.web;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author zjjfly
 */
public class Repl implements InitializingBean, DisposableBean {
    static final Logger logger= Logger.getLogger(Repl.class);
    public static final String REPL_NAME="repl";
    @Override
    public void afterPropertiesSet() throws Exception {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("clojure.core.server"));
        IFn server = Clojure.var("clojure.core.server", "start-server");
        server.invoke(Clojure.read("{:port 8888 :name "+REPL_NAME+" :accept clojure.core.server/repl :server-daemon false}"));
        logger.info("repl started");
    }

    @Override
    public void destroy() throws Exception {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("clojure.core.server"));
        IFn server = Clojure.var("clojure.core.server", "stop-server");
        server.invoke(REPL_NAME);
        logger.info("repl stopped");
    }
}