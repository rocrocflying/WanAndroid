package model.homepage;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Article {

    private int offset;
    private int pageCoun;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageCoun() {
        return pageCoun;
    }

    public void setPageCoun(int pageCoun) {
        this.pageCoun = pageCoun;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<ArticleItem> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<ArticleItem> datas) {
        this.datas = datas;
    }

    private int size;
    private int total;
    private ArrayList<ArticleItem> datas;

    public static class ArticleItem {

        public String title;
        public String shareUser;
        public String niceDate;
        public String link;
        public String chapterName;

        public String toString() {

            return "title:" + title + ":" + "link:" + link;


        }

    }

}
