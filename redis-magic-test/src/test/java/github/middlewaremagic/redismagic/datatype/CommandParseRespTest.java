package github.middlewaremagic.redismagic.datatype;

import github.middlewaremagic.redismagic.parser.CommandParser;
import org.junit.Test;

public class CommandParseRespTest {

    private final CommandParser commandParser = new CommandParser();

    @Test
    public void testEvaluateResp() {
        String resp = "*2\r\n" +
                "*3\r\n" +
                ":1\r\n" +
                ":2\r\n" +
                ":3\r\n" +
                "*2\r\n" +
                "+Hello\r\n" +
                "-World\r\n";
        System.out.println(commandParser.parseResp(resp));

    }

}
