package com.doctortech.framework.orm;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@MappedSuperclass
public class CommonModel<T extends CommonModel> extends Model<CommonModel> {

    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;//自增id

    protected Integer deleted=0;//逻辑删除

    @Version
    protected Integer version=0;//版本号

    protected Date createTime;//创建时间

    protected Date updateTime;//更新时间

    protected Long createUserId;//创建者

    protected Long updateUserId;//更新者


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @UpdateTimestamp
    @Column(nullable = true,insertable=true)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
