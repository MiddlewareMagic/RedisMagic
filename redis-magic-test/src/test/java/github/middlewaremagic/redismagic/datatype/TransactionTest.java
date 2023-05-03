package github.middlewaremagic.redismagic.datatype;

import github.middlewaremagic.redismagic.parser.CommandParser;
import github.middlewaremagic.redismagic.parser.CommandHandler;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TransactionTest {

    @Test
    public void testTran() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());
        try {
            List<String> sendCommandAuth = commandHandler.sendCommand("auth gaoxiang");
            System.out.println(sendCommandAuth);
            System.out.println(commandHandler.sendCommand("multi"));
            System.out.println(commandHandler.sendCommand("incr b"));
            System.out.println(commandHandler.sendCommand("incr b"));
            System.out.println(commandHandler.sendCommand("incr b"));
            System.out.println(commandHandler.sendCommand("incr b"));
            System.out.println(commandHandler.sendCommand("exec"));
        } finally {
            commandHandler.sendCommand("discard");
        }
    }

}
