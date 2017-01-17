package com.test.model;

/**
 * Created by ronnie wang on 16/12/27.
 */
public class Comment {

    private Integer id;

    private Integer articleId;

    private Integer index;

    private String content;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public Integer getArticleId() {

        return articleId;
    }

    public void setArticleId(Integer articleId) {

        this.articleId = articleId;
    }

    public Integer getIndex() {

        return index;
    }

    public void setIndex(Integer index) {

        this.index = index;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }
}
