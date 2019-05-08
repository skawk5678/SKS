package com.sks.api.domain;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 접근권한을 protected로 제한
@Data
@Entity
@Table(name="posts")
public class Posts {

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 500, nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	private String author;

	@Builder
	public Posts(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}
}