package com.example.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;




@Entity
@Table(name="articles")
public class Article extends DateAudit  {
	
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="article_id")
    private long articleId;

    @Size(min=2, max=100, message="The title must be between 2 and 100 characters.")
    @Column(name="title")
    private String title;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotEmpty(message="Please enter the name of the author.")
    @Column(name="author")
    private String author;

    @Lob
    @Type(type = "org.hibernate.type.TextType") //heroku config
    @NotEmpty(message="The description cannot be empty.")
    @Column(name="description")
    private String description;

    @Lob
    @Type(type = "org.hibernate.type.TextType") //heroku config
    @NotEmpty(message="The content of the article cannot be empty.")
    @Column(name="content")
    private String content;

    
    public Article() {
    	this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
    }

    public long getArticleId() {
        return articleId;
    }
    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
   
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
    
    

}