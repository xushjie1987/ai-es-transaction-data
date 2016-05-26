/**
 * Project Name:ai-es-transaction-data
 * File Name:TestJson.java
 * Package Name:com.oneapm.es.json
 * Date:2016年5月25日下午2:04:16
 * Copyright (c) 2016, All Rights Reserved.
 *
 */

package com.oneapm.es.json;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.common.xcontent.XContentParser.Token;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContentParser;
import org.junit.Test;

/**
 * ClassName:TestJson <br/>
 * Function: <br/>
 * Date: 2016年5月25日 下午2:04:16 <br/>
 * @author xushjie
 * @version
 * @since JDK 1.7
 * @see
 */
public class TestJson {
    
    /**
     * testJsonParser01: <br/>
     * @author xushjie
     * @throws IOException
     * @since JDK 1.7
     */
    @Test
    public void testJsonParser01() throws IOException {
        String json = "{\"k1\":\"v1\", \"k2\":\"v2\"}";
        XContentParser parser = XContentFactory.xContent(XContentType.JSON)
                                               .createParser(json.getBytes());
        System.out.println(parser.currentName());
        System.out.println(parser.nextToken()
                                 .name());
        System.out.println(parser.nextToken()
                                 .name());
        System.out.println(parser.nextToken()
                                 .isValue());
        System.out.println(((JsonXContentParser) parser).objectText());
        System.out.println(((JsonXContentParser) parser).objectText()
                                                        .getClass()
                                                        .getName());
        System.out.println(parser.nextToken()
                                 .name());
        System.out.println(parser.nextToken()
                                 .name());
        System.out.println(parser.currentName());
    }
    
    /**
     * testJsonParser02: <br/>
     * @author xushjie
     * @throws IOException
     * @since JDK 1.7
     */
    @Test
    public void testJsonParser02() throws IOException {
        String json = "{\"k1\":\"v1\", \"k2\":\"v2\"}";
        XContentParser parser = XContentFactory.xContent(XContentType.JSON)
                                               .createParser(json.getBytes());
        System.out.println(parser.currentName());
        parser.nextToken();
        System.out.println(parser.currentName());
        parser.nextToken();
        System.out.println(parser.currentName());
        parser.nextToken();
        System.out.println(parser.currentName());
        parser.nextToken();
        System.out.println(parser.currentName());
        parser.nextToken();
        System.out.println(parser.currentName());
        parser.nextToken();
        System.out.println(parser.currentName());
        parser.nextToken();
        System.out.println(parser.currentName());
        parser.nextToken();
        System.out.println(parser.currentName());
        parser.nextToken();
        System.out.println(parser.currentName());
        parser.nextToken();
        System.out.println(parser.currentName());
        parser.nextToken();
        System.out.println(parser.currentName());
        parser.nextToken();
    }
    
    /**
     * testJsonParser03: <br/>
     * @author xushjie
     * @throws IOException
     * @since JDK 1.7
     */
    @Test
    public void testJsonParser03() throws IOException {
        String json = "{\"k1\":\"v1\", \"k2\":\"v2\"}";
        XContentParser parser = XContentFactory.xContent(XContentType.JSON)
                                               .createParser(json.getBytes());
        //
        if (parser.nextToken() == null) {
            System.out.println("xx");
        }
        parser.nextToken();
        System.out.println(parser.currentName() +
                           ", " +
                           parser.currentToken()
                                 .name() +
                           ", " +
                           parser.currentToken()
                                 .isValue());
        //
        if (parser.nextToken() == null) {
            System.out.println("xx");
        }
        parser.nextToken();
        System.out.println(parser.currentName() +
                           ", " +
                           parser.currentToken()
                                 .name() +
                           ", " +
                           parser.currentToken()
                                 .isValue());
        //
        if (parser.nextToken() == null) {
            System.out.println("xx");
        }
        parser.nextToken();
        System.out.println(parser.currentName() +
                           ", " +
                           parser.currentToken()
                                 .name() +
                           ", " +
                           parser.currentToken()
                                 .isValue());
        //
        if (parser.nextToken() == null) {
            System.out.println("xx");
        }
        parser.nextToken();
        System.out.println(parser.currentName() +
                           ", " +
                           parser.currentToken()
                                 .name() +
                           ", " +
                           parser.currentToken()
                                 .isValue());
        //
        if (parser.nextToken() == null) {
            System.out.println("xx");
        }
        parser.nextToken();
        System.out.println(parser.currentName() +
                           ", " +
                           parser.currentToken()
                                 .name() +
                           ", " +
                           parser.currentToken()
                                 .isValue());
        //
        if (parser.nextToken() == null) {
            System.out.println("xx");
        }
        parser.nextToken();
        System.out.println(parser.currentName() +
                           ", " +
                           parser.currentToken()
                                 .name() +
                           ", " +
                           parser.currentToken()
                                 .isValue());
        //
        if (parser.nextToken() == null) {
            System.out.println("xx");
        }
        parser.nextToken();
        // --------------------------------------
        System.out.println("end");
        // --------------------------------------
        System.out.println(parser.currentName() +
                           ", " +
                           parser.currentToken()
                                 .name() +
                           ", " +
                           parser.currentToken()
                                 .isValue());
        parser.nextToken();
        System.out.println(parser.currentName() +
                           ", " +
                           parser.currentToken()
                                 .name() +
                           ", " +
                           parser.currentToken()
                                 .isValue());
        parser.nextToken();
        System.out.println(parser.currentName() +
                           ", " +
                           parser.currentToken()
                                 .name() +
                           ", " +
                           parser.currentToken()
                                 .isValue());
        parser.nextToken();
        System.out.println(parser.currentName() +
                           ", " +
                           parser.currentToken()
                                 .name() +
                           ", " +
                           parser.currentToken()
                                 .isValue());
        parser.nextToken();
        System.out.println(parser.currentName() +
                           ", " +
                           parser.currentToken()
                                 .name() +
                           ", " +
                           parser.currentToken()
                                 .isValue());
        parser.nextToken();
    }
    
    /**
     * testJsonParser04: <br/>
     * @author xushjie
     * @throws IOException
     * @since JDK 1.7
     */
    @Test
    public void testJsonParser04() throws IOException {
        String json = "{\"k1\":\"v1\", \"k2\":\"v2\"}";
        XContentParser parser = XContentFactory.xContent(XContentType.JSON)
                                               .createParser(json.getBytes());
        //
        while (parser.nextToken() != null) {
            System.out.println(parser.currentName() +
                               parser.currentToken()
                                     .name() +
                               parser.currentToken()
                                     .isValue() +
                               parser.text());
        }
    }
    
    /**
     * testJsonParser05: <br/>
     * @author xushjie
     * @throws IOException
     * @since JDK 1.7
     */
    @Test
    public void testJsonParser05() throws IOException {
        String json = "{\"k1\":\"v1\", \"k2\":\"v2\"}";
        XContentParser parser = XContentFactory.xContent(XContentType.JSON)
                                               .createParser(json.getBytes());
        //
        System.out.println(parser.nextToken()
                                 .compareTo(Token.START_OBJECT) == 0
                                                                    ? "yes"
                                                                    : "no");
        System.out.println(parser.nextToken()
                                 .compareTo(Token.START_OBJECT) == 0
                                                                    ? "yes"
                                                                    : "no");
    }
    
}
