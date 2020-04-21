package com.pl.portal.service;

import com.google.common.base.Strings;
import com.pl.data.model.Invitee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * csv文件转换器
 * @param <T> 任意类型
 */
public abstract class CsvReader<T> {
    private static final int MIN_CONTENT_LEN = 2;
    protected List<String[]> content;

    public CsvReader() {
    }

    public static class Readers{
        public static class InviteeReader extends CsvReader<Invitee> {

            public InviteeReader(String source){
                super(source);
            }
            @Override
            public int getColCount() {
                return 8;
            }

            @Override
            protected Invitee toItem(String[] t) {
                Invitee i=new Invitee();
                i.setName(t[0]);
                i.setEmail(t[1]);
                i.setCat1(t[2]);
                i.setCat2(t[3]);
                i.setCat3(t[4]);
                i.setCat4(t[5]);
                i.setCat5(t[6]);
                i.setRemarks(t[7]);
                i.setDclt(new Date());
                return i;
            }
        }
    }

    public CsvReader(String s) {
        this.content = read(s);
    }

    /**
     * @return 总列数
     */
    public abstract int getColCount();

    /**
     * 转换一行
     * @param t
     * @return
     */
    protected abstract T toItem(String[] t);

    /**
     * @return 全部转换
     */
    public List<T> toValues() {
        if (!isContentValid()) {
            return null;
        }
        List<T> r = new ArrayList<>(content.size() - 1);
        for (int i = 1; i < content.size(); i++) {
            String[] ts = content.get(i);
            if (ts.length != getColCount()) {
                continue;
            }
            T t = toItem(ts);
            if (t == null) {
                continue;
            }
            r.add(t);
        }
        return r;
    }

    /**
     * @return 内容是否有效
     */
    public boolean isContentValid() {
        if (content == null || content.size() < MIN_CONTENT_LEN) {
            return false;
        }
        if (content.get(0).length != getColCount()) {
            return false;
        }
        return true;
    }

    /**
     * 分隔符含义
     ,           // Split on comma
     (?=         // Followed by
     (?:      // Start a non-capture group
     [^"]*  // 0 or more non-quote characters
     "      // 1 quote
     [^"]*  // 0 or more non-quote characters
     "      // 1 quote
     )*       // 0 or more repetition of non-capture group (multiple of 2 quotes will be even)
     [^"]*    // Finally 0 or more non-quotes
     $        // Till the end  (This is necessary, else every comma will satisfy the condition)
     )
     */
    private static final String CSV_COMMA_SPLITTER = ",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
    private List<String[]> read(String csv) {
        List<String[]> r = new ArrayList<>();
        csv = csv.replace("\r\n", "\n");
        String[] la = csv.split("\n");
        if (la == null || la.length == 0) {
            return r;
        }
        for (String s : la) {
            if (Strings.isNullOrEmpty(s)) {
                continue;
            }
            try {
                String[] fa = s.split(CSV_COMMA_SPLITTER);
                r.add(fa);
            }catch (Exception e){
                // on purpose
            }
        }
        return r;
    }

    /**
     * @return 获取分割好的文件内容
     */
    public List<String[]> getContent() {
        return content;
    }
}
