package github.middlewaremagic.redismagic.parser;
/*
 * ClassName: CommandHandler
 * Description:
 * @Author: zjh
 * @Create: 2023/4/26
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class CommandHandler {

    private final Socket socket;
    private final CommandParser commandParser;

    public CommandHandler(String host, int port, CommandParser commandParser) throws IOException {
        this.commandParser = commandParser;
        socket = new Socket(host, port);
    }

    public String parse(String longCommands) {
        return this.commandParser.parse(longCommands);
    }

    public String sendRESP(String resp) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        outputStream.write(resp.getBytes());
        //big enough to meet all scenes
        byte[] bytes = new byte[1024 * 1024 * 1024];
        int read = inputStream.read(bytes);
        return new String(bytes, 0, read);
    }

    public List<String> parseResp(String resp) {
        return this.commandParser.parseResp(resp);
    }

    public List<String> sendCommand(String longCommands) throws IOException {
        String parse = this.parse(longCommands);
        String sendRESP = this.sendRESP(parse);
        return this.parseResp(sendRESP);
    }

    public void close() throws IOException {
        socket.close();
    }

}
