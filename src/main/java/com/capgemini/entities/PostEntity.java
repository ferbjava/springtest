package com.capgemini.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.JoinFormula;

@Entity
@Table(name = "POST")
public class PostEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 45)
	private String title;
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CommentEntity> comments = new ArrayList<>();
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinFormula(value = "(SELECT c.id FROM comment c WHERE c.post_id = id ORDER BY c.created_on DESC LIMIT 1)")
	private CommentEntity latestComment;

	public PostEntity() {
	}

	public PostEntity(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<CommentEntity> getCommnents() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments.clear();
		this.comments.addAll(comments);
	}

	public void addComment(CommentEntity comment) {
		this.comments.add(comment);
	}

	public CommentEntity getLatestComment() {
		return latestComment;
	}

}
