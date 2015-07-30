package ustc.siplexy.miniplane.models;

/**
 * Created by siplexypeng on 2015/7/28.
 */
public class PaperPlaneDetail {
    /**
     * @TODO:字段常量,后台接口json格式中涉及到的常量
     *
     */
    public static final String PARAGRAPH_ID="_id"; //段落id 也就是每一个纸飞机
    public static final String AUTHOR="author"; //作者
    public static final String AUTHOR_NICKNAME="nickname"; //作者昵称
    public static final String FAVOR_COUNT="favour_users"; // 点赞数
    public static final String AUTHOR_PICURL="avatar"; //图片url
    public static final String CONTENT="content"; //内容


    //内容
    public String content;
    //段落id
    public String paragraph_id;
    //昵称
    public String author_nickname;
    //赞数
    public Integer favor_count;
    //头像图片url
    public String author_picurl;
    //所在飞机中
    public PaperPlane paperplane;


    /**
     * 以下都是get set 方法
     *
     */

    public PaperPlane getPaperplane() {
        return paperplane;
    }

    public void setPaperplane(PaperPlane paperplane) {
        this.paperplane = paperplane;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public String getAuthor_picurl() {
        return author_picurl;
    }

    public void setAuthor_picurl(String author_picurl) {
        this.author_picurl = author_picurl;
    }

    public String getParagraph_id() {
        return paragraph_id;
    }

    public void setParagraph_id(String paragraph_id) {
        this.paragraph_id = paragraph_id;
    }

    public String getAuthor_nickname() {
        return author_nickname;
    }

    public void setAuthor_nickname(String author_nickname) {
        this.author_nickname = author_nickname;
    }

    public Integer getFavor_count() {
        return favor_count;
    }

    public void setFavor_count(Integer favor_count) {
        this.favor_count = favor_count;
    }



}
