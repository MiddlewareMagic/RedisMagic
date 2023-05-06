package github.middlewaremagic.redismagic.parser;
/*
 * ClassName: CommandParser
 * Description:
 * @Author: zjh
 * @Create: 2023/4/26
 */

import github.middlewaremagic.redismagic.datatype.RESP;
import github.middlewaremagic.redismagic.datatype.impl.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandParser {

    private final Map<String, RESP> respMap = new HashMap<>();

    public CommandParser() {
        ArraysRESP arraysRESP = new ArraysRESP(this);
        respMap.putIfAbsent(arraysRESP.startFlag(), arraysRESP);

        BulkStringsRESP bulkStringsRESP = new BulkStringsRESP();
        respMap.putIfAbsent(bulkStringsRESP.startFlag(), bulkStringsRESP);

        IntegersRESP integersRESP = new IntegersRESP();
        respMap.putIfAbsent(integersRESP.startFlag(), integersRESP);

        SimpleStringsRESP simpleStringsRESP = new SimpleStringsRESP();
        respMap.putIfAbsent(simpleStringsRESP.startFlag(), simpleStringsRESP);

        ErrorsRESP errorsRESP = new ErrorsRESP();
        respMap.putIfAbsent(errorsRESP.startFlag(), errorsRESP);

    }

    public String parse(String longCommands) {

        List<String> params = Arrays.stream(longCommands.split("\\s+")).collect(Collectors.toList());

        RESP bulkStringsRESP = respMap.get("$");
        List<String> bulkStringParse = bulkStringsRESP.parse(params);

        RESP arraysRESP = respMap.get("*");
        List<String> parse = arraysRESP.parse(bulkStringParse);

        return String.join("", parse);
    }

    public List<String> parseResp(String longCommands) {
        List<String> results = new UnWrapperCommandList();
        reverseParse(longCommands, results);
        return results;
    }

    public String reverseParse(String longCommands, List<String> results) {
        char startFlag = longCommands.charAt(0);
        return this.respMap.get(Character.toString(startFlag)).reverseParse(longCommands, results);
    }

}
