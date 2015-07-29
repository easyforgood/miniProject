package ustc.siplexy.miniplane.models;/**
 * Created by Ïè on 2015/7/28.
 */

/**
 * Created with Android Studio.
 *
 * @Project:
 * @Package:
 * @Description:
 * @author:
 * @date:
 * @version: V1.0
 */
public class PaperPlane {
    public static final String STORYID="id";
    public static final String TOTAL_FAVOURS="total_favours";
    public static final String TOTAL_COLLECTIONS="total_collections";
    public static final String CREATE_TIME="create_time";


    private String storyid;
    private String title;
    private String total_favours;
    private String state;
    private String create_time;
    private String total_collections;
    public String lock_time;

    public String getTotal_collections() {
        return total_collections;
    }

    public void setTotal_collections(String total_collections) {
        this.total_collections = total_collections;
    }



    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }



    public String getStoryid() {
        return storyid;
    }

    public void setStoryid(String storyid) {
        this.storyid = storyid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotal_favours() {
        return total_favours;
    }

    public void setTotal_favours(String total_favours) {
        this.total_favours = total_favours;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLock_time() {
        return lock_time;
    }

    public void setLock_time(String lock_time) {
        this.lock_time = lock_time;
    }
}
