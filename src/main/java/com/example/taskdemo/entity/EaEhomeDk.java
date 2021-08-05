package com.example.taskdemo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "ea_ehome_dk")
public class EaEhomeDk {
    @Id
    private Long id;

    //工号
    @Column(name = "salesNo")
    private String salesNo;

    //'密码'
    @Column
    private String password;

    //'联系人'
    @Column
    private String agentcontact;

    //'排班日期'
    @Column
    private String arrange;

    //'所属的代理'
    @Column
    private Long daili_id;

    //'打卡时间1'
    @Column
    private String basetime1;

    //'打卡时间2'
    @Column
    private String basetime2;

    //'打卡时间3'
    @Column
    private String basetime3;

    //'打卡时间4'
    @Column
    private String basetime4;

    //'bssId地址'
    @Column(name = "bssId")
    private String bssId;

    //'保留'
    @Column
    private String restype;

    //'排序'
    @Column
    private Integer sort;

    //'状态(0:禁用,1:启用)'
    @Column
    private Byte status;

    //'备注说明'
    @Column
    private String remark;

    //'工号授权到期时间'
    @Column
    private Long end_time;

    //执行结果
    @Column
    private String exec_results;

    //'创建时间'
    @Column
    private Long create_time;

    //'更新时间'
    @Column
    private Long update_time;

    //'删除时间'
    @Column
    private Long delete_time;

    //'执行时间1'
    @Column
    private Byte exectime1;

    //'执行时间2'
    @Column
    private Byte exectime2;

    //'执行时间3'
    @Column
    private Byte exectime3;

    //'执行时间4'
    @Column
    private Byte exectime4;

    //'随机时间1'
    @Column
    private String randomtime1;

    //'随机时间2'
    @Column
    private String randomtime2;

    //'随机时间3'
    @Column
    private String randomtime3;

    //'随机时间4'
    @Column
    private String randomtime4;

    //'外链PID'
    @Column
    private String pid;

    //'代理姓名'
    @Column
    private String daili_username;

    //'他人刷脸'
    @Column
    private Byte iswithoutPass;

    //'他人刷脸工号'
    @Column
    private String exsalesNo;

    //'他人刷脸密码'
    @Column
    private String expassword;

    //'职场ID'
    @Column
    private String zcid;

    //'验证打卡'
    @Column
    private String yzdk;

    //'批量打卡'
    @Column
    private String pldk;
}
