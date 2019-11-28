package com.petar.workspring.domain.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "test", schema = "dbo", catalog = "xc")
public class TestEntity {
    private Integer id;
    private Integer testId;

    @Id
    @Basic
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "test_id")
    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntity that = (TestEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(testId, that.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testId);
    }
}
