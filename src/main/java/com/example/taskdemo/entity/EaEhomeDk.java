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
    @Column(name = "password")
    private String password;

    //'联系人'
    @Column(name = "agentcontact")
    private String agentcontact;

    //'排班日期'
    @Column(name = "arrange")
    private String arrange;

    //'所属的代理'
    @Column(name = "daili_id")
    private Long daili_id;

    //'打卡时间1'
    @Column(name = "basetime1")
    private String basetime1;

    //'打卡时间2'
    @Column(name = "basetime2")
    private String basetime2;

    //'打卡时间3'
    @Column(name = "basetime3")
    private String basetime3;

    //'打卡时间4'
    @Column(name = "basetime4")
    private String basetime4;

    //'bssId地址'
    @Column(name = "bssId")
    private String bssId;

    //'保留'
    @Column(name = "restype")
    private String restype;

    //'排序'
    @Column(name = "sort")
    private Integer sort;

    //'状态(0:禁用,1:启用)'
    @Column(name = "status")
    private Byte status;

    //'备注说明'
    @Column(name = "remark")
    private String remark;

    //'工号授权到期时间'
    @Column(name = "end_time")
    private Long end_time;

    //执行结果
    @Column(name = "exec_results")
    private String exec_results;

    //'创建时间'
    @Column(name = "create_time")
    private Long create_time;

    //'更新时间'
    @Column(name = "update_time")
    private Long update_time;

    //'删除时间'
    @Column(name = "delete_time")
    private Long delete_time;

    //'执行时间1'
    @Column(name = "exectime1")
    private Byte exectime1;

    //'执行时间2'
    @Column(name = "exectime2")
    private Byte exectime2;

    //'执行时间3'
    @Column(name = "exectime3")
    private Byte exectime3;

    //'执行时间4'
    @Column(name = "exectime4")
    private Byte exectime4;

    //'随机时间1'
    @Column(name = "randomtime1")
    private String randomtime1;

    //'随机时间2'
    @Column(name = "randomtime2")
    private String randomtime2;

    //'随机时间3'
    @Column(name = "randomtime3")
    private String randomtime3;

    //'随机时间4'
    @Column(name = "randomtime4")
    private String randomtime4;

    //'外链PID'
    @Column(name = "pid")
    private String pid;

    //'代理姓名'
    @Column(name = "daili_username")
    private String daili_username;

    //'他人刷脸'
    @Column(name = "iswithoutPass")
    private Byte iswithoutPass;

    //'他人刷脸工号'
    @Column(name = "exsalesNo")
    private String exsalesNo;

    //'他人刷脸密码'
    @Column(name = "expassword")
    private String expassword;

    //'职场ID'
    @Column(name = "zcid")
    private String zcid;

    //'验证打卡'
    @Column(name = "yzdk")
    private String yzdk;

    //'批量打卡'
    @Column(name = "pldk")
    private String pldk;
}
