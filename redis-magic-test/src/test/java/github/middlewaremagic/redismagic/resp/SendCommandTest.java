//package github.middlewaremagic.redismagic.resp;
//
//import github.middlewaremagic.redismagic.parser.CommandParser;
//import github.middlewaremagic.redismagic.parser.CommandHandler;
//import org.junit.Test;
//
//import java.io.IOException;
//
//public class SendCommandTest {
//
//    private final String redisServerHost = "124.71.84.228";
//
//    private final int redisServerPort = 6379;
//
//    @Test
//    public void sendCommandIncr() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//        System.out.println(commandHandler.sendCommand("incr a"));
//        commandHandler.close();
//    }
//
//    @Test
//    public void sendCommandIncrBy() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//        System.out.println(commandHandler.sendCommand("incrby a 100"));
//        commandHandler.close();
//    }
//
//    @Test
//    public void sendCommandExp() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//        System.out.println(commandHandler.sendCommand("expire a 11"));
//        commandHandler.close();
//    }
//
//    @Test
//    public void sendCommandLPush() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//        System.out.println(commandHandler.sendCommand("lpush game d e f g h"));
//        commandHandler.close();
//    }
//
//    @Test
//    public void sendCommandLType() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//        System.out.println(commandHandler.sendCommand("type game"));
//        commandHandler.close();
//    }
//
//    @Test
//    public void sendCommandLLen() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//        System.out.println(commandHandler.sendCommand("llen game"));
//        commandHandler.close();
//    }
//
//    @Test
//    public void sendCommandLLenNotExists() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//        System.out.println(commandHandler.sendCommand("llen game2"));
//        commandHandler.close();
//    }
//
//    @Test
//    public void sendCommandLRange() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//        System.out.println(commandHandler.sendCommand("lrange game 0 100"));
//        commandHandler.close();
//    }
//
//    @Test
//    public void sendCommandSetNX() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//        System.out.println(commandHandler.sendCommand("setnx name lisi"));
//        System.out.println(commandHandler.sendCommand("setnx name2 zhangsan"));
//        commandHandler.close();
//    }
//
//    @Test
//    public void sendCommandMGet() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//        System.out.println(commandHandler.sendCommand("mget a x y z"));
//        commandHandler.close();
//    }
//
//    @Test
//    public void sendCommandError() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//        System.out.println(commandHandler.sendCommand("set radfdf ffff"));
//        System.out.println(commandHandler.sendCommand("incr radfdf"));
//        commandHandler.close();
//    }
//
//    @Test
//    public void testKeys() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//        System.out.println(commandHandler.sendCommand("keys *"));
//    }
//
//    @Test
//    public void testPing() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//        System.out.println(commandHandler.sendCommand("ping"));
//    }
//
//    @Test
//    public void testTran() throws IOException {
//        CommandHandler commandHandler = new CommandHandler(redisServerHost, redisServerPort, new CommandParser());
//        try {
//            System.out.println(commandHandler.sendCommand("auth gaoxiang"));
//            System.out.println(commandHandler.sendCommand("multi"));
//            System.out.println(commandHandler.sendCommand("incr b"));
//            System.out.println(commandHandler.sendCommand("incr b"));
//            System.out.println(commandHandler.sendCommand("incr b"));
//            System.out.println(commandHandler.sendCommand("incr b"));
//            System.out.println(commandHandler.sendCommand("exec"));
//        } finally {
//            commandHandler.sendCommand("discard");
//        }
//    }
//
//}
