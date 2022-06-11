package com.example.springboot_3;

import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.ResourceScope;
import jdk.incubator.foreign.ValueLayout;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Executors;

import static java.util.Calendar.FRIDAY;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.SATURDAY;
import static java.util.Calendar.SUNDAY;
import static java.util.Calendar.THURSDAY;
import static java.util.Calendar.TUESDAY;
import static java.util.Calendar.WEDNESDAY;

/**
 * @author voidm
 * @date 2022/5/1
 */
public class JavaFeautre extends Springboot3ApplicationTests {

    @Test
    void jdk9() throws IOException, InterruptedException {


        jdk9Httpclient();

        // 不可变集合
        Map<String, String> map = Map.of("key", "val");

        // try-with-resources
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("HELP.md")))) {
            System.out.println(bufferedWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // JSON API
        // javax.json.JsonObject

    }

    private void jdk9Httpclient() throws IOException, InterruptedException {
        //创建 builder
        HttpClient.Builder builder = HttpClient.newBuilder();

        //链式调用
        HttpClient client = builder

                //http 协议版本 1.1 或者 2
                .version(HttpClient.Version.HTTP_2) //.version(HttpClient.Version.HTTP_1_1)

                //连接超时时间，单位为毫秒
                .connectTimeout(Duration.ofMillis(5000)) //.connectTimeout(Duration.ofMinutes(1))

                //连接完成之后的转发策略
                .followRedirects(HttpClient.Redirect.NEVER) //.followRedirects(HttpClient.Redirect.ALWAYS)

                //指定线程池
                .executor(Executors.newFixedThreadPool(5))

                //认证，默认情况下 Authenticator.getDefault() 是 null 值，会报错
                //.authenticator(Authenticator.getDefault())

                //代理地址
                //.proxy(ProxySelector.of(new InetSocketAddress("http://www.baidu.com", 8080)))

                //缓存，默认情况下 CookieHandler.getDefault() 是 null 值，会报错
                //.cookieHandler(CookieHandler.getDefault())

                //创建完成
                .build();

        //创建 builder
        HttpRequest.Builder reBuilder = HttpRequest.newBuilder();

        //链式调用
        HttpRequest request = reBuilder
                //存入消息头
                //消息头是保存在一张 TreeMap 里的
                .header("Content-Type", "application/json")
                //http 协议版本
                .version(HttpClient.Version.HTTP_2)
                //url 地址
                .uri(URI.create("http://www.baidu.com/"))
                //超时时间
                .timeout(Duration.ofMillis(5009))
                //发起一个 post 消息，需要存入一个消息体
                .POST(HttpRequest.BodyPublishers.ofString("hello"))
                //发起一个 get 消息，get 不需要消息体
                //.GET()
                //method(...) 方法是 POST(...) 和 GET(...) 方法的底层，效果一样
                //.method("POST",HttpRequest.BodyPublishers.ofString("hello"))
                //创建完成
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    @Test
    void jdk10() {
        var date = new Date();
        System.out.println(date);
    }

    @Test
    void jdk12() {
        int day = 1;
        switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> System.out.println(6);
            case TUESDAY -> System.out.println(7);
            case THURSDAY, SATURDAY -> System.out.println(8);
            case WEDNESDAY -> System.out.println(9);
        }
    }

    @Test
    void jdk13() {
        textBlocks();
    }

    private void textBlocks() {
        String html = """
                <html>
                    <body>
                        <p>Hello, world</p>
                    </body>
                </html>
                """;

        String query = """
                SELECT `EMP_ID`, `LAST_NAME` FROM `EMPLOYEE_TB`
                WHERE `CITY` = 'INDIANAPOLIS'
                ORDER BY `EMP_ID`, `LAST_NAME`;
                """;
    }

    @Test
    void jdk14() {

        // instanceof 的模式匹配
        Object obj = "123";
        if (obj instanceof String s) {
            System.out.println(s);
        }
        // 非常有帮助的空指针异常
        Integer integer = null;
        integer.toString();
        // Cannot invoke "java.lang.Integer.toString()" because "integer" is null
        // java.lang.NullPointerException: Cannot invoke "java.lang.Integer.toString()" because "integer" is null
    }


    @Test
    /**
     * The following code shows how to use {@code Optional.isPresent}:
     * {@snippet :
     * if (v.isPresent()) {
     *     System.out.println("v: " + v.get());
     * }
     *}
     */
    void jdk18() throws UnknownHostException {

        //  JEP 418: Internet-Address Resolution SPI
        var addressBytes = new byte[]{(byte) 192, (byte) 168, (byte) 199, (byte) 160};
        var resolvedHostName = InetAddress.getByAddress(addressBytes)
                .getCanonicalHostName();
        System.out.println(resolvedHostName);

        // JEP 419: Foreign Function & Memory API (Second Incubator)
        // MemorySegment segment = MemorySegment.allocateNative(100, ResourceScope.globalScope());
        // for (int i = 0; i < 25; i++) {
        //     segment.setAtIndex(ValueLayout.JAVA_INT,
        //             /* index */ i,
        //             /* value to write */ i);
        // }


    }
}