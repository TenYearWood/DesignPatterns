package com.cy.dp.cor;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是一段处理敏感词、尖括号js、等的代码。
 * 真正实际中代码会很复杂，如果后续加其他过滤等，直接写代码会很多，而且不易扩展。
 * 对这段带马甲进行优化：
 * 1.思想：封装变化，那部分可能有变化，封装哪部分。
 */
public class Main {
    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("大家好:), <script>, 欢迎访问 mashibing.com , 大家都是996 ");

        /**
        //处理msg
        String r = msg.getMsg();
        r = r.replace('<', '[');
        r = r.replace('>', ']');
        msg.setMsg(r);

        r = r.replaceAll("996", "955");
        msg.setMsg(r);**/

        /*
        new HTMLFilter().doFilter(msg);
        new SensitiveFilter().doFilter(msg);*/

        /**
         * 每个filter负责自己的过滤责任，直观上看是责任链模式了
         * 但是还没有写完，还没有串在一起
         * 责任链条
         */
        /*
        List<Filter> filters = new ArrayList<>();
        filters.add(new HTMLFilter());
        filters.add(new SensitiveFilter());

        for(Filter f : filters){
            f.doFilter(msg);
        }*/

        /**
         * 这个和上面写法，咋一看没什么不同啊，区别在哪？
         * 1.一个filterChain和另外的filterChain两个chain应该是可以连在一起的
         *
         */
        /*
        FilterChain fc = new FilterChain();
        fc.add(new HTMLFilter()).add(new SensitiveFilter());

        FilterChain fc2 = new FilterChain();
        fc2.add(new FaceFilter()).add(new URLFilter());

        fc.doFilter(msg);
        fc2.doFilter(msg);*/

        /**
         * 让filterChain也实现filter接口
         */
        /**
        FilterChain fc = new FilterChain();
        fc.add(new HTMLFilter()).add(new SensitiveFilter());

        FilterChain fc2 = new FilterChain();
        fc2.add(new FaceFilter()).add(new URLFilter());

        fc.add(fc2);
        fc.doFilter(msg);**/

        /**
         * 怎么样让某一个filter决定要不要继续往下走，比如如果碰到某一个敏感词，就不往数据库插了，怎么做？
         * 要不要继续往下处理这个逻辑必须得在filter自身里面来决定
         */
        FilterChain fc = new FilterChain();
        fc.add(new HTMLFilter()).add(new SensitiveFilter());

        FilterChain fc2 = new FilterChain();
        fc2.add(new FaceFilter()).add(new URLFilter());

        fc.add(fc2);
        fc.doFilter(msg);

        System.out.println(msg);
    }
}

interface Filter {
    boolean doFilter(Msg m);
}

class FilterChain implements Filter {

    private List<Filter> filters = new ArrayList<>();

    public FilterChain add(Filter f) {
        this.filters.add(f);
        return this;
    }

    /**
     * 应该有个方法让这个链条从头走到尾
     */
    public boolean doFilter(Msg m) {
        for (Filter f : filters) {
            if (!f.doFilter(m)) return false;
        }
        return true;
    }
}

class HTMLFilter implements Filter {
    @Override
    public boolean doFilter(Msg msg) {

        //处理msg
        String r = msg.getMsg();
        r = r.replace('<', '[');
        r = r.replace('>', ']');
        msg.setMsg(r);
        return true;
    }
}

class SensitiveFilter implements Filter{

    /**
     * 一旦发现有敏感词，return false
     * @param msg
     * @return
     */
    @Override
    public boolean doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replaceAll("996", "955");
        msg.setMsg(r);
        return false;
    }
}

class FaceFilter implements Filter{
    @Override
    public boolean doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replace(":)", "^ ^");
        msg.setMsg(r);
        return true;
    }
}

class URLFilter implements Filter{
    @Override
    public boolean doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replace("mashibing.com", "http://www.mashibing.com");
        msg.setMsg(r);
        return true;
    }
}

class Msg{
    String name;
    String msg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}