package github.middlewaremagic.redismagic.datatype;

import github.middlewaremagic.redismagic.parser.CommandParser;
import org.junit.Test;

public class CommandParserTest {

    private final CommandParser commandParser = new CommandParser();

    @Test
    public void parserTest() {
        String longCommands = "get a";
        String parser = this.commandParser.parse(longCommands);
        System.out.println(parser);

    }

    @Test
    public void testPing() {
        String longCommands = "PING";
        String parser = this.commandParser.parse(longCommands);
        System.out.println(parser);
    }

    @Test
    public void testReverseparser() {
        String longCommands = "PING";
        String parser = this.commandParser.parse(longCommands);
        System.out.println(parser);
    }

    @Test
    public void testReverseparser2() {
        String longCommands = "lrange a";
        String parser = this.commandParser.parse(longCommands);
        System.out.println(parser);
    }

    @Test
    public void parserSetNX() {
        String longCommands = "setnx name linuxea";
        String parser = this.commandParser.parse(longCommands);
        System.out.println(parser);
    }

}
