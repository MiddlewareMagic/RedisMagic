package github.middlewaremagic.redismagic.data;

import github.middlewaremagic.redismagic.datatype.RESP;
import github.middlewaremagic.redismagic.datatype.impl.*;
import github.middlewaremagic.redismagic.parser.UnWrapperCommandList;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: `redisRewrite
 * @description: RESP 状态转换表
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-05-06 15:31
 **/
@Data
public class RespTable {

    final static Map<String, RESP> respMap = new HashMap<>();

    static {
        ArraysRESP arraysRESP = new ArraysRESP();
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

    public static String parse(String longCommands) {

        List<String> params = Arrays.stream(longCommands.split("\\s+")).collect(Collectors.toList());

        RESP bulkStringsRESP = respMap.get("$");
        List<String> bulkStringParse = bulkStringsRESP.parse(params);

        RESP arraysRESP = respMap.get("*");
        List<String> parse = arraysRESP.parse(bulkStringParse);

        return String.join("", parse);
    }

    public static List<String> parseResp(String longCommands) {
        List<String> results = new UnWrapperCommandList();
        reverseParse(longCommands, results);
        return results;
    }

    public static String reverseParse(String longCommands, List<String> results) {
        char startFlag = longCommands.charAt(0);
        return respMap.get(Character.toString(startFlag)).reverseParse(longCommands, results);
    }
}
