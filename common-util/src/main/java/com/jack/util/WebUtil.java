package com.jack.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * Web 操作工具类
 *
 * @author qy
 * @since 1.0
 */
public class WebUtil {

    private static final Logger logger = LoggerFactory.getLogger(WebUtil.class);

    /**
     * 将数据以 JSON 格式写入响应中
     */
    public static void writeJSON(HttpServletResponse response, Object data) {
        try {
            // 设置响应头
            // 指定内容类型为 JSON 格式
            response.setContentType("application/json");
            // 防止中文乱码
            response.setCharacterEncoding("utf-8");
            // 向响应中写入数据
            PrintWriter writer = response.getWriter();
            // 转为 JSON 字符串
            writer.write(JSON.toJSONString(data));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.error("在响应中写数据出错！", e);
            throw new RuntimeException(e);
        }
    }
}
