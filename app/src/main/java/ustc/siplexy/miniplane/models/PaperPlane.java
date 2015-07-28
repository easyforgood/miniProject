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
    public String storyid;
    public String title;
    public String total_favours;
    public String state;
    public String lock_time;

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
