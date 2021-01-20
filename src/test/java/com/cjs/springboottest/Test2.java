package com.cjs.springboottest;

import com.cjs.springboottest.bean.HttpResult;
import com.cjs.springboottest.util.DateTimeUtils;
import com.cjs.springboottest.util.FastJsonUtils;
import com.cjs.springboottest.util.FileOperatorUtil;
import com.cjs.springboottest.util.HttpClientUtils;
import com.google.common.collect.ImmutableMap;
import javafx.beans.binding.ObjectExpression;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Slf4j
public class Test2 {

    private static final String URL = "https://www.e-cology.com.cn/api/workrelate/portrait/personWorkrelateInfo";

    private static final String JSESSIONID = "cba_xmHw5j9HifRuWHSyx";

    private static final String ecology_JSessionid = "cba_xmHw5j9HifRuWHSyx";

    private static final String __clusterSessionCookieName = "880E70DAE90DC3821FFF0D7735541C1F";

    private static final String SERVERID = "9917ee87ff7a5198c6e90530490d870f|1610084772|1609201877";

    private static final String DOMAIN = "www.e-cology.com.cn";

    private static final String filepathname = "G:/scores.txt";

    private static final List<Map<String, String>> USERS = new ArrayList<Map<String, String>>(){{
        add(ImmutableMap.of("name", "陈劲树", "userid", "11258"));
        add(ImmutableMap.of("name", "吴泳林", "userid", "9026"));
        add(ImmutableMap.of("name", "王玉粮", "userid", "9023"));
        add(ImmutableMap.of("name", "张博文", "userid", "5690"));
        add(ImmutableMap.of("name", "张明松", "userid", "11316"));
        add(ImmutableMap.of("name", "张明", "userid", "11259"));
        add(ImmutableMap.of("name", "陈佳伟", "userid", "9029"));
        add(ImmutableMap.of("name", "陈佳敏", "userid", "7005"));
        add(ImmutableMap.of("name", "张笑天", "userid", "5724"));
        add(ImmutableMap.of("name", "吕益", "userid", "3232"));
//        add(ImmutableMap.of("name", "杨金鹏", "userid", "11128"));
    }};

    @Test
    public void getScores() throws Exception {
        List<Cookie> cookies = new ArrayList<>();

        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", JSESSIONID);
        cookie.setDomain(DOMAIN);
        cookie.setPath("/");
        cookies.add(cookie);

        cookie = new BasicClientCookie("ecology_JSessionid", ecology_JSessionid);
        cookie.setDomain(DOMAIN);
        cookie.setPath("/");
        cookies.add(cookie);

        cookie = new BasicClientCookie("__clusterSessionCookieName", __clusterSessionCookieName);
        cookie.setDomain(DOMAIN);
        cookie.setPath("/");
        cookies.add(cookie);

        cookie = new BasicClientCookie("SERVERID", SERVERID);
        cookie.setDomain(DOMAIN);
        cookie.setPath("/");
        cookies.add(cookie);

        File file = new File(filepathname);
        FileWriter writer = new FileWriter(file);
        if(file.exists()) {
            file.delete();
        }
        file.createNewFile();

        for (Map<String, String> user: USERS) {
            writer.write("\n");
            HttpResult httpResult = HttpClientUtils.doGet(URL, null, cookies, new HashMap<String, String>(){{
                put("userid", user.get("userid"));
                put("operation", "3");
            }});
            Map<String, Object> resultMap = FastJsonUtils.toBean(httpResult.getData().toString(), Map.class);
            List<Map<String, String>> datas = (List<Map<String, String>>) resultMap.get("data");
            for (Map<String, String> data: datas) {
                writer.write("月份：" + data.get("month") + "         " + user.get("name") + "的分数：" + data.get("myScore") + "          " + "平均分数：" + data.get("avgScore"));
                writer.write("\n");
            }
        }
        writer.close();   // 关闭流，不然字符串内容不会被写入到文件中，当然也可以使用writer.flush方法强制输出。
    }

}
