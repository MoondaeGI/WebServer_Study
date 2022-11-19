package com.moondaegi.study.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass  // 해당 클래스를 상속할 경우, 클래스 내의 필드를 컬럼으로 가져간다
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {  // 모든 entity들의 상위 클래스로 등록/수정 시간을 일괄 관리함
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
