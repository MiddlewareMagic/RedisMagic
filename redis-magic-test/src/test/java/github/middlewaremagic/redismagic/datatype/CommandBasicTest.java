package github.middlewaremagic.redismagic.datatype;

import github.middlewaremagic.redismagic.parser.CommandParser;
import github.middlewaremagic.redismagic.parser.CommandHandler;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class CommandBasicTest {

    @Test
    public void sendCommandGet() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());
        List<String> sendCommandAuth = commandHandler.sendCommand("auth gaoxiang");
        System.out.println(sendCommandAuth);
        List<String> sendCommand = commandHandler.sendCommand("incr a");
        System.out.println(sendCommand);
        commandHandler.close();
    }

    @Test
    public void sendCommandIncr() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());
        List<String> sendCommand = commandHandler.sendCommand("incr a");
        System.out.println(sendCommand);
        commandHandler.close();
    }

    @Test
    public void sendCommandExp() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());
        List<String> sendCommand = commandHandler.sendCommand("expire a 11");
        System.out.println(sendCommand);
        commandHandler.close();
    }

    @Test
    public void sendCommandIncrBy() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());
        List<String> sendCommandAuth = commandHandler.sendCommand("auth gaoxiang");
        System.out.println(sendCommandAuth);
        List<String> sendCommand = commandHandler.sendCommand("incrby a 100");
        System.out.println(sendCommand);
        commandHandler.close();
    }

    @Test
    public void sendCommandLType() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());
        List<String> sendCommand = commandHandler.sendCommand("type game");
        System.out.println(sendCommand);
        commandHandler.close();
    }

    @Test
    public void sendCommandLPush() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());
        List<String> sendCommandAuth = commandHandler.sendCommand("auth gaoxiang");
        System.out.println(sendCommandAuth);
        List<String> sendCommand = commandHandler.sendCommand("lpush game d e f g h");
        System.out.println(sendCommand);
        commandHandler.close();
    }

    @Test
    public void sendCommandLLen() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());
        List<String> sendCommand = commandHandler.sendCommand("llen game");
        System.out.println(sendCommand);
        commandHandler.close();
    }

    @Test
    public void sendCommandLLenNotExists() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());
        List<String> sendCommand = commandHandler.sendCommand("llen game2");
        System.out.println(sendCommand);
        commandHandler.close();
    }


    @Test
    public void sendCommandLRange() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());
        List<String> sendCommand = commandHandler.sendCommand("lrange game 0 100");
        System.out.println("======================");
        System.out.println(sendCommand);
        commandHandler.close();
    }

    @Test
    public void sendCommandLGet() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());

        List<String> setNameLinuxea = commandHandler.sendCommand("set name linuxea");
        System.out.println(setNameLinuxea);
        System.out.println("======================");

        List<String> sendCommand = commandHandler.sendCommand("get name");
        System.out.println("======================");
        System.out.println(sendCommand);

        commandHandler.close();
    }

    @Test
    public void sendCommandSetNX() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());

        List<String> setNameLinuxea = commandHandler.sendCommand("setnx name linuxea");
        System.out.println(setNameLinuxea);


        setNameLinuxea = commandHandler.sendCommand("setnx name2 linuxeaaaa");
        System.out.println(setNameLinuxea);
        commandHandler.close();
    }


    @Test
    public void sendCommandMGet() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());
        List<String> sendCommand = commandHandler.sendCommand("mget a x y z");
        System.out.println(sendCommand);
        commandHandler.close();
    }

    @Test
    public void sendCommandError() throws IOException {
//        String redisServerHost = System.getenv("redisServerHost");
        String redisServerHost = "124.71.84.228";
        CommandHandler commandHandler = new CommandHandler(redisServerHost, 6379, new CommandParser());
        System.out.println(commandHandler.sendCommand("set radfdf ffff"));
        List<String> sendCommand = commandHandler.sendCommand("incr radfdf");
        System.out.println(sendCommand);
        commandHandler.close();
    }

}
