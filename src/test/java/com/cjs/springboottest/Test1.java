package com.cjs.springboottest;


import com.cjs.springboottest.util.FileOperatorUtil;
import org.junit.Test;
import com.cjs.springboottest.util.Util;
import com.cjs.springboottest.util.DateTimeUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test1 {

    private static final String sqlserver = "SQLServer";

    private static final String mysql = "Mysql";

    private static final String oracle = "Oracle";

    private static final String dm = "DM";

    private static final String st = "ST";

    private static final String gs = "GS";

    private static final String jc = "JC";

    private static final String sourcePath = "C:/Users/Administrator/Downloads/ecology9/sqlupgrade/#type/";

    private static final String targetFilePath = "C:/Users/Administrator/Downloads/ecology9/sqlupgrade/#type/SQL" + DateTimeUtils.dateToStr(new Date(), "yyyyMMddHHmmss") + ".sql";

    private static final List<String> excludeFilenames = new ArrayList<String>() {{
//        add("sql201906251103.sql");
    }};

    @Test
    public void mergeFiles() {
        try {
            String type = st;
            FileOperatorUtil.mergeFiles(sourcePath.replace("#type", type), targetFilePath.replace("#type", type));
            System.out.println("合并成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
