package ustc.siplexy.miniplane.models;

/**
 * Created by siplexypeng on 2015/7/28.
 */
public class PaperPlaneDetail {
    /**
     * @TODO:�ֶγ���,��̨�ӿ�json��ʽ���漰���ĳ���
     *
     */
    public static final String PARAGRAPH_ID="_id"; //����id Ҳ����ÿһ��ֽ�ɻ�
    public static final String AUTHOR="author"; //����
    public static final String AUTHOR_NICKNAME="nickname"; //�����ǳ�
    public static final String FAVOR_COUNT="favour_users"; // ������
    public static final String AUTHOR_PICURL="avatar"; //ͼƬurl
    public static final String CONTENT="content"; //����


    //����
    public String content;
    //����id
    public String paragraph_id;
    //�ǳ�
    public String author_nickname;
    //����
    public Integer favor_count;
    //ͷ��ͼƬurl
    public String author_picurl;
    //���ڷɻ���
    public PaperPlane paperplane;


    /**
     * ���¶���get set ����
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
