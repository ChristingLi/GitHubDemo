package com.cn.dbFile;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ld on 2017/10/13.
 */
/*@Entity GreenDao 可识别的数据实体
* */
@Entity
public class GreenDaoEntity {
    /*@Id：通过这个注解标记的字段必须是Long类型的，这个字段在数据库中表示它就是主键，并且它默认就是自增的
     */
    @Id
    private Long id;
    private String name;
    /*@Transient：表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化
    * */
    @Transient
    private int tempUsageCout;// not persisted
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 530867653)
    public GreenDaoEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1963997359)
    public GreenDaoEntity() {
    }
}
