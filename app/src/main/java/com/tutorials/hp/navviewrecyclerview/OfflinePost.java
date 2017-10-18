package com.tutorials.hp.navviewrecyclerview;

/**
 * Created by tontus on 9/21/17.
 */

public class OfflinePost {
    private int _id;
    private int _post_id;
    private String _title;
    private String _content;

    public OfflinePost() {
    }

    public OfflinePost(int _id, int _post_id, String _title, String _content) {
        this._id = _id;
        this._post_id = _post_id;
        this._title = _title;
        this._content = _content;
    }

    public OfflinePost(int _post_id, String _title, String _content) {
        this._post_id = _post_id;
        this._title = _title;
        this._content = _content;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_post_id(int _post_id) {
        this._post_id = _post_id;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public void set_content(String _content) {
        this._content = _content;
    }

    public int get_id() {

        return _id;
    }

    public int get_post_id() {
        return _post_id;
    }

    public String get_title() {
        return _title;
    }

    public String get_content() {
        return _content;
    }
}
