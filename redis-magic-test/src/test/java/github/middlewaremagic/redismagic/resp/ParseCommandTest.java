package github.middlewaremagic.redismagic.resp;

import github.middlewaremagic.redismagic.parser.CommandParser;
import org.junit.Test;

public class ParseCommandTest {

    private final CommandParser commandParser = new CommandParser();

    @Test
    public void parseGet() {
        String longCommands = "get a";
        System.out.println(this.commandParser.parse(longCommands));
    }

    @Test
    public void parsePing() {
        String longCommands = "PING";
        System.out.println(this.commandParser.parse(longCommands));
    }


    @Test
    public void parseSetNX() {
        String longCommands = "setnx name linuxea";
        System.out.println(this.commandParser.parse(longCommands));
    }

}
